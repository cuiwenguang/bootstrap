package com.cwg.bootstrap.merits.servcie.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cwg.bootstrap.merits.mapper.StandardMapper;
import com.cwg.bootstrap.merits.model.Standard;
import com.cwg.bootstrap.merits.servcie.IStandardService;

public class StandardService implements IStandardService {
	@Autowired
	StandardMapper standardMapper;

	@Override
	public boolean add(Standard standard) {
		return standardMapper.insert(standard) > 0;
	}

	@Override
	public boolean update(Standard standard) {
		return standardMapper.updateByPrimaryKeySelective(standard) > 0;
	}

	@Override
	public boolean remove(Integer standardId) {
		return standardMapper.deleteByPrimaryKey(standardId) > 0;
	}

	@Override
	public Standard getById(Integer standardId) {
		return standardMapper.selectByPrimaryKey(standardId);
	}
	
	@Override
	public List<Standard> getListByParentId(Integer parentId) {
		return standardMapper.selecListByParentId(parentId);
	}
	
	@Override
	public List<Standard> getTreeList() {
		List<Standard> list = standardMapper.selecList();
		return buildTree(list, 0);
	}

	// 构造指标树
	private List<Standard> buildTree(List<Standard> sourceData, Integer parentId) {
		List<Standard> result = new ArrayList<>();
		for (Standard standard : sourceData) {
			if (standard.getParentId().equals(parentId)) {
				standard.setChildren(buildTree(sourceData, standard.getStandardId()));
				result.add(standard);
			}
		}
		return result;
	}
}
