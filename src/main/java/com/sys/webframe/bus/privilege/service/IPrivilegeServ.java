/**
 * date: 2017年6月14日
 */
package com.sys.webframe.bus.privilege.service;

import java.util.List;

import com.sys.webframe.bus.privilege.entity.Button;
import com.sys.webframe.bus.privilege.entity.Menu;
import com.sys.webframe.bus.privilege.entity.Privilege;
import com.sys.webframe.bus.privilege.entity.Role;
import com.sys.webframe.bus.privilege.entity.RoleOption;
import com.sys.webframe.bus.privilege.entity.UserRole;
import com.sys.webframe.bus.team.entity.TeamBean;


/**
 * @name: IPermissionService.java
 * @Description:
 * @author duanyongrui
 * @since: 2017年6月14日
 */
public interface IPrivilegeServ
{
	/**
	 * 添加按钮
	 * 
	 * @param button
	 * @return 按钮id
	 * @Description: 添加按钮操作不进行排序
	 */
	public boolean addButtons(List<Button> buttons);

	/**
	 * 删除按钮
	 * 
	 * @param buttonIds
	 *            按钮的id集合
	 * @Description:
	 */
	public boolean removeButtons(List<Integer> buttonIds);

	/**
	 * 获取一个或多个按钮信息
	 * 
	 * @param menuIds
	 *            按钮的id集合
	 * @return 按钮信息集合
	 * @Description:
	 */
	public List<Button> getButtons(List<Integer> buttonIds);

	/**
	 * 更改按钮的排序
	 * 
	 * @param list
	 *            排序按钮的id和排列号{"id": Integer,"seq":Integer}
	 * @Description: 必须一次性对同一菜单的所有按钮进行更改
	 */
	public boolean modifyAllButtonSeq(List<Button> list);

	/**
	 * 添加菜单
	 * 
	 * @param menus
	 * @return 菜单id
	 * @Description: 添加按钮操作不进行排序
	 */
	public boolean addMenus(List<Menu> menus);

	/**
	 * 获取一个或多个菜单信息
	 * 
	 * @param menuIds
	 *            菜单的id集合
	 * @return 菜单信息集合
	 * @Description:
	 */
	public List<Menu> getMenus(List<Integer> menuIds);

	/**
	 * 获取子菜单信息
	 * 
	 * @param parentId
	 *            父菜单id
	 * @return 子菜单信息集合
	 * @Description:
	 */
	public List<Menu> getSubmenusByParentMenuId(int parentId);

	/**
	 * 更改菜单的排序
	 * 
	 * @param list
	 *            排序菜单的id和排列号{"id": Integer,"seq":Integer}
	 * @Description: 必须一次性对同一菜单的所有子菜单进行更改
	 */
	public boolean modifyAllMenuSeq(List<Menu> list);

	/**
	 * 新增角色
	 * 
	 * @param roles
	 *            角色信息集合
	 * @Description:
	 */
	public List<Integer> addRoles(List<Role> roles);

	/**
	 * 修改角色信息(需要角色id)
	 * 
	 * @param role
	 *            角色信息
	 * @Description: 支持修改的属性：name, desc, modify_id, modify_date
	 */
	public boolean modifyRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param roleIds
	 *            角色的id集合
	 * @Description:
	 */
	public boolean removeRoles(List<Integer> roleIds);

	/**
	 * 批量添加权限
	 * 
	 * @param list
	 * @Description: 先删除所有再添加全部权限
	 */
	public boolean addPrivileges(List<Privilege> list);

	/**
	 * 删除权限
	 * 
	 * @param list 权限的id集合
	 * @Description:
	 */
	public boolean removePrivilegesById(List<Integer> list);
	
	/**
	 * 删除权限
	 * 
	 * @param list 权限的信息集合
	 * @Description:
	 */
	public boolean removePrivileges(List<Privilege> list);

