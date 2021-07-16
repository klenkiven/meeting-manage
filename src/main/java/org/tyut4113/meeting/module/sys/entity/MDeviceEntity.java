package org.tyut4113.meeting.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 设备信息
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Data
@TableName("m_device")
public class MDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 设备ID
	 */
	@TableId
	private Long id;

	/**
	 * 设备名称
	 */
	private String name;

	/**
	 * 类型  0：固定   1：流动
	 */
	private Integer type;

	/**
	 * 状态  0：占用   1：可用
	 */
	private Integer status;

}
