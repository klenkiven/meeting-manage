package org.tyut4113.meeting.module.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.module.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-12 09:57:43
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    /**
     * 生成一个Token
     *
     * @param userId 用户ID
     * @return 返回带有Token的结果
     */
    Result<?> createToken(Long userId);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);
}

