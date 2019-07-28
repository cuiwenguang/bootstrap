package com.cwg.bootstrap.service;

import java.util.List;

import com.cwg.bootstrap.domain.Role;

public interface IRoleService {
	public int add(Role role);
	public int remove(int roleId);
	public int update(Role role);
	public List<Role> getList(Role role);
	public Role getById(int roleId);
}
