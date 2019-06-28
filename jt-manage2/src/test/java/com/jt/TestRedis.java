package com.jt;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;

import redis.clients.jedis.Jedis;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TestRedis {
	//IP:端口号
	
	
	@Test  //测试redis的spring类型
	public void testString() throws Exception {
		Jedis jedis = new Jedis("192.168.211.141", 6379);
		jedis.setex("1902",2, "aaaaa");
		System.out.println(jedis.get("aa"));
		Thread.sleep(3000);
		jedis.setnx("1902", "222");
		System.out.println("值是"+jedis.get("1902"));
	}
	
	@Test   //对象和JSON互换
	public void ObjectToJSON() throws Exception {
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(100L).setItemDesc("快快快");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(itemDesc);
		System.out.println(json);
		
		ItemDesc ItemDesc = mapper.readValue(json, ItemDesc.class);
		System.out.println(ItemDesc);
	}
	
	//实现LIST集合和JSON转换
	@Test
	public  void ListToJSON() throws Exception {
		ItemDesc itemDesc1 = new ItemDesc();
		itemDesc1.setItemId(100L).setItemDesc("快快快");
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(100L).setItemDesc("快快快");
		ArrayList<ItemDesc> list = new ArrayList<>();
		list.add(itemDesc);
		list.add(itemDesc1);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println(json);
		
		Jedis jedis = new Jedis("192.168.211.141", 6379);
		jedis.set("itemdesc", json);
		
		String itedjosn = jedis.get("itemdesc");
		List<ItemDesc> descList = mapper.readValue(itedjosn, list.getClass());
		System.out.println(descList);
	
		
	} 
	
	
	

	
	
	
	//利用redis保存业务
}