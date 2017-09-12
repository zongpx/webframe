/**
 * date: 2017年6月14日
 */
package com.sys.webframe.bus.privilege.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sys.webframe.bus.privilege.dao.IPrivilegeDao;
import com.sys.webframe.bus.privilege.entity.Button;
import com.sys.webframe.bus.privilege.entity.Menu;
import com.sys.webframe.bus.privilege.entity.Privilege;
import com.sys.webframe.bus.privilege.entity.Role;
import com.sys.webframe.bus.privilege.entity.RoleOption;
import com.sys.webframe.bus.privilege.entity.UserRole;
import com.sys.webframe.bus.team.entity.TeamBean;
import com.sys.webframe.core.support.BaseController;


/**
 * @name: PermissionServiceImpl.java
 * @Description:
 * @author duanyongrui
 * @since: 2017年6月14日
 */
@Service("_privilegeServImpl")
public class PrivilegeServImpl extends BaseController implements IPrivilegeServ
{

	@Resource
	private IPrivilegeDao _privilegeDao;

	/**
	 * 
	 * @name: LockServiceImpl.java
	 * @Description: 参数错误
	 * @author duanyongrui
	 * @since: 2017年5月22日
	 */
	class InvalidArgumentException extends Exception
	{

		/**
		 * 版本号
		 */
		private static final long serialVersionUID = 1L;

