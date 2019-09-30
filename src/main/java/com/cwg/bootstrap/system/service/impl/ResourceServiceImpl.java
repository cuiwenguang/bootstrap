package com.cwg.bootstrap.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwg.bootstrap.system.mapper.ResourceMapper;
import com.cwg.bootstrap.system.model.Resource;
import com.cwg.bootstrap.system.service.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	ResourceMapper resourceMapper;

	@Override
	public int add(Resource resource) {
		return resourceMapper.insert(resource);
	}

	@Override
	public int update(Resource resource) {
		return resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public int remove(Integer resourceId) throws Exception {
		if (resourceMapper.selectChildrenCount(resourceId) > 0) {
			throw new Exception("存在子节点不允许删除");
		}
		return resourceMapper.deleteByPrimaryKey(resourceId);
	}

	@Override
	public List<Resource> getTreeList() {
		List<Resource> resources = resourceMapper.selectList();
		return buildTree(resources, 0);
	}

	// 构造资源树
	private List<Resource> buildTree(List<Resource> sourceData, Integer parentId) {
		List<Resource> result = new ArrayList<>();
		for (Resource resource : sourceData) {
			if (resource.getResourceParent().equals(parentId)) {
				resource.setChildren(buildTree(sourceData, resource.getResourceId()));
				result.add(resource);
			}
		}
		return result;
	}

}
