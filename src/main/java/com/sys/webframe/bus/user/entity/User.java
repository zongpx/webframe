package com.sys.webframe.bus.user.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Description 用户管理
 * @date 2017年7月5日 上午9:54:27
 * 
 */
public class User implements Serializable
{
	private static final long serialVersionUID = 4817054344128452957L;

	/**
	 * 返回结果状态
	 */
	private int resultCode;

	/**
	 * 返回结果信息
	 */
	private String resultInfo;

	/**
	 * 客户ID
	 */
	private int userId;

	/**
	 * 客户姓名
	 */
	private String userName;

	/**
	 * 客户状态码（0：停用，1：正常）
	 */
	private int state;

	/**
	 * 客户状态
	 */
	private String stateName;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 团队ID
	 */
	private int teamId;

	/**
	 * 根团队ID
	 */
	private int topTeamid;

	/**
	 * 团队名称
	 */
	private String teamName;

	/**
	 * 组织ID
	 */
	private String orgId;

	/**
	 * 组织名称
	 */
	private String orgName;

	/**
	 * 用户登录手机号
	 */
	private String userPhone;

	/**
	 * 用户登录密码
	 */
	private String password;

	/**
	 * 原始密码
	 */
	private String oldPassword;

	/**
	 * 新密码
	 */
	private String newPassword;

	/**
	 * 确认新密码
	 */
	private String againPassword;

	/**
	 * 用户注册时间
	 */
	private String createTime;

	/**
	 * C端注册ID
	 */
	private String registrationId;

	/**
	 * 终端类型
	 */
	private String terminalType;

	/**
	 * 终端版本号
	 */
	private String versionCode;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 身份证号
	 */
	private String certificateno;

	/**
	 * CA是否验证
	 */
	private String caAuthor;

	/**
	 * 登录设备
	 */
	private String device;

	/**
	 * 地址
	 */
	private String address;
	/**
	 * IP地址
	 */
	private String ipAdress;

	/**
	 * 最后登录时间
	 */
	private String lastLoginTime;

	/**
	 * 是否删除
	 */
	private String isDelete;

	/**
	 * 说明
	 */
	private String descText;

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public int getTopTeamid()
	{
		return topTeamid;
	}

	public void setTopTeamid(int topTeamid)
	{
		this.topTeamid = topTeamid;
	}

	public String getNewPassword()
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}

	public String getAgainPassword()
	{
		return againPassword;
	}

	public void setAgainPassword(String againPassword)
	{
		this.againPassword = againPassword;
	}

	public String getDescText()
	{
		return descText;
	}

	public void setDescText(String descText)
	{
		this.descText = descText;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getIsDelete()
	{
		return isDelete;
	}

	public void setIsDelete(String isDelete)
	{
		this.isDelete = isDelete;
	}

	public String getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	public String getIpAdress()
	{
		return ipAdress;
	}

	public void setIpAdress(String ipAdress)
	{
		this.ipAdress = ipAdress;
	}

	public String getDevice()
	{
		return device;
	}

	public void setDevice(String device)
	{
		this.device = device;
	}

	public String getCaAuthor()
	{
		return caAuthor;
	}

	public void setCaAuthor(String caAuthor)
	{
		this.caAuthor = caAuthor;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCertificateno()
	{
		return certificateno;
	}

	public void setCertificateno(String certificateno)
	{
		this.certificateno = certificateno;
	}

	public int getResultCode()
	{
		return resultCode;
	}

	public void setResultCode(int resultCode)
	{
		this.resultCode = resultCode;
	}

	public String getResultInfo()
	{
		return resultInfo;
	}

	public void setResultInfo(String resultInfo)
	{
		this.resultInfo = resultInfo;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserPhone()
	{
		return userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public String getStateName()
	{
		return stateName;
	}

	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public int getTeamId()
	{
		return teamId;
	}

	public void setTeamId(int teamId)
	{
		this.teamId = teamId;
	}

	public String getTeamName()
	{
		return teamName;
	}

	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

	public String getOrgId()
	{
		return orgId;
	}

	public void setOrgId(String orgId)
	{
		this.orgId = orgId;
	}

	public String getOrgName()
	{
		return orgName;
	}

	public void setOrgName(String orgName)
	{
		this.orgName = orgName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getRegistrationId()
	{
		return registrationId;
	}

	public void setRegistrationId(String registrationId)
	{
		this.registrationId = registrationId;
	}

	public String getTerminalType()
	{
		return terminalType;
	}

	public void setTerminalType(String terminalType)
	{
		this.terminalType = terminalType;
	}

	public String getVersionCode()
	{
		return versionCode;
	}

	public void setVersionCode(String versionCode)
	{
		this.versionCode = versionCode;
	}

	/**
	 * 格式化输出
	 */
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}