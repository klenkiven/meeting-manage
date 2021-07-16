package org.tyut4113.meeting.module.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 会议预定 Controller
 * @author ：klenkiven
 * @date ：2021/7/15 10:35
 */
@RestController
@RequestMapping("/m/meeting/appointment")
@AllArgsConstructor
public class MAppointmentController {

    /**
     * 保存会议预约信息
     */
    @PostMapping("/save")
    public Result<?> save(@RequestBody MMeetingInfoEntity meetingInfo) {

        return Result.ok();
    }

    /**
     * 获取时段内可用的会议室
     */
    @PostMapping("/except")
    public Result<List<Long>> except(@RequestBody Map<String, Object> params) {

        return Result.ok();
    }

}
