package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.vo.EasyUIData;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/item")
public class itemController {
	@Autowired
	private ItemService itemService;

	//localhost:8091/item/query?page=1&rows=20
	//查询商品列表信息    分页查询
	@RequestMapping("/query")
	public EasyUIData findItemByPage(Integer page,Integer rows) {
		return itemService.findItemByPage(page,rows);
	}

	//新增操作localhost:8001/item/save
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		try {
			itemService.saveItem(item,itemDesc);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}

	//修改商品信息
	@RequestMapping("/update")
	public SysResult  updateItem(Item item,ItemDesc itemDesc) {		
		try {
			itemService.updateItem(item,itemDesc);
			return SysResult.ok("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail("修改失败");
		}
	}

	//删除商品信息
	//参数 {ids:ids}
	@RequestMapping("/delete")
	public SysResult deleteItem(Long[] ids) {
		try {
			itemService.deleteItem(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail("删除失败");
		}
	}
	//修改商品上架下架状态
	@RequestMapping("/instock")
	public SysResult instock(Long[] ids) {
		try {
			int status=2;   //下架
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail("下架失败");
		}
	}
	@RequestMapping("/reshelf")
	public SysResult reshelf(Long[] ids) {
		try {
			int status=1;  //上架
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail("下架失败");
		}
	}

	//根据商品iD 查询商品详情 '/item/query/item/desc/'
	@RequestMapping("/query/item/desc/{itemId}")    //先根据页面的js写controller 
	public SysResult findItemDescById(@PathVariable Long itemId) {
		try {
			ItemDesc itemDesc=itemService.findItemDescById(itemId);
			return SysResult.ok(itemDesc);  //将数据 带回页面
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}

	}




























































}
