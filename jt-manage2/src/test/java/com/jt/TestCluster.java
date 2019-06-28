package com.jt;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestCluster {

	@Test
		public void testCluster() {
			Set<HostAndPort> nodes=new HashSet<>();
			nodes.add(new HostAndPort("192.168.211.141",7000));
			nodes.add(new HostAndPort("192.168.211.141",7001));
			nodes.add(new HostAndPort("192.168.211.141",7002));
			nodes.add(new HostAndPort("192.168.211.141",7003));
			nodes.add(new HostAndPort("192.168.211.141",7004));
			nodes.add(new HostAndPort("192.168.211.141",7005));
			JedisCluster cluster= new JedisCluster(nodes);
			cluster.set("aa", "are you ok?");
			System.out.println("集群搭建完成"+cluster.get("aa"));
			System.out.println(cluster.get("as"));
			
			
			
		}
	
}
