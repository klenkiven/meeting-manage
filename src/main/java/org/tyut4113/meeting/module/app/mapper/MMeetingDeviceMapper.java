package org.tyut4113.meeting.module.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyut4113.meeting.module.app.entity.MMeetingDeviceEntity;

/**
 * 会议与设备对应关系
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Mapper
public interface MMeetingDeviceMapper extends BaseMapper<MMeetingDeviceEntity> {
	
}
