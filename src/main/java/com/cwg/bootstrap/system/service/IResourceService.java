/**
 * 资源管理
 */
package com.cwg.bootstrap.system.service;

import java.util.List;

import com.cwg.bootstrap.system.model.Resource;

public interface IResourceService {
	public int add(Resource resource);
	public int save(Resource resource);
	public int remove(Long resourceId);
	public List<Resource> getList(Resource resource);
}
