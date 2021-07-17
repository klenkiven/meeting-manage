package org.tyut4113.meeting.module.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;

import java.util.List;

/**
 * 设备信息
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MDeviceService extends IService<MDeviceEntity> {
    /**
     * 保存设备信息
     * @param device 设备信息
     */
    void saveDevice(MDeviceEntity device);

    /**
     * 更新设备信息
     * @param device 设备
     */
    void updateDevice(MDeviceEntity device);

    /**
     * 删除多个设备信息
     * @param deviceIds 设备ID
     */
    void deleteBatch(Long[] deviceIds);

    /**
     * 保存设备信息状态
     *
     * @param deviceIdList 设备ID列表
     */
    void setDeviceStatusByIds(List<Long> deviceIdList);
}

