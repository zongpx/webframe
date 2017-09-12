/**
 * date: 2017年7月6日
 */
package com.sys.webframe.bus.privilege.entity;

/**
 * @name: RoleOption.java
 * @Description: 
 * @author duanyongrui
 * @since: 2017年7月6日
 */
public class RoleOption
{
	/**
	 * 团队名称
	 */
	private int team_id;
	
	/**
	 * 角色名称
	 */
	private String role_name = "";
	
	/**
	 * 登录人id
	 */
	private int login_id;

	

	public int getTeam_id()
	{
		return team_id;
	}

	public void setTeam_id(int team_id)
	{
		this.team_id = team_id;
	}

	public String getRole_name()
	{
		return role_name;
	}

	public void setRole_name(String role_name)
	{
		this.role_name = role_name;
	}

	public int getLogin_id()
	{
		return login_id;
	}

	public void setLogin_id(int login_id)
	{
		this.login_id = login_id;
	}

	
	/**
	 * 
	 */
	public RoleOption()
	{
		super();
	}

	/**
	 * @param team_id
	 * @param role_name
	 */
	public RoleOption(int team_id, String role_name)
	{
		super();
		this.team_id = team_id;
		this.role_name = role_name;
	}

	/**
	 * @param team_id
	 * @param role_name
	 * @param login_id
	 */
	public RoleOption(int team_id, String role_name, int login_id)
	{
		super();
		this.team_id = team_id;
		this.role_name = role_name;
		this.login_id = login_id;
	}
	
	
}
