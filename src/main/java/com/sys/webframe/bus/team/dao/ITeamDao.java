package com.sys.webframe.bus.team.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.team.controller.fo.TeamSelectFo;
import com.sys.webframe.bus.team.entity.Group;
import com.sys.webframe.bus.team.entity.TeamBean;
import com.sys.webframe.bus.team.entity.TeamRelHouse;
import com.sys.webframe.bus.team.entity.TeamRelation;


/**
 * @author 冷文佩
 * @date 20170701
 */
@Component("_teamDao")
public interface ITeamDao
{

	/**
	 * 根据leaderId直接获取团队id
	 * 
	 * @param userId
	 * @return
	 */
	int selectTeamIdByUser(@Param("userId") int userId);

	/**
	 * 获取未关联团队的小区列表
	 * 
	 * @param teamId
	 * @return
	 */
	List<Group> selectAreaList(@Param("teamId") int teamId);

	/**
	 * 某团队名下的该用户未关联的小区列表
	 * 
	 * @param userId
	 * @param teamId
	 * @return
	 */
	List<Group> selectAreaByTeam(@Param("memberId") int memberId, @Param("teamId") int teamId);

	/**
	 * 用户名下关联的小区列表
	 * 
	 * @param userId
	 * @return
	 */
	List<Group> selectAreaByUser(@Param("memberId") int memberId);

	/**
	 * 某团队下关联的小区列表
	 * 
	 * @param teamId
	 * @return
	 */
	List<Group> selectAreaListByTeam(@Param("teamId") int teamId);

	/**
	 * 获取未分配团队的成员列表信息
	 * 
	 * @param teamFo
	 * @return
	 */
	List<User> selectUserList(TeamSelectFo teamFo);

	/**
	 * 获取团队下团员信息
	 * 
	 * @param teamFo
	 * @return
	 */
	List<User> selectUserListByTeam(TeamSelectFo teamFo);

	/**
	 * 获取团队列表/本身团队基本信息/子团队信息
	 * 
	 * @param teamFo
	 * @return
	 */
	List<TeamBean> selectTeamInfo(TeamSelectFo teamFo);

	/**
	 * 判断团队是否关联小区
	 * 
	 * @param teamId
	 * @return
	 */
	int selectIfRelArea(@Param("teamId") int teamId);

	/**
	 * 团队关联小区的房源数量
	 * 
	 * @param teamId
	 * @return
	 */
	int selectHouseCnt(@Param("teamId") int teamId);

	/**
	 * 根据所选团队，显示团队下的成员
	 * 
	 * @param teamId
	 * @return
	 */
	List<TeamRelation> selectMemberListById(@Param("teamId") int teamId);

	/**
	 * 判断人员是否关联小区
	 * 
	 * @param memberId
	 * @return
	 */
	int selectIfRelMember(@Param("memberId") int memberId);

	/**
	 * 人员关联小区对应的房源数量
	 * 
	 * @param memberId
	 * @return
	 */
	int selectMemberHCnt(@Param("memberId") int memberId);

	/**
	 * 根据传入的团队名称，模糊匹配出符合要求的团队名称和id
	 * 
	 * @param teamLevel
	 * @param teamName
	 * @return
	 */
	List<Map<String, Integer>> selectTeamIdList(@Param("teamLevel") int teamLevel, @Param("teamName") String teamName);

	/**
	 * List<Map<String,Integer>> selectUserIdList(@Param("teamLevel") int
	 * teamLevel,@Param("userName") String userName);
	 *
	 **/

	/**
	 * 是否是团队负责人
	 * 
	 * @param teamId
	 * @param userId
	 * @return
	 */
	int selectCharge(@Param("userId") int userId, @Param("teamId") int teamId);

	/**
	 * 找出这个团队的父团队id
	 * 
	 * @param teamId
	 * @return
	 */
	Map<String, Object> selectParentTeamId(@Param("teamId") int teamId);

	/**
	 * 获取负责人所在团队的团队深度
	 * 
	 * @param userId
	 * @return
	 */
	int selectTeamLevel(@Param("userId") int userId);

	/**
	 * 根据leaderId查询查询其下子团队的id和leaderId
	 * 
	 * @param leaderId
	 * @return
	 */
	List<Map<String, Object>> selectSubTeamIdsAndLeaderIds(@Param("teamId") int teamId);

	/**
	 * 判断团队名称是否唯一
	 * 
	 * @param name
	 * @return
	 */
	int selectUniName(@Param("name") String name, @Param("teamId") int teamId);

	/**
	 * 判断是否是平台管理员
	 * 
	 * @param userId
	 * @return
	 */
	int selectPlatFormAdmin(@Param("userId") int userId);

	/**
	 * 插入团队记录
	 * 
	 * @param team
	 * @return
	 */
	int insertTeam(TeamBean team);

	/**
	 * 插入团队关联记录
	 * 
	 * @param teamRel
	 * @return
	 */
	int insertTeamRelation(TeamRelation teamRel);

	/**
	 * 团队关联小区
	 * 
	 * @param relArea
	 * @return
	 */
	int insertAreaRel(TeamRelHouse relArea);

	/**
	 * 人员关联小区
	 * 
	 * @param teamRel
	 * @return
	 */
	int insertMemberRelArea(TeamRelation teamRel);

	/**
	 * 删除团队信息
	 * 
	 * @param team
	 * @return
	 */
	int deleteTeam(TeamBean team);

	/**
	 * 删除团队成员
	 * 
	 * @param userId
	 * @param teamId
	 * @return
	 */
	int deleteTeamMember(@Param("userId") int userId, @Param("teamId") int teamId);

	/**
	 * 删除团队与小区关联
	 * 
	 * @param relArea
	 * @return
	 */
	int deleteAreaRel(TeamRelHouse relArea);

	/**
	 * 删除人员与小区关联
	 * 
	 * @param memberRel
	 * @return
	 */
	int deleteMemberRelAreaList(TeamRelation memberRel);

	/**
	 * 更新团队名称
	 * 
	 * @param team
	 * @return
	 */
	int updateTeamName(TeamBean team);

	/**
	 * 变更团队负责人
	 * 
	 * @param team
	 * @return
	 */
	int updateTeamCharge(TeamBean team);

	/**
	 * 变更关联表里团队负责人的信息
	 * 
	 * @param team
	 * @return
	 */
	int updateTeamRel(TeamBean team);
	
	int updateChargeToNull(TeamBean team);
}
