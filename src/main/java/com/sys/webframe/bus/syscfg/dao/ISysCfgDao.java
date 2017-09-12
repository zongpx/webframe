package com.sys.webframe.bus.syscfg.dao;

import java.awt.geom.Area;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sys.webframe.bus.login.entity.User;
import com.sys.webframe.bus.syscfg.entity.DictiItem;

/**
 * 获取系统配置信息
 * @author suntf
 * @date 2016年12月1日
 */
@Component("_sysCfgDao")
public interface ISysCfgDao
{
	/**
	 * 区域信息
	 * @param fatherid
	 * @param area_type
	 * @return
	 */
	List<Area> getAreaList(@Param("fatherid") String fatherid, @Param("area_type")String area_type);
	
	/**
	 * 获取管家信息	 * @return
	 */
	List<User> getManagerList(@Param("role_id") String role_id);
	
	/**
	 * 获取配置信息
	 */
	List<DictiItem> getDictit(@Param("group_id") String group_id);
	
	/**
	 * 获取配置信息
	 */
	List<DictiItem> getDictitAll(@Param("group_id") String group_id);
    
    /**
     * 通过手机号码加载用户信息
     * @param mobile
     * @return
     */
    List<User> getUserInfoByMobile(@Param("mobile") String mobile);
    
    /**
     * 保存用户对象
     * @param u
     * @return
     */
    void saveUserInfo(User u);
    
    /**
     * 更新用户信息
     * @param u
     */
    void updateUserInfo(User u);
    
    /**
     * 验证url是否被访问
     * @param powerUrl
     * @return
     */
    Map<String,Object> checkUrlExists(@Param("power_url") String powerUrl);
    
    /**
     * 验证权限是否存在
     * @param user_id
     * @param power_id
     * @return
     */
    int checkUserPower(@Param("user_id") String user_id, @Param("power_id") String power_id);
    
    /**
     * 增加访问历史记录
     * @param ip
     * @param user_id
     * @param heads
     * @param powerId
     * @param menu_id
     * @param url
     */
    void addHistory(@Param("ip") String ip, @Param("user_id") String user_id, @Param("heads") String heads, @Param("powerId") String powerId, @Param("menu_id") String menu_id, @Param("url") String url);
    
    /**
     * 获取最大版本
     * @param terminaltype
     * @return
     */
    Map<String,String> selectMaxVesion(@Param("terminaltype") String terminaltype);
    
    
    /**
     * 加载银行信息
     * @param area_id
     * @param fatherId
     * @param bankName
     * @return
     */
    List<Map<String,String>> getBankList(@Param("area_id") String area_id, @Param("father_id") String fatherId, @Param("bankName") String bankName, @Param("startPage") int startPage, @Param("pageSize") int pageSize);
}
