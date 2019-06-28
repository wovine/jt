package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.ImageVO;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService{
	/*1.获取图片名字
	 * 2.娇艳是否为图片类型
	 * 3.校验是否为恶意程序
	 * 4.份文件保存,按照时间存储
	 * 5.防止文件重名  用UUID
	 */
	//定义本地磁盘路径
	@Value("${image.localDirPath}")
	private String localDirPath;
	//定义虚拟路径名称
	@Value("${image.urlPath}")
	private String  urlPath;
	
	
	@Override
	public ImageVO updateFile(MultipartFile uploadFile) {
		ImageVO imageVO=new ImageVO();
		//1.获取图片名字
		String fileName = uploadFile.getOriginalFilename();
		fileName=fileName.toLowerCase();
		//2.校验图片类型 ---正则表达式
			/*^ 开始 ,   $ 结束  .代表任意单个字符 .+至少一个的任意字符
			 */
		if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
			imageVO.setError(1);
			return imageVO;
		}
		//3.判断是否为恶意程序--图片的宽高
		try {
			BufferedImage bufferedImage=ImageIO.read(uploadFile.getInputStream());
			int  width=bufferedImage.getWidth();
			int height=bufferedImage.getHeight();
			if(width==0||height==0) {
				imageVO.setError(1);
				return imageVO;
			}
			//4.时间转换为字符串
			String dateDir=new SimpleDateFormat("yyyy/MM/dd").format(new Date());  //时间转换工具
			//5.准备文件夹   D:/1-jt/image/yyyy/MM/dd
			String localDir=localDirPath+dateDir;
			File dirFile = new File(localDir);   //准备文件夹
			if(!dirFile.exists()) {
				dirFile.mkdirs();
			}
			//6.将文件名字UUID重命名
			String uuid=UUID.randomUUID().toString().replace("-","");
			String fileTyoe=fileName.substring(fileName.lastIndexOf("."));//获取最后一个 点的位置,开始截串
			//7.拼接新文件名称 
			String realLocalPath=localDir+"/"+uuid+fileTyoe;
				//文件上传
			uploadFile.transferTo(new File(realLocalPath));
			
			//==拼接url路径http://image.jt.com/yyyy/MM/dd/
			String realUrlPath=urlPath+dateDir+"/"+uuid+fileTyoe;
			System.out.println(realUrlPath);
		
			//8.将文件信息回显给页面
			imageVO.setError(0).setHeight(height).setWidth(width)
			.setUrl(realUrlPath);
		} catch (IOException e) {
			e.printStackTrace();
			imageVO.setError(1);
			return imageVO;
		}
		return imageVO;
	}

}
