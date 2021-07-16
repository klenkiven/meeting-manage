package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.common.exception.GeneralException;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;
import org.tyut4113.meeting.module.app.mapper.MMeetingInfoMapper;
import org.tyut4113.meeting.module.app.service.MMeetingDeviceService;
import org.tyut4113.meeting.module.app.service.MMeetingInfoService;
import org.tyut4113.meeting.module.app.service.MUserMeetingService;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("mMeetingInfoService")
@RequiredArgsConstructor
public class MMeetingInfoServiceImpl extends ServiceImpl<MMeetingInfoMapper, MMeetingInfoEntity>
        implements MMeetingInfoService {

    public final static int MAX_TIME = 10 * 60 * 60 * 1000;

    private final MMeetingDeviceService mMeetingDeviceService;
    private final MUserMeetingService mUserMeetingService;

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void saveMeetingInfo(MMeetingInfoEntity meetingInfo) {
        // 验证会议的数据完整性
        checkValidity(meetingInfo);

        meetingInfo.setCreateTime(new Date());
        this.save(meetingInfo);

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
        // 时间碰撞情况一：上一个的结束时间，晚于当前的开始时间
        queryWrapper.ge("end_time", new Date(meetingStartMilli));
        // 逻辑连接
        queryWrapper.or();
        // 时间碰撞情况二：下一个的开始时间，早于当前的结束时间
        queryWrapper.le("start_time", new Date(meetingEndMilli));
        List<MMeetingInfoEntity> meetingInfoList = baseMapper.selectList(queryWrapper);

        List<Long> roomIdList = new ArrayList<>();
        meetingInfoList.forEach((item) -> {
           roomIdList.add(item.getRoomId()); 
        });
        
        return roomIdList;
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
        if (meetingStartMilli < System.currentTimeMillis()/1000) {
            throw new GeneralException("开始时间早于当前时间");
        }


        // 时间碰撞情况一：上一个的结束时间，晚于当前的开始时间
        // 时间碰撞情况二：下一个的开始时间，早于当前的结束时间
        List<Long> roomIdList = listAllCollusionMeeting(meetingInfo.getStartTime(), meetingInfo.getEndTime());
        if (roomIdList != null && roomIdList.contains(meetingInfo.getRoomId())) {
            throw new GeneralException("此会议室会议时间冲突");
        }
    }
}