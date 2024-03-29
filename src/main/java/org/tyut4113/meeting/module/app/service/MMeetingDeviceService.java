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

    /**
     * 根据会议的ID获取设备的ID列表
     * @param meetingId 会议ID
     * @return 设备ID列表
     */
    List<Long> listDeviceIdByMeetingId(Long meetingId);

    /**
     * 根据会议ID数组批量删除会议设备关系
     * @param meetingIds 会议ID列表
     */
    void deleteBatch(Long[] meetingIds);
}

