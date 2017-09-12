package com.sys.webframe.bus.user.service;

import java.util.Map;

import com.sys.webframe.bus.login.entity.User;


/**
 * @Description 用户登录
 * @author sunchengming
 * @date 2017年7月18日 下午2:16:30
 * 
 */
public interface ILoginServ
{
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return
	 */
	User searchLogin(User user);

	/**
	 * 加载菜单
	 * 
	 * @return
	 */
	Map<String, Object> selectUserMenuById(User user);

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	int modifyPassword(User user);
}
