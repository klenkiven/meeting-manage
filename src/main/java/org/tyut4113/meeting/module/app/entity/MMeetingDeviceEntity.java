package org.tyut4113.meeting.module.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会议与设备对应关系
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-15 10:08:51
 */
@Data
@TableName("m_meeting_device")
public class MMeetingDeviceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会议ID
	 */
	@TableId(type = IdType.INPUT)
	private Long meetingId;
	/**
	 * 设备ID
	 */
	private Long deviceId;

}
