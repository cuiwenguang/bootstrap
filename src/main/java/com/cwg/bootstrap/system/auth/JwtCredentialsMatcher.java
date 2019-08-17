package com.cwg.bootstrap.system.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import com.cwg.bootstrap.system.model.User;

public class JwtCredentialsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String tokenString = (String) token.getCredentials(); 
		Object stored = info.getCredentials();
		String salt = stored.toString();
		User user = (User) info.getPrincipals().getPrimaryPrincipal();
		return JwtUtil.verify(tokenString, user.getUserName(), salt);
	}

}
