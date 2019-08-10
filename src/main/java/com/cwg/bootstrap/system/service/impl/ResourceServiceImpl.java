package com.cwg.bootstrap.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cwg.bootstrap.system.mapper.ResourceMapper;
import com.cwg.bootstrap.system.model.Resource;
import com.cwg.bootstrap.system.service.IResourceService;

public class ResourceServiceImpl implements IResourceService {

	@Autowired
	ResourceMapper resourceMapper;
	
	@Override
	public int add(Resource resource) {
		return resourceMapper.insert(resource);
	}

	@Override
	public int save(Resource resource) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(Long resourceId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Resource> getList(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

}
