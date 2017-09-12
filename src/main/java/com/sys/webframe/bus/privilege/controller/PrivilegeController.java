/**
 * date: 2017年7月5日
 */
package com.sys.webframe.bus.privilege.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.webframe.bus.privilege.PublicRoleBean;
import com.sys.webframe.bus.privilege.controller.fo.RoleFo;
import com.sys.webframe.bus.privilege.controller.fo.TeamFo;
import com.sys.webframe.bus.privilege.controller.fo.UserFo;
import com.sys.webframe.bus.privilege.entity.Menu;
import com.sys.webframe.bus.privilege.entity.Privilege;
import com.sys.webframe.bus.privilege.entity.Role;
import com.sys.webframe.bus.privilege.service.IPrivilegeServ;
import com.sys.webframe.bus.team.entity.TeamBean;
import com.sys.webframe.core.annotation.CaasAPI;
import com.sys.webframe.core.support.BaseController;


/**
 * @name: permissionController.java
 * @Description:
 * @author duanyongrui
 * @since: 2017年7月5日
 */
@Controller
@RequestMapping("caas/privilege")
public class PrivilegeController extends BaseController {

	@Autowired
	private IPrivilegeServ _privilegeServImpl;
	
/*	@RequestMapping("/reloadRoles")
	@CaasAPI(TREQMSG = "", TRSPMSG = "初始化成功", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object reloadPublicRoles() {
		log.info("reloadPublicRoles -- begin");
		try
		{
			PublicRoleBean bean = new PublicRoleBean();
			bean.initRoleMap();
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("reloadPublicRoles -- end");
		return "初始化成功";
	}*/

