package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.common.utils.Constant;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;
import org.tyut4113.meeting.module.app.service.MMeetingDeviceService;
import org.tyut4113.meeting.module.app.service.impl.MMeetingDeviceServiceImpl;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;
import org.tyut4113.meeting.module.sys.mapper.MDeviceMapper;
import org.tyut4113.meeting.module.sys.service.MDeviceService;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service("mDeviceService")
@RequiredArgsConstructor
public class MDeviceServiceImpl extends ServiceImpl<MDeviceMapper, MDeviceEntity> implements MDeviceService {

    private final MRoomDeviceService mRoomDeviceService;
    private final MMeetingDeviceService mMeetingDeviceService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveDevice(MDeviceEntity device) {
        this.save(device);
    }
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateDevice(MDeviceEntity device) {
        this.updateById(device);

        // 在固定设备设备中设置未分配，可以取消房间和该设备的关系
        if (device.getType() == 0 &&
                device.getStatus() == Constant.DeviceStatus.UNALLOCATED.getValue()) {
            QueryWrapper<MRoomDeviceEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("device_id", device.getId());
            mRoomDeviceService.remove(queryWrapper);
        }
    }

    @Override
    public void deleteBatch(Long[] deviceIds) {
        this.removeByIds(Arrays.asList(deviceIds));

        // 删除设备和会议室之间的联系
        mRoomDeviceService.removeByIds(Arrays.asList(deviceIds));

        // 删除设备和会议之间的联系
        QueryWrapper<MMeetingDeviceEntity> query = new QueryWrapper<>();
        for (Long deviceId: deviceIds) {
            query.eq("device_id", deviceId);
        }
        mMeetingDeviceService.remove(query);
    }

    @Override
    public void setDeviceStatusByIds(List<Long> deviceIdList) {
        if (deviceIdList == null || deviceIdList.size() == 0) {
            return;
        }
        List<MDeviceEntity> mDeviceEntities = this.listByIds(deviceIdList);

        for (MDeviceEntity m: mDeviceEntities) {
            if (m.getType() == 0) {
                m.setStatus(
                        m.getStatus() == Constant.DeviceStatus.UNALLOCATED.getValue() ?
                                Constant.DeviceStatus.ALLOCATED.getValue() :
                                Constant.DeviceStatus.UNALLOCATED.getValue());
            }
            this.updateById(m);
        }
    }

}