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

    /**
     * 根据会议的ID获取用户的UID列表
     * @param meetingId 会议ID
     * @return 用户UID列表
     */
    List<Long> listUidByMeetingId(Long meetingId);

    /**
     * 根据会议ID数组批量删除会议用户关系
     * @param meetingIds 会议ID列表
     */
    void deleteBatch(Long[] meetingIds);
}

