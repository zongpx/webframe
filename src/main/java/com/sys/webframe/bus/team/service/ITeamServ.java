package com.sys.webframe.bus.team.service;

import java.util.List;
import java.util.Map;

import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.team.controller.fo.TeamRelAreaFo;
import com.sys.webframe.bus.team.controller.fo.TeamRelFo;
import com.sys.webframe.bus.team.controller.fo.TeamSelectFo;
import com.sys.webframe.bus.team.entity.Group;
import com.sys.webframe.bus.team.entity.TeamBean;

/**
 * @date 20170701
 * @author 冷文佩
 *
 */
public interface ITeamServ
{
	/**
	 * 某团队名下的该用户未关联的小区列表/获取未分配团队的小区列表/获取某用户名下关联的小区列表
	 * 
	 * @param teamFo
	 * @return
	 */
	List<Group> getAreaInfo(TeamSelectFo teamFo);

	/**
	 * 获取人员列表
	 * 
	 * @param teamFo
	 * @return
	 */
	List<User> getUserList(TeamSelectFo teamFo);

	/**
	 * 查询新建的团队等级，返回正数为正常，返回负数则不能新建
	 * 
	 * @param userId
	 * @return
	 */
	int getTeamLevel(int userId);
	
	/**
	 * 获取团队首页信息
	 * 
	 * @param teamFo
	 * @return
	 */
	List<TeamBean> getTeamPage(TeamSelectFo teamFo);

	/**
	 * 根据teamId显示下级团队情况
	 * 
	 * @param teamFo
	 * @return
	 */
	Map<String, Object> getTeamByTeamId(TeamSelectFo teamFo);

	/**
	 * 根据团队名称模糊匹配符合条件的团队信息
	 * 
	 * @param teamFo
	 * @return
	 */
	List<Map<String, TeamBean>> getTeamByDimSearch(TeamSelectFo teamFo);

	/**
	 * 新建团队
	 * 
	 * @param teamFo
	 * @return
	 */
	int addTeam(TeamSelectFo teamFo);

	/**
	 * 新增团员
	 * 
	 * @param teamFo
	 * @return
	 */
	int addTeamMember(TeamSelectFo teamFo);

	/**
	 * 删除团队
	 * 
	 * @param teamFo
	 * @return
	 */
	int removeTeam(TeamSelectFo teamFo);

	/**
	 * 修改团队名称
	 * 
	 * @param teamFo
	 * @return
	 */
	int modifyTeamName(TeamSelectFo teamFo);

	/**
	 * 变更团队负责人
	 * 
	 * @param teamFo
	 * @return
	 */
	int modifyTeamCharge(TeamSelectFo teamFo);

	/**
	 * 变更团队成员
	 * 
	 * @param teamRelFo
	 * @return
	 */
	int modifyTeamMember(TeamRelFo teamRelFo);

	/**
	 * 删除团员
	 * 
	 * @param teamRelFo
	 * @return
	 */
	int removeTeamMember(TeamRelFo teamRelFo);

	/**
	 * 团队关联小区
	 * 
	 * @param AreaFo
	 * @return
	 */
	int findAreaRel(TeamRelAreaFo AreaFo);

	/**
	 * 小区关联人员
	 * 
	 * @param teamFo
	 * @return
	 */
	int findAreaMemberRel(TeamRelFo teamFo);

}
