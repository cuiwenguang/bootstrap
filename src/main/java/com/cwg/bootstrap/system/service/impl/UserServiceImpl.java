package com.cwg.bootstrap.system.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwg.bootstrap.system.mapper.UserMapper;
import com.cwg.bootstrap.system.model.User;
import com.cwg.bootstrap.system.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public boolean create(User user) {
		String pwd = new Md5Hash(user.getPassword(), user.getSalt(), 2).toString();
		String salt = String.valueOf(new Random().nextInt(10000)); 
		user.setPassword(pwd);
		user.setSalt(salt);
		return userMapper.insert(user) > 0;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int assignRole(Integer userId, List<Integer> roleIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean changePassword(String account, String oldPwd, String newPwd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeState(Integer userId, String state) {
		// TODO Auto-generated method stub
		return false;
	}

}
