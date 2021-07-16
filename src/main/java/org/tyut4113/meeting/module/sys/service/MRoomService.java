package org.tyut4113.meeting.module.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

/**
 * 会议室信息
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MRoomService extends IService<MRoomEntity> {

    /**
     * 保存会议室信息
     * @param room 会议室信息
     */
    void saveRoom(MRoomEntity room);

    /**
     * 更新会议室信息
     * @param room 会议室
     */
    void updateRoom(MRoomEntity room);

    /**
     * 删除多个会议室信息
     * @param roomIds 会议室ID
     */
    void deleteBatch(Long[] roomIds);

    /**
     * 根据排除清单和参与人数推荐数量会议室
     *
     * @param exceptRoomIdList 排除清单（发生时间碰撞的会议）
     * @param peopleNum 人数
     * @return 房间推荐清单
     */
    List<MRoomEntity> recommendRoomList(ArrayList<Integer> exceptRoomIdList, Integer peopleNum);
}

