package org.tyut4113.meeting.module.sys.controller;

import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;

import java.util.List;

/**
 * 设备管理 Controller
 * @author ：klenkiven
 * @date ：2021/7/15 10:37
 */
@RestController
@RequestMapping("/m/device")
@AllArgsConstructor
public class MDeviceController {

    /**
     * 选择设备列表
     */
    @GetMapping("/select")
    @RequiresPermissions("meeting:device:select")
    public Result<List<MDeviceEntity>> select() {
        return Result.ok();
    }

}
