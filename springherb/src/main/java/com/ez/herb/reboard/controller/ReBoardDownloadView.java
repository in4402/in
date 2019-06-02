package com.ez.herb.reboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class ReBoardDownloadView  extends AbstractView{
	private static final Logger logger=LoggerFactory.getLogger(ReBoardController.class);
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file=(File) map.get("file");
		String fileName=new String(file.getName().getBytes("euc-kr"),"8859_1");
		logger.info("다운로드할 파일 명 : {}",fileName);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		
		OutputStream os=response.getOutputStream();
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(file);
			FileCopyUtils.copy(fis, os);
			
		}finally {
			if(fis!=null) fis.close();
			if(os!=null) os.close();
		}
	}
}
