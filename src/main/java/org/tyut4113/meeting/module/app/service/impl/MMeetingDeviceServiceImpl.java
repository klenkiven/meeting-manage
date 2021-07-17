package org.tyut4113.meeting.module.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;
import org.tyut4113.meeting.module.app.mapper.MMeetingDeviceMapper;
import org.tyut4113.meeting.module.app.service.MMeetingDeviceService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service("mMeetingDeviceService")
public class MMeetingDeviceServiceImpl extends ServiceImpl<MMeetingDeviceMapper, MMeetingDeviceEntity>
        implements MMeetingDeviceService {

    @Override
    public void saveOrUpdate(Long meetingInfoId, List<Long> deviceList) {
        // 删除原有关系
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("meeting_id", meetingInfoId);
        baseMapper.deleteByMap(condition);

        if (deviceList == null || deviceList.size() == 0) {
            return;
        }

        // 保存现有关系
        for(Long deviceId: deviceList) {
            MMeetingDeviceEntity mMeetingDeviceEntity = new MMeetingDeviceEntity();
            mMeetingDeviceEntity.setMeetingId(meetingInfoId);
            mMeetingDeviceEntity.setDeviceId(deviceId);

            baseMapper.insert(mMeetingDeviceEntity);
        }

    }

    @Override
    public List<Long> listDeviceIdByMeetingId(Long meetingId) {
        return baseMapper.listDeviceIdByMeetingId(meetingId);
    }

    @Override
    public void deleteBatch(Long[] meetingIds) {
        baseMapper.deleteBatch(meetingIds);
    }
}