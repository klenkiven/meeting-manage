package org.tyut4113.meeting.module.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;
import org.tyut4113.meeting.module.sys.service.MDeviceService;

import java.util.List;
import java.util.Map;

/**
 * 设备管理 Controller
 * @author ：klenkiven
 * @date ：2021/7/15 10:37
 */
@RestController
@RequestMapping("/m/device")
@AllArgsConstructor
public class MDeviceController {

    private final MDeviceService mDeviceService;

    /**
     * 选择设备列表
     */
    @GetMapping("/select")
    @RequiresPermissions("meeting:device:select")
    public Result<List<MDeviceEntity>> select() {
        return Result.ok();
    }

    /**
     * 分页查询设备列表
     */
    @GetMapping("/list/{current}/{limit}")
    @RequiresPermissions("meeting:device:list")
    public Result<Page<MDeviceEntity>> list(@PathVariable String current,
                                            @PathVariable String limit,
                                            @RequestBody Map<String, Object> params) {
        return Result.ok();
    }

    /**
     * 查询设备详细信息
     */
    @GetMapping("/info/{deviceId}")
    @RequiresPermissions("meeting:device:info")
    public Result<MDeviceEntity> info(@PathVariable String deviceId) {
        return Result.ok();
    }

    /**
     * 保存设备信息
     */
    @PostMapping("save")
    @RequiresPermissions("meeting:device:save")
    public Result<?> save(@RequestBody MDeviceEntity device) {
        return Result.ok();
    }

    /**
     * 更新设备信息
     */
    @PostMapping("update")
    @RequiresPermissions("meeting:device:update")
    public Result<?> update(@RequestBody MDeviceEntity device) {
        return Result.ok();
    }

    /**
     * 删除设备信息
     */
    @PostMapping("delete")
    @RequiresPermissions("meeting:device:delete")
    public Result<?> delete(Long[] ids) {
        return Result.ok();
    }

}
