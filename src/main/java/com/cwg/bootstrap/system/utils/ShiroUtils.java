package com.cwg.bootstrap.system.utils;

import org.apache.shiro.SecurityUtils;

import com.cwg.bootstrap.system.model.User;

public final class ShiroUtils {
	public static User getUser() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
	public static String getUserName() {
		return getUser().getUserName();
	}
	
	public static String getSalt() {
		return getUser().getSalt();
	}
}
