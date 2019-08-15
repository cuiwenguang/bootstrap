package com.cwg.bootstrap.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cwg.bootstrap.system.mapper.UserMapper;
import com.cwg.bootstrap.system.model.User;

public class DBRealm extends AuthorizingRealm {
	@Autowired
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		JwtToken jwtToken = (JwtToken) token;
		String userName = JwtUtil.getUsername(jwtToken.getToken());
		
        if (userName == null) {
            throw new AuthenticationException("token 无效");
        }
        User user = userMapper.selectByUserName(userName);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }

        if (! JwtUtil.verify(jwtToken.getToken(), userName, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
	}
	
 
}
