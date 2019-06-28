package com.jt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;

@Controller
public class FileController {
	//当用户上传完成时重定向到上传页面    file.jsp
	/** 思路
	 * 1.获取文件信息--文件名称
	 * 2.指定文件上传磁盘路径
	 * 3.实现上传
	 * @throws Exception 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/file")
	public String file(MultipartFile fileImage) throws IllegalStateException, Exception {
		//1.获取input标签名称
		String inputName = fileImage.getName();
		System.out.println(inputName);
		//2.获取文件名字
		String fileName = fileImage.getOriginalFilename();
		//3.定义文件夹名字
		File fileDir = new File("D:/1-jt/image");
		if(!fileDir.exists()) {
			//不存在就创建文件夹
			fileDir.mkdirs();
		}
		//4.实现文件上传
		fileImage.transferTo(new File("D:/1-jt/image/"+fileName));
		return "redirect:/file.jsp";
	}
	
	@Autowired
	private FileService fileService;
	
	
	//实现文件上传   http://localhost:8091/pic/upload?dir=image
	@RequestMapping("/pic/upload")
	@ResponseBody
	public ImageVO  updateFile( MultipartFile uploadFile) {
		return  fileService.updateFile(uploadFile);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
