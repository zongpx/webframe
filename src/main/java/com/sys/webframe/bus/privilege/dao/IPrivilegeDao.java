/**
 * date: 2017年6月12日
 */
package com.sys.webframe.bus.privilege.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sys.webframe.bus.privilege.entity.Button;
import com.sys.webframe.bus.privilege.entity.Menu;
import com.sys.webframe.bus.privilege.entity.Privilege;
import com.sys.webframe.bus.privilege.entity.Role;
import com.sys.webframe.bus.privilege.entity.RoleOption;
import com.sys.webframe.bus.privilege.entity.UserRole;
import com.sys.webframe.bus.team.entity.TeamBean;

/**
 * @name: IPermissionDao.java
 * @Description:
 * @author duanyongrui
 * @since: 2017年6月12日
 */
@Component("_privilegeDao")
public interface IPrivilegeDao
{

	/**
	 * 添加按钮
	 * 
	 * @param button
	 * @Description: 添加按钮操作不进行排序
	 */
	public int insertButtons(List<Button> buttons);

	/**
	 * 修改按钮信息(需要按钮id)
	 * 
	 * @Description: 支持修改的属性包括：name, url, url, visible
	 */
	public int updateButton(Button button);

	/**
	 * 修改按钮可见性
	 * 
	 * @param list
	 * @Description:
	 */
	public int updateButtonsVisible(List<Button> list);

	/**
	 * 删除按钮
	 * 
	 * @param buttonIds 按钮的id集合
	 * @Description:
	 */
	public int deleteButtons(List<Integer> buttonIds);

	/**
	 * 获取按钮信息
	 * 
	 * @param id 按钮id
	 * @return 按钮信息
	 * @Description:
	 */
	public List<Button> selectButtons(List<Integer> ids);

	/**
	 * 获取菜单的所有按钮信息
	 * 
	 * @param menuId 菜单id
	 * @return 按钮信息集合
	 * @Description:
	 */
	public List<Button> selectSubbuttonsByMenuId(@Param(value = "menuId") int menuId);

	/**
	 * 更改按钮的排序
	 * 
	 * @param list
	 *            排序按钮的id和排列号{"id": Integer,"seq": Integer}
	 * @Description:
	 */
	public int updateAllButtonSeq(List<Button> idAndSeqs);

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @Description: 添加菜单操作不进行排序
	 */
	public int insertMenus(List<Menu> menus);

	/**
	 * 修改菜单信息(需要菜单id)
	 * 
	 * @param menu
	 *            菜单信息
	 * @Description: 支持修改的属性包括：name, url, url, visible
	 */
	public int updateMenu(Menu menu);

	/**
	 * 修改菜单可见性
	 * 
	 * @param list
	 * @Description:
	 */
	public int updateMenusVisible(List<Menu> list);

	/**
	 * 删除菜单
	 * 
	 * @param buttonIds
	 *            菜单的id集合
	 * @Description: 此方法将删除菜单并级联删除所有子菜单和按钮
	 */
	public int deleteMenus(List<Integer> menuIds);

	/**
	 * 获取菜单信息
	 * 
	 * @param id
	 *            菜单id
	 * @return 菜单信息
	 * @Description:
	 */
	public List<Menu> selectMenus(List<Integer> ids);

	/**
	 * 获取子菜单信息
	 * 
	 * @param parentId
	 *            父菜单id
	 * @return 子菜单信息集合
	 * @Description:
	 */
	public List<Menu> selectSubmenusByParentMenuId(@Param(value = "parentId") int parentId);

	/**
	 * 更改菜单的排序
	 * 
	 * @param list
	 *            排序菜单的id和排列号{"id": Integer,"seq": Integer}
	 * @Description:
	 */
	public int updateAllMenuSeq(List<Menu> idAndSeqs);

	/**
	 * 批量添加角色
	 * 
	 * @param roles
	 *            角色信息
	 * @Description:
	 */
	public int insertRole(Role role);

	/**
	 * 修改角色信息(需要角色id)
	 * 
	 * @param role
	 *            角色信息
	 * @Description: 支持修改的属性：name, desc, modify_id, modify_date
	 */
	public int updateRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param roleIds 角色的id集合
	 * @Description:
	 */
	public int deleteRoles(List<Integer> roleIds);
	
	/**
	 * 获取角色数量
	 * @param role_id
	 * @return
	 * @Description:
	 */
	public int selectCountOfRole(int role_id);

	/**
	 * 批量添加权限
	 * 
	 * @param list
	 * @Description:
	 */
	public int insertPrivileges(List<Privilege> privileges);

