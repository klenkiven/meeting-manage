package org.tyut4113.meeting.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 会议室信息
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Data
@TableName("m_room")
public class MRoomEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会议室ID
	 */
	@TableId
	private Long id;
	/**
	 * 会议室名称
	 */
	private String name;
	/**
	 * 容量
	 */
	private Integer capacity;

	/**
	 * 会议室的设备列表
	 */
	@TableField(exist = false)
	private List<Long> deviceIdList;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

}
