package com.cwg.bootstrap.system.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cwg.bootstrap.system.mapper.UserMapper;
import com.cwg.bootstrap.system.model.User;

public class JwtRealm extends AuthorizingRealm  {
	
	@Autowired
	UserMapper userMapper;
	
	public JwtRealm() {
		this.setCredentialsMatcher(new JwtCredentialsMatcher());
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return new SimpleAuthorizationInfo();
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		JwtToken jwtToken = (JwtToken) token;
		String tokenString = jwtToken.getToken();
		User user=userMapper.selectByUserName(JwtUtil.getUsername(tokenString));
		if(user == null) {
			throw new AuthenticationException("token 过期，重新登录");
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),
				user.getSalt(), "jwtRealm");
		return authenticationInfo;
	}

}
