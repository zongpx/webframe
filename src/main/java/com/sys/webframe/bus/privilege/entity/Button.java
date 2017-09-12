/**
 * date: 2017年6月13日
 */
package com.sys.webframe.bus.privilege.entity;

import com.sys.webframe.core.support.BaseModel;

/**
 * @name: Button.java
 * @Description: 按钮模型类
 * @author duanyongrui
 * @since: 2017年6月13日
 */
public class Button extends BaseModel
{
	private int id;
	/**
	 * 是否有此按钮的权限
	 */
	private int isHave;
	/**
	 * 按钮名称
	 */
	private String name;
	/**
	 * 按钮请求的url
	 */
	private String url;
	/**
	 * 按钮所在的menuid
	 */
	private int menu_id;
	/**
	 * 排列序列号
	 */
	private int seq;
	/**
	 * 是否可见 1/0，默认可见 1
	 */
	private int visible = 1;

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

	public int getMenu_id()
	{
		return menu_id;
	}

	public void setMenu_id(int menu_id)
	{
		this.menu_id = menu_id;
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

	/**
	 * 
	 */
	public Button()
	{
		super();
	}

	/**
	 * 修改该按钮可见性时使用
	 * 
	 * @param id
	 * @param visible
	 */
	public Button(int id, int visible)
	{
		super();
		this.id = id;
		this.visible = visible;
	}

	/**
	 * 添加按钮时使用
	 * 
	 * @param name
	 * @param url
	 * @param menu_id
	 * @param visible
	 */
	public Button(String name, String url, int menu_id, int visible)
	{
		super();
		this.name = name;
		this.url = url;
		this.menu_id = menu_id;
	}

}
