package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.module.sys.entity.MRoomDeviceEntity;
import org.tyut4113.meeting.module.sys.mapper.MRoomDeviceMapper;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;

import java.util.HashMap;
import java.util.List;


@Service("mRoomDeviceService")
public class MRoomDeviceServiceImpl extends ServiceImpl<MRoomDeviceMapper, MRoomDeviceEntity> implements MRoomDeviceService {

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void saveOrUpdate(Long roomId, List<Long> deviceIdList) {
        // 删除旧的关系
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("room_id", roomId);
        baseMapper.deleteByMap(condition);

        // 保存新的关系
        for(Long deviceId: deviceIdList) {
            MRoomDeviceEntity rd = new MRoomDeviceEntity();
            rd.setRoomId(roomId);
            rd.setDeviceId(deviceId);

            this.save(rd);
        }
    }
}