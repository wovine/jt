package com.jt.service;

import java.util.List;

import com.jt.vo.EasyUITree;

public interface ItemCatService {

	String findItemCatNameById(Long itemCatId);

	List<EasyUITree> findItemCatByParentId(Long parentId);

	//查询缓存
	//List<EasyUITree> findItemCatByCache(Long parentId);

	
}
