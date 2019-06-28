package com.jt.service;


import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

@Service
public class itemServiceImpl implements ItemService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIData findItemByPage(Integer page, Integer rows) {
		//1.获取商品记录总数---利用 mybatis-plus
		int total =itemMapper.selectCount(null);
		//2.分页之后回传的数据
		int start=(page-1)*rows;
		List<Item>  itemList=itemMapper.findItemByPage(start,rows);
		return new EasyUIData(total,itemList);
	}

	@Override //新增操作
	@Transactional  	//需要控制事务---
	public void saveItem(Item item,ItemDesc itemDesc) {
		item.setStatus(1);//添加新增页面没有的 数据  一个是状态信息 一个是修改时间
		item.setCreated(new Date()).setUpdated(item.getCreated());
		itemMapper.insert(item);
		
		itemDesc.setItemId(item.getId())
		.setCreated(item.getCreated()).setUpdated(item.getUpdated());
		itemDescMapper.insert(itemDesc);
	}

	@Override     //修改商品信息
	@Transactional   
	public void updateItem(Item item,ItemDesc itemDesc) {
		item.setUpdated(new Date());
		itemMapper.updateById(item);
		
		//同时修改两张表
		itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	@Transactional
	public void deleteItem(Long[] ids) {
		//1.手动删除
		//itemMapper.deleteItem(ids);
		//2.mubatisplus  自动删除
		List<Long>  itemList=Arrays.asList(ids);
		itemMapper.deleteBatchIds(itemList);
		
		//2.两张表一起删除
		itemDescMapper.deleteBatchIds(itemList);
	}

	@Override  
	@Transactional
	public void updateStatus(Long[] ids, int status) {
		Item item = new Item();
		item.setStatus(status).setUpdated(new Date());
		List<Long> asList = Arrays.asList(ids);
		UpdateWrapper updateWrapper=new UpdateWrapper();
		updateWrapper.in("id", asList);
		itemMapper.update(item, updateWrapper);
	}
	
	
	
	
//=======================================
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		return itemDescMapper.selectById(itemId) ;
	}

	@Override
	public Item findItemById(Long id) {
		
		return  itemMapper.selectById(id);
	}



















































}
