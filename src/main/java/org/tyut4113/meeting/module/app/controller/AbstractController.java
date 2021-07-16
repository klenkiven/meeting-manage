package org.tyut4113.meeting.module.app.controller;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tyut4113.meeting.module.sys.entity.SysUserEntity;

/**
 * Controller的公共抽象组件
 *
 * @author ：klenkiven
 * @date ：2021/7/13 12:10
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

}
