package com.cwg.bootstrap.system.auth;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;	
	private String token;
	private String host;
	
	public JwtToken() {}
	
	public JwtToken(String token2) {
		this.token = token2;
	}
	
	public JwtToken(String token2, String host) {
		this.token = token2;
		this.host = host;
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
