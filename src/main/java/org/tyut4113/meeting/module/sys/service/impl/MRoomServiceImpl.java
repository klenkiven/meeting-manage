package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.module.sys.entity.MRoomDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;
import org.tyut4113.meeting.module.sys.mapper.MRoomMapper;
import org.tyut4113.meeting.module.sys.service.MDeviceService;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;
import org.tyut4113.meeting.module.sys.service.MRoomService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("mRoomService")
@AllArgsConstructor
public class MRoomServiceImpl extends ServiceImpl<MRoomMapper, MRoomEntity> implements MRoomService {

    public final MRoomDeviceService mRoomDeviceService;
    public final MDeviceService mDeviceService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveRoom(MRoomEntity room) {
        List<Long> deviceIdList = room.getDeviceIdList();
        this.save(room);

        // 保存关系
        mRoomDeviceService.saveOrUpdate(room.getId(), deviceIdList);
        // 保存设备状态
        mDeviceService.setDeviceStatusByIds(deviceIdList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateRoom(MRoomEntity room) {
        List<Long> deviceIdList = room.getDeviceIdList();
        this.updateById(room);

        // 更新关系
        mRoomDeviceService.saveOrUpdate(room.getId(), deviceIdList);
        // 保存设备状态
        mDeviceService.setDeviceStatusByIds(deviceIdList);
    }

    @Override
    public void deleteBatch(Long[] roomIds) {
        this.removeByIds(Arrays.asList(roomIds));
    }

    @Override
    public List<MRoomEntity> recommendRoomList(ArrayList<Integer> exceptRoomIdList, Integer peopleNum) {
        QueryWrapper<MRoomEntity> query = new QueryWrapper<>();

        // 被禁用的会议室无法被选中
        query.ne("status", 0);
        for (Integer eRoomId: exceptRoomIdList) {
            query.ne("id", eRoomId);
        }
        query.ge("capacity", peopleNum);
        query.orderByAsc(true, "capacity");

        return baseMapper.selectList(query);
    }
}