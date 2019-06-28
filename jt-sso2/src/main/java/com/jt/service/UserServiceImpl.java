package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	/*
	 * 1.param  参数
	 * 2.type   1:用户名      2.手机   3.email	
	 * 3.将type转换为具体字段
	 */
	@Override
	public boolean checkUser(String param, Integer type) {
		//判断输入的字段
		String column=(type==1)?"username":((type==2)?"phone":"email");
		//思考从数据库查询的返回 用什么封装  ---count
		
		QueryWrapper queryWrapper=new QueryWrapper<>();
		queryWrapper.eq(column, param);
		int count =userMapper.selectCount(queryWrapper);
		return count==0?false:true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
