package com.sys.webframe.bus.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.webframe.bus.team.controller.fo.TeamRelAreaFo;
import com.sys.webframe.bus.team.controller.fo.TeamRelFo;
import com.sys.webframe.bus.team.controller.fo.TeamSelectFo;
import com.sys.webframe.bus.team.service.ITeamServ;
import com.sys.webframe.core.support.BaseController;


@Controller
@RequestMapping("/caas/team")
public class TeamController extends BaseController
{
	@Autowired
	private ITeamServ _teamServImpl;

	/**
	 * 某团队名下的该用户未关联的小区列表/获取未分配团队的小区列表/获取某用户名下关联的小区列表
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/getAreaInfo")
	public @ResponseBody Object getAreaInfo(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into getAreaInfo");
		return _teamServImpl.getAreaInfo(teamFo);
	}

	/**
	 * 获取团队首页信息
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/getTeamPage")
	public @ResponseBody Object getTeamPage()
	{
		log.info("into getTeamPage");
		TeamSelectFo teamFo = new TeamSelectFo();
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.getTeamPage(teamFo);
	}

	/**
	 * 获取未分配团队的成员列表信息/该团队下成员列表
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/getUserList")
	public @ResponseBody Object getUserList(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into getUserList");
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.getUserList(teamFo);
	}

	/**
	 * 获取新建团队所属子团队
	 * 
	 * @return
	 */
	@RequestMapping("/getTeamLevel")
	public @ResponseBody Object getTeamLevel()
	{
		log.info("into getTeamLevel");
		return _teamServImpl.getTeamLevel(getUser().getUserId());
	}

	/**
	 * 根据teamId显示下级团队情况
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/getTeamByTeamId")
	public @ResponseBody Object getTeamByTeamId(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into getTeamByTeamId");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.getTeamByTeamId(teamFo);
	}

	/**
	 * 根据团队名称模糊匹配符合条件的团队信息
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/getTeamByDimSearch")
	public @ResponseBody Object getTeamByDimSearch(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into getTeamByDimSearch");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.getTeamByDimSearch(teamFo);
	}

	/**
	 * 新建团队
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/addTeam")
	public @ResponseBody Object addTeam(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into addTeam");
		teamFo.setTeamId(getUser().getTeamId());
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.addTeam(teamFo);
	}

	@RequestMapping("/addTeamMember")
	public @ResponseBody Object addTeamMember(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into addTeamMember");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.addTeamMember(teamFo);
	}

	/**
	 * 删除团队
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/removeTeam")
	public @ResponseBody Object removeTeam(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into removeTeam");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.removeTeam(teamFo);
	}

	/**
	 * 修改团队名称/变更团队负责人
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/modifyTeamName")
	public @ResponseBody Object modifyTeamName(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into modifyTeamName");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.modifyTeamName(teamFo);
	}

	/**
	 * 变更团队负责人
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/modifyTeamCharge")
	public @ResponseBody Object modifyTeamCharge(@RequestBody TeamSelectFo teamFo)
	{
		log.info("into modifyTeamCharge");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.modifyTeamCharge(teamFo);
	}

	/**
	 * 变更团队成员
	 * 
	 * @param teamRelFo
	 * @return
	 */
	@RequestMapping("/modifyTeamMember")
	public @ResponseBody Object modifyTeamMember(@RequestBody TeamRelFo teamRelFo)
	{
		log.info("into modifyTeamMember");
		teamRelFo.setUserId(getUser().getUserId());
		teamRelFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.modifyTeamMember(teamRelFo);
	}

	/**
	 * 删除团队成员
	 * 
	 * @param teamRelFo
	 * @return
	 */
	@RequestMapping("/removeTeamMember")
	public @ResponseBody Object removeTeamMember(@RequestBody TeamRelFo teamRelFo)
	{
		log.info("into removeTeamMember");
		teamRelFo.setUserId(getUser().getUserId());
		teamRelFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.removeTeamMember(teamRelFo);
	}

	/**
	 * 团队关联房源
	 * 
	 * @param areaFo
	 * @return
	 */
	@RequestMapping("/findAreaRel")
	public @ResponseBody Object findAreaRel(@RequestBody TeamRelAreaFo areaFo)
	{
		log.info("into findAreaRel");
		areaFo.setUserId(getUser().getUserId());
		areaFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.findAreaRel(areaFo);
	}

	/**
	 * 人员关联房源
	 * 
	 * @param teamFo
	 * @return
	 */
	@RequestMapping("/findAreaMemberRel")
	public @ResponseBody Object findAreaMemberRel(@RequestBody TeamRelFo teamFo)
	{
		log.info("into findAreaMemberRel");
		teamFo.setUserId(getUser().getUserId());
		teamFo.setTopTeamId(getUser().getTopTeamid());
		return _teamServImpl.findAreaMemberRel(teamFo);
	}

}
