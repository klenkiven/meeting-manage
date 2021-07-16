package org.tyut4113.meeting.module.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.app.entity.MUserMeetingEntity;

import java.util.List;

/**
 * 员工与会议对应关系
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MUserMeetingService extends IService<MUserMeetingEntity> {

    /**
     * 根据会议信息ID，新增或者更新关系信息
     * @param meetingInfoId 会议信息ID
     * @param uidList 员工工号列表
     */
    void saveOrUpdate(Long meetingInfoId, List<Long> uidList);
}

