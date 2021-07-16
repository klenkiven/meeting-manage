package org.tyut4113.meeting.module.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;
import org.tyut4113.meeting.module.app.service.MMeetingInfoService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会议预定 Controller
 * @author ：klenkiven
 * @date ：2021/7/15 10:35
 */
@RestController
@RequestMapping("/m/meeting/appointment")
@RequiredArgsConstructor
public class MAppointmentController extends AbstractController {

    private final MMeetingInfoService mMeetingInfoService;

    /**
     * 保存会议预约信息
     *
     * 注意：会议发起者必须是会议的成员之一
     */
    @PostMapping("/save")
    public Result<?> save(@RequestBody MMeetingInfoEntity meetingInfo) {
        meetingInfo.setCreateUserId(getUserId());
        mMeetingInfoService.saveMeetingInfo(meetingInfo);

        return Result.ok();
    }

    /**
     * 获取时段内碰撞的会议室
     */
    @PostMapping("/except")
    public Result<List<Long>> except(@RequestBody Map<String, Date> params) {
        Date startTime = params.get("startTime");
        Date endTime = params.get("endTime");
        mMeetingInfoService.listAllCollusionMeeting(startTime, endTime);

        return Result.ok();
    }

}
