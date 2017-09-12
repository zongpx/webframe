
package com.sys.webframe.bus.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sys.webframe.bus.user.entity.User;



@Component("_userDao")
public interface IUserDao
{
	/**
	 * 查询登录用户信息
	 * 
	 * @param user
	 * @return
	 */
	User selectUserInfo(User user);

	/**
	 * 更新终端注册ID
	 * 
	 * @param user
	 * @return
	 */
	int updateUserRegistrationId(User user);

	/**
	 * 插入登录日志
	 * 
	 * @param map
	 * @return
	 */
	int insertLoginLog(Map<String, Object> map);

	/**
	 * 查询用户的权限菜单
	 * 
	 * @param user
	 * @return
	 */
	List<Map<String, Object>> selectUserMenuById(User user);

	/**
	 * 根据用户ID查询用户密码
	 * 
	 * @param user
	 * @return
	 */
	User selectUserPasswordById(User user);
}
