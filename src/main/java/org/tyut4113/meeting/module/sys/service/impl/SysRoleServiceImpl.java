package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.common.exception.GeneralException;
import org.tyut4113.meeting.common.utils.Constant;
import org.tyut4113.meeting.module.sys.entity.SysRoleEntity;
import org.tyut4113.meeting.module.sys.mapper.SysRoleMapper;
import org.tyut4113.meeting.module.sys.service.SysRoleMenuService;
import org.tyut4113.meeting.module.sys.service.SysRoleService;
import org.tyut4113.meeting.module.sys.service.SysUserRoleService;
import org.tyut4113.meeting.module.sys.service.SysUserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 角色信息服务实现类
 * @author ：klenkiven
 * @date ：2021/7/14 17:33
 */
@Service("sysRoleService")
@Setter
@RequiredArgsConstructor(onConstructor =@_(@Autowired))
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;
    private final SysUserRoleService sysUserRoleService;
    /**
     * 使用Set的注入方式，防止循环依赖的问题
     */
    private SysUserService sysUserService;

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void saveRole(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        // 检查是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void update(SysRoleEntity role) {
        this.updateById(role);

        // 检查是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Long> listRoleIdByCreateUserId(Long createUserId) {
        return sysUserRoleService.listRoleIdByUserId(createUserId);
    }

    /**
     * 检查权限是否越权
     * 薮泽抛出异常回滚事务处理
     */
    private void checkPrems(SysRoleEntity role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateUserId() == Constant.ADMIN){
            return;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = sysUserService.listAllMenuId(role.getCreateUserId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new GeneralException("新增角色的权限，已超出你的权限范围");
        }
    }
}
