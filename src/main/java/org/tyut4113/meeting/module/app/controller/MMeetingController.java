package org.tyut4113.meeting.module.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tyut4113.meeting.common.utils.Result;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;
import org.tyut4113.meeting.module.app.service.MMeetingInfoService;
import org.tyut4113.meeting.module.app.vo.MMeetingInfoVo;

import java.util.List;
import java.util.Map;


/**
 * 会议管理 Controller
 * @author ：klenkiven
 * @date ：2021/7/15 10:35
 */
@RestController
@RequestMapping("/m/meeting")
@AllArgsConstructor
public class MMeetingController extends AbstractController {

    private final MMeetingInfoService mMeetingInfoService;

    /**
     * 获取发起者的所有会议列表
     */
    @GetMapping("/list/create/{current}/{limit}")
    public Result<Page<MMeetingInfoVo>> listCreate(@PathVariable Integer current,
                                                   @PathVariable Integer limit,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("userId") Long userId) {

        Page<MMeetingInfoVo> result = mMeetingInfoService.page(current, limit, name, userId);

        return Result.ok(result);
    }

    /**
     * 参与会议者的会议列表
     */
    @GetMapping("/list/participate")
    public Result<List<MMeetingInfoVo>> listParticipate(@RequestParam("uid") Long uid) {

        List<MMeetingInfoVo> result = mMeetingInfoService.listAllParticipatedMeeting(uid);

        return Result.ok(result);
    }

    /**
     * 获取会议的详细信息
     */
    @GetMapping("/info/{meetingId}")
    public Result<MMeetingInfoEntity> info(@PathVariable Long meetingId) {

        MMeetingInfoEntity result = mMeetingInfoService.getMeetingByMeetingId(meetingId);

        return Result.ok(result);
    }

    /**
     * 会议信息更新
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody MMeetingInfoEntity meetingInfo) {
        mMeetingInfoService.updateMeetingInfo(meetingInfo);

        return Result.ok();
    }

    /**
     * 会议信息删除
     */
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody Long[] meetingIds) {
        mMeetingInfoService.deleteBatch(meetingIds);

        return Result.ok();
    }

}
