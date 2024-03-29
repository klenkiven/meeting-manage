package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.module.sys.entity.MRoomDeviceEntity;
import org.tyut4113.meeting.module.sys.mapper.MRoomDeviceMapper;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;

import java.util.ArrayList;
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

        // 如果为空或者列表对象为空，那么
        if (deviceIdList == null || deviceIdList.size() == 0) {
            return;
        }

        // 保存新的关系
        for(Long deviceId: deviceIdList) {
            MRoomDeviceEntity rd = new MRoomDeviceEntity();
            rd.setRoomId(roomId);
            rd.setDeviceId(deviceId);

            this.save(rd);
        }
    }

    @Override
    public List<Long> listDeviceIdByRoomId(long roomId) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("room_id", roomId);
        List<MRoomDeviceEntity> mRoomDeviceEntities = baseMapper.selectByMap(condition);
        List<Long> result = new ArrayList<>();
        mRoomDeviceEntities.forEach((item) -> {
            result.add(item.getDeviceId());
        });
        return result;
    }
}