	/**
	 * 修改权限开启状态
	 * 
	 * @param list
	 * @Description:
	 */
	public boolean modifyOper(List<Privilege> list);

	/**
	 * 批量获取权限信息
	 * 
	 * @param list
	 *            权限的id集合
	 * @return 权限信息集合
	 * @Description:
	 */
	public List<Privilege> getPrivileges(List<Integer> list);
	
	/**
	 * 给用户分配权限
	 * @param userRoles 用户权限分配信息
	 * @Description:
	 */
	public boolean addUserRoles(List<UserRole> userRoles);
	
	/**
	 * 删除用户的所有角色
	 * @param user_id 用户id
	 * @Description:
	 */
	public boolean removeAllUserRoles(int user_id);
	
	/**
	 * 删除角色所有权限
	 * @param role_id 角色id
	 * @Description: 
	 */
	public boolean removeAllRolePrivileges(int role_id);
	
	/**
	 * 查询用户所有角色
	 * @param user_id 用户id
	 * @return 用户角色集合
	 * @Description:
	 */
	public List<Role> getUserRoles(int user_id);
	
	/**
	 * 查询角色所有权限
	 * @param role_id 角色id
	 * @return 角色权限集合
	 * @Description:
	 */
	public List<Privilege> getRolePrivilege(int role_id);
	
	/**
	 * 根据团队名称、角色名称查询角色
	 * @param option 
	 * @return
	 * @Description:
	 */
	public List<Role> searchRoles(RoleOption option);
	
	/** 
	 * 根据团队名称、角色名称查询角色
	 * @param option(不需要login_id)
	 * @return
	 * @Description: 平台管理员 查询
	 */
	public List<Role> searchRolesOfPlatform(RoleOption option);
	
	/**
	 * 根据团队管理员id获取团队及子团队的角色
	 * @param team_id 团队管理员id
	 * @return
	 * @Description:
	 */
	public List<Role> getRolesByTeamId(int team_id);
	
	/**
	 * 判断用户是否有对另一个用户分配角色的操作权限
	 * @param userRole
	 * @param login_id 
	 * @return 是否可操作
	 * @Description:
	 */
	public boolean checkOperationPermissionTodistributeRoles(UserRole userRole, int login_id);
	
	/**
	 * 判断用户是否有对另一个用户的操作权限
	 * @param login_id
	 * @param user_id
	 * @return 是否可操作
	 * @Description:
	 */
	public boolean checkOperationPermissionToOtherUserId(int login_id, int user_id);
	
	/**
	 * 获取用户所在团队以及子团队信息
	 * @param user_id
	 * @return
	 * @Description:
	 */
	public List<TeamBean> getTeamByUserId(int user_id);
	
	/**
	 * 校验用户是否有角色的操作权限
	 * @param login_id 用户id
	 * @param role_id 角色id
	 * @return
	 * @Description:
	 */
	public boolean checkOperationRole(int login_id, int role_id);
	
	/**
	 * 判断是否有对团队的操作权限
	 * @param login_id
	 * @param team_id
	 * @return
	 * @Description:
	 */
	public boolean checkOperationTeam(int login_id, int team_id);
	
	/**
	 * 获取角色的菜单
	 * @param login_id
	 * @param role_id
	 * @return
	 * @Description:
	 */
	public List<Menu> getMenuByRoleId(int role_id);
	
	/**
	 * 校验用户是否是平台管理员或团队管理员
	 * @param user_id
	 * @return
	 * @Description:
	 */
	public boolean checkOperationManager(int login_id);
	
	/**
	 * 判断用户是不是平台管理员
	 * @param login_id
	 * @return
	 * @Description:
	 */
	public boolean checkPlatFormManager(int login_id);
	
	/**
	 * 获取所有的角色权限信息
	 * @return
	 * @Description:
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 根据角色id获取角色
	 * @param roleId
	 * @return
	 * @Description:
	 */
	public Role getRoleById(int roleId);
}
