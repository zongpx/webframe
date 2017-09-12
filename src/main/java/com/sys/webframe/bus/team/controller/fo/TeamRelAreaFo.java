package com.sys.webframe.bus.team.controller.fo;

import java.util.List;

import com.sys.webframe.bus.team.entity.Group;


/** 团队关联小区入参 **/
public class TeamRelAreaFo
{
	/** 房源关联id **/
	private int id;
	/** 团队id **/
	private int teamId;
	/** 小区id **/
	private List<Group> areaIdList;
	/** 登录用户id **/
	private int userId;
	/** 根团队id **/
	private int topTeamId;
	/** 团队级别 **/
	private int teamLevel;
	/** 移除的小区id **/
	private List<Group> removeAreaList;

	public List<Group> getRemoveAreaList()
	{
		return removeAreaList;
	}

	public void setRemoveAreaList(List<Group> removeAreaList)
	{
		this.removeAreaList = removeAreaList;
	}

	public List<Group> getAreaIdList()
	{
		return areaIdList;
	}

	public void setAreaIdList(List<Group> areaIdList)
	{
		this.areaIdList = areaIdList;
	}

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

	@Override
	public String toString()
	{
		return "TeamRelHouse userId[" + userId + "],id=[" + id + "], teamId=[" + teamId + "], " + "topTeamId=[" + topTeamId + "]";
	}

}
