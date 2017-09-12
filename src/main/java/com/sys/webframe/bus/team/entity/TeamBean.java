package com.sys.webframe.bus.team.entity;

import java.sql.Timestamp;
import java.util.List;

public class TeamBean
{
	/** 团队id **/
	private int teamId;
	/** 团队名称 **/
	private String teamName;
	/** 团队负责人 **/
	private int leaderId;
	/** 团队负责人姓名 **/
	private String leaderName;
	/** 要更新的leaderId **/
	private int newLeaderId;
	/** 父团队 **/
	private int parentTeamId;
	/** 创建人 **/
	private int createId;
	/** 修改人 **/
	private int modifyId;
	/** 修改时间 **/
	private Timestamp modifyDate;
	/** 是否被删除，默认为0，未被删除 **/
	private int isDelete = 0;
	/** 团队级别，1是大团队，2是二级团队，依次类推 **/
	private int teamLevel;
	/** 团队关联房源数量 **/
	private int houseCnt;
	/** 子团队信息 **/
	private List<TeamBean> subTeam;
	/** 团队成员 **/
	private List<TeamRelation> memberList;
	/**
	 * 子团队集合
	 */
	private List<TeamBean> subTeams;

	public List<TeamBean> getSubTeams()
	{
		return subTeams;
	}

	public void setSubTeams(List<TeamBean> subTeams)
	{
		this.subTeams = subTeams;
	}

	public List<TeamRelation> getMemberList()
	{
		return memberList;
	}

	public void setMemberList(List<TeamRelation> memberList)
	{
		this.memberList = memberList;
	}

	public List<TeamBean> getSubTeam()
	{
		return subTeam;
	}

	public void setSubTeam(List<TeamBean> subTeam)
	{
		this.subTeam = subTeam;
	}

	public String getLeaderName()
	{
		return leaderName;
	}

	public void setLeaderName(String leaderName)
	{
		this.leaderName = leaderName;
	}

	public int getHouseCnt()
	{
		return houseCnt;
	}

	public void setHouseCnt(int houseCnt)
	{
		this.houseCnt = houseCnt;
	}

	public int getTeamId()
	{
		return teamId;
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

	public int getParentTeamId()
	{
		return parentTeamId;
	}

	public void setParentTeamId(int parentTeamId)
	{
		this.parentTeamId = parentTeamId;
	}

	public int getCreateId()
	{
		return createId;
	}

	public void setCreateId(int createId)
	{
		this.createId = createId;
	}

	public int getModifyId()
	{
		return modifyId;
	}

	public void setModifyId(int modifyId)
	{
		this.modifyId = modifyId;
	}

	public Timestamp getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public void setTeamId(int teamId)
	{
		this.teamId = teamId;
	}

	public int getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(int isDelete)
	{
		this.isDelete = isDelete;
	}

	public int getTeamLevel()
	{
		return teamLevel;
	}

	public void setTeamLevel(int teamLevel)
	{
		this.teamLevel = teamLevel;
	}

	public int getNewLeaderId()
	{
		return newLeaderId;
	}

	public void setNewLeaderId(int newLeaderId)
	{
		this.newLeaderId = newLeaderId;
	}

	@Override
	public String toString()
	{
		return "TeamParam teamId=[" + teamId + "], teamName=[" + teamName + "], leaderId=[" + leaderId + "],newLeaderId=[" + newLeaderId + "] parentTeamId=[" + parentTeamId
				+ "], createId=[" + createId + "], modifyId=[" + modifyId + "], modifyDate=[" + modifyDate + "]" + ",isDelete=[" + isDelete + "],teamLevel=[" + teamLevel + "],leaderName=["
				+ leaderName + "],houseCnt=[" + houseCnt + "]";
	}
}
