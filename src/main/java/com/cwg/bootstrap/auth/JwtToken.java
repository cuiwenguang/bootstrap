package com.cwg.bootstrap.auth;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;	
	private String token;
	
	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