	/**
	 * 获取权限数量
	 * 
	 * @param privilege 权限信息
	 * @Description:
	 */
	public int selectCountOfPrivilege(Privilege privilege);

	/**
	 * 删除权限
	 * @param list 权限的id集合
	 * @Description:
	 */
	public int deletePrivilegesById(List<Integer> ids);
	
	/**
	 * 删除权限
	 * 
	 * @param list 权限的信息集合
	 * @Description:
	 */
	public int deletePrivileges(List<Privilege> privileges);

	/**
	 * 修改权限开启状态
	 * 
	 * @param list
	 * @Description:
	 */
	public int updatePrivilegesOper(List<Privilege> idAndOpers);

	/**
	 * 批量获取权限信息
	 * 
	 * @param list 权限的id集合
	 * @return 权限信息集合
	 * @Description:
	 */
	public List<Privilege> selectPrivileges(List<Integer> ids);

	/**
	 * 给用户分配权限
	 * @param userRoles 用户权限分配信息
	 * @Description:
	 */
	public int insertUserRoles(List<UserRole> userRoles);
	
	/**
	 * 删除用户的所有角色
	 * @param user_id 用户id
	 * @Description:
	 */
	public int deleteAllUserRoles(int user_id);
	
	/**
	 * 删除角色所有权限
	 * @param role_id 角色id
	 * @Description:
	 */
	public int deleteAllRolePrivileges(int role_id);
	
	/**
	 * 查询用户所有角色
	 * @param user_id 用户id
	 * @return 用户角色集合
	 * @Description:
	 */
	public List<Role> selectUserRoles(int user_id);
	
	/**
	 * 查询角色所有权限
	 * @param role_id 角色id
	 * @return 角色权限集合
	 * @Description:
	 */
	public List<Privilege> selectRolePrivilege(int role_id);
	
	/**
	 * 根据团队id、角色名称查询角色
	 * @param option
	 * @return
	 * @Description:
	 */
	public List<Role> selectRoles(RoleOption option);
	
	/**
	 * 根据团队名称、角色名称查询角色
	 * @param option(不需要login_id)
	 * @return
	 * @Description:
	 */
	public List<Role> selectRolesOfPlatform(RoleOption option);
	
	/**
	 * 根据团队管理员id获取团队的角色
	 * @param team_id 团队管理员id
	 * @return
	 * @Description:
	 */
	public List<Role> selectRolesByTeamId(int team_id);
	
	/**
	 * 判断用户是不是平台管理员
	 * @param login_id
	 * @return
	 * @Description:
	 */
	public int selectPlatFormManager(int login_id);
	
	/**
	 * 判断用户是不是团队管理员
	 * @param login_id
	 * @return
	 * @Description:
	 */
	public int selectOperationManager(int login_id);
	
	/**
	 * 判断是否有对团队的操作权限
	 * @param login_id
	 * @param team_id
	 * @return
	 * @Description:
	 */
	public int selectOperationTeam(@Param(value = "login_id") int login_id, @Param(value = "team_id") int team_id);
	
	/**
	 * 判断用户是不是团队成员
	 * @param login_id
	 * @param user_id
	 * @return
	 * @Description:
	 */
	public int selectTeamMemberByUserId(@Param(value = "login_id") int login_id, @Param(value = "user_id") int user_id);
	
	/**
	 * 获取用户所在团队以及子团队信息
	 * @param user_id
	 * @return
	 * @Description:
	 */
	public List<TeamBean> selectTeamByUserId(int user_id);
	
	/**
	 * 获取所有团队以及子团队信息
	 * @return 团队列表
	 * @Description:
	 */
	public List<TeamBean> selectAllTeams();
	
	/**
	 * 校验用户是否有角色的操作权限
	 * @param login_id 用户id
	 * @param role_id 角色id
	 * @return
	 * @Description:
	 */
	public int selectOperationRole(@Param(value = "login_id") int login_id, @Param(value = "role_id") int role_id);
	
	/**
	 * 获取角色的菜单
	 * @param role_id
	 * @return
	 * @Description:
	 */
	public List<Menu> selectMenuByRoleId(int id);
	
	/**
	 * 根据名称获取角色数量
	 * @param name
	 * @return
	 * @Description:
	 */
	public int selectCountOfRoleByName(@Param(value = "name") String name);
	
	/**
	 * 获取所有的角色权限信息
	 * @return
	 * @Description:
	 */
	public List<Role> selectAllRoles();
	
	/**
	 * 根据角色id获取角色
	 * @param roleId
	 * @return
	 * @Description:
	 */
	public Role selectRoleById(int roleId);
}
