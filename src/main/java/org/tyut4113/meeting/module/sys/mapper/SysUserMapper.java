package org.tyut4113.meeting.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.tyut4113.meeting.module.sys.entity.SysUserEntity;

import java.util.List;

/**
 * 用户Mapper
 * @author ：klenkiven
 * @date ：2021/7/12 9:05
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 根据用户ID查询该用户所有的权限
     * @param userId 用户ID
     * @return 该用户所有的权限信息
     */
    List<String> listAllPerms(Long userId);

    /**
     * 根据用户的ID查询用户所有的菜单ID列表
     * @param userId 用户ID
     * @return 菜单ID列表
     */
    List<Long> listAllMenuId(Long userId);

}
