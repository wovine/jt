package com.jt.servcice;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface ItemService {

	//web根据id查询商品
	Item findItemById(Long itemId);

	//商品详情
	ItemDesc findItemDescById(Long itemId);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
