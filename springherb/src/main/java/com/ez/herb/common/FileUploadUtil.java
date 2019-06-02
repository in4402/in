package com.ez.herb.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUploadUtil {
	private static final Logger logger
		=LoggerFactory.getLogger(FileUploadUtil.class);
	
	@Resource(name="fileuploadProperties")
	private Properties fileProperties;
	
	public List<Map<String, Object>> fileUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		//파일 업로드 처리
		MultipartHttpServletRequest multiRequest 
			=(MultipartHttpServletRequest)request;
		
		Map<String, MultipartFile> fileMap=multiRequest.getFileMap();
		
		//결과를 저장할 List
		List<Map<String, Object>> resultList
			=new ArrayList<Map<String,Object>>();
		
		Iterator<String> iter =fileMap.keySet().iterator();
		while(iter.hasNext()) {
			String key=iter.next();
			MultipartFile tempFile=fileMap.get(key);
			//=> 업로드된 파일을 임시파일 형태로 제공함
			
			//업로드된 경우에, 업로드된 파일의 정보를 얻어온다
			if(!tempFile.isEmpty()) {
				String originFileName=tempFile.getOriginalFilename();
				long fileSize=tempFile.getSize();
				
				//파일명이 중복되는 경우, 파일이름 변경
				String fileName=getUniqueFileName(originFileName);
				
				//파일 업로드 처리
				String path=getUploadPath(request);
				File file = new File(path, fileName);
				tempFile.transferTo(file);
				
				
				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("originalFileName", originFileName);
				resultMap.put("fileSize", fileSize);
				resultMap.put("fileName", fileName);
				
				resultList.add(resultMap);
			}
		}//while
		
		return resultList;
	}
	
	public String getUploadPath(HttpServletRequest request) {
		//업로드 경로 구하기
		String uploadPath="";
		String type=fileProperties.getProperty("file.upload.type");
		if(type.equals("test")) {
			//테스트 환경인 경우
			uploadPath=fileProperties.getProperty("file.upload.path.test");			
		}else {
			//배포시
			uploadPath=fileProperties.getProperty("file.upload.path");
			
			//실제 물리적인 경로 구하기
			uploadPath=request.getSession().getServletContext().getRealPath(uploadPath);
		}
		
		logger.info("type={}, uploadPath={}", type, uploadPath);
		
		return uploadPath;
	}

	public String getUniqueFileName(String originFileName) {
		//현재시간을 이용하여 파일명 변경하기
		//순수파일명_현재시간.확장자
		//예) abc.txt => abc_20190118150530123.txt
		
		int lastIdx = originFileName.lastIndexOf(".");
		String fileNm = originFileName.substring(0, lastIdx);  //abc
		String ext = originFileName.substring(lastIdx);   //.txt
		
		String fileName = fileNm+"_"+getCurrentMilis()+ext;
		
		return fileName;		
	}
	
	public String getCurrentMilis() {
		//현재시간을 밀리초까지 표현
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str=sdf.format(today);
		logger.info("현재시간:{} 밀리초", str);
		
		return str;
	}
	
	
}









