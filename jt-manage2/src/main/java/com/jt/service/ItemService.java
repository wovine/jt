package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

public interface ItemService {

	EasyUIData findItemByPage(Integer page, Integer rows);
	//新增商品信息
	void saveItem(Item item, ItemDesc itemDesc);
	//修改商品信息
	void updateItem(Item item, ItemDesc itemDesc);
	//删除商品信息
	void deleteItem(Long[] ids);
	
	//上架下架状态
	void updateStatus(Long[] ids, int status);
	//根据id 查询商品详情
	ItemDesc findItemDescById(Long itemId);
	
	
	//web根据id查询商品信息
	Item findItemById(Long id);

}
