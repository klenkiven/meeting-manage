package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyut4113.meeting.module.app.entity.MUserMeetingEntity;
import org.tyut4113.meeting.module.app.mapper.MUserMeetingMapper;
import org.tyut4113.meeting.module.app.service.MUserMeetingService;


@Service("mUserMeetingService")
public class MUserMeetingServiceImpl extends ServiceImpl<MUserMeetingMapper, MUserMeetingEntity> implements MUserMeetingService {

}