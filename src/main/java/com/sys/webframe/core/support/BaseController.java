package com.sys.webframe.core.support;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sys.webframe.bus.login.entity.User;

@SessionAttributes("session")
@RequestMapping(value = "/caas", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
public class BaseController {

	protected org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

	protected static String notEmpty(Object value) {
		if (value == null) {
			value = "";
		}
		return String.valueOf(value);
	}

	@SuppressWarnings("rawtypes")
	protected static String get(Map map, String keyName) {
		return notEmpty(map.get(keyName));
	}

	public static User getUser() {
		HttpSession httpSession = getSession();
		User user = null;
		try {
			user = (User) httpSession.getAttribute("user");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	private static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
		}
		return session;
	}

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}
}