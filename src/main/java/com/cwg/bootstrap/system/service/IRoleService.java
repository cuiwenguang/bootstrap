package com.cwg.bootstrap.system.service;

import java.util.List;

import com.cwg.bootstrap.system.model.Role;

public interface IRoleService {
	public int add(Role role);
	public int remove(Integer roleId);
	public int update(Role role);
	public List<Role> getList(Role role);
	public Role getById(Integer roleId);
}
