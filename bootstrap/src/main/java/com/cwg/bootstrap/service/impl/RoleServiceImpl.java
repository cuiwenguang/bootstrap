package com.cwg.bootstrap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwg.bootstrap.domain.Role;
import com.cwg.bootstrap.mapper.RoleMapper;
import com.cwg.bootstrap.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleMapper roleMapper;
	@Override
	public int add(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public int remove(int roleId) {
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int update(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}

	@Override
	public List<Role> getList(Role role) {
		return null;
	}

	@Override
	public Role getById(int roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

}
