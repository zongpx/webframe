package com.sys.webframe.bus.team.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.team.controller.fo.TeamRelAreaFo;
import com.sys.webframe.bus.team.controller.fo.TeamRelFo;
import com.sys.webframe.bus.team.controller.fo.TeamSelectFo;
import com.sys.webframe.bus.team.dao.ITeamDao;
import com.sys.webframe.bus.team.entity.Group;
import com.sys.webframe.bus.team.entity.TeamBean;
import com.sys.webframe.bus.team.entity.TeamRelHouse;
import com.sys.webframe.bus.team.entity.TeamRelation;
import com.sys.webframe.core.support.BaseService;


@Service("_teamServImpl")
public class TeamServImpl extends BaseService implements ITeamServ
{

	@Resource(name = "_teamDao")
	private ITeamDao _teamDao;

	/**
	 * 某团队名下的该用户未关联的小区列表/获取未分配团队的小区列表/获取某用户名下关联的小区列表/该团队关联的小区列表
	 */
	public List<Group> getAreaInfo(TeamSelectFo teamFo)
	{
		log.info("into getAreaList:");
		List<Group> areaList = new ArrayList<Group>();
		// 某团队下，该团员未关联的小区列表
		if (teamFo.getIfMemRel() == -1)
		{
			areaList = _teamDao.selectAreaByTeam(teamFo.getMemberList().get(0).getUserId(), teamFo.getTeamId());
		}
		// 该团员名下关联的小区列表
		else if (teamFo.getIfMemRel() == 1)
		{
			areaList = _teamDao.selectAreaByUser(teamFo.getMemberList().get(0).getUserId());
		}
		// 未关联团队的小区列表
		else if (teamFo.getIfTeamRel() == -1)
		{
			areaList = _teamDao.selectAreaList(teamFo.getTeamId());
		}
		// 该团队关联的小区列表
		else if (teamFo.getIfTeamRel() == 1)
		{
			areaList = _teamDao.selectAreaListByTeam(teamFo.getTeamId());
		} else
		{
			log.info("不获取任何小区列表！");
		}
		log.info("end getAreaList");
		return areaList;
	}

	/**
	 * 获取人员列表（有根团队，无子团队）/获取某团队下团员信息，需要teamId
	 * 
	 */
	@Override
	public List<User> getUserList(TeamSelectFo teamFo)
	{
		log.info("into getUserList:");
		List<User> userList = new ArrayList<User>();
		if (teamFo.getIfMem() == 1)
		{
			userList = _teamDao.selectUserListByTeam(teamFo);
		} else
		{
			userList = _teamDao.selectUserList(teamFo);
		}
		log.info("end getUserList");
		return userList;
	}

	/**
	 * 查询新建子团队的等级
	 */
	@Override
	public int getTeamLevel(int userId)
	{
		int teamLevel = 0;
		int state = ifPermission(userId, 0);
		if (state == 2)
		{
			teamLevel = 1;
		} else if (state == 0)
		{
			teamLevel = _teamDao.selectTeamLevel(userId);
			if (teamLevel >= 3)
			{
				teamLevel = -1;
				log.info("子团队深度已达上限，不能再新建子他团队！");
			} else
			{
				teamLevel = teamLevel + 1;
			}
		} else
		{
			teamLevel = -1;
			log.info("该用户没有权限新建子团队！");
		}
		return teamLevel;
	}

