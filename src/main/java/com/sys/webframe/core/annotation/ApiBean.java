/**
 * 
 */
package com.sys.webframe.core.annotation;

import java.util.List;

import com.sys.webframe.core.support.BaseModel;


/**
 * @author stephen
 * 
 */
public class ApiBean extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 216785266120946072L;
	private String apiName;
	private List<MethodBean> methods;

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public List<MethodBean> getMethods() {
		return methods;
	}

	public void setMethods(List<MethodBean> methods) {
		this.methods = methods;
	}
}
