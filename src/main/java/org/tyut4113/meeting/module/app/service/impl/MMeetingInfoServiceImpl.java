package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;
import org.tyut4113.meeting.module.app.mapper.MMeetingInfoMapper;
import org.tyut4113.meeting.module.app.service.MMeetingInfoService;


@Service("mMeetingInfoService")
public class MMeetingInfoServiceImpl extends ServiceImpl<MMeetingInfoMapper, MMeetingInfoEntity> implements MMeetingInfoService {

}