	/**
	 * 获取用户所有角色及菜单按钮的权限信息
	 * 
	 * @param userId
	 *            用户id
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/getUserPrivileges")
	@CaasAPI(TREQMSG = "", TRSPMSG = "[{\"id\":1,\"name\":\"系统管理员\",\"role_desc\":\"系统管理员\",\"create_id\":1,\"modify_id\":-1,\"modify_date\":\"2017-07-26 04:03:43\",\"team_id\":1,\"menus\":[{\"id\":22,\"isHave\":1,\"name\":\"银城千万间后台管理系统\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":1,\"visible\":1,\"submenus\":[{\"id\":2214,\"isHave\":1,\"name\":\"运营指标\",\"url\":\"html/home.html\",\"parent_menu_id\":22,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2206,\"isHave\":1,\"name\":\"业务款项\",\"url\":\"html/pages/report/finance.html\",\"parent_menu_id\":22,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2217,\"isHave\":1,\"name\":\"服务报表\",\"url\":\"html/pages/report/order_statistics.html\",\"parent_menu_id\":22,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":1,\"isHave\":1,\"name\":\"工作台\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":1,\"visible\":0,\"submenus\":[],\"buttons\":[]},{\"id\":9,\"isHave\":1,\"name\":\"系统权限管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":2,\"visible\":1,\"submenus\":[{\"id\":2226,\"isHave\":1,\"name\":\"用户管理1\",\"url\":\"html/yc/rm/caass/appserv/bus/user/pages/userInfo.html\",\"parent_menu_id\":9,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":10,\"isHave\":1,\"name\":\"菜单管理\",\"url\":\"html/pages/sys/menu.html\",\"parent_menu_id\":9,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":11,\"isHave\":1,\"name\":\"权限管理\",\"url\":\"html/pages/sys/power.html\",\"parent_menu_id\":9,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":13,\"isHave\":1,\"name\":\"用户管理\",\"url\":\"html/pages/sys/account/account.html\",\"parent_menu_id\":9,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":19,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"html/pages/sys/t_grid/t_gridList.html\",\"parent_menu_id\":9,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":19,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"\",\"menu_id\":19,\"seq\":0,\"visible\":1}]},{\"id\":2229,\"isHave\":1,\"name\":\"权限管理dyr\",\"url\":\"html/yc/rm/caass/appserv/base/privilege/pages/privilege.html\",\"parent_menu_id\":9,\"seq\":5,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2228,\"isHave\":1,\"name\":\"团队管理\",\"url\":\"html/yc/rm/caass/appserv/bus/team/pages/teamInfo.html\",\"parent_menu_id\":9,\"seq\":6,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2224,\"isHave\":1,\"name\":\"团队管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":3,\"visible\":0,\"submenus\":[],\"buttons\":[]},{\"id\":2210,\"isHave\":1,\"name\":\"营销管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":3,\"visible\":1,\"submenus\":[{\"id\":2212,\"isHave\":1,\"name\":\"卡券管理\",\"url\":\"html/pages/coupon/coupon.html\",\"parent_menu_id\":2210,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2,\"isHave\":1,\"name\":\"账务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":3,\"visible\":1,\"submenus\":[{\"id\":5,\"isHave\":1,\"name\":\"财务项目\",\"url\":\"html/pages/finance/project.html\",\"parent_menu_id\":2,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2367,\"isHave\":1,\"name\":\"财务明细新增\",\"url\":\"financial/mxSave.do\",\"menu_id\":5,\"seq\":0,\"visible\":1}]},{\"id\":3,\"isHave\":1,\"name\":\"出账\",\"url\":\"html/pages/finance/expend.html\",\"parent_menu_id\":2,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":4,\"isHave\":1,\"name\":\"入账\",\"url\":\"html/pages/finance/income.html\",\"parent_menu_id\":2,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":12,\"isHave\":1,\"name\":\"财务类目\",\"url\":\"html/pages/finance/type.html\",\"parent_menu_id\":2,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":7,\"isHave\":1,\"name\":\"市场部\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":4,\"visible\":1,\"submenus\":[{\"id\":8,\"isHave\":1,\"name\":\"房源管理\",\"url\":\"html/pages/house/houseInfo/baseHouse.html\",\"parent_menu_id\":7,\"seq\":1,\"visible\":1,\"submenus\":[{\"id\":18,\"isHave\":1,\"name\":\"银行列表查询\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":8,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":419,\"isHave\":1,\"name\":\"获取银行\",\"url\":\"agreementMge/bankList.do\",\"menu_id\":18,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":418,\"isHave\":1,\"name\":\"获取材料\",\"url\":\"sys/queryMcate.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2329,\"isHave\":1,\"name\":\"验证出租房源是否签约\",\"url\":\"houserank/checkRankHouseStatus.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2332,\"isHave\":1,\"name\":\"审批房源\",\"url\":\"BaseHouse/approvalHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2333,\"isHave\":1,\"name\":\"交接房源\",\"url\":\"BaseHouse/houseTransfer.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2328,\"isHave\":1,\"name\":\"查询出租房源信息\",\"url\":\"BaseHouse/seeRentHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2331,\"isHave\":1,\"name\":\"发布审批\",\"url\":\"BaseHouse/approvalPublish.do\",\"menu_id\":8,\"seq\":0,\"visible\":1}]},{\"id\":2209,\"isHave\":1,\"name\":\"房源抓取信息\",\"url\":\"html/pages/house/spiderinfo/spiderInfo.html\",\"parent_menu_id\":7,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":6,\"isHave\":1,\"name\":\"小区管理\",\"url\":\"html/pages/house/group/groupIndex.html\",\"parent_menu_id\":7,\"seq\":8,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1410,\"isHave\":1,\"name\":\"区域选择\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":7,\"seq\":10,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2215,\"isHave\":1,\"name\":\"合约管理\",\"url\":\"html/pages/house/agreement/newAgreementList.html\",\"parent_menu_id\":7,\"seq\":14,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":15,\"isHave\":1,\"name\":\"工程管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":5,\"visible\":0,\"submenus\":[{\"id\":1501,\"isHave\":1,\"name\":\"施工监理\",\"url\":\"/Onstruction_Panel\",\"parent_menu_id\":15,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1502,\"isHave\":1,\"name\":\"供应商管理\",\"url\":\"/Supplier_Panel\",\"parent_menu_id\":15,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1503,\"isHave\":1,\"name\":\"材料清单\",\"url\":\"/PurchaserMaterial/PurchaserMaterial\",\"parent_menu_id\":15,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1504,\"isHave\":1,\"name\":\"采购管理\",\"url\":\"/PurchaseItem/PurchaseItem\",\"parent_menu_id\":15,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1505,\"isHave\":1,\"name\":\"出库管理\",\"url\":\"/material/Materialout_Panel\",\"parent_menu_id\":15,\"seq\":5,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1506,\"isHave\":1,\"name\":\"退库管理\",\"url\":\"/material/Materialback_Panel\",\"parent_menu_id\":15,\"seq\":6,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":16,\"isHave\":1,\"name\":\"服务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":6,\"visible\":1,\"submenus\":[{\"id\":1601,\"isHave\":1,\"name\":\"服务菜单\",\"url\":\"html/pages/order_flow/order_service_list.html\",\"parent_menu_id\":16,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":1601,\"isHave\":1,\"name\":\"订单查询\",\"url\":\"/Order/getList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1609,\"isHave\":1,\"name\":\"订单撤销支付\",\"url\":\"/Order/repealOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1607,\"isHave\":1,\"name\":\"订单状态报表\",\"url\":\"/Order/orderReport.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1604,\"isHave\":1,\"name\":\"订单派单\",\"url\":\"/Order/dispatch.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1602,\"isHave\":1,\"name\":\"订单新增\",\"url\":\"/Order/createOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":2358,\"isHave\":1,\"name\":\"获取管家\",\"url\":\"/sys/getMangerList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1608,\"isHave\":1,\"name\":\"订单类型报表\",\"url\":\"/Order/typeReport.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1605,\"isHave\":1,\"name\":\"关闭订单\",\"url\":\"/Order/closeOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1603,\"isHave\":1,\"name\":\"订单修改\",\"url\":\"/Order/updateOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1}]},{\"id\":26,\"isHave\":1,\"name\":\"售后查询\",\"url\":\"html/pages/order_flow/order_customer_list.html\",\"parent_menu_id\":16,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":26,\"isHave\":1,\"name\":\"售后查询\",\"url\":\"\",\"menu_id\":26,\"seq\":0,\"visible\":1}]}],\"buttons\":[]},{\"id\":17,\"isHave\":1,\"name\":\"合同结算\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":7,\"visible\":0,\"submenus\":[{\"id\":1701,\"isHave\":1,\"name\":\"订单管理\",\"url\":\"/Order_Panel\",\"parent_menu_id\":17,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1702,\"isHave\":1,\"name\":\"合约管理\",\"url\":\"/agreement/Agreement\",\"parent_menu_id\":17,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1703,\"isHave\":1,\"name\":\"供应商合约\",\"url\":\"no_panel\",\"parent_menu_id\":17,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1704,\"isHave\":1,\"name\":\"结算中心\",\"url\":\"/agreement/Settlement\",\"parent_menu_id\":17,\"seq\":5,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":28,\"name\":\"供应商\",\"role_desc\":\"供应商\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":23,\"name\":\"商务助理\",\"role_desc\":\"负责合约审核，结算，收入支出等信息维护\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":2,\"isHave\":1,\"name\":\"账务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":3,\"visible\":1,\"submenus\":[{\"id\":5,\"isHave\":1,\"name\":\"财务项目\",\"url\":\"html/pages/finance/project.html\",\"parent_menu_id\":2,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":3,\"isHave\":1,\"name\":\"出账\",\"url\":\"html/pages/finance/expend.html\",\"parent_menu_id\":2,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":4,\"isHave\":1,\"name\":\"入账\",\"url\":\"html/pages/finance/income.html\",\"parent_menu_id\":2,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]}]},{\"id\":27,\"name\":\"客服\",\"role_desc\":\"客服服务，负责对租客，业主进行全方位服务跟踪协调\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":7,\"isHave\":1,\"name\":\"市场部\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":4,\"visible\":1,\"submenus\":[{\"id\":2209,\"isHave\":1,\"name\":\"房源抓取信息\",\"url\":\"html/pages/house/spiderinfo/spiderInfo.html\",\"parent_menu_id\":7,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1410,\"isHave\":1,\"name\":\"区域选择\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":7,\"seq\":10,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1}]},{\"id\":16,\"isHave\":1,\"name\":\"服务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":6,\"visible\":1,\"submenus\":[{\"id\":1601,\"isHave\":1,\"name\":\"服务菜单\",\"url\":\"html/pages/order_flow/order_service_list.html\",\"parent_menu_id\":16,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2358,\"isHave\":1,\"name\":\"获取管家\",\"url\":\"/sys/getMangerList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1608,\"isHave\":1,\"name\":\"订单类型报表\",\"url\":\"/Order/typeReport.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1605,\"isHave\":1,\"name\":\"关闭订单\",\"url\":\"/Order/closeOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1602,\"isHave\":1,\"name\":\"订单新增\",\"url\":\"/Order/createOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1601,\"isHave\":1,\"name\":\"订单查询\",\"url\":\"/Order/getList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1609,\"isHave\":1,\"name\":\"订单撤销支付\",\"url\":\"/Order/repealOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1607,\"isHave\":1,\"name\":\"订单状态报表\",\"url\":\"/Order/orderReport.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1604,\"isHave\":1,\"name\":\"订单派单\",\"url\":\"/Order/dispatch.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1}]},{\"id\":26,\"isHave\":1,\"name\":\"售后查询\",\"url\":\"html/pages/order_flow/order_customer_list.html\",\"parent_menu_id\":16,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":26,\"isHave\":1,\"name\":\"售后查询\",\"url\":\"\",\"menu_id\":26,\"seq\":0,\"visible\":1}]}],\"buttons\":[]},{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":26,\"name\":\"工程管家\",\"role_desc\":\"负责房源改造，维护人员，需要对收房进行预算排期跟踪等\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":22,\"name\":\"市场管家\",\"role_desc\":\"负责收房，出租，业主与租客一条龙服务跟踪\",\"create_id\":1,\"modify_id\":-1,\"modify_date\":\"2017-07-26 04:03:43\",\"team_id\":1,\"menus\":[{\"id\":7,\"isHave\":1,\"name\":\"市场部\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":4,\"visible\":1,\"submenus\":[{\"id\":8,\"isHave\":1,\"name\":\"房源管理\",\"url\":\"html/pages/house/houseInfo/baseHouse.html\",\"parent_menu_id\":7,\"seq\":1,\"visible\":1,\"submenus\":[{\"id\":18,\"isHave\":1,\"name\":\"银行列表查询\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":8,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":418,\"isHave\":1,\"name\":\"获取材料\",\"url\":\"sys/queryMcate.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2329,\"isHave\":1,\"name\":\"验证出租房源是否签约\",\"url\":\"houserank/checkRankHouseStatus.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2332,\"isHave\":1,\"name\":\"审批房源\",\"url\":\"BaseHouse/approvalHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2333,\"isHave\":1,\"name\":\"交接房源\",\"url\":\"BaseHouse/houseTransfer.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2328,\"isHave\":1,\"name\":\"查询出租房源信息\",\"url\":\"BaseHouse/seeRentHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2331,\"isHave\":1,\"name\":\"发布审批\",\"url\":\"BaseHouse/approvalPublish.do\",\"menu_id\":8,\"seq\":0,\"visible\":1}]},{\"id\":2209,\"isHave\":1,\"name\":\"房源抓取信息\",\"url\":\"html/pages/house/spiderinfo/spiderInfo.html\",\"parent_menu_id\":7,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":6,\"isHave\":1,\"name\":\"小区管理\",\"url\":\"html/pages/house/group/groupIndex.html\",\"parent_menu_id\":7,\"seq\":8,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1410,\"isHave\":1,\"name\":\"区域选择\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":7,\"seq\":10,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2215,\"isHave\":1,\"name\":\"合约管理\",\"url\":\"html/pages/house/agreement/newAgreementList.html\",\"parent_menu_id\":7,\"seq\":14,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":25,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"html/pages/sys/t_grid/t_gridList.html\",\"parent_menu_id\":7,\"seq\":15,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":25,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"\",\"menu_id\":25,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1}]},{\"id\":16,\"isHave\":1,\"name\":\"服务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":6,\"visible\":1,\"submenus\":[{\"id\":1601,\"isHave\":1,\"name\":\"服务菜单\",\"url\":\"html/pages/order_flow/order_service_list.html\",\"parent_menu_id\":16,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":1601,\"isHave\":1,\"name\":\"订单查询\",\"url\":\"/Order/getList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1}]}],\"buttons\":[]},{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":24,\"name\":\"高管\",\"role_desc\":\"对所有信息监管，查询报表等\",\"create_id\":1,\"modify_id\":-1,\"modify_date\":\"2017-07-26 04:03:43\",\"team_id\":1,\"menus\":[{\"id\":22,\"isHave\":1,\"name\":\"银城千万间后台管理系统\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":1,\"visible\":1,\"submenus\":[{\"id\":2214,\"isHave\":1,\"name\":\"运营指标\",\"url\":\"html/home.html\",\"parent_menu_id\":22,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2206,\"isHave\":1,\"name\":\"业务款项\",\"url\":\"html/pages/report/finance.html\",\"parent_menu_id\":22,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2217,\"isHave\":1,\"name\":\"服务报表\",\"url\":\"html/pages/report/order_statistics.html\",\"parent_menu_id\":22,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":1,\"isHave\":1,\"name\":\"工作台\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":1,\"visible\":0,\"submenus\":[],\"buttons\":[{\"id\":1,\"isHave\":1,\"name\":\"1\",\"url\":\"financial/project/getProjectList.do\",\"menu_id\":1,\"seq\":0,\"visible\":1}]},{\"id\":9,\"isHave\":1,\"name\":\"系统权限管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":2,\"visible\":1,\"submenus\":[{\"id\":2226,\"isHave\":1,\"name\":\"用户管理1\",\"url\":\"html/yc/rm/caass/appserv/bus/user/pages/userInfo.html\",\"parent_menu_id\":9,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":19,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"html/pages/sys/t_grid/t_gridList.html\",\"parent_menu_id\":9,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":19,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"\",\"menu_id\":19,\"seq\":0,\"visible\":1}]},{\"id\":2228,\"isHave\":1,\"name\":\"团队管理\",\"url\":\"html/yc/rm/caass/appserv/bus/team/pages/teamInfo.html\",\"parent_menu_id\":9,\"seq\":6,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2,\"isHave\":1,\"name\":\"账务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":3,\"visible\":1,\"submenus\":[{\"id\":5,\"isHave\":1,\"name\":\"财务项目\",\"url\":\"html/pages/finance/project.html\",\"parent_menu_id\":2,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":13,\"isHave\":1,\"name\":\"财务项目删除\",\"url\":\"financial/project/deleteProject.do\",\"menu_id\":5,\"seq\":0,\"visible\":1},{\"id\":13,\"isHave\":1,\"name\":\"财务项目删除\",\"url\":\"financial/project/deleteProject.do\",\"menu_id\":5,\"seq\":0,\"visible\":1}]},{\"id\":3,\"isHave\":1,\"name\":\"出账\",\"url\":\"html/pages/finance/expend.html\",\"parent_menu_id\":2,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":4,\"isHave\":1,\"name\":\"入账\",\"url\":\"html/pages/finance/income.html\",\"parent_menu_id\":2,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":12,\"isHave\":1,\"name\":\"财务类目\",\"url\":\"html/pages/finance/type.html\",\"parent_menu_id\":2,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":12,\"isHave\":1,\"name\":\"1\",\"url\":\"\",\"menu_id\":12,\"seq\":0,\"visible\":1}]}],\"buttons\":[]},{\"id\":7,\"isHave\":1,\"name\":\"市场部\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":4,\"visible\":1,\"submenus\":[{\"id\":8,\"isHave\":1,\"name\":\"房源管理\",\"url\":\"html/pages/house/houseInfo/baseHouse.html\",\"parent_menu_id\":7,\"seq\":1,\"visible\":1,\"submenus\":[{\"id\":18,\"isHave\":1,\"name\":\"银行列表查询\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":8,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":419,\"isHave\":1,\"name\":\"获取银行\",\"url\":\"agreementMge/bankList.do\",\"menu_id\":18,\"seq\":0,\"visible\":1},{\"id\":419,\"isHave\":1,\"name\":\"获取银行\",\"url\":\"agreementMge/bankList.do\",\"menu_id\":18,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":203,\"isHave\":1,\"name\":\"保存收房信息\",\"url\":\"BaseHouse/saveHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":415,\"isHave\":1,\"name\":\"设置或取消精品\",\"url\":\"BaseHouse/rankIstop.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":201,\"isHave\":1,\"name\":\"收房列表查询\",\"url\":\"BaseHouse/getHouseList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":416,\"isHave\":1,\"name\":\"下架或上架\",\"url\":\"BaseHouse/soldOut.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":205,\"isHave\":1,\"name\":\"获取管家列表信息\",\"url\":\"BaseHouse/getMangerList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":418,\"isHave\":1,\"name\":\"获取材料\",\"url\":\"sys/queryMcate.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":206,\"isHave\":1,\"name\":\"提交房源到待审核\",\"url\":\"BaseHouse/submitHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":203,\"isHave\":1,\"name\":\"保存收房信息\",\"url\":\"BaseHouse/saveHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2329,\"isHave\":1,\"name\":\"验证出租房源是否签约\",\"url\":\"houserank/checkRankHouseStatus.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":204,\"isHave\":1,\"name\":\"删除房屋信息\",\"url\":\"BaseHouse/deleteHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":201,\"isHave\":1,\"name\":\"收房列表查询\",\"url\":\"BaseHouse/getHouseList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2332,\"isHave\":1,\"name\":\"审批房源\",\"url\":\"BaseHouse/approvalHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":202,\"isHave\":1,\"name\":\"查询收房明细\",\"url\":\"BaseHouse/loadHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":416,\"isHave\":1,\"name\":\"下架或上架\",\"url\":\"BaseHouse/soldOut.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":416,\"isHave\":1,\"name\":\"下架或上架\",\"url\":\"BaseHouse/soldOut.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":205,\"isHave\":1,\"name\":\"获取管家列表信息\",\"url\":\"BaseHouse/getMangerList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":206,\"isHave\":1,\"name\":\"提交房源到待审核\",\"url\":\"BaseHouse/submitHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":203,\"isHave\":1,\"name\":\"保存收房信息\",\"url\":\"BaseHouse/saveHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":204,\"isHave\":1,\"name\":\"删除房屋信息\",\"url\":\"BaseHouse/deleteHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":201,\"isHave\":1,\"name\":\"收房列表查询\",\"url\":\"BaseHouse/getHouseList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":202,\"isHave\":1,\"name\":\"查询收房明细\",\"url\":\"BaseHouse/loadHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":416,\"isHave\":1,\"name\":\"下架或上架\",\"url\":\"BaseHouse/soldOut.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":206,\"isHave\":1,\"name\":\"提交房源到待审核\",\"url\":\"BaseHouse/submitHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":415,\"isHave\":1,\"name\":\"设置或取消精品\",\"url\":\"BaseHouse/rankIstop.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":204,\"isHave\":1,\"name\":\"删除房屋信息\",\"url\":\"BaseHouse/deleteHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2328,\"isHave\":1,\"name\":\"查询出租房源信息\",\"url\":\"BaseHouse/seeRentHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":205,\"isHave\":1,\"name\":\"获取管家列表信息\",\"url\":\"BaseHouse/getMangerList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":202,\"isHave\":1,\"name\":\"查询收房明细\",\"url\":\"BaseHouse/loadHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2331,\"isHave\":1,\"name\":\"发布审批\",\"url\":\"BaseHouse/approvalPublish.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":203,\"isHave\":1,\"name\":\"保存收房信息\",\"url\":\"BaseHouse/saveHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":415,\"isHave\":1,\"name\":\"设置或取消精品\",\"url\":\"BaseHouse/rankIstop.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":201,\"isHave\":1,\"name\":\"收房列表查询\",\"url\":\"BaseHouse/getHouseList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":206,\"isHave\":1,\"name\":\"提交房源到待审核\",\"url\":\"BaseHouse/submitHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":415,\"isHave\":1,\"name\":\"设置或取消精品\",\"url\":\"BaseHouse/rankIstop.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":204,\"isHave\":1,\"name\":\"删除房屋信息\",\"url\":\"BaseHouse/deleteHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":205,\"isHave\":1,\"name\":\"获取管家列表信息\",\"url\":\"BaseHouse/getMangerList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":202,\"isHave\":1,\"name\":\"查询收房明细\",\"url\":\"BaseHouse/loadHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1}]},{\"id\":6,\"isHave\":1,\"name\":\"小区管理\",\"url\":\"html/pages/house/group/groupIndex.html\",\"parent_menu_id\":7,\"seq\":8,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":228,\"isHave\":1,\"name\":\"小区房屋报表\",\"url\":\"group/queryHouseFlow.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":228,\"isHave\":1,\"name\":\"小区房屋报表\",\"url\":\"group/queryHouseFlow.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":228,\"isHave\":1,\"name\":\"小区房屋报表\",\"url\":\"group/queryHouseFlow.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":228,\"isHave\":1,\"name\":\"小区房屋报表\",\"url\":\"group/queryHouseFlow.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1}]},{\"id\":1410,\"isHave\":1,\"name\":\"区域选择\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":7,\"seq\":10,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2215,\"isHave\":1,\"name\":\"合约管理\",\"url\":\"html/pages/house/agreement/newAgreementList.html\",\"parent_menu_id\":7,\"seq\":14,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1},{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1},{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1}]},{\"id\":15,\"isHave\":1,\"name\":\"工程管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":5,\"visible\":0,\"submenus\":[{\"id\":1501,\"isHave\":1,\"name\":\"施工监理\",\"url\":\"/Onstruction_Panel\",\"parent_menu_id\":15,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1502,\"isHave\":1,\"name\":\"供应商管理\",\"url\":\"/Supplier_Panel\",\"parent_menu_id\":15,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1503,\"isHave\":1,\"name\":\"材料清单\",\"url\":\"/PurchaserMaterial/PurchaserMaterial\",\"parent_menu_id\":15,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1504,\"isHave\":1,\"name\":\"采购管理\",\"url\":\"/PurchaseItem/PurchaseItem\",\"parent_menu_id\":15,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1505,\"isHave\":1,\"name\":\"出库管理\",\"url\":\"/material/Materialout_Panel\",\"parent_menu_id\":15,\"seq\":5,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1506,\"isHave\":1,\"name\":\"退库管理\",\"url\":\"/material/Materialback_Panel\",\"parent_menu_id\":15,\"seq\":6,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":16,\"isHave\":1,\"name\":\"服务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":6,\"visible\":1,\"submenus\":[{\"id\":1601,\"isHave\":1,\"name\":\"服务菜单\",\"url\":\"html/pages/order_flow/order_service_list.html\",\"parent_menu_id\":16,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":1607,\"isHave\":1,\"name\":\"订单状态报表\",\"url\":\"/Order/orderReport.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1604,\"isHave\":1,\"name\":\"订单派单\",\"url\":\"/Order/dispatch.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1602,\"isHave\":1,\"name\":\"订单新增\",\"url\":\"/Order/createOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1602,\"isHave\":1,\"name\":\"订单新增\",\"url\":\"/Order/createOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1602,\"isHave\":1,\"name\":\"订单新增\",\"url\":\"/Order/createOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":2358,\"isHave\":1,\"name\":\"获取管家\",\"url\":\"/sys/getMangerList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1608,\"isHave\":1,\"name\":\"订单类型报表\",\"url\":\"/Order/typeReport.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1605,\"isHave\":1,\"name\":\"关闭订单\",\"url\":\"/Order/closeOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1603,\"isHave\":1,\"name\":\"订单修改\",\"url\":\"/Order/updateOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1603,\"isHave\":1,\"name\":\"订单修改\",\"url\":\"/Order/updateOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1603,\"isHave\":1,\"name\":\"订单修改\",\"url\":\"/Order/updateOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1601,\"isHave\":1,\"name\":\"订单查询\",\"url\":\"/Order/getList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1},{\"id\":1609,\"isHave\":1,\"name\":\"订单撤销支付\",\"url\":\"/Order/repealOrder.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1}]}],\"buttons\":[]},{\"id\":17,\"isHave\":1,\"name\":\"合同结算\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":7,\"visible\":0,\"submenus\":[{\"id\":1701,\"isHave\":1,\"name\":\"订单管理\",\"url\":\"/Order_Panel\",\"parent_menu_id\":17,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1702,\"isHave\":1,\"name\":\"合约管理\",\"url\":\"/agreement/Agreement\",\"parent_menu_id\":17,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":227,\"isHave\":1,\"name\":\"查看房屋配置\",\"url\":\"sys/queryMcate.do\",\"menu_id\":1702,\"seq\":0,\"visible\":1},{\"id\":227,\"isHave\":1,\"name\":\"查看房屋配置\",\"url\":\"sys/queryMcate.do\",\"menu_id\":1702,\"seq\":0,\"visible\":1},{\"id\":227,\"isHave\":1,\"name\":\"查看房屋配置\",\"url\":\"sys/queryMcate.do\",\"menu_id\":1702,\"seq\":0,\"visible\":1},{\"id\":227,\"isHave\":1,\"name\":\"查看房屋配置\",\"url\":\"sys/queryMcate.do\",\"menu_id\":1702,\"seq\":0,\"visible\":1}]},{\"id\":1703,\"isHave\":1,\"name\":\"供应商合约\",\"url\":\"no_panel\",\"parent_menu_id\":17,\"seq\":4,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":1704,\"isHave\":1,\"name\":\"结算中心\",\"url\":\"/agreement/Settlement\",\"parent_menu_id\":17,\"seq\":5,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":30,\"name\":\"财务\",\"role_desc\":\"财务中心\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[]},{\"id\":2,\"name\":\"测试角色\",\"role_desc\":\"账号用于测试使用\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":2218,\"isHave\":1,\"name\":\"临时合约管理\",\"url\":\"html/pages/house/agreement/rankAgreementList.html\",\"parent_menu_id\":0,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2371,\"isHave\":1,\"name\":\"审批\",\"url\":\"rankHouse/approvalAreement.do\",\"menu_id\":2218,\"seq\":0,\"visible\":1}]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":33,\"name\":\"租务\",\"role_desc\":\"\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":16,\"isHave\":1,\"name\":\"服务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":6,\"visible\":1,\"submenus\":[{\"id\":1601,\"isHave\":1,\"name\":\"服务菜单\",\"url\":\"html/pages/order_flow/order_service_list.html\",\"parent_menu_id\":16,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":1601,\"isHave\":1,\"name\":\"订单查询\",\"url\":\"/Order/getList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1}]},{\"id\":26,\"isHave\":1,\"name\":\"售后查询\",\"url\":\"html/pages/order_flow/order_customer_list.html\",\"parent_menu_id\":16,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":26,\"isHave\":1,\"name\":\"售后查询\",\"url\":\"\",\"menu_id\":26,\"seq\":0,\"visible\":1}]}],\"buttons\":[]},{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]},{\"id\":34,\"name\":\"业务合作\",\"role_desc\":\"财务中心收入、支出审批操作\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[]},{\"id\":35,\"name\":\"运营\",\"role_desc\":\"\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":[{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]}]}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object getUserPrivileges(@RequestBody UserFo userFo) {
		// 需要先判断用户是否有权限查看userId的权限信息
		int login_id = super.getUser().getUserId();
		boolean check = _privilegeServImpl
				.checkOperationPermissionToOtherUserId(login_id, userFo.getUserId());
		if (check == false) {
			// 无查看权限时返回
			return "无操作权限";
		}
		if (userFo.getUserId() <= 0) {
			userFo.setUserId(login_id);
		}
		List<Role> roles = new ArrayList<Role>();
		List<Role> temp = _privilegeServImpl.getUserRoles(userFo.getUserId());
		for (Iterator<Role> iterator = temp.iterator(); iterator.hasNext();)
		{
			Role role = iterator.next();
			roles.add(PublicRoleBean.getRole(role.getId()));
		}
		return roles;
	}

	/**
	 * 获取团队的角色
	 * 
	 * @param teamId
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/getTeamRoles")
	@CaasAPI(TREQMSG = "{id: \"1\"}", TRSPMSG = "{\"success\":1,\"method\":\"/caas/privilege/getTeamRoles\",\"time\":1500797593,\"parameter\":{\"id\":1},\"result\":[{\"id\":1,\"name\":\"系统管理员\",\"role_desc\":\"系统管理员\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":2,\"name\":\"测试角色\",\"role_desc\":\"账号用于测试使用\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":22,\"name\":\"市场管家\",\"role_desc\":\"负责收房，出租，业主与租客一条龙服务跟踪\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":23,\"name\":\"商务助理\",\"role_desc\":\"负责合约审核，结算，收入支出等信息维护\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":24,\"name\":\"高管\",\"role_desc\":\"对所有信息监管，查询报表等\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":25,\"name\":\"平台管理员\",\"role_desc\":\"用于研发团队维护系统，代理系统管理员\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":26,\"name\":\"工程管家\",\"role_desc\":\"负责房源改造，维护人员，需要对收房进行预算排期跟踪等\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":27,\"name\":\"客服\",\"role_desc\":\"客服服务，负责对租客，业主进行全方位服务跟踪协调\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":28,\"name\":\"供应商\",\"role_desc\":\"供应商\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":29,\"name\":\"售后\",\"role_desc\":\"\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":30,\"name\":\"财务\",\"role_desc\":\"财务中心\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":31,\"name\":\"财务明细录入\",\"role_desc\":\"财务明细录入\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":33,\"name\":\"租务\",\"role_desc\":\"\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":34,\"name\":\"业务合作\",\"role_desc\":\"财务中心收入、支出审批操作\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null},{\"id\":35,\"name\":\"运营\",\"role_desc\":\"\",\"create_id\":-1,\"modify_id\":-1,\"modify_date\":\"2017-07-17 11:15:57\",\"team_id\":1,\"menus\":null}]}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object getTeamRoles(@RequestBody TeamFo teamFo) {
		log.debug("getTeamRoles ----  begin");
		// 校验登录人权限
		int login_id = super.getUser().getUserId();
		boolean check = _privilegeServImpl.checkOperationTeam(login_id,
				teamFo.getTeamId());
		if (check == false) {
			return "无操作权限";
		}
		List<Role> roles = _privilegeServImpl.getRolesByTeamId(teamFo.getTeamId());
		log.debug("getTeamRoles ----  end ----"+roles.toString());
		return roles;
	}

	/**
	 * 修改角色信息
	 * 
	 * @param role
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/modifyRole")
	@CaasAPI(TREQMSG = "", TRSPMSG = "", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object modifyRole(@RequestBody Role role) {
		// 校验登录人权限
		int login_id = super.getUser().getUserId();
		boolean check = _privilegeServImpl.checkOperationRole(login_id,
				role.getId());
		if (check == false) {
			return "无操作权限";
		}
		role.setModify_id(login_id);
		if (role.getName() != null && !(role.getName().equals(""))) {
			role.setName(role.getName());
		}
		log.debug(PublicRoleBean.getRoleMap().values().toString());
		Collection<Role> roles = PublicRoleBean.getRoleMap().values();
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();)
		{
			Role role2 = iterator.next();
			if (role2.getName() == role.getName() && role2.getId() != role.getId()) {
				return "角色名称重复";
			}
		}
		boolean flag = _privilegeServImpl.modifyRole(role);
		if (flag) {
			// 修改内存中角色数
			Role roleBean = PublicRoleBean.getRoleMap().get(role.getId());
			Role newRole = _privilegeServImpl.getRoleById(role.getId());
			roleBean.setModify_id(newRole.getModify_id());
			roleBean.setName(newRole.getName());
			roleBean.setRole_desc(newRole.getRole_desc());
			roleBean.setModify_date(newRole.getModify_date());
			return "操作成功";
		}
		return "操作失败";
	}

	/**
	 * 修改某一个角色的权限
	 * 
	 * @param rolePrivileges
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/modifyRolePrivileges")
	@CaasAPI(TREQMSG = "[{m_value: 40, access: \"menu_id\", a_value: \"1\"}, {m_value: 40, access: \"menu_id\", a_value: \"2\"}]", TRSPMSG = "{\"success\":1,\"method\":\"/caas/privilege/modifyRolePrivileges\",\"time\":1500862570,\"parameter\":[{\"id\":0,\"master\":\"role_id\",\"m_value\":40,\"access\":\"menu_id\",\"a_value\":1,\"oper\":\"enabled\"},{\"id\":0,\"master\":\"role_id\",\"m_value\":40,\"access\":\"menu_id\",\"a_value\":2,\"oper\":\"enabled\"}],\"result\":\"操作成功\"}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object modifyRolePrivileges(@RequestBody List<Privilege> rolePrivileges) {
		if (rolePrivileges.size() <= 0) {
			return "操作失败";
		}
		if (rolePrivileges.get(0).getMaster() == "role_id") {
			int role_id = rolePrivileges.get(0).getM_value();
			for (Iterator<Privilege> iterator = rolePrivileges.iterator(); iterator
					.hasNext();) {
				Privilege rolePrivilege = iterator.next();
				if (rolePrivilege.getM_value() != role_id) {
					return "权限的角色id不统一";
				}
			}
			// 校验用户对角色是否有操作权限
			int login_id = super.getUser().getUserId();
			boolean check = _privilegeServImpl.checkOperationRole(login_id,
					role_id);
			if (check == false) {
				return "操作失败";
			}
			boolean addFlag = _privilegeServImpl.addPrivileges(rolePrivileges);
			log.debug("addFlag     ** ** ** ** **    " + addFlag);
			if (addFlag) {
				PublicRoleBean.getRoleMap().get(role_id).setMenus(_privilegeServImpl.getMenuByRoleId(role_id));
				return "操作成功";
			}
		}
		return "操作失败";
	}

	/**
	 * 为团队创建角色
	 * 
	 * @param roles
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/addRoles")
	@CaasAPI(TREQMSG = "[{name: \"测试\", team_id: \"1\", role_desc: \"\"}]", TRSPMSG = "{\"success\":1,\"method\":\"/caas/privilege/addRoles\",\"time\":1500859733,\"parameter\":[{\"id\":40,\"name\":\"测试\",\"role_desc\":\"\",\"create_id\":545454,\"modify_id\":545454,\"modify_date\":\"2017-07-24 01:28:53\",\"team_id\":1,\"menus\":null}],\"result\":[40]}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object addRole(@RequestBody List<Role> roles) {
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();) {
			Role role = iterator.next();
			if (role.getName() != null && !(role.getName().equals(""))) {
				role.setName(role.getName());
			}
			// 校验用户对角色是否有操作权限
			int login_id = super.getUser().getUserId();
			role.setCreate_id(login_id);
			role.setModify_id(login_id);
			role.setModify_date(new Timestamp(new Date().getTime()));
			boolean check = _privilegeServImpl.checkOperationTeam(login_id,
					role.getTeam_id());
			if (check == false) {
				return "无操作权限";
			}
			if (role.getTeam_id() <= 0) {
				return "无操作权限";
			}
		}
		List<Integer> ids = _privilegeServImpl.addRoles(roles);
		if (ids != null && ids.size() == roles.size()) {
			for (Iterator<Integer> iterator = ids.iterator(); iterator.hasNext();)
			{
				Integer id = iterator.next();
				Role role = _privilegeServImpl.getRoleById(id);
				PublicRoleBean.getRoleMap().put(id, role);
			}
			return ids;
		}
		return "操作失败";
	}

	/**
	 * 删除角色
	 * 
	 * @param roleIds
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/deleteRoles")
	@CaasAPI(TREQMSG = "[40]", TRSPMSG = "{\"success\":1,\"method\":\"/caas/privilege/deleteRoles\",\"time\":1500862833,\"parameter\":[40],\"result\":\"操作成功\"}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object deleteRole(@RequestBody List<Integer> roleIds) {
		for (Iterator<Integer> iterator = roleIds.iterator(); iterator
				.hasNext();) {
			Integer role_id = iterator.next();
			// 校验用户对角色是否有操作权限
			int login_id = super.getUser().getUserId();
			boolean check = _privilegeServImpl.checkOperationRole(login_id,
					role_id);
			if (check == false) {
				return null;
			}
		}
		boolean flag = _privilegeServImpl.removeRoles(roleIds);
		if (flag) {
			for (Iterator<Integer> iterator = roleIds.iterator(); iterator.hasNext();)
			{
				Integer id = iterator.next();
				PublicRoleBean.getRoleMap().remove(id);
			}
			return "操作成功";
		}
		return "操作失败,请检查是否存在用户拥有此角色";
	}

	/**
	 * 给用户分配角色
	 * 
	 * @param userRoles
	 * @param request
	 * @return
	 * @Description:
	 */
//	@RequestMapping("/distributeRolesToUser")
//	@CaasAPI(TREQMSG = "", TRSPMSG = "", ERRORCODE = "-1:错误,1:正常")
//	public @ResponseBody
//	Object distributeRolesToUser(@RequestBody List<UserRole> userRoles) {
//		if (userRoles.size() <= 0) {
//			return null;
//		}
//		for (Iterator<UserRole> iterator = userRoles.iterator(); iterator
//				.hasNext();) {
//			UserRole userRole = iterator.next();
//			// 校验用户对角色是否有操作权限
//			int login_id = super.getUser().getUserId();
//			boolean check = _privilegeServImpl
//					.checkOperationPermissionTodistributeRoles(userRole,
//							login_id);
//			if (check == false) {
//				return "无操作权限";
//			}
//		}
//		boolean flag = _privilegeServImpl.addUserRoles(userRoles);
//		if (flag) {
//			return "操作成功";
//		}
//		return "操作失败";
//	}

//	@RequestMapping("/searchRoles")
//	@CaasAPI(TREQMSG = "", TRSPMSG = "", ERRORCODE = "-1:错误,1:正常")
//	public @ResponseBody
//	Object searchRoles(@RequestBody SearchRoleOption option) {
//		// 进行emoji转换
//		option.setRoleName(EmojiParser.parseToAliases(option.getRoleName()));
//		log.debug(" getRoleName()    **************    "
//				+ option.getRoleName());
//		log.debug(" getTeamName()    **************    "
//				+ option.getTeamId());
//		int login_id = super.getUser().getUserId();
//		boolean checkPlatform = _privilegeServImpl
//				.checkPlatFormManager(login_id);
//		if (checkPlatform) {
//			// 平台管理员查询所有角色
//			List<Role> roles = _privilegeServImpl
//					.searchRolesOfPlatform(new RoleOption(option.getTeamId(),
//							option.getRoleName()));
//			return roles;
//		}
//		boolean check = _privilegeServImpl.checkOperationManager(login_id);
//		if (check == false) {
//			return "无操作权限";
//		}
//		List<Role> roles = _privilegeServImpl.searchRoles(new RoleOption(option
//				.getTeamId(), option.getRoleName(), login_id));
//		return roles;
//	}

