package com.cwg.bootstrap.mapper;

import com.cwg.bootstrap.domain.RoleResourceKey;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(RoleResourceKey key);

    int insert(RoleResourceKey record);

    int insertSelective(RoleResourceKey record);
}