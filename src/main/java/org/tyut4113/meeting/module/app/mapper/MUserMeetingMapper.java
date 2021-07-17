package org.tyut4113.meeting.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyut4113.meeting.module.app.entity.MUserMeetingEntity;

import java.util.List;

/**
 * 员工与会议对应关系
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Mapper
public interface MUserMeetingMapper extends BaseMapper<MUserMeetingEntity> {

    /**
     * 根据会议ID查询所有的UID
     * @param meetingId 会议ID
     * @return 员工工号
     */
    List<Long> listUidByMeetingId(Long meetingId);

    /**
     * 根据会议ID数组删除所有的用户会议关系
     * @param meetingIds 会议ID列表
     * @return 删除数量
     */
    int deleteBatch(Long[] meetingIds);
	
}
