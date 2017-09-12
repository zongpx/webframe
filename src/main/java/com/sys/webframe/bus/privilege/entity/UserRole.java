/**
 * date: 2017年6月13日
 */
package com.sys.webframe.bus.privilege.entity;

import java.sql.Timestamp;

import com.sys.webframe.core.support.BaseModel;

/**
 * @name: UserRole.java
 * @Description: 用户角色分配
 * @author duanyongrui
 * @since: 2017年6月13日
 */
public class UserRole extends BaseModel {
	private int id;
	/**
	 * 用户id
	 */
	private int user_id;
	/**
	 * 角色id
	 */
	private int role_id;
	/**
	 * 创建者id
	 */
	private int create_id;
	/**
	 * 修改者id
	 */
	private int modify_id;
	/**
	 * 用户id
	 */
	private Timestamp modify_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getCreate_id() {
		return create_id;
	}

	public void setCreate_id(int create_id) {
		this.create_id = create_id;
	}

	public int getModify_id() {
		return modify_id;
	}

	public void setModify_id(int modify_id) {
		this.modify_id = modify_id;
	}

	public Timestamp getModify_date() {
		return modify_date;
	}

	public void setModify_date(Timestamp modify_date) {
		this.modify_date = modify_date;
	}

	/**
	 * 
	 */
	public UserRole() {
		super();
	}

	/**
	 * @param user_id
	 * @param role_id
	 * @param create_id
	 * @param modify_id
	 * @param modify_date
	 */
	public UserRole(int user_id, int role_id, int create_id, int modify_id, Timestamp modify_date) {
		super();
		this.user_id = user_id;
		this.role_id = role_id;
		this.create_id = create_id;
		this.modify_id = modify_id;
		this.modify_date = modify_date;
	}
}
