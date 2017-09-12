package com.sys.webframe.bus.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.webframe.bus.login.dao.ILoginDao;
import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.privilege.PublicRoleBean;
import com.sys.webframe.bus.privilege.entity.Menu;
import com.sys.webframe.bus.privilege.entity.Role;
import com.sys.webframe.bus.privilege.service.IPrivilegeServ;
import com.sys.webframe.bus.syscfg.dao.ISysCfgDao;
import com.sys.webframe.core.support.BaseService;



@Service("_loginServImpl")
public class LoginServImpl extends BaseService implements ILoginServ
{

	@Resource
	private IUserDao _loginDao;

	@Resource
	private ISysCfgDao _sysCfgDao;

	@Autowired
	private IPrivilegeServ _privilegeServImpl;

	@Override
	public User searchLogin(User user)
	{
		log.info("searchLogin begin");
		// 密码加密
		String password = user.getPassword();
		password = new Encrypt("SHA-1").getEncrypt(password);
		user.setPassword(password);
		// 验证用户名和密码是否正确
		User userInfo = _loginDao.selectUserInfo(user);
		// 用户名或密码不正确
		if (null == userInfo)
		{
			// 用户不存在
			userInfo = new User();
			userInfo.setResultCode(-2);
			userInfo.setResultInfo("账号不存在");
		} else
		{
			log.debug("userInfo:" + userInfo);
			// 用户存在，判断是否禁用
			if (userInfo.getState() == 0)
			{
				userInfo.setResultCode(-5);
				userInfo.setResultInfo("账号已经禁用");
			} else
			{
				if (StringUtils.equals(user.getPassword(), userInfo.getPassword()))
				{
					// 密码匹配成功
					userInfo.setResultCode(1);
					if (StringUtils.isNotBlank(user.getRegistrationId())
							&& !user.getRegistrationId().equals(userInfo.getRegistrationId()))
					{
						// 更新
						_loginDao.updateUserRegistrationId(user);
						String terminalType = user.getTerminalType();
						if (StringUtils.isNotBlank(user.getTerminalType()))
						{
							String versionCode = super.get(_sysCfgDao.selectMaxVesion(terminalType), "version_code");
							userInfo.setVersionCode(versionCode);
						}
					}
				} else
				{
					userInfo.setResultCode(-3);
					userInfo.setResultInfo("密码错误");
				}
			}
		}
		return userInfo;
	}

	@Override
	public Map<String, Object> selectUserMenuById(User user)
	{
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("state", 1);
		if (null == user)
		{
			returnMap.put("state", -2);
			return returnMap;
		}

		List<Map<String, Object>> result = new ArrayList<>();
		// 加载菜单项
		List<Role> roles = new ArrayList<Role>();
		List<Role> temp = _privilegeServImpl.getUserRoles(user.getUserId());
		for (Iterator<Role> iterator = temp.iterator(); iterator.hasNext();)
		{
			Role role = iterator.next();
			roles.add(PublicRoleBean.getRole(role.getId()));
		}
		log.info("新接口菜单： " + roles);

		/*
		 * List<Map<String, Object>> list = _loginDao.selectUserMenuById(user);
		 * log.info("老接口菜单：" + list);
		 */

		List<List<Menu>> menuList = new ArrayList<>();
		for (Role role : roles)
		{
			List<Menu> menu = role.getMenus();
			menuList.add(menu);
		}

		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < menuList.size(); i++)
		{
			List<Menu> menus = menuList.get(i);
			for (int j = 0; j < menus.size(); j++)
			{
				Menu m = menus.get(j);
				if (m.getVisible() == 0)
				{
					continue;
				} else
				{
					map = new HashMap<>();
					map.put("isnext", 1);
					map.put("menu_name", m.getName());
					map.put("menu_level", 1);
					map.put("menu_url", m.getUrl());
					map.put("super_id", m.getParent_menu_id());
					map.put("menu_id", m.getId());
					map.put("order_id", m.getSeq());
					result.add(map);
					List<Menu> menus2 = m.getSubmenus();
					for (Menu menu3 : menus2)
					{
						map = new HashMap<>();
						map.put("isnext", 0);
						map.put("menu_name", menu3.getName());
						map.put("menu_level", 2);
						map.put("menu_url", menu3.getUrl());
						map.put("super_id", menu3.getParent_menu_id());
						map.put("menu_id", menu3.getId());
						map.put("order_id", menu3.getSeq());
						result.add(map);
					}
				}
			}
		}

		Set<Map<String, Object>> hs = new HashSet<>(result);
		returnMap.put("menu", hs);

		// 加载用户信息
		returnMap.put("user", user);

		return returnMap;
	}

	@Override
	public int modifyPassword(User user)
	{
		int result = 1;
		// session中的原密码
		String password = user.getPassword();
		// 用户输入的老密码
		String oldPassword = user.getOldPassword();
		// 新密码
		String newPassword = user.getNewPassword();
		// 确认密码
		String againPassword = user.getAgainPassword();
		
		if ("".equals(oldPassword))
		{
			result = -4;
			return result;
		}
		if ("".equals(newPassword))
		{
			result = -7;
			return result;
		}
		if (oldPassword.equals(newPassword))
		{
			result = -6;
			return result;
		}
		if (!newPassword.equals(againPassword))
		{
			result = -5;
			return result;
		}
		
		// 存在，加密原密码
		String shaPassword = new Encrypt("SHA-1").getEncrypt(oldPassword);


		return result;
	}
}
