package com.cwg.bootstrap.system.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.cwg.bootstrap.system.model.User;

public class JwtAuthFilter extends AuthenticatingFilter {
	
	/**
     * 父类会在请求进入拦截器后调用该方法，返回true则继续，
     * 返回false则会调用onAccessDenied()。
     * 这里在不通过时，还调用了isPermissive()方法。
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if(isLoginRequest(request, response)) {
			return true;
		}
		boolean allowed = false;
		try {
			allowed = executeLogin(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allowed || isPermissive(mappedValue);
	}
	
	/**
     * 这里重写了父类的方法，使用我们自己定义的Token类，提交给shiro
     * 这个方法返回null的话会直接抛出异常，进入isAccessAllowed（）的异常处理逻辑。
     */
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		String token = ((HttpServletRequest)request).getHeader("Authentication");
		if(!StringUtils.isEmpty(token)){
			return new JwtToken(token);
		}
		return null;
	}
	
	/**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletResponse httpResponse = WebUtils.toHttp(response);
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json;charset=UTF-8");
		httpResponse.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
		return false;
	}
}
