/**
 * date: 2017年6月13日
 */
package com.sys.webframe.bus.privilege.entity;

import java.util.List;

import com.sys.webframe.core.support.BaseModel;


/**
 * @name: Menu.java
 * @Description: 菜单信息
 * @author duanyongrui
 * @since: 2017年6月13日
 */
public class Menu extends BaseModel
{
	private int id;
	/**
	 * 是否有此菜单的权限
	 */
	private int isHave;
	/**
	 * 菜单名
	 */
	private String name;
	/**
	 * 菜单url
	 */
	private String url;
	/**
	 * 父菜单id(根菜单为-1)
	 */
	private int parent_menu_id;
	/**
	 * 菜单排列号
	 */
	private int seq;
	/**
	 * 是否可见 1/0，默认可见 1
	 */
	private int visible = 1;
	/**
	 * 子菜单
	 */
	private List<Menu> submenus;
	/**
	 * 按钮
	 */
	private List<Button> buttons;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIsHave()
	{
		return isHave;
	}

	public void setIsHave(int isHave)
	{
		this.isHave = isHave;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getParent_menu_id()
	{
		return parent_menu_id;
	}

	public void setParent_menu_id(int parent_menu_id)
	{
		this.parent_menu_id = parent_menu_id;
	}

	public int getSeq()
	{
		return seq;
	}

	public void setSeq(int seq)
	{
		this.seq = seq;
	}

	public int getVisible()
	{
		return visible;
	}

	public void setVisible(int visible)
	{
		this.visible = visible;
	}

	public List<Menu> getSubmenus()
	{
		return submenus;
	}

	public void setSubmenus(List<Menu> submenus)
	{
		this.submenus = submenus;
	}

	public List<Button> getButtons()
	{
		return buttons;
	}

	public void setButtons(List<Button> buttons)
	{
		this.buttons = buttons;
	}

	/**
	 * 
	 */
	public Menu()
	{
		super();
	}

	/**
	 * 修改菜单可见性时使用
	 * 
	 * @param id
	 * @param visible
	 */
	public Menu(int id, int visible)
	{
		super();
		this.id = id;
		this.visible = visible;
	}

	/**
	 * 添加菜单时使用
	 * 
	 * @param name
	 * @param url
	 * @param parent_menu_id
	 * @param visible
	 */
	public Menu(String name, String url, int parent_menu_id, int visible)
	{
		super();
		this.name = name;
		this.url = url;
		this.parent_menu_id = parent_menu_id;
	}

}
