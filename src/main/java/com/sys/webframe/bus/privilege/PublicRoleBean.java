/**
 * date: 2017年7月25日
 */
package com.sys.webframe.bus.privilege;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sys.webframe.bus.privilege.entity.Role;
import com.sys.webframe.bus.privilege.service.IPrivilegeServ;

/**
 * @name: PublicRoleMap.java
 * @Description: 角色权限的公共信息类
 * @author duanyongrui
 * @since: 2017年7月25日
 */
public class PublicRoleBean
{
	private IPrivilegeServ _privilegeServImpl;
	
	// 系统中所有角色和权限的集合   key：角色id ，value： 角色信息(包括角色拥有的菜单)
	private static Map<Integer, Role> roleMap = new HashMap<Integer, Role>();
	
	public void initRoleMap() {
		roleMap = new HashMap<Integer, Role>();
		List<Role> roles = _privilegeServImpl.getAllRoles();
		for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();)
		{
			Role role = iterator.next();
			roleMap.put(role.getId(), role);
		}
	}

	public static Map<Integer, Role> getRoleMap()
	{
		return roleMap;
	}

	public static void setRoleMap(Map<Integer, Role> roleMap)
	{
		PublicRoleBean.roleMap = roleMap;
	}
	
	public static Role getRole(int roleId) {
		return roleMap.get(roleId);
	}

	public IPrivilegeServ get_privilegeServImpl()
	{
		return _privilegeServImpl;
	}

	public void set_privilegeServImpl(IPrivilegeServ _privilegeServImpl)
	{
		this._privilegeServImpl = _privilegeServImpl;
	}
	
}