		public InvalidArgumentException(String message)
		{
			super(message);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#addButtons(java.
	 * util.List)
	 */
	@Override
	public boolean addButtons(List<Button> buttons)
	{
		log.info("addButtons -- begin");
		// List<Integer> buttonIds = new ArrayList<>();
		int count = 0;
		try
		{
			List<Integer> ids = new ArrayList<>();
			// 获取所有按钮的菜单的id集合
			for (Iterator<Button> iterator = buttons.iterator(); iterator.hasNext();)
			{
				Button button = iterator.next();
				ids.add(button.getMenu_id());
			}
			List<Menu> menus = this._privilegeDao.selectMenus(ids);
			if (menus != null && menus.size() == ids.size())
			{
				count = this._privilegeDao.insertButtons(buttons);
			} else
			{
				throw new InvalidArgumentException("一个或多个按钮的菜单不存在");
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("addButtons -- end");
		if (count == buttons.size()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#removeButtons(java.
	 * util.List)
	 */
	@Override
	public boolean removeButtons(List<Integer> buttonIds)
	{
		log.info("removeButtons -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.deleteButtons(buttonIds);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("removeButtons -- end");
		if (count == buttonIds.size()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#getButtons(java.
	 * util.List)
	 */
	@Override
	public List<Button> getButtons(List<Integer> buttonIds)
	{
		// TODO Auto-generated method stub
		log.info("getButtons -- begin");
		List<Button> buttons = null;
		try
		{
			buttons = _privilegeDao.selectButtons(buttonIds);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getButtons -- end");
		return buttons;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#modifyAllButtonSeq(
	 * java.util.List)
	 */
	@Override
	public boolean modifyAllButtonSeq(List<Button> list)
	{
		// TODO Auto-generated method stub
		log.info("modifyAllButtonSeq -- begin");
		int count = 0;
		try
		{
			if (list == null || list.size() == 0)
			{
				throw new Exception("参数不能为空或空集合");
			}
			Button buttonIdAndSeq = list.get(0);
			List<Integer> buttonIds = new ArrayList<>();
			buttonIds.add(buttonIdAndSeq.getId());
			// 获取第一个按钮的菜单id
			int menuId = _privilegeDao.selectButtons(buttonIds).get(0).getMenu_id();
			List<Button> buttons = _privilegeDao.selectSubbuttonsByMenuId(menuId);
			// 判断是否更新了所有按钮的排列号(若缺少按钮，会导致排列号出现重复等问题)
			if (list.size() < buttons.size())
			{
				throw new InvalidArgumentException("必须更新菜单所有自按钮的排列号");
			}
			if (list.size() > buttons.size())
			{
				throw new InvalidArgumentException("按钮的菜单不一致");
			}
			List<Integer> seqs = new ArrayList<>();
			// 判断按钮是否同属于一个菜单(此接口只能一次性更新一个菜单的子按钮的顺序)
			for (int i = 0; i < list.size(); i++)
			{
				if (buttons.get(i).getId() != list.get(i).getId())
				{
					throw new InvalidArgumentException("按钮的菜单不一致");
				}
				seqs.add(list.get(i).getSeq());
			}
			// 判断排列号是否重复
			Set<Integer> set = new HashSet<Integer>();
			for (Iterator<Integer> iterator = seqs.iterator(); iterator.hasNext();)
			{
				Integer obj = iterator.next();
				set.add(obj);
			}
			if (set.size() != list.size())
			{
				// 有重复
				throw new InvalidArgumentException("排列号重复");
			}
			Collections.sort(seqs);
			log.debug("Collections seqs  :" + (seqs.get(0) != 1) + "  ----  " + seqs.get(0));
			if (seqs.get(0) != 1)
			{
				throw new InvalidArgumentException("排列号未从“1”开始");
			}
			if (seqs.size() != seqs.get(seqs.size() - 1))
			{
				throw new InvalidArgumentException("排列号不连续");
			}
			count = _privilegeDao.updateAllButtonSeq(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("modifyAllButtonSeq -- end");
		if (count == list.size()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#addMenus(java.util.
	 * List)
	 */
	@Override
	public boolean addMenus(List<Menu> menus)
	{
		log.info("addMenus -- begin");
		int count = 0;
		try
		{
			// List<Integer> menuIds = new ArrayList<>();
			// 根菜单的parent_menu_id 均为 -1
			List<Integer> ids = new ArrayList<>();
			for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext();)
			{
				Menu menu = iterator.next();
				if (menu.getParent_menu_id() == -1)
				{
					continue;
				}
				ids.add(menu.getParent_menu_id());
			}
			List<Menu> parentMenus = this._privilegeDao.selectMenus(ids);
			if (parentMenus != null && parentMenus.size() == ids.size())
			{
				count = this._privilegeDao.insertMenus(menus);
			} else
			{
				throw new InvalidArgumentException("一个或多个菜单的父菜单不存在");
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("addMenus -- end");
		if (count == menus.size()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#getMenus(java.util.
	 * List)
	 */
	@Override
	public List<Menu> getMenus(List<Integer> menuIds)
	{
		log.info("getMenus -- begin");
		List<Menu> menus = null;
		try
		{
			menus = _privilegeDao.selectMenus(menuIds);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getMenus -- end");
		return menus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yc.rm.caas.permissions.service.IPermissionService#
	 * getSubmenusByParentMenuId(int)
	 */
	@Override
	public List<Menu> getSubmenusByParentMenuId(int parentId)
	{
		log.info("getSubmenusByParentMenuId -- begin");
		List<Menu> menus = null;
		try
		{
			menus = _privilegeDao.selectSubmenusByParentMenuId(parentId);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getSubmenusByParentMenuId -- end");
		return menus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yc.rm.caas.permissions.service.IPermissionService#modifyAllMenuSeq(
	 * java.util.List)
	 */
	@Override
	public boolean modifyAllMenuSeq(List<Menu> list)
	{
		log.info("modifyAllMenuSeq -- begin");
		int count = 0;
		try
		{
			if (list == null || list.size() == 0)
			{
				throw new Exception("参数不能为空或空集合");
			}
			Menu menuIdAndSeq = list.get(0);
			List<Integer> menuIds = new ArrayList<>();
			menuIds.add(menuIdAndSeq.getId());
			// 获取第一个父菜单id
			int parentId = _privilegeDao.selectMenus(menuIds).get(0).getParent_menu_id();
			List<Menu> menus = _privilegeDao.selectSubmenusByParentMenuId(parentId);
			// 判断是否更新了所有子菜单的排列号(若缺少子菜单，会导致排列号出现重复等问题)
			if (list.size() < menus.size())
			{
				throw new InvalidArgumentException("必须更新所有子菜单的排列号");
			}
			if (list.size() > menus.size())
			{
				throw new InvalidArgumentException("菜单的父菜单不一致");
			}
			List<Integer> seqs = new ArrayList<>();
			// 判断菜单是否同属于一个父菜单(此接口只能一次性更新一个菜单的子菜单的顺序)
			for (int i = 0; i < list.size(); i++)
			{
				if (menus.get(i).getId() != list.get(i).getId())
				{
					throw new InvalidArgumentException("菜单的父菜单不一致");
				}
				seqs.add(list.get(i).getSeq());
			}
			// 判断排列号是否重复
			Set<Integer> set = new HashSet<Integer>();
			for (Iterator<Integer> iterator = seqs.iterator(); iterator.hasNext();)
			{
				Integer obj = iterator.next();
				set.add(obj);
			}
			if (set.size() != list.size())
			{
				// 有重复
				throw new InvalidArgumentException("排列号重复");
			}
			Collections.sort(seqs);
			log.debug("Collections seqs  :" + (seqs.get(0) != 1) + "  ----  " + seqs.get(0));
			if (seqs.get(0) != 1)
			{
				throw new InvalidArgumentException("排列号未从“1”开始");
			}
			if (seqs.size() != seqs.get(seqs.size() - 1))
			{
				throw new InvalidArgumentException("排列号不连续");
			}
			count = _privilegeDao.updateAllMenuSeq(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("modifyAllMenuSeq -- end");
		if (count == list.size()) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#addRoles(java.util.List)
	 * 
	 */
	@Override
	public List<Integer> addRoles(List<Role> roles)
	{
		// TODO Auto-generated method stub
		log.info("addRoles -- begin");
		List<Integer> ids = new ArrayList<Integer>();
		try
		{
			boolean isUnique = true;
			for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();)
			{
				Role role = iterator.next();
				int count = _privilegeDao.selectCountOfRoleByName(role.getName());
				if (count > 0) {
					isUnique = false;
					break;
				}
			}
			if (isUnique) {
				for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext();)
				{
					Role role = iterator.next();
					log.debug("role" + role.toString());
					_privilegeDao.insertRole(role);
					ids.add(role.getId());
				} 
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("addRoles -- end");
		if (ids.size() == roles.size()) {
			return ids;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#
	 * modifyRole(com.yc.rm.caas.appserver.base.privilege.entity.Role)
	 */
	@Override
	public boolean modifyRole(Role role)
	{
		// TODO Auto-generated method stub
		log.info("modifyRole -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.updateRole(role);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("modifyRole -- end");
		if (count > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#
	 * removeRoles(java.util.List)
	 */
	@Override
	public boolean removeRoles(List<Integer> roleIds)
	{
		// TODO Auto-generated method stub
		log.info("removeRoles -- begin");
		int count = 0;
		try
		{
			for (Iterator<Integer> iterator = roleIds.iterator(); iterator.hasNext();)
			{
				Integer role_id = iterator.next();
				//先删除角色的所有权限
				boolean deleteFlag = this.removeAllRolePrivileges(role_id);
				if (deleteFlag != true) {
					return false;
				}
			}
			count = _privilegeDao.deleteRoles(roleIds);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("removeRoles -- end");
		if (count == roleIds.size()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addPrivileges(List<Privilege> list)
	{
		log.info("addPrivileges -- begin");
		int addCount = 0;
		boolean deleteFlag = false;
		try
		{
			for (Iterator<Privilege> iterator = list.iterator(); iterator.hasNext();)
			{
				Privilege privilege = iterator.next();
				// 判断是否有相同的权限存在
//				int count = _privilegeDao.selectCountOfPrivilege(privilege);
//				if (count != 0)
//				{
//					throw new InvalidArgumentException("一个或多个权限已经存在，请勿重复添加");
//				}
				// 判断角色是否存在
				if (privilege.getMaster().equals("role_id"))
				{
					int countOfRole = _privilegeDao.selectCountOfRole(privilege.getM_value());
					if (countOfRole <= 0) {
						throw new InvalidArgumentException("一个或多个权限的所属角色不存在");
					} else if (countOfRole > 1) {
						throw new InvalidArgumentException("存在一个权限的所属角色多余一个");
					}
				}
				if (privilege.getAccess().equals("menu_id"))
				{
					// 判断权限的菜单是否存在
					List<Integer> ids = new ArrayList<>();
					ids.add(privilege.getA_value());
					if (_privilegeDao.selectMenus(ids).size() <= 0)
					{
						throw new InvalidArgumentException("菜单不存在");
					}
				}
				if (privilege.getAccess().equals("btn_id"))
				{
					// 判断权限的按钮是否存在
					List<Integer> ids = new ArrayList<>();
					ids.add(privilege.getA_value());
					if (_privilegeDao.selectButtons(ids).size() <= 0)
					{
						throw new InvalidArgumentException("按钮不存在");
					}
				}
			}
			deleteFlag = this.removeAllRolePrivileges(list.get(0).getM_value());
			if (deleteFlag == false) {
				return false;
			}
			addCount = _privilegeDao.insertPrivileges(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.debug("addPrivileges  --------  count = "+addCount);
		log.info("addPrivileges -- end");
		if (addCount == list.size()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removePrivilegesById(List<Integer> list)
	{
		log.info("removePrivilegesById -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.deletePrivilegesById(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("removePrivilegesById -- end");
		if (count == list.size()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removePrivileges(List<Privilege> list)
	{
		log.info("removePrivileges -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.deletePrivileges(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("removePrivileges -- end");
		if (count == list.size()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean modifyOper(List<Privilege> list)
	{
		log.info("modifyOper -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.updatePrivilegesOper(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("modifyOper -- end");
		if (count == list.size()) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Privilege> getPrivileges(List<Integer> list)
	{
		log.info("getPrivileges -- begin");
		List<Privilege> privileges = null;
		try
		{
			privileges = _privilegeDao.selectPrivileges(list);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getPrivileges -- end");
		return privileges;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#addUserRoles(java.util.List)
	 */
	@Override
	public boolean addUserRoles(List<UserRole> userRoles)
	{
		// TODO Auto-generated method stub
		log.info("addUserRoles -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.insertUserRoles(userRoles);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("addUserRoles -- end");
		if (count == userRoles.size()) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#removeAllUserRoles(int)
	 */
	@Override
	public boolean removeAllUserRoles(int user_id)
	{
		// TODO Auto-generated method stub
		log.info("removeAllUserRoles -- begin");
		int count = 0;
		int total = 0;
		try
		{
			total = _privilegeDao.selectUserRoles(user_id).size();
			count = _privilegeDao.deleteAllUserRoles(user_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("removeAllUserRoles -- end");
		if (count >= total) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#removeAllRolePrivileges(int)
	 */
	@Override
	public boolean removeAllRolePrivileges(int role_id)
	{
		// TODO Auto-generated method stub
		log.info("removeAllRolePrivileges -- begin");
		int count = 0;
		int total = 0;
		try
		{
			total = _privilegeDao.selectRolePrivilege(role_id).size();
			count = _privilegeDao.deleteAllRolePrivileges(role_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("removeAllRolePrivileges -- end");
		if (count == total) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#selectUserRoles(int)
	 */
	@Override
	public List<Role> getUserRoles(int user_id)
	{
		// TODO Auto-generated method stub
		log.info("selectUserRoles -- begin");
		List<Role> roles = null;
		try
		{
			roles = _privilegeDao.selectUserRoles(user_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("selectUserRoles -- end");
		return roles;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#getRolePrivilege(int)
	 */
	@Override
	public List<Privilege> getRolePrivilege(int role_id)
	{
		// TODO Auto-generated method stub
		log.info("getRolePrivilege -- begin");
		List<Privilege> privileges = null;
		try
		{
			privileges = _privilegeDao.selectRolePrivilege(role_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getRolePrivilege -- end");
		return privileges;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#searchRoles(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Role> searchRoles(RoleOption option)
	{
		// TODO Auto-generated method stub
		log.info("searchRoles -- begin");
		List<Role> roles = null;
		try
		{
			boolean check = this.checkOperationTeam(option.getLogin_id(), option.getTeam_id());
			if (check) {
				roles = _privilegeDao.selectRoles(option);
			} else {
				roles = new ArrayList<Role>();
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("searchRoles -- end");
		return roles;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#searchRolesOfPlatform(com.yc.rm.caas.appserver.base.privilege.entity.RoleOption)
	 */
	@Override
	public List<Role> searchRolesOfPlatform(RoleOption option)
	{
		// TODO Auto-generated method stub
		log.info("searchRolesOfPlatform -- begin");
		List<Role> roles = null;
		try
		{
			roles = _privilegeDao.selectRolesOfPlatform(option);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("searchRolesOfPlatform -- end");
		return roles;
	}
	
	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#getRolesByManagerId(int)
	 */
	@Override
	public List<Role> getRolesByTeamId(int team_id)
	{
		// TODO Auto-generated method stub
		log.info("getRolesByTeamId -- begin");
		List<Role> roles = null;
		try
		{
			roles = _privilegeDao.selectRolesByTeamId(team_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getRolesByTeamId -- end");
		return roles;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#checkOperationPermissionTodistributeRoles(com.yc.rm.caas.appserver.base.privilege.entity.UserRole, int)
	 */
	@Override
	public boolean checkOperationPermissionTodistributeRoles(UserRole userRole, int login_id)
	{
		// TODO Auto-generated method stub
		log.info("checkOperationPermissionTodistributeRoles -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.selectPlatFormManager(login_id);
			if (count <= 0) {
				count = _privilegeDao.selectOperationManager(login_id);
				if (count > 0) {
					count = _privilegeDao.selectTeamMemberByUserId(login_id, userRole.getUser_id());
					if (count > 0) {
						count = _privilegeDao.selectOperationRole(login_id, userRole.getRole_id());
						if (count > 0) {
							return true;
						}
					}
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("checkOperationPermissionTodistributeRoles -- end");
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#checkOperationUserToOtherUserId(int, int)
	 */
	@Override
	public boolean checkOperationPermissionToOtherUserId(int login_id, int user_id) {
		log.info("checkOperationPermissionToOtherUserId -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.selectPlatFormManager(login_id);
			if (count <= 0) {
				count = _privilegeDao.selectOperationManager(login_id);
				if (count > 0) {
					count = _privilegeDao.selectTeamMemberByUserId(login_id, user_id);
					if (count > 0) {
						return true;
					}
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("checkOperationPermissionToOtherUserId -- end");
		if (count > 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#getTeamByUserId(int)
	 */
	@Override
	public List<TeamBean> getTeamByUserId(int user_id)
	{
		// TODO Auto-generated method stub
		log.info("selectTeamByUserId -- begin");
		List<TeamBean> teams = null;
		try
		{
			boolean check = this.checkPlatFormManager(user_id);
			if (check) {
				teams = _privilegeDao.selectAllTeams();
			} else {
				boolean checkOperation = this.checkOperationManager(user_id);
				if (checkOperation) {
					teams = _privilegeDao.selectTeamByUserId(user_id);
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("selectTeamByUserId -- end");
		return teams;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#checkOperationRole(int, int)
	 */
	@Override
	public boolean checkOperationRole(int login_id, int role_id)
	{
		// TODO Auto-generated method stub
		log.info("checkOperationRole -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.selectPlatFormManager(login_id);
			if (count <= 0) {
				count = _privilegeDao.selectOperationManager(login_id);
				if (count > 0) {
					count = _privilegeDao.selectOperationRole(login_id, role_id);
				} else {
					return false;
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("checkOperationRole -- end");
		if (count > 0) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#checkOperationTeam(int, int)
	 */
	@Override
	public boolean checkOperationTeam(int login_id, int team_id)
	{
		// TODO Auto-generated method stub
		log.info("checkOperationTeam -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.selectPlatFormManager(login_id);
			if (count <= 0) {
				count = _privilegeDao.selectOperationManager(login_id);
				if (count > 0) {
					count = _privilegeDao.selectOperationTeam(login_id, team_id);
				} else {
					return false;
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		if (count > 0) {
			log.info("checkOperationTeam -- end");
			return true;
		}
		log.info("checkOperationTeam -- end");
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#getMenuByRoleId(int)
	 */
	@Override
	public List<Menu> getMenuByRoleId(int role_id)
	{
		// TODO Auto-generated method stub
		log.info("selectMenuByRoleId -- begin");
		List<Menu> menus = null;
		try
		{
			menus = _privilegeDao.selectMenuByRoleId(role_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("selectMenuByRoleId -- end");
		return menus;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#checkOperationManager(int)
	 */
	@Override
	public boolean checkOperationManager(int login_id)
	{
		// TODO Auto-generated method stub
		log.info("checkOperationManager -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.selectOperationManager(login_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		if (count > 0) {
			log.info("checkOperationManager -- end");
			return true;
		}
		log.info("checkOperationManager -- end");
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPermissionService#checkPlatFormManager(int)
	 */
	@Override
	public boolean checkPlatFormManager(int login_id)
	{
		// TODO Auto-generated method stub
		log.info("checkPlatFormManager -- begin");
		int count = 0;
		try
		{
			count = _privilegeDao.selectPlatFormManager(login_id);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		if (count > 0) {
			log.info("checkPlatFormManager -- end");
			return true;
		}
		log.info("checkPlatFormManager -- end");
		return false;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPrivilegeServ#selectAllRoles()
	 */
	@Override
	public List<Role> getAllRoles()
	{
		// TODO Auto-generated method stub
		log.info("getAllRoles -- begin");
		List<Role> roles = null;
		try
		{
			roles = _privilegeDao.selectAllRoles();
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getAllRoles -- end");
		return roles;
	}

	/* (non-Javadoc)
	 * @see com.yc.rm.caas.appserver.base.privilege.service.IPrivilegeServ#selectRoleById(int)
	 */
	@Override
	public Role getRoleById(int roleId)
	{
		// TODO Auto-generated method stub
		log.info("getRoleById -- begin");
		Role role = null;
		try
		{
			role = _privilegeDao.selectRoleById(roleId);
		} catch (Exception e)
		{
			// TODO: handle exception
			log.error("", e);
		}
		log.info("getRoleById -- end");
		return role;
	}

}
