package com.sys.webframe.bus.team.entity;

public class TeamRelation
{
	int id;
	/** 团队id **/
	int teamId;
	/** 登录用户id **/
	int userId;
	/** 团队成员id **/
	int memberId;
	/** 新团队成员 **/
	int newMemberId;
	/** 团队人员名称 **/
	private String memberName;
	/** 人员关联房源数量 **/
	private int memCnt;
	/** 团队级别，1是大团队，2是二级团队，依次类推 **/
	private int teamLevel;
	/** 根团队id **/
	private int topTeamId;
	/** 是否是团队负责人，1是，0不是 **/
	private int ifCharge;
	/** 小区id **/
	private int areaId;

	public int getAreaId()
	{
		return areaId;
	}

	public void setAreaId(int areaId)
	{
		this.areaId = areaId;
	}

	public int getIfCharge()
	{
		return ifCharge;
	}

	public void setIfCharge(int ifCharge)
	{
		this.ifCharge = ifCharge;
	}

	public int getTopTeamId()
	{
		return topTeamId;
	}

	public void setTopTeamId(int topTeamId)
	{
		this.topTeamId = topTeamId;
	}

	public int getNewMemberId()
	{
		return newMemberId;
	}

	public void setNewMemberId(int newMemberId)
	{
		this.newMemberId = newMemberId;
	}

	public int getTeamLevel()
	{
		return teamLevel;
	}

	public void setTeamLevel(int teamLevel)
	{
		this.teamLevel = teamLevel;
	}

	public String getMemberName()
	{
		return memberName;
	}

	public void setMemberName(String memberName)
	{
		this.memberName = memberName;
	}

	public int getMemCnt()
	{
		return memCnt;
	}

	public void setMemCnt(int memCnt)
	{
		this.memCnt = memCnt;
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

	public int getMemberId()
	{
		return memberId;
	}

	public void setMemberId(int memberId)
	{
		this.memberId = memberId;
	}

	@Override
	public String toString()
	{
		return "TeamRelation [id=" + id + ", teamId=" + teamId + ", userId=" + userId + ", memberId=" + memberId + ", newMemberId=" + newMemberId + ", memberName=" + memberName
				+ ", memCnt=" + memCnt + ", teamLevel=" + teamLevel + ", topTeamId=" + topTeamId + ", ifCharge=" + ifCharge + ", areaId=" + areaId + "]";
	}

}
