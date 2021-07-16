package org.tyut4113.meeting.module.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tyut4113.meeting.common.exception.GeneralException;
import org.tyut4113.meeting.common.utils.Constant;
import org.tyut4113.meeting.module.sys.entity.SysUserEntity;
import org.tyut4113.meeting.module.sys.mapper.SysUserMapper;
import org.tyut4113.meeting.module.sys.service.SysRoleService;
import org.tyut4113.meeting.module.sys.service.SysUserRoleService;
import org.tyut4113.meeting.module.sys.service.SysUserService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 * @author ：klenkiven
 * @date ：2021/7/12 9:24
 */
@Service("sysUserService")
@Setter
@RequiredArgsConstructor(onConstructor =@_(@Autowired))
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService  {

    private final SysUserRoleService sysUserRoleService;
    /**
     * 使用Set的注入方式，防止循环依赖的问题
     */
    private SysRoleService sysRoleService;

    @Override
    public List<Long> listAllMenuId(Long userId) {
        return baseMapper.listAllMenuId(userId);
    }

    @Override
    public List<String> listAllPerms(Long userId) {
        return baseMapper.listAllPerms(userId);
    }

    @Override
    public SysUserEntity getSysUserByUserName(String userName) {

        if (StringUtils.isBlank(userName)) {
            return null;
        }

        QueryWrapper<SysUserEntity> query = new QueryWrapper<>();
        query.eq("username", userName);

        return baseMapper.selectOne(query);
    }

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void saveUser(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphabetic(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = {GeneralException.class, Exception.class})
    public void update(SysUserEntity user) {
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        this.removeByIds(Arrays.asList(userIds));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {

        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.update(userEntity,
                new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
    }

    /**
     * 检查角色是否越权
     * 如果发现越权操作，则抛出异常然后回滚
     */
    private void checkRole(SysUserEntity user){

        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }

        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.ADMIN) {
            return ;
        }

        //查询用户创建的角色列表
        List<Long> roles = sysRoleService.listRoleIdByCreateUserId(user.getCreateUserId());

        // 判断是否越权
        if(!roles.containsAll(user.getRoleIdList())){
            throw new GeneralException("新增用户所选角色，不是本人创建");
        }
    }
}
