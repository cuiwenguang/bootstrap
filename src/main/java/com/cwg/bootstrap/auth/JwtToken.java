package com.cwg.bootstrap.auth;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;	
	private String token;
	
	
	public JwtToken() {}
	
	public JwtToken(String token2) {
		this.token = token2;
	}

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
