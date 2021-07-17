package org.tyut4113.meeting.module.app.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.tyut4113.meeting.module.app.entity.MMeetingInfoEntity;
import org.tyut4113.meeting.module.app.vo.MMeetingInfoVo;

import java.util.Date;
import java.util.List;

/**
 * 会议信息
 *
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
public interface MMeetingInfoService extends IService<MMeetingInfoEntity> {

    /**
     * 保存会议信息及其相关关系
     * @param meetingInfo 会议信息
     */
    void saveMeetingInfo(MMeetingInfoEntity meetingInfo);

    /**
     * 更新会议信息及其相关关系
     * @param meetingInfo 会议信息
     */
    void updateMeetingInfo(MMeetingInfoEntity meetingInfo);

    /**
     * 获取所有时间碰撞的会议
     *
     * @param meetingEndTime 会议开始时间
     * @param meetingStartTime 会议结束时间
     * @return 碰撞会议的会议室信息
     */
    List<Long> listAllCollusionMeeting(Date meetingStartTime, Date meetingEndTime);

    /**
     * 根据会议名称进行模糊分页查询
     *
     * @param current 当前页
     * @param limit 页限制
     * @param name 模糊名
     * @param userId 创建者ID
     * @return 数据传输页
     */
    Page<MMeetingInfoVo> page(Integer current, Integer limit, String name, Long userId);

    /**
     * 根据会议ID获取会议的详细信息
     *
     * @param meetingId 会议ID
     * @return 会议详细信息
     */
    MMeetingInfoEntity getMeetingByMeetingId(Long meetingId);

    /**
     * 根据会议ID数组批量删除会议信息
     * @param meetingIds 会议ID列表
     */
    void deleteBatch(Long[] meetingIds);

    /**
     * 列出所有员工所参与的会议
     *
     * @param uid 员工工号
     * @return 会议信息
     */
    List<MMeetingInfoVo> listAllParticipatedMeeting(Long uid);
}

