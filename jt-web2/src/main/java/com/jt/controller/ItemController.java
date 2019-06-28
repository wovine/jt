package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.servcice.ItemService;

@Controller
@RequestMapping
public class ItemController {
	/*根据商品Id查询后台服务器数据\
	 * 步骤:
	 * 1.在前台service实现httpclient的调用
	 * 2.后台根据itemID查询数据集返回对象的json串
	 * 3.将json串转换为item对象
	 * 4.将item丢下保存到request域中
	 * 5.返回页面逻辑名称item
	 */
	@Autowired
	private  ItemService itemService;
	

	@RequestMapping("/items/{itemId}")
	public String findItemById(@PathVariable Long itemId,Model model) {
		Item item=itemService.findItemById(itemId);
		model.addAttribute("item", item);  //存入域中的名字必须和jsp名字一致
		
		ItemDesc itemDesc=itemService.findItemDescById(itemId);    //查询商品详情,
		model.addAttribute("itemDesc", itemDesc); //存到域中,jsp取值
		return "item";	
	}




















}
