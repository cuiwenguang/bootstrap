package com.cwg.bootstrap.system.mapper;

import com.cwg.bootstrap.system.model.RoleResourceKey;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(RoleResourceKey key);

    int insert(RoleResourceKey record);

    int insertSelective(RoleResourceKey record);
}