	/**
	 * 获取角色的权限列表
	 * 
	 * @param roleId
	 * @param request
	 * @return
	 * @Description:
	 */
	@RequestMapping("/getRolePrivileges")
	@CaasAPI(TREQMSG = "{id: 22}", TRSPMSG = "{\"success\":1,\"method\":\"/caas/privilege/getRolePrivileges\",\"time\":1500797749,\"parameter\":{\"id\":22},\"result\":[{\"id\":7,\"isHave\":1,\"name\":\"市场部\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":4,\"visible\":1,\"submenus\":[{\"id\":8,\"isHave\":1,\"name\":\"房源管理\",\"url\":\"html/pages/house/houseInfo/baseHouse.html\",\"parent_menu_id\":7,\"seq\":1,\"visible\":1,\"submenus\":[{\"id\":18,\"isHave\":1,\"name\":\"银行列表查询\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":8,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":419,\"isHave\":1,\"name\":\"获取银行\",\"url\":\"agreementMge/bankList.do\",\"menu_id\":18,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":2332,\"isHave\":1,\"name\":\"审批房源\",\"url\":\"BaseHouse/approvalHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":415,\"isHave\":1,\"name\":\"设置或取消精品\",\"url\":\"BaseHouse/rankIstop.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":205,\"isHave\":1,\"name\":\"获取管家列表信息\",\"url\":\"BaseHouse/getMangerList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":203,\"isHave\":1,\"name\":\"保存收房信息\",\"url\":\"BaseHouse/saveHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":201,\"isHave\":1,\"name\":\"收房列表查询\",\"url\":\"BaseHouse/getHouseList.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2333,\"isHave\":1,\"name\":\"交接房源\",\"url\":\"BaseHouse/houseTransfer.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2328,\"isHave\":1,\"name\":\"查询出租房源信息\",\"url\":\"BaseHouse/seeRentHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2331,\"isHave\":1,\"name\":\"发布审批\",\"url\":\"BaseHouse/approvalPublish.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":416,\"isHave\":1,\"name\":\"下架或上架\",\"url\":\"BaseHouse/soldOut.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":206,\"isHave\":1,\"name\":\"提交房源到待审核\",\"url\":\"BaseHouse/submitHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":204,\"isHave\":1,\"name\":\"删除房屋信息\",\"url\":\"BaseHouse/deleteHouse.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":202,\"isHave\":1,\"name\":\"查询收房明细\",\"url\":\"BaseHouse/loadHouseInfo.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":418,\"isHave\":1,\"name\":\"获取材料\",\"url\":\"sys/queryMcate.do\",\"menu_id\":8,\"seq\":0,\"visible\":1},{\"id\":2329,\"isHave\":1,\"name\":\"验证出租房源是否签约\",\"url\":\"houserank/checkRankHouseStatus.do\",\"menu_id\":8,\"seq\":0,\"visible\":1}]},{\"id\":2209,\"isHave\":1,\"name\":\"房源抓取信息\",\"url\":\"html/pages/house/spiderinfo/spiderInfo.html\",\"parent_menu_id\":7,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2357,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2209,\"seq\":0,\"visible\":1}]},{\"id\":6,\"isHave\":1,\"name\":\"小区管理\",\"url\":\"html/pages/house/group/groupIndex.html\",\"parent_menu_id\":7,\"seq\":8,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":207,\"isHave\":1,\"name\":\"区域查询\",\"url\":\"sys/queryArea.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":226,\"isHave\":1,\"name\":\"小区审批拒绝\",\"url\":\"group/approveNO.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":224,\"isHave\":1,\"name\":\"小区删除\",\"url\":\"group/delete.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":222,\"isHave\":1,\"name\":\"小区新增\",\"url\":\"group/create.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":220,\"isHave\":1,\"name\":\"小区查询\",\"url\":\"group/getList.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":228,\"isHave\":1,\"name\":\"小区房屋报表\",\"url\":\"group/queryHouseFlow.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":225,\"isHave\":1,\"name\":\"小区审批通过\",\"url\":\"group/approveOk.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":223,\"isHave\":1,\"name\":\"小区修改\",\"url\":\"group/update.do\",\"menu_id\":6,\"seq\":0,\"visible\":1},{\"id\":221,\"isHave\":1,\"name\":\"小区房源关系查询\",\"url\":\"grouproom/getRoom.do\",\"menu_id\":6,\"seq\":0,\"visible\":1}]},{\"id\":1410,\"isHave\":1,\"name\":\"区域选择\",\"url\":\"html/pages/house/agreement/bank_list.html\",\"parent_menu_id\":7,\"seq\":10,\"visible\":1,\"submenus\":[],\"buttons\":[]},{\"id\":2215,\"isHave\":1,\"name\":\"合约管理\",\"url\":\"html/pages/house/agreement/newAgreementList.html\",\"parent_menu_id\":7,\"seq\":14,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2364,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2215,\"seq\":0,\"visible\":1}]},{\"id\":25,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"html/pages/sys/t_grid/t_gridList.html\",\"parent_menu_id\":7,\"seq\":15,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":25,\"isHave\":1,\"name\":\"网格管理\",\"url\":\"\",\"menu_id\":25,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1},{\"id\":417,\"isHave\":1,\"name\":\"获取用户\",\"url\":\"BaseHouse/getUserName.do\",\"menu_id\":7,\"seq\":0,\"visible\":1},{\"id\":7,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":7,\"seq\":0,\"visible\":1}]},{\"id\":16,\"isHave\":1,\"name\":\"服务中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":6,\"visible\":1,\"submenus\":[{\"id\":1601,\"isHave\":1,\"name\":\"服务菜单\",\"url\":\"html/pages/order_flow/order_service_list.html\",\"parent_menu_id\":16,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":1601,\"isHave\":1,\"name\":\"订单查询\",\"url\":\"/Order/getList.do\",\"menu_id\":1601,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":16,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":16,\"seq\":0,\"visible\":1}]},{\"id\":20,\"isHave\":1,\"name\":\"任务管理\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":12,\"visible\":1,\"submenus\":[{\"id\":2001,\"isHave\":1,\"name\":\"我发起的任务\",\"url\":\"flow/myStartTask.do\",\"parent_menu_id\":20,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2001,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2001,\"seq\":0,\"visible\":1}]},{\"id\":2002,\"isHave\":1,\"name\":\"待处理任务\",\"url\":\"flow/myDisposeFlow.do\",\"parent_menu_id\":20,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2002,\"isHave\":1,\"name\":\"获取人员\",\"url\":\"sys/getMangerList.do\",\"menu_id\":2002,\"seq\":0,\"visible\":1}]},{\"id\":2003,\"isHave\":1,\"name\":\"已处理任务\",\"url\":\"flow/yetDisposeTask.do\",\"parent_menu_id\":20,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2003,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2003,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":20,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":20,\"seq\":0,\"visible\":1}]},{\"id\":2220,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"parent_menu_id\":0,\"seq\":13,\"visible\":1,\"submenus\":[{\"id\":2221,\"isHave\":1,\"name\":\"全部消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":1,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2373,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2221,\"seq\":0,\"visible\":1}]},{\"id\":2222,\"isHave\":1,\"name\":\"未读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":2,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2374,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2222,\"seq\":0,\"visible\":1}]},{\"id\":2223,\"isHave\":1,\"name\":\"已读消息\",\"url\":\"html/pages/notification/notificationCenter.html\",\"parent_menu_id\":2220,\"seq\":3,\"visible\":1,\"submenus\":[],\"buttons\":[{\"id\":2375,\"isHave\":1,\"name\":\"\",\"url\":\"\",\"menu_id\":2223,\"seq\":0,\"visible\":1}]}],\"buttons\":[{\"id\":2376,\"isHave\":1,\"name\":\"消息中心\",\"url\":\"\",\"menu_id\":2220,\"seq\":0,\"visible\":1}]}]}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object getRolePrivileges(@RequestBody RoleFo roleFo) {
		int login_id = super.getUser().getUserId();
		boolean check = _privilegeServImpl.checkOperationRole(login_id,
				roleFo.getId());
		if (check == false) {
			return "无操作权限";
		}
		List<Menu> menus= PublicRoleBean.getRole(roleFo.getId()).getMenus();
		return menus;
	}

