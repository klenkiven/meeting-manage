package org.tyut4113.meeting.module.app.vo;

import lombok.Data;

import java.util.Date;

/**
 * 会议信息 前后端数据传输类
 *
 * @author ：klenkiven
 * @date ：2021/7/17 8:05
 */
@Data
public class MMeetingInfoVo {

    /**
     * 会议ID
     */
    private Long id;
    /**
     * 会议室名
     */
    private String roomName;
    /**
     * 会议名称
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 会议创建时间
     */
    private Date createTime;

}
