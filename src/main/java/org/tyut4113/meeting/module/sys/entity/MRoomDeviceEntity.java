package org.tyut4113.meeting.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 会议室与设备对应关系
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Data
@TableName("m_room_device")
public class MRoomDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会议室ID
	 */
	@TableId
	private Long roomId;
	/**
	 * 设备ID
	 */
	private Long deviceId;

}
