package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.common.exception.GeneralException;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;
import org.tyut4113.meeting.module.app.mapper.MMeetingInfoMapper;
import org.tyut4113.meeting.module.app.service.MMeetingDeviceService;
import org.tyut4113.meeting.module.app.service.MMeetingInfoService;
import org.tyut4113.meeting.module.app.service.MUserMeetingService;
import org.tyut4113.meeting.module.app.vo.MMeetingInfoVo;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;
import org.tyut4113.meeting.module.sys.service.MRoomService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service("mMeetingInfoService")
@RequiredArgsConstructor
public class MMeetingInfoServiceImpl extends ServiceImpl<MMeetingInfoMapper, MMeetingInfoEntity>
        implements MMeetingInfoService {

    public final static int MAX_TIME = 10 * 60 * 60 * 1000;

    /** 系统级别模块 */
    private final MRoomService mRoomService;

    /** 普通业务级别模块 */
    private final MMeetingDeviceService mMeetingDeviceService;
    private final MUserMeetingService mUserMeetingService;

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void saveMeetingInfo(MMeetingInfoEntity meetingInfo) {
        // 验证会议的数据完整性
        checkValidity(meetingInfo);
        // 时间碰撞情况
        List<Long> roomIdList = listAllCollusionMeeting(meetingInfo.getStartTime(), meetingInfo.getEndTime());
        if (roomIdList != null && roomIdList.contains(meetingInfo.getRoomId())) {
            throw new GeneralException("此会议室会议时间冲突");
        }

        meetingInfo.setCreateTime(new Date());
        this.save(meetingInfo);

        List<Long> deviceList = meetingInfo.getDeviceList();
        List<Long> uidList = meetingInfo.getUidList();

        mMeetingDeviceService.saveOrUpdate(meetingInfo.getId(), deviceList);
        mUserMeetingService.saveOrUpdate(meetingInfo.getId(), uidList);
    }

    @Override
    public void updateMeetingInfo(MMeetingInfoEntity meetingInfo) {

        this.updateById(meetingInfo);

        List<Long> deviceList = meetingInfo.getDeviceList();
        List<Long> uidList = meetingInfo.getUidList();

        mMeetingDeviceService.saveOrUpdate(meetingInfo.getId(), deviceList);
        mUserMeetingService.saveOrUpdate(meetingInfo.getId(), uidList);
    }

    @Override
    public List<Long> listAllCollusionMeeting(Date meetingStartTime, Date meetingEndTime) {

        long meetingStartMilli = meetingStartTime.getTime();
        long meetingEndMilli = meetingEndTime.getTime();

        QueryWrapper<MMeetingInfoEntity> queryWrapper = new QueryWrapper<>();
        List<MMeetingInfoEntity> meetingInfoList = new ArrayList<>();
        // 时间碰撞情况一：结束时间在预约时间之内
        queryWrapper.clear();
        queryWrapper.ge("end_time", new Date(meetingStartMilli));
        queryWrapper.le("end_time", new Date(meetingEndMilli));
        meetingInfoList.addAll(baseMapper.selectList(queryWrapper));

        // 时间碰撞情况二：开始时间在预约时间之内
        queryWrapper.clear();
        queryWrapper.ge("start_time", new Date(meetingStartMilli));
        queryWrapper.le("start_time", new Date(meetingEndMilli));
        meetingInfoList.addAll(baseMapper.selectList(queryWrapper));

        // 时间碰撞情况三：预约时间在另一个预约时间之内
        queryWrapper.clear();
        queryWrapper.ge("start_time", new Date(meetingStartMilli));
        queryWrapper.le("end_time", new Date(meetingStartMilli));
        meetingInfoList.addAll(baseMapper.selectList(queryWrapper));

        List<Long> roomIdList = new ArrayList<>();
        meetingInfoList.forEach((item) -> {
           roomIdList.add(item.getRoomId()); 
        });
        
        return roomIdList;
    }

    @Override
    public Page<MMeetingInfoVo> page(Integer current, Integer limit, String name) {
        Page<MMeetingInfoEntity> page = new Page<>(current, limit);
        QueryWrapper<MMeetingInfoEntity> query = new QueryWrapper<>();
        query.like(!StringUtils.isBlank(name), "name", "%" + name + "%");
        Page<MMeetingInfoEntity> meetingInfoEntityPage = this.page(page, query);

        List<MMeetingInfoVo> mMeetingInfoVoRecords = new ArrayList<>();
        for(MMeetingInfoEntity mm: meetingInfoEntityPage.getRecords()) {
            MMeetingInfoVo mmv = new MMeetingInfoVo();
            BeanUtils.copyProperties(mm, mmv);

            MRoomEntity room = mRoomService.getById(mm.getRoomId());
            mmv.setRoomName(room.getName());

            mMeetingInfoVoRecords.add(mmv);
        }

        Page<MMeetingInfoVo> result = new Page<>();
        result.setRecords(mMeetingInfoVoRecords);
        result.setTotal(meetingInfoEntityPage.getTotal());

        return result;
    }

    @Override
    public MMeetingInfoEntity getMeetingByMeetingId(Long meetingId) {
        MMeetingInfoEntity result = this.getById(meetingId);

        List<Long> uidList = mUserMeetingService.listUidByMeetingId(meetingId);
        List<Long> deviceIdList = mMeetingDeviceService.listDeviceIdByMeetingId(meetingId);
        result.setUidList(uidList);
        result.setDeviceList(deviceIdList);

        return result;
    }

    @Override
    public void deleteBatch(Long[] meetingIds) {
        this.removeByIds(Arrays.asList(meetingIds));

        mUserMeetingService.deleteBatch(meetingIds);
        mMeetingDeviceService.deleteBatch(meetingIds);
    }

    /**
     * 校验数据的完整性
     * @param meetingInfo 会议数据
     */
    private void checkValidity(MMeetingInfoEntity meetingInfo) {
        long meetingStartMilli = meetingInfo.getStartTime().getTime();
        long meetingEndMilli = meetingInfo.getEndTime().getTime();

        // 基础时长：预约时间限制
        if (meetingEndMilli - meetingStartMilli > MAX_TIME) {
            throw new GeneralException("预约时间过长：请预约" + MAX_TIME / (60 * 60 * 1000) + "小时以内的时间");
        } else if (meetingEndMilli - meetingStartMilli == 0) {
            throw new GeneralException("预约时间过短：请预约0.5小时以上的时间");
        }

        // 基础情况：开始时间是不是晚于结束时间
        if (meetingEndMilli < meetingStartMilli) {
            throw new GeneralException("开始时间晚于结束时间");
        }

        // 基础情况：开始时间早于今天
        // 这里获得的系统时间是毫秒，因此需要除以1000
        if (meetingStartMilli < System.currentTimeMillis()) {
            throw new GeneralException("开始时间早于当前时间");
        }

        // 会议室此时是否被禁用？
        if (mRoomService.getById(meetingInfo.getRoomId()).getStatus() == 0) {
            throw new GeneralException("会议室已经被禁用");
        }
    }
}