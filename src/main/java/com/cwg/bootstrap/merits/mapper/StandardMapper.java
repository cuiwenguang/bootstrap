package com.cwg.bootstrap.merits.mapper;

import java.util.List;

import com.cwg.bootstrap.merits.model.Standard;

public interface StandardMapper {
    int deleteByPrimaryKey(Integer standardId);

    int insert(Standard record);

    int insertSelective(Standard record);

    Standard selectByPrimaryKey(Integer standardId);

    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKey(Standard record);
    
    List<Standard> selecList();
    
    List<Standard> selecListByParentId(Integer parentId);
}