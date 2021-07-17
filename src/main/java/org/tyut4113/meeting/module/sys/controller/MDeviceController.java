package org.tyut4113.meeting.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.tyut4113.meeting.common.utils.Constant;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.common.validation.ValidatorUtils;
import org.tyut4113.meeting.common.validation.group.AddGroup;
import org.tyut4113.meeting.common.validation.group.UpdateGroup;
import org.tyut4113.meeting.module.sys.entity.MDeviceEntity;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;
import org.tyut4113.meeting.module.sys.service.MDeviceService;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;

import java.util.*;

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
    private final MRoomDeviceService mRoomDeviceService;

    /**
     * 选择设备列表
     */
    @GetMapping("/select/{type}")
    @RequiresPermissions("meeting:device:select")
    public Result<List<MDeviceEntity>> select(@PathVariable Integer type,
                                              @RequestParam("roomId") Long roomId) {
        QueryWrapper<MDeviceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        if (type == 0) {
            if (roomId != null) {
                List<Long> longs = mRoomDeviceService.listDeviceIdByRoomId(roomId);
                queryWrapper.in(longs.size() != 0, "id", longs);
                queryWrapper.or(longs.size() != 0);
            }
            queryWrapper.eq("status", Constant.DeviceStatus.UNALLOCATED.getValue());
        }
        List<MDeviceEntity> result = mDeviceService.list(queryWrapper);

        return Result.ok(result);
    }

    /**
     * 分页查询设备列表
     */
    @GetMapping("/list/{current}/{limit}")
    @RequiresPermissions("meeting:device:list")
    public Result<Page<MDeviceEntity>> list(@PathVariable Integer current,
                                            @PathVariable Integer limit,
                                            @RequestParam Map<String, Object> params) {
        Page<MDeviceEntity> page = new Page<>(current, limit);

        String name = (String) params.get("name");
        QueryWrapper<MDeviceEntity> query = new QueryWrapper<>();
        query.like(!StringUtils.isBlank(name), "name", "%"+name+"%");

        Page<MDeviceEntity> result = mDeviceService.page(page, query);
        return Result.ok(result);
    }

    /**
     * 查询设备详细信息
     */
    @GetMapping("/info/{deviceId}")
    @RequiresPermissions("meeting:device:info")
    public Result<MDeviceEntity> info(@PathVariable String deviceId) {
        MDeviceEntity result = mDeviceService.getById(deviceId);
        return Result.ok(result);
    }

    /**
     * 保存设备信息
     */
    @PostMapping("save")
    @RequiresPermissions("meeting:device:save")
    public Result<?> save(@RequestBody MDeviceEntity device) {
        ValidatorUtils.validateEntity(device, AddGroup.class);

        mDeviceService.saveDevice(device);
        return Result.ok();
    }

    /**
     * 更新设备信息
     */
    @PostMapping("update")
    @RequiresPermissions("meeting:device:update")
    public Result<?> update(@RequestBody MDeviceEntity device) {
        ValidatorUtils.validateEntity(device, UpdateGroup.class);

        mDeviceService.updateDevice(device);

        return Result.ok();
    }

    /**
     * 删除设备信息
     */
    @PostMapping("delete")
    @RequiresPermissions("meeting:device:delete")
    public Result<?> delete(@RequestBody Long[] deviceIds) {
        mDeviceService.deleteBatch(deviceIds);
        return Result.ok();
    }

}
