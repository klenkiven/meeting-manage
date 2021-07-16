package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;
import org.tyut4113.meeting.module.sys.mapper.MDeviceMapper;
import org.tyut4113.meeting.module.sys.service.MDeviceService;


@Service("mDeviceService")
public class MDeviceServiceImpl extends ServiceImpl<MDeviceMapper, MDeviceEntity> implements MDeviceService {

}