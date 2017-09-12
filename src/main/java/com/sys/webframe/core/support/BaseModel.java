/**
 * 
 */
package com.sys.webframe.core.support;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author stephen
 * 
 */
public class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4021044518958641455L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
