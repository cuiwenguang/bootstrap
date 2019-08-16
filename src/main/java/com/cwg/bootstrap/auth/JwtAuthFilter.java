package com.cwg.bootstrap.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;

public class JwtAuthFilter extends AuthenticatingFilter {

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
	
	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
		String token = ((HttpServletRequest)request).getHeader("Authentication");
		if(!StringUtils.isEmpty(token)){
			return new JwtToken(token);
		}
		return null;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
}