	/**
	 * 团队首页信息获取
	 * 
	 */
	@Override
	public List<TeamBean> getTeamPage(TeamSelectFo teamFo)
	{
		log.info("into getTeamPage:");
		int userId = teamFo.getUserId();
		int state = ifPermission(userId, teamFo.getTeamId());
		List<TeamBean> teamList = new ArrayList<TeamBean>();
		if (state >= 0)
		{
			if (state == 2)
			{
				teamFo.setIfAdmin(2);
			} else
			{
				teamFo.setIfAdmin(1);
			}
			// 获取自己所在团队，若是平台管理员，则显示所有一级团队（多个）
			teamList = _teamDao.selectTeamInfo(teamFo);
			log.debug("teamList:" + teamList);
			for (int i = 0; i < teamList.size(); i++)
			{
				int teamId = teamList.get(i).getTeamId();
				log.debug("teamId:" + teamId);
				// 根据团队id判断此团队是否关联小区
				if (_teamDao.selectIfRelArea(teamId) > 0)
				{
					// 是的话，计算小区下的房源数量
					teamList.get(i).setHouseCnt(_teamDao.selectHouseCnt(teamList.get(i).getTeamId()));
				} else
				{
					// 不是，房源数量为0
					teamList.get(i).setHouseCnt(0);
				}
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end getTeamPage");
		return teamList;
	}

	/**
	 * 点击团队名称获取团队子团队信息
	 * 
	 */
	@Override
	public Map<String, Object> getTeamByTeamId(TeamSelectFo teamFo)
	{
		log.info("into getTeamByTeamId:");
		int userId = teamFo.getUserId();
		TeamBean team = new TeamBean();
		int state = ifPermission(userId, teamFo.getTeamId());
		Map<String, Object> teamInfo = new HashMap<String, Object>();
		// TeamList teamList = ifPermission(userId, team.getTeamId());
		if (state >= 1)
		{
			if (teamFo.getTeamId() == 0)
			{
				log.info("请点击团队进行子团队查看！");
			} else
			{
				// 根据所选团队id获取此团队下的子团队
				teamFo.setIfSubTeam(1);
				team.setSubTeam(_teamDao.selectTeamInfo(teamFo));
				teamInfo.put("teamList", team.getSubTeam());
				for (int i = 0; i < team.getSubTeam().size(); i++)
				{
					// 判断每一个子团队是否关联小区
					if (_teamDao.selectIfRelArea(team.getSubTeam().get(i).getTeamId()) > 0)
					{
						// 关联过，计算关联小区对应的房源数量
						team.getSubTeam().get(i).setHouseCnt(_teamDao.selectHouseCnt(team.getSubTeam().get(i).getTeamId()));
					} else
					{
						// 否则，数量为0
						team.getSubTeam().get(i).setHouseCnt(0);
					}
				}
				// 根据所选团队，显示团队下的成员
				team.setMemberList(_teamDao.selectMemberListById(teamFo.getTeamId()));
				teamInfo.put("memberList", team.getMemberList());
				for (int j = 0; j < team.getMemberList().size(); j++)
				{
					// 判断人员是否关联小区
					if (_teamDao.selectIfRelMember(team.getMemberList().get(j).getMemberId()) > 0)
					{
						team.getMemberList().get(j).setMemCnt(_teamDao.selectMemberHCnt(team.getMemberList().get(j).getMemberId()));
					} else
					{
						team.getMemberList().get(j).setMemCnt(0);
					}
				}
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end getTeamByTeamId");
		return teamInfo;
	}

	/**
	 * 通过关键字查询
	 * 
	 */
	@Override
	public List<Map<String, TeamBean>> getTeamByDimSearch(TeamSelectFo teamFo)
	{
		log.info("into getTeamByDimSearch:");
		List<Map<String, TeamBean>> teamList = new ArrayList<Map<String, TeamBean>>();
		int userId = teamFo.getUserId();
		String teamName = teamFo.getTeamName();
		int teamLevel = teamFo.getTeamLevel();
		int state = ifPermission(userId, teamFo.getTeamId());
		if (state >= 0)
		{
			// 通过团队名称模糊匹配查找团队id
			List<Map<String, Integer>> teamIds = _teamDao.selectTeamIdList(teamLevel, teamName);
			if (teamIds != null && teamIds.size() > 0)
			{
				for (int i = 0; i < teamIds.size(); i++)
				{
					TeamBean team = new TeamBean();
					int teamId = teamIds.get(i).get("teamId");
					team.setTeamId(teamId);
					// 根据团队id查找基本信息
					teamFo.setTeamId(teamId);
					teamFo.setIfSelf(1);
					team = _teamDao.selectTeamInfo(teamFo).get(0);
					team.setHouseCnt(_teamDao.selectHouseCnt(teamId));
					Map<String, TeamBean> oneTeamInfo = new HashMap<String, TeamBean>();
					// 存放底层团队的信息

					int myLevel = 0;
					if (state == 2)
					{
						myLevel = 1;
					} else
					{
						myLevel = _teamDao.selectTeamLevel(userId);
					}
					int d = teamLevel - myLevel;
					if (d >= 0)
					{
						oneTeamInfo.put("teamBean" + (d + 1), team);
					} else
					{
						log.info("搜索等级大于自身权限！");
						return teamList;
					}
					for (int j = 0; j < d; j++)
					{
						// 查找父团队id
						int parentTeamId = Integer.parseInt(_teamDao.selectParentTeamId(teamId).get("parent_teamid").toString());
						teamId = parentTeamId;
						teamFo.setTeamId(parentTeamId);
						team = _teamDao.selectTeamInfo(teamFo).get(0);
						team.setTeamId(parentTeamId);
						team.setHouseCnt(_teamDao.selectHouseCnt(teamId));
						oneTeamInfo.put("teamBean" + (d - j), team);
					}
					if (state == 0)
					{
						if (teamId == _teamDao.selectTeamIdByUser(userId))
						{
							teamList.add(oneTeamInfo);
						} else
						{
							log.info("团队不符合要求！");
						}
					} else
					{
						teamList.add(oneTeamInfo);
					}
				}
			} else
			{
				log.info("没有找到符合的团队！");
			}
		} else
		{
			log.info("权限不够！");
		}
		log.debug("teamList:" + teamList);
		log.info("end getTeamByDimSearch");
		return teamList;
	}

	/**
	 * 新增团队
	 */
	@Override
	public int addTeam(TeamSelectFo teamFo)
	{
		TeamBean team = new TeamBean();
		log.info("into insertTeam");
		int userId = teamFo.getUserId();
		int state = ifPermission(userId, teamFo.getTeamId());
		// state为2，平台管理员，创建大团队
		if (state > 1)
		{
			// parent_teamid为0则是大团队
			team.setParentTeamId(0);
			team.setCreateId(userId);
			team.setModifyId(userId);
			team.setTeamLevel(1);
			team.setLeaderId(teamFo.getLeaderId());
		}
		// state为1，团队负责人，创建子团队
		else if (state > 0)
		{
			team.setParentTeamId(teamFo.getTeamId());
			team.setCreateId(userId);
			team.setModifyId(userId);
			team.setTeamLevel(teamFo.getTeamLevel());
			team.setLeaderId(teamFo.getLeaderId());
		} else
		{
			log.info("身份信息不合法!");
			return state;
		}
		if (_teamDao.selectUniName(teamFo.getTeamName(), 0) > 0)
		{
			log.info("团队名称不唯一！");
			state = -1;
		} else
		{
			log.info("团队名称唯一");
			team.setTeamName(teamFo.getTeamName());
			try
			{
				int j = _teamDao.insertTeam(team);
				if (j < 1)
				{
					state = -1;
					log.info("新增团队失败！");
				} else
				{
					state = 1;
					log.info("新增团队成功！");
				}
				log.debug("teamId:" + team.getTeamId());
				for (int i = 0; i < teamFo.getMemberList().size(); i++)
				{
					TeamRelation teamRel = new TeamRelation();
					if (teamFo.getMemberList().get(i).getUserId() == teamFo.getLeaderId())
					{
						teamRel.setIfCharge(1);
					} else
					{
						teamRel.setIfCharge(0);
					}
					teamRel.setMemberId(teamFo.getMemberList().get(i).getUserId());
					teamRel.setTeamId(team.getTeamId());
					teamRel.setTopTeamId(teamFo.getTopTeamId());
					int n = _teamDao.insertTeamRelation(teamRel);
					if (n < 0)
					{
						state = -1;
						log.info("插入关联记录失败！");
					} else
					{
						state = 1;
						log.info("插入关联记录成功！");
					}
				}
			} catch (Exception e)
			{
				log.error("e:", e);
			}
		}
		log.info("end insertTeam");
		return state;
	}

	/**
	 * 新增团员
	 */
	public int addTeamMember(TeamSelectFo teamFo)
	{
		int state = 0;
		state = ifPermission(teamFo.getUserId(), teamFo.getTeamId());
		if (state > 0)
		{
			for (int i = 0; i < teamFo.getMemberList().size(); i++)
			{
				TeamRelation member = new TeamRelation();
				member.setIfCharge(0);
				member.setTopTeamId(teamFo.getTopTeamId());
				member.setMemberId(teamFo.getMemberList().get(i).getUserId());
				member.setTeamId(teamFo.getTeamId());
				if (_teamDao.insertTeamRelation(member) > 0)
				{
					state = 1;
					log.info("新增团员成功!");
				} else
				{
					state = -1;
					log.info("新增团员失败!");
					return state;
				}
			}

		} else
		{
			log.info("身份信息不合法!");
		}
		return state;
	}

	public void removeRec(TeamBean team)
	{
		// 查询出所有下级
		List<Map<String, Object>> teamIds = _teamDao.selectSubTeamIdsAndLeaderIds(team.getTeamId());
		// 循环下级
		for (int i = 0; i < teamIds.size(); i++)
		{
			int leaderId = Integer.parseInt(teamIds.get(i).get("leader_id").toString());
			int id = Integer.parseInt(teamIds.get(i).get("id").toString());
			team.setLeaderId(leaderId);
			team.setTeamId(id);
			// 删除
			try
			{
				// 删除团队表
				_teamDao.deleteTeam(team);
				// 删除团队关联表
				_teamDao.deleteTeamMember(0, id);
				TeamRelation teamRel = new TeamRelation();
				TeamRelHouse relArea = new TeamRelHouse();
				relArea.setTeamId(id);
				teamRel.setTeamId(id);
				_teamDao.deleteAreaRel(relArea);
				_teamDao.deleteMemberRelAreaList(teamRel);
			} catch (Exception e)
			{
				log.error("e", e);
			}
			removeRec(team);
		}
	}

	/**
	 * 删除团队
	 */
	@Override
	public int removeTeam(TeamSelectFo teamFo)
	{
		TeamBean team = new TeamBean();
		log.info("into deleteTeam");
		int userId = teamFo.getUserId();
		team.setModifyId(userId);
		team.setLeaderId(teamFo.getLeaderId());
		team.setTeamId(teamFo.getTeamId());
		int state = ifPermission(userId, teamFo.getTeamId());
		if (state >= 1)
		{
			try
			{
				_teamDao.deleteTeam(team);
				_teamDao.deleteTeamMember(0, teamFo.getTeamId());
				TeamRelHouse relArea = new TeamRelHouse();
				TeamRelation teamRel = new TeamRelation();
				relArea.setTeamId(teamFo.getTeamId());
				teamRel.setTeamId(teamFo.getTeamId());
				_teamDao.deleteAreaRel(relArea);
				_teamDao.deleteMemberRelAreaList(teamRel);
			} catch (Exception e)
			{
				log.error("e", e);
			}
			// 该团队无leaderId，通过teamId去删除
			if (teamFo.getLeaderId() == 0)
			{
				log.info("通过teamid删除团队信息成功！");
				state = 1;
			}
			// 有leaderId，通过leaderId，去递归删除
			else
			{
				removeRec(team);
				log.info("通过leaderid递归删除团队信息完成！");
				state = 1;
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end deleteTeam");
		return state;
	}

	/**
	 * 更改团队名称
	 */
	@Override
	public int modifyTeamName(TeamSelectFo teamFo)
	{
		log.info("into modifyTeamName");
		int userId = teamFo.getUserId();
		TeamBean team = new TeamBean();
		int state = 0;
		state = ifPermission(userId, teamFo.getTeamId());
		if (state >= 1)
		{
			team.setModifyId(userId);
			team.setTeamName(teamFo.getTeamName());
			team.setTeamId(teamFo.getTeamId());
			if (_teamDao.selectUniName(teamFo.getTeamName(), teamFo.getTeamId()) == 0)
			{
				_teamDao.updateTeamName(team);
				log.info("团队名称修改完成！");
				state = 1;
			} else
			{
				log.info("团队名称重复，请重新输入！");
				state = -1;
				return state;
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end modifyTeamName");
		return state;
	}

	/**
	 * 变更团队负责人
	 */
	@Override
	public int modifyTeamCharge(TeamSelectFo teamFo)
	{
		log.info("into modifyTeamCharge");
		TeamBean team = new TeamBean();
		int userId = teamFo.getUserId();
		int state = ifPermission(userId, teamFo.getTeamId());
		if (state >= 1)
		{
			team.setModifyId(userId);
			team.setNewLeaderId(teamFo.getNewLeaderId());
			team.setLeaderId(teamFo.getLeaderId());
			team.setTeamId(teamFo.getTeamId());
			int i=0;
			
			// 同时更新人员关联表里的数据，将原负责人的团队id变更为null，更新团队关联表中新的团队负责人的teamId，更新团队表的负责人id
			//_teamDao.updateTeamRel(team);
			_teamDao.updateChargeToNull(team);
		  _teamDao.updateTeamCharge(team);
			i=_teamDao.updateTeamRel(team);
			log.debug("team:" + team);
			if (i == 0)
			{
				log.info("变更团队负责人失败！");
				state = -1;
			} else
			{
				log.info("变更团队负责人成功！");
				state = 1;
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end modifyTeamCharge");
		return state;
	}

	/**
	 * 变更团队成员
	 */
	@Override
	public int modifyTeamMember(TeamRelFo teamRelFo)
	{
		log.info("into modifyTeamMember");
		log.debug("teamRelFo:" + teamRelFo);
		int userId = teamRelFo.getUserId();
		int state = ifPermission(userId, teamRelFo.getTeamId());
		if (state >= 1)
		{
			if (teamRelFo.getMemberList() == null)
			{
				state = 1;
				log.info("没有要变更的人员！");
			} else
			{
				for (int n = 0; n < teamRelFo.getMemberList().size(); n++)
				{
					// 新增新成员
					TeamRelation teamRel = new TeamRelation();
					teamRel.setTeamId(teamRelFo.getTeamId());
					teamRel.setMemberId(teamRelFo.getMemberList().get(n).getUserId());
					teamRel.setTopTeamId(teamRelFo.getTopTeamId());
					teamRel.setIfCharge(0);
					int j = _teamDao.insertTeamRelation(teamRel);
					if (j == 0)
					{
						log.info("新增成员失败！");
						state = -1;
					} else
					{
						log.info("新增成员成功！");
						state = 1;
					}

				}
			}
			if (teamRelFo.getRemoveMemberList() == null)
			{
				state = 1;
				log.info("没有要删除的关联！");
			} else
			{
				for (int m = 0; m < teamRelFo.getRemoveMemberList().size(); m++)
				{
					int i = _teamDao.deleteTeamMember(teamRelFo.getRemoveMemberList().get(m).getUserId(), teamRelFo.getTeamId());
					if (i == 0)
					{
						log.info("删除成员失败！");
						state = -1;
					} else
					{
						log.info("删除成员成功！");
						state = 1;
					}
				}
			}
			log.debug("teamRelFo:" + teamRelFo);
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end modifyTeamMember");
		return state;
	}

	/**
	 * 团队关联小区
	 */
	@Override
	public int findAreaRel(TeamRelAreaFo areaFo)
	{
		log.info("into findAreaRel");
		log.debug("areaFo:" + areaFo);
		int userId = areaFo.getUserId();
		int state = ifPermission(userId, areaFo.getTeamId());
		TeamRelHouse relArea = new TeamRelHouse();
		relArea.setTeamId(areaFo.getTeamId());
		relArea.setTopTeamId(1);
		if (state >= 1)
		{
			if (areaFo.getAreaIdList() == null || areaFo.getAreaIdList().size() == 0)
			{
				state = 1;
				log.info("没有要关联的小区！");
			} else
			{
				int n = 0;
				for (int i = 0; i < areaFo.getAreaIdList().size(); i++)
				{
					relArea.setAreaId(areaFo.getAreaIdList().get(i).getGroupId());
					try
					{
						_teamDao.insertAreaRel(relArea);
						n++;
					} catch (Exception e)
					{
						log.error("e:", e);
					}
				}
				if (n > 0)
				{
					log.info("团队关联小区成功！");
					state = 1;
				} else
				{
					log.info("关联小区失败！");
					state = -1;
				}
			}
			if (areaFo.getRemoveAreaList() == null || areaFo.getRemoveAreaList().size() == 0)
			{
				state = 1;
				log.info("没有要删除的小区！");
			} else
			{
				for(int i=0;i<areaFo.getRemoveAreaList().size();i++)
				{
					log.debug("removeList.size:"+areaFo.getRemoveAreaList().size());
					log.debug("removeList:"+areaFo.getRemoveAreaList().get(i).getGroupId());
					if(areaFo.getRemoveAreaList().get(i).getGroupId()==0)
					{
						state = 1;
						log.info("没有要删除的小区！");
						return state;
					}
				}
				int m = 0;
				for (int j = 0; j < areaFo.getRemoveAreaList().size(); j++)
				{
					relArea.setAreaId(areaFo.getRemoveAreaList().get(j).getGroupId());
					_teamDao.deleteAreaRel(relArea);
					m++;
				}
				if (m > 0)
				{
					log.info("删除团队关联小区成功！");
					state = 1;
				} else
				{
					log.info("没有要删除的小区关联！");
					state = 1;
				}
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end findAreaRel");
		return state;
	}

	/**
	 * 删除团员
	 */
	@Override
	public int removeTeamMember(TeamRelFo teamRelFo)
	{
		log.info("into removeTeamMember:");
		int userId = teamRelFo.getUserId();
		TeamRelation teamRel = new TeamRelation();
		teamRel.setTeamId(teamRelFo.getTeamId());
		teamRel.setMemberId(teamRelFo.getMemberId());
		int state = ifPermission(userId, teamRelFo.getTeamId());
		if (state >= 1)
		{
			int i = _teamDao.deleteTeamMember(teamRelFo.getMemberId(), teamRelFo.getTeamId());
			_teamDao.deleteMemberRelAreaList(teamRel);
			if (i > 0)
			{
				state = 1;
				log.info("删除成员成功！");
			} else
			{
				state = -1;
				log.info("删除成员失败！");
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end removeTeamMember");
		return state;
	}

	/**
	 * 人员关联小区
	 */
	@Override
	public int findAreaMemberRel(TeamRelFo teamFo)
	{
		log.info("into findAreaMemberRel:");
		int userId = teamFo.getUserId();
		int state = ifPermission(userId, teamFo.getTeamId());
		TeamRelation teamRel = new TeamRelation();
		teamRel.setTeamId(teamFo.getTeamId());
		teamRel.setMemberId(teamFo.getMemberId());
		teamRel.setTopTeamId(1);
		if (state >= 1)
		{
			if (teamFo.getAreaIdList() == null || teamFo.getAreaIdList().size() == 0)
			{
				state = 1;
				log.info("没有要关联小区的人员！");
			} else
			{
				int n = 0;
				for (int i = 0; i < teamFo.getAreaIdList().size(); i++)
				{
					try
					{
						teamRel.setAreaId(teamFo.getAreaIdList().get(i).getGroupId());
						_teamDao.insertMemberRelArea(teamRel);
						n++;
					} catch (Exception e)
					{
						log.error("e:", e);
					}
				}
				if (n > 0)
				{
					log.info("人员关联小区成功！");
				} else
				{
					log.info("人员关联小区失败！");
				}
			}
			if (teamFo.getRemoveAreaList() == null || teamFo.getRemoveAreaList().size() == 0)
			{
				state = 1;
				log.info("没有要删除关联的人员！");
			} else
			{
				for (int j = 0; j < teamFo.getRemoveAreaList().size(); j++)
				{
					teamRel.setAreaId(teamFo.getRemoveAreaList().get(j).getGroupId());
					try
					{
						_teamDao.deleteMemberRelAreaList(teamRel);
					} catch (Exception e)
					{
						log.error("e:", e);
					}
				}
			}
		} else
		{
			log.info("身份信息不合法!");
		}
		log.info("end findAreaMemberRel");
		return state;
	}

	public int permission(int teamId, int userId)
	{
		int state = 0;
		// 查询父团队id
		if (_teamDao.selectParentTeamId(teamId) == null || _teamDao.selectParentTeamId(teamId).size() == 0)
		{
			state = -1;
			log.info("输入的团队无对应的父团队id！");
			return state;
		} else
		{
			String pId = String.valueOf(_teamDao.selectParentTeamId(teamId).get("parent_teamid"));
			int parentTeamId = Integer.parseInt(pId);

			// 根据父团队id，找他的团队负责人
			if (_teamDao.selectParentTeamId(parentTeamId) == null || _teamDao.selectParentTeamId(parentTeamId).size() == 0)
			{
				state = -1;
				log.info("输入的团队无对应的信息！");
				return state;
			} else
			{
				String lId = String.valueOf(_teamDao.selectParentTeamId(parentTeamId).get("leader_id"));

				int leaderId = Integer.parseInt(lId);
				log.debug("leaderId:" + leaderId);
				log.debug("userId:" + userId);
				if (leaderId == userId)
				{
					state = 1;
					log.info("该用户有权限操作此团队");
					return state;
				}
				// leaderId不为空，查找下一级团队
				else
				{
					teamId = parentTeamId;
				}
			}
			return permission(teamId, userId);
		}
	}

	public int ifPermission(int userId, int teamId)
	{
		int state = -1;
		log.info("into ifPermission");
		// 通过用户id，获取权限
		if (_teamDao.selectPlatFormAdmin(userId) > 0)
		{
			state = 2;
			log.info("你是平台管理员！");
			return state;
		} else
		{
			log.info("你不是平台管理员！");
		}
		if (teamId <= 0)
		{
			// 判断用户是否为团队负责人，是的话，可进行团队首页查看
			if (_teamDao.selectCharge(userId, teamId) > 0)
			{
				state = 0;
				log.info("你是团队负责人！");
			} else
			{
				state = -1;
				log.info("你没有权限操作！");
			}
		} else
		{
			if (_teamDao.selectCharge(userId, teamId) > 0)
			{
				state = 1;
				log.info("你是该团队负责人！");
				return state;
			} else
				log.info("你不是该团队的负责人");
			state = permission(teamId, userId);
		}
		log.info("end ifPermission");
		return state;
	}
}
