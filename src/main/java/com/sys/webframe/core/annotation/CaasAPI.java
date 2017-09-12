/**
 * 
 */
package com.sys.webframe.core.annotation;

/**
 * @author stephen
 *
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口报文注解
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaasAPI {
	/*
	 * 测试请求报文
	 */
	String TREQMSG() default "";

	/*
	 * 测试响应报文
	 */
	String TRSPMSG() default "";

	/*
	 * 错误码
	 */
	String ERRORCODE() default "";
}
