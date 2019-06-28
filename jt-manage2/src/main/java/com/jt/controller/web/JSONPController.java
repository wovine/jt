package com.jt.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.ItemDesc;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;

//在后台编辑方法
@RestController
public class JSONPController {

		//http://manage.jt.com/web/testJSONP?callback=hello&_=1561596064167
	@RequestMapping("/web/testJSONP")
	public String testJSONP(String callback) {
	User user = new User();
	user.setId(100L);
		
	user.setUsername("tomcat");
		String json = ObjectMapperUtil.tojson(user);
		return callback+"("+json+")";
		
	}
	
	
	
	
	
}
