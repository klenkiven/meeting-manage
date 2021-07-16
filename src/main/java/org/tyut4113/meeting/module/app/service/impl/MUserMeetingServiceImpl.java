package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyut4113.meeting.module.app.entity.MUserMeetingEntity;
import org.tyut4113.meeting.module.app.mapper.MUserMeetingMapper;
import org.tyut4113.meeting.module.app.service.MUserMeetingService;

import java.util.HashMap;
import java.util.List;


@Service("mUserMeetingService")
public class MUserMeetingServiceImpl extends ServiceImpl<MUserMeetingMapper, MUserMeetingEntity>
        implements MUserMeetingService {

    @Override
    public void saveOrUpdate(Long meetingInfoId, List<Long> uidList) {
        // 删除原有关系
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("meeting_id", meetingInfoId);
        baseMapper.deleteByMap(condition);

        if (uidList == null || uidList.size() == 0) {
            return;
        }

        // 保存关系
        for (Long uid: uidList) {
            MUserMeetingEntity mUserMeetingEntity = new MUserMeetingEntity();
            mUserMeetingEntity.setMeetingId(meetingInfoId);
            mUserMeetingEntity.setUid(uid);

            baseMapper.insert(mUserMeetingEntity);
        }
    }
}