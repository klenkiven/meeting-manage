package org.tyut4113.meeting.module.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import org.tyut4113.meeting.common.validation.group.AddGroup;
import org.tyut4113.meeting.common.validation.group.UpdateGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 系统用户
 * 
 * @author klenkiven
 * @email wzl709@outlook.com
 * @date 2021-07-12 09:12:55
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;

	/**
	 * 工号
	 */
	private Long uid;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;

	/**
	 * 姓名
	 */
	@NotBlank(message="姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;

	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String password;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 邮箱
	 */
	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
