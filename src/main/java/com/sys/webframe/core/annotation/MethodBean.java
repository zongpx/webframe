/**
 * 
 */
package com.sys.webframe.core.annotation;

import com.sys.webframe.core.support.BaseModel;

/**
 * @author stephen
 *
 */
public class MethodBean extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7995296007306498786L;
	private String method;
	private String reqMsg;
	private String rspMsg;
	private String errorCode;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getReqMsg() {
		return reqMsg;
	}
	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}
	public String getRspMsg() {
		return rspMsg;
	}
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
