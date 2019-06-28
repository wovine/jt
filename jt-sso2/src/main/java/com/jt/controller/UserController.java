package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.vo.SysResult;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/* 1.校验用户是否存在
	 *http://sso.jt.com/user/check/fhgfdhgdrg/1?r=0.9060365614075092&callback=jsonp1561635997352&_=1561636010411
	 * 2."http://sso.jt.com/user/check/{param}/{type}  ---开发手册
	 * 3.返回值是SYSResult  跨域访问,	返回值必须特殊处理callback(josn)
	 */
	
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(@PathVariable String param,
			@PathVariable Integer type,String callback) {
		JSONPObject object=null;
		try {
			boolean flag=userService.checkUser(param,type);
			object=new JSONPObject(callback, SysResult.ok(flag));
		} catch (Exception e) {
			e.printStackTrace();
			object=new JSONPObject(callback, SysResult.fail());
		}
		return object;
	} 
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
