package com.cwg.bootstrap.system.service;

import java.util.List;

import com.cwg.bootstrap.system.model.User;

public interface IUserService {
	boolean create(User user);
	boolean update(User user);
	int assignRole(Integer userId, List<Integer> roleIds);
	boolean changePassword(String account, String oldPwd, String newPwd);
	boolean changeState(Integer userId, String state);
}
