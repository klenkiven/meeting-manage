package org.tyut4113.meeting.module.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;

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
}

