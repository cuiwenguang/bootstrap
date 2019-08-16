package com.cwg.bootstrap.merits.servcie;

import java.util.List;

import com.cwg.bootstrap.merits.model.Standard;

public interface IStandardService {
	boolean add(Standard standard);
	boolean update(Standard standard);
	boolean remove(Integer standardId);
	Standard getById(Integer standardId);
	List<Standard> getListByParentId(Integer parentId);
	List<Standard> getTreeList();
}
