package com.sys.webframe.bus.team.controller.fo;

/**
 * 人员关联小区入参
 */
import java.util.List;

import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.team.entity.Group;


public class TeamRelFo
{
	/** 登录用户id **/
	private int userId;
	/** 所操作团队id **/
	private int teamId;
	/** 人员id **/
	private int memberId;
	/** 根团队id **/
	private int topTeamId;
	/** 是否是团队管理员，1是，0不是 **/
	private int ifCharge;
	/** 小区id **/
	private List<Group> areaIdList;
	/** 移除的小区id **/
	private List<Group> removeAreaList;
	/** 人员列表 **/
	private List<User> memberList;
	/** 移除的人员列表 **/
	private List<User> removeMemberList;

	public List<User> getMemberList()
	{
		return memberList;
	}

	public void setMemberList(List<User> memberList)
	{
		this.memberList = memberList;
	}

	public List<User> getRemoveMemberList()
	{
		return removeMemberList;
	}

	public void setRemoveMemberList(List<User> removeMemberList)
	{
		this.removeMemberList = removeMemberList;
	}

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

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getTeamId()
	{
		return teamId;
	}

	public void setTeamId(int teamId)
	{
		this.teamId = teamId;
	}

	public int getMemberId()
	{
		return memberId;
	}

	public void setMemberId(int memberId)
	{
		this.memberId = memberId;
	}

	public int getTopTeamId()
	{
		return topTeamId;
	}

	public void setTopTeamId(int topTeamId)
	{
		this.topTeamId = topTeamId;
	}

	public int getIfCharge()
	{
		return ifCharge;
	}

	public void setIfCharge(int ifCharge)
	{
		this.ifCharge = ifCharge;
	}

	@Override
	public String toString()
	{
		return "TeamRelFo [userId=" + userId + ", teamId=" + teamId + ", memberId=" + memberId + ", topTeamId=" + topTeamId + ", ifCharge=" + ifCharge + "]";
	}
}
