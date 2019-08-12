package com.cwg.bootstrap.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwg.bootstrap.system.mapper.RoleMapper;
import com.cwg.bootstrap.system.model.Role;
import com.cwg.bootstrap.system.model.RoleResourceKey;
import com.cwg.bootstrap.system.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleMapper roleMapper;
	@Override
	public int add(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public int remove(Integer roleId) {
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
	public Role getById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public int assign(Integer roleId, List<Integer> resourceIds) {
		if(resourceIds==null) {
			return 0;
		}
		Map<String, Object> paramsMap = new HashMap<>(); 
		paramsMap.put("roleId", roleId);
		paramsMap.put("resourceIds", resourceIds);
		return roleMapper.updateResource(paramsMap);
	}
	
	

}
