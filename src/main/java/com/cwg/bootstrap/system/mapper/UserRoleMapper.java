package com.cwg.bootstrap.system.mapper;

import com.cwg.bootstrap.system.model.UserRoleKey;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);
}