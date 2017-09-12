package com.sys.webframe.bus.team.entity;

public class TeamRelHouse
{

	/** 房源关联id **/
	private int id;
	/** 团队id **/
	private int teamId;
	/** 小区id **/
	private int areaId;
	/** 登录用户id **/
	private int userId;
	/** 根团队id **/
	private int topTeamId;
	/** 团队级别 **/
	private int teamLevel;

	public int getTeamLevel()
	{
		return teamLevel;
	}

	public void setTeamLevel(int teamLevel)
	{
		this.teamLevel = teamLevel;
	}

	public int getTopTeamId()
	{
		return topTeamId;
	}

	public void setTopTeamId(int topTeamId)
	{
		this.topTeamId = topTeamId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getTeamId()
	{
		return teamId;
	}

	public void setTeamId(int teamId)
	{
		this.teamId = teamId;
	}

	public int getAreaId()
	{
		return areaId;
	}

	public void setAreaId(int areaId)
	{
		this.areaId = areaId;
	}

	@Override
	public String toString()
	{
		return "TeamRelHouse userId[" + userId + "],id=[" + id + "], teamId=[" + teamId + "], areaId=[" + areaId + "]" + "，topTeamId=[" + topTeamId + "]";
	}

}
