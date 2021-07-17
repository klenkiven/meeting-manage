package org.tyut4113.meeting.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;

import java.util.List;

/**
 * 会议与设备对应关系
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Mapper
public interface MMeetingDeviceMapper extends BaseMapper<MMeetingDeviceEntity> {

    /**
     * 根据会议ID查询所有流动设备ID列表
     * @param meetingId 会议ID
     * @return 流动设备ID列表
     */
    List<Long> listDeviceIdByMeetingId(Long meetingId);

    /**
     * 根据会议ID批量信息
     * @param meetingIds 会议ID
     * @return 删除数量
     */
    int deleteBatch(Long[] meetingIds);
}
