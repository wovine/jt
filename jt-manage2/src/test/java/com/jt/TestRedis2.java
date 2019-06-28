package com.jt;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

//测试redis事务控制 hash  list
public class TestRedis2 {
	
	@Test
	public  void  testHash() {
			Jedis jedis = new Jedis("192.168.211.141", 6379);
			jedis.hset("user", "id", "200");
			jedis.hset("user", "name", "value");
			String result = jedis.hget("user", "id");
			System.out.println(result);
	}
	
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.211.141", 6379);
	jedis.lpush("list", "1","2","3");
	System.out.println(jedis.lpop("list"));
		
	}
	
	@Test  //事务控制
	@Transactional
	public void testTran() {
			
		
		
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
