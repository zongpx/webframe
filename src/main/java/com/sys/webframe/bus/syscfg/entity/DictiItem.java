package com.sys.webframe.bus.syscfg.entity;

/**
 * 字典配置
 * @author suntf
 * @date 2016年12月1日
 */
public class DictiItem
{
	private String item_id;
	
	private String item_name;
	
	private String item_value;
	
	private String remark;

	/**
	 * @return the item_id
	 */
	public String getItem_id()
	{
		return item_id;
	}

	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id)
	{
		this.item_id = item_id;
	}

	/**
	 * @return the item_name
	 */
	public String getItem_name()
	{
		return item_name;
	}

	/**
	 * @param item_name the item_name to set
	 */
	public void setItem_name(String item_name)
	{
		this.item_name = item_name;
	}

	/**
	 * @return the item_value
	 */
	public String getItem_value()
	{
		return item_value;
	}

	/**
	 * @param item_value the item_value to set
	 */
	public void setItem_value(String item_value)
	{
		this.item_value = item_value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
