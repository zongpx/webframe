package com.sys.webframe.bus.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.login.service.ILoginServ;
import com.sys.webframe.core.annotation.CaasAPI;
import com.sys.webframe.core.support.BaseController;

@Controller
@RequestMapping("/caas/checklogin/")
public class CheckLoginController extends BaseController {

	@Autowired
	private ILoginServ _loginServImpl;

	/**
	 * 用户登录
	 * 
	 * @param person
	 * @return
	 */
	@RequestMapping("/login")
	@CaasAPI(TREQMSG = "入参", TRSPMSG = "出参")
	public @ResponseBody Object login(@RequestBody User user) {
		User u = _loginServImpl.searchLogin(user);
		log.debug("user:" + u);
		return u;
	}

	/**
	 * 加载菜单
	 * 
	 * @return
	 */
	@RequestMapping("/loadUserMenu")
	@CaasAPI(TREQMSG = "入参", TRSPMSG = "出参")
	public @ResponseBody Object loadUserMenu() {
		User user = getUser();
		Map<String, Object> map = _loginServImpl.selectUserMenuById(user);
		return map;
	}

	/**
	 * 用户登出
	 * 
	 * @return
	 */
	@RequestMapping("/loginOut")
	@CaasAPI(TREQMSG = "入参", TRSPMSG = "出参")
	public @ResponseBody Object loginOut() {
		return getUser();
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("/modifyPassword")
	@CaasAPI(TREQMSG = "入参", TRSPMSG = "出参")
	public @ResponseBody Object modifyPassword(@RequestBody User user) {
		user.setUserId(getUser().getUserId());
		user.setPassword(getUser().getPassword());
		int result = _loginServImpl.modifyPassword(user);
		return result;
	}
}
