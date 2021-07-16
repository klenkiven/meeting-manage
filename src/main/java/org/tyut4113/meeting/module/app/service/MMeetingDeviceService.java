package org.tyut4113.meeting.module.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;

import java.util.List;

/**
 * 会议与设备对应关系
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MMeetingDeviceService extends IService<MMeetingDeviceEntity> {

    /**
     * 根据会议信息ID，更新或者删除关系
     *
     * @param meetingInfoId 会议信息ID
     * @param deviceList 设备信息ID列表
     */
    void saveOrUpdate(Long meetingInfoId, List<Long> deviceList);
}

