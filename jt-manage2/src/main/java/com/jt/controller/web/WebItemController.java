package com.jt.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;

//后台接受前台用户请求获取的商品信息----拦截web层的请求
//url;manage.jt.com/web/item/findItemById?id=562379
@RestController
@RequestMapping("/web/item")
public class WebItemController {
	
	@Autowired
	private ItemService itemservice; 

	@RequestMapping("/findItemById")
	public  Item findItemById(Long id) {
		
		return itemservice.findItemById(id);
	}
	
	@RequestMapping("/findItemDescById")
	public  ItemDesc findItemDescById(Long id) {
		return itemservice.findItemDescById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
