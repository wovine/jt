package com.jt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class testSentinel {

	@Test
	public  void test01() {
		//sentinels set<String>  IP:端口
		Set<String> sentinels = new HashSet<>();
		
		sentinels.add("192.168.211.141:26379");
		JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);
		
		Jedis jedis = sentinelPool.getResource();
		jedis.set("1900", "行不");
		System.out.println(jedis.get("1900"));
		jedis.close();
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
