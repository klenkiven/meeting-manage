package org.tyut4113.meeting.module.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.sys.entity.MRoomDeviceEntity;

import java.util.List;

/**
 * 会议室与设备对应关系
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MRoomDeviceService extends IService<MRoomDeviceEntity> {

    /**
     * 保存或者更新全部的关系
     * @param roomId 会议室ID
     * @param deviceIdList 设备名称
     */
    void saveOrUpdate(Long roomId, List<Long> deviceIdList);

    /**
     * 根据会议室ID获取设备ID列表
     * @param roomId 会议室ID
     * @return 设备ID列表
     */
    List<Long> listDeviceIdByRoomId(long roomId);
}

