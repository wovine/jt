package com.jt;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jt.util.HttpClientService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestHttpClient {
	/*测试
	 * 1.实例化httpclient对象
	 * 2.定义路径
	 * 3.定义请求方式
	 * 4.发起请求
	 * 5.获取返回值,判断状态信息 200
	 * 6.获取响应数据
	 * 7.转换为字符串
	 */
	@Autowired
	private CloseableHttpClient client;
	
	
	
	/*	@Test
	public void testhttpclient() throws ClientProtocolException, IOException {
		
		//CloseableHttpClient client = HttpClients.createDefault();
		String url="https://www.baidu.com";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpGet);
		
		if(response.getStatusLine().getStatusCode()==200) {
			System.out.println("访问成功");
			HttpEntity entity = response.getEntity();   //转换为字符串
			
			String result = EntityUtils.toString(entity);
			System.out.println(result);
		}
	}*/
	
	//测试工具API
	@Autowired
	private HttpClientService service;
	@Test
	public void testUtil() {
		String url="https://www.baidu.com";
		String result = service.doGet(url);
		System.out.println(result);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
