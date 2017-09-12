package com.sys.webframe.bus.privilege.entity;

import com.sys.webframe.core.support.BaseModel;

/**
 * date: 2017年6月13日
 */


/**
 * @name: privilege.java
 * @Description: 权限信息
 * @author duanyongrui
 * @since: 2017年6月13日
 */
public class Privilege extends BaseModel
{
	private int id;
	/**
	 * 权限所有者的对应字段，目前默认都是'role_id'
	 */
	private String master = "role_id";
	/**
	 * 权限对应字段的id值，目前默认都是role_id的值
	 */
	private int m_value;
	/**
	 * 功能对应字段，可选menu_id或btn_id
	 */
	private String access;
	/**
	 * 功能对应字段的id值，menu_id或btn_id的值
	 */
	private int a_value;
	/**
	 * 是否启用, 默认值 'enabled'
	 */
	private String oper = "enabled";

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMaster()
	{
		return master;
	}

	public void setMaster(String master)
	{
		this.master = master;
	}

	public int getM_value()
	{
		return m_value;
	}

	public void setM_value(int m_value)
	{
		this.m_value = m_value;
	}

	public String getAccess()
	{
		return access;
	}

	public void setAccess(String access)
	{
		this.access = access;
	}

	public int getA_value()
	{
		return a_value;
	}

	public void setA_value(int a_value)
	{
		this.a_value = a_value;
	}

	public String getOper()
	{
		return oper;
	}

	public void setOper(String oper)
	{
		this.oper = oper;
	}

	/**
	 * 
	 */
	public Privilege()
	{
		super();
	}

	/**
	 * 修改权限开启状态时构造方法
	 * 
	 * @param id
	 * @param oper
	 */
	public Privilege(int id, String oper)
	{
		super();
		this.id = id;
		this.oper = oper;
	}

	/**
	 * 添加权限时构造方法
	 * 
	 * @param m_value
	 * @param access
	 * @param a_value
	 */
	public Privilege(int m_value, String access, int a_value)
	{
		super();
		this.m_value = m_value;
		this.access = access;
		this.a_value = a_value;
	}

}
