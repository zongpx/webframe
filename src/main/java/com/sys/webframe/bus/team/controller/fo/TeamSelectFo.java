package com.sys.webframe.bus.team.controller.fo;

import java.util.List;

import com.sys.webframe.bus.login.entity.User;


/**
 * 
 * @author 冷文佩
 * @since 2017年7月4日
 * @category com.yc.rm.caas.appserver.bus.controller.team.fo
 * @copyright
 * 
 */
public class TeamSelectFo
{

	/** 团队id **/
	private int teamId;
	/** 登录用户id **/
	private int userId;
	/** 是否为平台管理员标识，2是，1是团队管理员 **/
	private int ifAdmin;
	/** 团队名称，默认为空 **/
	private String teamName;
	/** 团队负责人id **/
	private int leaderId;
	/** 更新的团队负责人id **/
	private int newLeaderId;
	/** 团队级别 **/
	private int teamLevel;
	/** 是否查询子团队，1是，0不是 **/
	private int ifSubTeam;
	/** 是否查询本身团队 ，1是，0不是 **/
	private int ifSelf;
	/** 根团队id **/
	private int topTeamId;
	/** 人员列表 **/
	private List<User> memberList;
	/** 用户名下关联小区标识,1获取，-1获取该用户未关联的小区列表 **/
	private int ifMemRel;
	/** 小区关联团队标识，1获取，0不获取 ，-1获取该团队未关联的小区列表 **/
	private int ifTeamRel;
	/** 是否该团队下人员列表，1是，0不是 **/
	private int ifMem;
	/** 记录开始索引 **/
	private int startPage = 0;
	/** 每页包含的记录条数 **/
	private int pageSize = 25;

	public int getIfMem()
	{
		return ifMem;
	}

	public void setIfMem(int ifMem)
	{
		this.ifMem = ifMem;
	}

	public int getIfMemRel()
	{
		return ifMemRel;
	}

	public void setIfMemRel(int ifMemRel)
	{
		this.ifMemRel = ifMemRel;
	}

	public int getIfTeamRel()
	{
		return ifTeamRel;
	}

	public void setIfTeamRel(int ifTeamRel)
	{
		this.ifTeamRel = ifTeamRel;
	}

	public List<User> getMemberList()
	{
		return memberList;
	}

	public void setMemberList(List<User> memberList)
	{
		this.memberList = memberList;
	}

	public int getTopTeamId()
	{
		return topTeamId;
	}

	public void setTopTeamId(int topTeamId)
	{
		this.topTeamId = topTeamId;
	}

	public int getIfSubTeam()
	{
		return ifSubTeam;
	}

	public void setIfSubTeam(int ifSubTeam)
	{
		this.ifSubTeam = ifSubTeam;
	}

	public int getIfSelf()
	{
		return ifSelf;
	}

	public void setIfSelf(int ifSelf)
	{
		this.ifSelf = ifSelf;
	}

	public int getNewLeaderId()
	{
		return newLeaderId;
	}

	public void setNewLeaderId(int newLeaderId)
	{
		this.newLeaderId = newLeaderId;
	}

	public int getTeamLevel()
	{
		return teamLevel;
	}

	public void setTeamLevel(int teamLevel)
	{
		this.teamLevel = teamLevel;
	}

	public int getTeamId()
	{
		return teamId;
	}

	public void setTeamId(int teamId)
	{
		this.teamId = teamId;
	}

	public int getIfAdmin()
	{
		return ifAdmin;
	}

	public void setIfAdmin(int ifAdmin)
	{
		this.ifAdmin = ifAdmin;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getTeamName()
	{
		return teamName;
	}

	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

	public int getLeaderId()
	{
		return leaderId;
	}

	public void setLeaderId(int leaderId)
	{
		this.leaderId = leaderId;
	}

	public int getStartPage()
	{
		return startPage;
	}

	public void setStartPage(int startPage)
	{
		this.startPage = startPage;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	@Override
	public String toString()
	{
		return "TeamSelectFo:userId=[" + userId + "], roleId=[" + ifAdmin + "], teamName=[" + teamName + "], leaderId=[" + leaderId + "],startPage=[" + startPage + "],pageSize=["
				+ pageSize + "],teamId=[" + teamId + "],teamLevel[" + teamLevel + "]";
	}

}
