/**
 * date: 2017年6月13日
 */
package com.sys.webframe.bus.privilege.entity;

import java.sql.Timestamp;
import java.util.List;

import com.sys.webframe.core.support.BaseModel;


/**
 * @name: Role.java
 * @Description:
 * @author duanyongrui
 * @since: 2017年6月13日
 */
public class Role extends BaseModel
{
	private int id;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色描述
	 */
	private String role_desc;
	
	/**
	 * 被哪个用户创建
	 */
	private int create_id;
	
	/**
	 * 被哪个用户修改
	 */
	private int modify_id;
	
	/**
	 * 修改的时间戳
	 */
	private Timestamp modify_date;
	
	/**
	 * 团队id
	 */
	private int team_id;
	
	/**
	 * 角色拥有的菜单权限
	 */
	private List<Menu> menus;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRole_desc()
	{
		return role_desc;
	}

	public void setRole_desc(String role_desc)
	{
		this.role_desc = role_desc;
	}

	public int getCreate_id()
	{
		return create_id;
	}

	public void setCreate_id(int create_id)
	{
		this.create_id = create_id;
	}

	public int getModify_id()
	{
		return modify_id;
	}

	public void setModify_id(int modify_id)
	{
		this.modify_id = modify_id;
	}
	
	public Timestamp getModify_date()
	{
		return modify_date;
	}

	public void setModify_date(Timestamp modify_date)
	{
		this.modify_date = modify_date;
	}

	public int getTeam_id()
	{
		return team_id;
	}

	public void setTeam_id(int team_id)
	{
		this.team_id = team_id;
	}

	public List<Menu> getMenus()
	{
		return menus;
	}

	public void setMenus(List<Menu> menus)
	{
		this.menus = menus;
	}

	/**
	 * 
	 */
	public Role()
	{
		super();
	}

	/**
	 * 更新角色信息
	 * 
	 * @param name
	 * @param desc
	 * @param modify_id
	 * @param modify_date
	 */
	public Role(String name, String role_desc, int modify_id, Timestamp modify_date)
	{
		super();
		this.name = name;
		this.role_desc = role_desc;
		this.modify_id = modify_id;
		this.modify_date = modify_date;
	}

	/**
	 * 新增角色
	 * 
	 * @param name
	 * @param desc
	 * @param create_id
	 * @param modify_id
	 * @param modify_date
	 * @param team_id
	 */
	public Role(String name, String role_desc, int create_id, int modify_id, Timestamp modify_date, int team_id)
	{
		super();
		this.name = name;
		this.role_desc = role_desc;
		this.create_id = create_id;
		this.modify_id = modify_id;
		this.modify_date = modify_date;
		this.team_id = team_id;
	}

}
