package org.tyut4113.meeting.module.app.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.tyut4113.meeting.common.validation.group.AddGroup;
import org.tyut4113.meeting.common.validation.group.UpdateGroup;

import javax.validation.constraints.NotBlank;

/**
 * 会议信息
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Data
@TableName("m_meeting_info")
public class MMeetingInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会议ID
	 */
	@TableId
	private Long id;
	/**
	 * 会议室ID
	 */
	private Long roomId;
	/**
	 * 会议名称
	 */
	@NotBlank(message = "会议名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 开始时间
	 */
	@NotBlank(message = "开始时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Date startTime;
	/**
	 * 结束时间
	 */
	@NotBlank(message = "结束时间不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Date endTime;

	/**
	 * 与会人员列表
	 */
	@TableField(exist = false)
	private List<Long> uidList;

	/**
	 * 会议使用设备列表
	 */
	@TableField(exist = false)
	private List<Long> deviceList;

	/**
	 * 会议发起者ID
	 */
	private Long createUserId;

	/**
	 * 会议创建时间
	 */
	private Date createTime;

}
