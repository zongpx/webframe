/**
 * date: 2017年7月6日
 */
package com.sys.webframe.bus.privilege.controller.fo;

/**
 * @name: SearchRoleOption.java
 * @Description: 
 * @author duanyongrui
 * @since: 2017年7月6日
 */
public class SearchRoleOption
{
	
	private int teamId;
	
	private String roleName = "";

	

	public int getTeamId()
	{
		return teamId;
	}

	public void setTeamId(int teamId)
	{
		this.teamId = teamId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	/**
	 * 
	 */
	public SearchRoleOption()
	{
		super();
	}

	/**
	 * @param teamId
	 * @param roleName
	 */
	public SearchRoleOption(int teamId, String roleName)
	{
		super();
		this.teamId = teamId;
		this.roleName = roleName;
	}
	
}
