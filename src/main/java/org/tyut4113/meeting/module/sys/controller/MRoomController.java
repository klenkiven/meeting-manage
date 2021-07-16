package org.tyut4113.meeting.module.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.common.validation.ValidatorUtils;
import org.tyut4113.meeting.common.validation.group.AddGroup;
import org.tyut4113.meeting.common.validation.group.UpdateGroup;
import org.tyut4113.meeting.module.sys.entity.MRoomEntity;
import org.tyut4113.meeting.module.sys.service.MRoomDeviceService;
import org.tyut4113.meeting.module.sys.service.MRoomService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 会议室管理 Controller
 *
 * @author ：klenkiven
 * @date ：2021/7/15 10:34
 */
@RestController
@RequestMapping("/m/room")
@AllArgsConstructor
public class MRoomController {

    private final MRoomService mRoomService;
    private final MRoomDeviceService mRoomDeviceService;

    /**
     * 会议室推荐
     */
    @PostMapping("/recommend")
    @RequiresPermissions("meeting:room:recommend")
    public Result<List<MRoomEntity>> recommend(@RequestBody Map<String, Object> param) {
        return Result.ok();
    }

    /**
     * 查询会议室详细信息
     */
    @GetMapping("/info/{roomId}")
    @RequiresPermissions("meeting:room:info")
    public Result<MRoomEntity> info(@PathVariable("roomId") String roomId) {
        long rId = Long.parseLong(roomId);
        MRoomEntity result = mRoomService.getById(rId);

        List<Long> deviceIdList = mRoomDeviceService.listDeviceIdByRoomId(rId);
        result.setDeviceIdList(deviceIdList);

        return Result.ok(result);
    }

    /**
     * 查询会议室列表
     */
    @GetMapping("/list/{current}/{limit}")
    @RequiresPermissions("meeting:room:list")
    public Result<Page<MRoomEntity>> list(@PathVariable("current") Integer current,
                          @PathVariable("limit") Integer limit,
                          @RequestParam Map<String, Object> params) {

        Page<MRoomEntity> page = new Page<>(current, limit);

        String name = (String) params.get("name");
        QueryWrapper<MRoomEntity> query = new QueryWrapper<>();
        query.like(!StringUtils.isBlank(name), "name", "%"+name+"%");

        Page<MRoomEntity> result = mRoomService.page(page, query);
        return Result.ok(result);
    }

    /**
     * 保存会议室信息
     */
    @PostMapping("/save")
    @RequiresPermissions("meeting:room:save")
    public Result<?> save(@RequestBody MRoomEntity room) {
        ValidatorUtils.validateEntity(room, AddGroup.class);

        mRoomService.saveRoom(room);

        return Result.ok();
    }

    /**
     * 更新会议室信息
     */
    @PostMapping("/update")
    @RequiresPermissions("meeting:room:update")
    public Result<MRoomEntity> update(@RequestBody MRoomEntity room) {
        ValidatorUtils.validateEntity(room, UpdateGroup.class);

        mRoomService.updateRoom(room);

        return Result.ok();
    }

    /**
     * 删除会议室信息
     */
    @PostMapping("/delete")
    @RequiresPermissions("meeting:room:delete")
    public Result<?> delete(@RequestBody Long[] roomIds) {
        mRoomService.deleteBatch(roomIds);

        return Result.ok();
    }
}
