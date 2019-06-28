package com.jt.controller;


import java.util.List;

//实现叶子类目的展现
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.anno.Cache_Find;
import com.jt.emu.KEY_ENUM;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 1.用户发起post请求携带了itemCatId=560
	 * 2.servlet request  response
	 * @return   请求路径  url:"/item/cat/queryItemName",
    		  data:{itemCatId:val},
	 */
	//  根据id查询商品叶子类目信息
	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		return itemCatService.findItemCatNameById(itemCatId);
	}
	
	// 新增商品时  选择类目的路径 localhost:8001/item/cat/list  
	//动态接收名称参数,然后为指定参数赋值  
	@RequestMapping("/list")
	@Cache_Find(key="ITEM_CAT",keyType=KEY_ENUM.AUTO)
	public List<EasyUITree> findItemCatByParentId(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		//Long parentId=0L;//查询以及商品分类信息
		return itemCatService.findItemCatByParentId(parentId);
		
		//查询缓存
		//return itemCatService.findItemCatByCache(parentId);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}