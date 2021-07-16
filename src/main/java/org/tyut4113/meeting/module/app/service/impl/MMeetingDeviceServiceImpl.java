package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;
import org.tyut4113.meeting.module.app.mapper.MMeetingDeviceMapper;
import org.tyut4113.meeting.module.app.service.MMeetingDeviceService;

@Service("mMeetingDeviceService")
public class MMeetingDeviceServiceImpl extends ServiceImpl<MMeetingDeviceMapper, MMeetingDeviceEntity> implements MMeetingDeviceService {

}