package com.cwg.bootstrap.system.mapper;

import java.util.Map;

import com.cwg.bootstrap.system.model.Role;

public interface RoleMapper {
	int deleteByPrimaryKey(Integer roleId);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleId);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/**
	 * 更新角色的资源
	 * 
	 * @param params {roleId:Integer, resourceIds: List<Integer>}
	 * @return
	 */
	int updateResource(Map<String, Object> params);
}