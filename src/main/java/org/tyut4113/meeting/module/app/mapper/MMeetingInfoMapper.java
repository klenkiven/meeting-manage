package org.tyut4113.meeting.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;

/**
 * 会议信息
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Mapper
public interface MMeetingInfoMapper extends BaseMapper<MMeetingInfoEntity> {
	
}
