package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;
import org.tyut4113.meeting.module.app.service.MMeetingDeviceService;
import org.tyut4113.meeting.module.app.service.impl.MMeetingDeviceServiceImpl;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;
import org.tyut4113.meeting.module.sys.mapper.MDeviceMapper;
import org.tyut4113.meeting.module.sys.service.MDeviceService;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service("mDeviceService")
@RequiredArgsConstructor
public class MDeviceServiceImpl extends ServiceImpl<MDeviceMapper, MDeviceEntity> implements MDeviceService {

    private final MRoomDeviceService mRoomDeviceService;
    private final MMeetingDeviceService mMeetingDeviceService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveDevice(MDeviceEntity device) {
        this.save(device);
    }
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateDevice(MDeviceEntity device) {
        this.updateById(device);
    }

    @Override
    public void deleteBatch(Long[] deviceIds) {
        this.removeByIds(Arrays.asList(deviceIds));

        // 删除设备和会议室之间的联系
        mRoomDeviceService.removeByIds(Arrays.asList(deviceIds));

        // 删除设备和会议之间的联系
        QueryWrapper<MMeetingDeviceEntity> query = new QueryWrapper<>();
        for (Long deviceId: deviceIds) {
            query.eq("device_id", deviceId);
        }
        mMeetingDeviceService.remove(query);
    }

}