package org.tyut4113.meeting.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyut4113.meeting.module.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-12 09:57:43
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {
	
}
