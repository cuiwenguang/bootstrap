package com.cwg.bootstrap.system.service.impl;

import java.util.List;

import com.cwg.bootstrap.system.model.User;
import com.cwg.bootstrap.system.service.IUserService;

public class UserServiceImpl implements IUserService {

	@Override
	public String login(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(User user) {
		// TODO Auto-generated method stub
		return false;
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
