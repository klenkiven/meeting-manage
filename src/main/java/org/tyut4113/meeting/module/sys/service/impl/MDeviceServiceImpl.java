package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
public class MDeviceServiceImpl extends ServiceImpl<MDeviceMapper, MDeviceEntity> implements MDeviceService {


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
    }

}