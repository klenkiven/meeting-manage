package org.tyut4113.meeting.module.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;

import java.util.Date;
import java.util.List;

/**
 * 会议信息
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MMeetingInfoService extends IService<MMeetingInfoEntity> {

    /**
     * 保存会议信息及其相关关系
     * @param meetingInfo 会议信息
     */
    void saveMeetingInfo(MMeetingInfoEntity meetingInfo);

    /**
     * 获取所有时间碰撞的会议
     *
     * @param meetingEndTime 会议开始时间
     * @param meetingStartTime 会议结束时间
     * @return 碰撞会议的会议室信息
     */
    List<Long> listAllCollusionMeeting(Date meetingStartTime, Date meetingEndTime);
}