	@RequestMapping("/getTeams")
	@CaasAPI(TREQMSG = "", TRSPMSG = "{\"success\":1,\"method\":\"/caas/privilege/getTeams\",\"time\":1500797418,\"parameter\":\"\",\"result\":[{\"teamId\":1,\"teamName\":\"千万间\",\"leaderId\":1,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":0,\"createId\":545453,\"modifyId\":1,\"modifyDate\":\"2017-07-13 07:09:49\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[{\"teamId\":2,\"teamName\":\"市场部\",\"leaderId\":3,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":1,\"createId\":545453,\"modifyId\":1,\"modifyDate\":\"2017-07-13 07:10:11\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[{\"teamId\":7,\"teamName\":\"城北片区\",\"leaderId\":545448,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":2,\"createId\":3,\"modifyId\":3,\"modifyDate\":\"2017-07-13 08:56:11\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":8,\"teamName\":\"江宁一区\",\"leaderId\":28,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":2,\"createId\":3,\"modifyId\":3,\"modifyDate\":\"2017-07-13 08:56:50\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":9,\"teamName\":\"江宁二区\",\"leaderId\":10,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":2,\"createId\":3,\"modifyId\":3,\"modifyDate\":\"2017-07-13 08:57:36\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":10,\"teamName\":\"建邺片区\",\"leaderId\":19,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":2,\"createId\":3,\"modifyId\":3,\"modifyDate\":\"2017-07-13 08:58:15\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":11,\"teamName\":\"雨花片区\",\"leaderId\":4,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":2,\"createId\":3,\"modifyId\":3,\"modifyDate\":\"2017-07-13 08:58:47\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]}]},{\"teamId\":3,\"teamName\":\"财务部\",\"leaderId\":94,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":1,\"createId\":545453,\"modifyId\":1,\"modifyDate\":\"2017-07-13 08:51:01\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[{\"teamId\":15,\"teamName\":\"修改团队名称\",\"leaderId\":35,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":3,\"createId\":3,\"modifyId\":94,\"modifyDate\":\"2017-07-22 07:41:55\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":16,\"teamName\":\"测试团队2\",\"leaderId\":545453,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":3,\"createId\":3,\"modifyId\":3,\"modifyDate\":\"2017-07-22 05:07:22\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]}]},{\"teamId\":4,\"teamName\":\"客服部\",\"leaderId\":37,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":1,\"createId\":545453,\"modifyId\":1,\"modifyDate\":\"2017-07-13 08:52:00\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":5,\"teamName\":\"工程部\",\"leaderId\":49,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":1,\"createId\":545453,\"modifyId\":1,\"modifyDate\":\"2017-07-13 08:53:58\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]},{\"teamId\":6,\"teamName\":\"总经办\",\"leaderId\":8,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":1,\"createId\":545453,\"modifyId\":1,\"modifyDate\":\"2017-07-13 08:54:38\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]}]},{\"teamId\":12,\"teamName\":\"团队二号\",\"leaderId\":545454,\"leaderName\":null,\"newLeaderId\":0,\"parentTeamId\":0,\"createId\":545454,\"modifyId\":545454,\"modifyDate\":\"2017-07-18 02:01:40\",\"isDelete\":0,\"teamLevel\":0,\"houseCnt\":0,\"subTeam\":null,\"memberList\":null,\"subTeams\":[]}]}", ERRORCODE = "-1:错误,1:正常")
	public @ResponseBody
	Object getTeams() {
		log.debug("getTeams  -------  begin");
		int login_id = super.getUser().getUserId();
		List<TeamBean> teams = null;
		boolean check = _privilegeServImpl.checkOperationManager(login_id);
		if (!check) {
			return "无操作权限";
		}
		teams = _privilegeServImpl.getTeamByUserId(login_id);
		log.debug("getTeams ------ end");
		return teams;
	}
}
