package com.jt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	//@Autowired
	//private Jedis jedis;   //使用缓存  注入对象

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override     //查询每项的叶子类目 
	public String findItemCatNameById(Long itemCatId) {
		ItemCat itemCat = itemCatMapper.selectById(itemCatId);
		return itemCat.getName(); //获取名字
	}
	
	/**
	 * 1.根据parentId查询数据库记录返回itemCatList集合
	 * 2.将itemCatList集合中的数据按照指定的格式封装为
	 * List<EasyUITree>
	 */
	@Override
	public List<EasyUITree> findItemCatByParentId(Long parentId) {
		List<ItemCat> cartList = findItemCatList(parentId);
		List<EasyUITree> treeList = new ArrayList<>();
		for (ItemCat itemCat : cartList) {
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setId(itemCat.getId());
			easyUITree.setText(itemCat.getName());
			String state=itemCat.getIsParent()?"closed":"open";
			easyUITree.setState(state);
			treeList.add(easyUITree);
		}
		return treeList;
	}
	
	
	//根据父节点查询当前信息
	public List<ItemCat> findItemCatList(Long parentId){
		QueryWrapper<ItemCat> queryWrapper=new QueryWrapper<ItemCat>();
		queryWrapper.eq("parent_id", parentId);
		return itemCatMapper.selectList(queryWrapper);
		
	}

		
	/*@Override   //查询缓存
	public List<EasyUITree> findItemCatByCache(Long parentId) {
		String key="ITEM_CAT_"+parentId;
		String result = jedis.get(key);
		List<EasyUITree> treeList=new ArrayList<>();
		if(StringUtils.isEmpty(result)) {
			//如果为空  查询数据库--调用之前写的方法
			 treeList = findItemCatByParentId(parentId);
			//将查询到的数据存入缓存
			 String json = ObjectMapperUtil.tojson(treeList);
			jedis.setex(key, 7*24*3600, json);
			System.out.println("从数据库中取出数据");
		}else {
			//表示缓存有数据
			 treeList = ObjectMapperUtil.toObject(result,treeList.getClass());
			System.out.println("业务查询缓存");
		}
		return treeList;
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
