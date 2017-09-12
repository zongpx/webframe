/**
 * 
 */
package com.sys.webframe.core.interceptor;

/**
 * @author stephen
 *
 */
public class HttpMsgBean {
	private int success = -1;
	private String method = "";
	private long time = 0L;
	private Object parameter = "";
	private Object result = "";

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
