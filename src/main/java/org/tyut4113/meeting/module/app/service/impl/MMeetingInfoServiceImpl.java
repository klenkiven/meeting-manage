package org.tyut4113.meeting.module.app.service.impl;

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

import java.util.Date;
import java.util.List;


@Service("mMeetingInfoService")
@RequiredArgsConstructor
public class MMeetingInfoServiceImpl extends ServiceImpl<MMeetingInfoMapper, MMeetingInfoEntity>
        implements MMeetingInfoService {

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

    /**
     * 校验数据的完整性
     * @param meetingInfo 会议数据
     */
    private void checkValidity(MMeetingInfoEntity meetingInfo) {

    }
}