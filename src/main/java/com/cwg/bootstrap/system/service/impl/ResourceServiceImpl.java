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
	public int save(Resource resource) {
		return resourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public int remove(Long resourceId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Resource> getTreeList() {
		// TODO Auto-generated method stub
		List<Resource> resources = resourceMapper.selectList();
		return buildTree(resources, 0L);
	}
	
	// 构造资源树
	private List<Resource> buildTree(List<Resource> sourceData, Long parentId) {
		List<Resource> result = new ArrayList<>();
		for (Resource resource : sourceData) {
			if(resource.getResourceParent().equals(parentId)) {
				resource.setChildren(buildTree(sourceData, resource.getResourceId()));
				result.add(resource);
			}
		}
		return result;
	}
	

}
