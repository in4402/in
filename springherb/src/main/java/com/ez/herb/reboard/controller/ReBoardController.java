package com.ez.herb.reboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ez.herb.common.FileUploadUtil;
import com.ez.herb.common.PaginationInfo;
import com.ez.herb.common.SearchVO;
import com.ez.herb.common.Utility;
import com.ez.herb.reboard.model.ReBoardService;
import com.ez.herb.reboard.model.ReBoardVO;

@Controller
@RequestMapping("/reBoard")
public class ReBoardController {

	@Autowired
	private ReBoardService reBoardService;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	private static final Logger logger 
	= LoggerFactory.getLogger(ReBoardController.class);

	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String write_get() {
		logger.info("글쓰기 화면 보여주기");

		return "reBoard/write";
	}

	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write_post(@ModelAttribute ReBoardVO vo, 
			HttpServletRequest request,	Model model) {
		//1.
		logger.info("글쓰기 처리, 파라미터 vo={}", vo);

		//업로드 처리
		String fileName="", originalFileName="";
		long fileSize=0;
		try {
			List<Map<String, Object>> list =fileUploadUtil.fileUpload(request);
			for(Map<String, Object> map : list) {
				originalFileName=(String) map.get("originalFileName");
				fileName=(String) map.get("fileName");
				fileSize= (Long) map.get("fileSize");				
			}//for			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//2.
		vo.setOriginalFileName(originalFileName);
		vo.setFileName(fileName);
		vo.setFileSize(fileSize);

		int cnt = reBoardService.insertReBoard(vo);
		logger.info("글쓰기 처리 결과, cnt={}", cnt);

		String msg="", url="";
		if(cnt>0) {
			msg="글쓰기 처리 되었습니다.";
			url="/reBoard/list.do";
		}else {
			msg="글쓰기 실패.";
			url="/reBoard/write.do";
		}

		//3.
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

	@RequestMapping("/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글 목록, 파라미터 searchVo={}", searchVo);

		PaginationInfo pagingInfo = new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());

		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());

		List<ReBoardVO> list=reBoardService.selectAll(searchVo);
		logger.info("글 목록 조회, 결과 list.size={}, setting 후 searchVo={}",
				list.size(), searchVo);

		//전체 레코드 개수 조회
		int totalRecord=reBoardService.selectTotalRecord(searchVo);
		logger.info("글목록 조회, totalRecord={}", totalRecord);
		pagingInfo.setTotalRecord(totalRecord);

		model.addAttribute("alist", list);
		model.addAttribute("pageVo", pagingInfo);

		return "reBoard/list";		
	}

	@RequestMapping("/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue="0") int no, 
			Model model) {
		//1.
		logger.info("조회수 증가 처리, 파라미터 no={}", no);

		if(no==0) {
			model.addAttribute("msg", "잘못된  url입니다.");
			model.addAttribute("url", "/reBoard/list.do");

			return "common/message";
		}

		//2.
		int cnt=reBoardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}", cnt);
		String msg="", url="";
		if(cnt>0) {
			url="/reBoard/detail.do?no="+no;
		}else{
			msg="조회수 증가 실패";
			url="/reBoard/list.do";
		}

		//3.
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

	@RequestMapping("/detail.do")
	public String detail(@RequestParam(defaultValue="0") int no, 
			ModelMap model, HttpServletRequest request) {
		//1.
		logger.info("글 상세보기, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");

			return "common/message";
		}

		//2.
		ReBoardVO vo=reBoardService.selectByNo(no);
		logger.info("글 상세보기 결과, vo={}", vo);

		String fileInfo=reBoardService.getFileInfo(vo.getOriginalFileName(), vo.getFileSize(), request.getContextPath());
		logger.info("fileInfo={}",fileInfo);

		//3.
		model.addAttribute("vo", vo);
		model.addAttribute("fileInfo",fileInfo);

		return "reBoard/detail";
	}

	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue="0") int no, 
			Model model) {
		logger.info("수정 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg","잘못된 url입니다.");
			model.addAttribute("url","/reBoard/list.do");

			return "common/message";
		}

		ReBoardVO vo=reBoardService.selectByNo(no);
		logger.info("수정화면, 조회 결과 vo={}", vo);

		model.addAttribute("vo", vo);

		return "reBoard/edit";
	}

	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String edit_post(@ModelAttribute ReBoardVO vo, Model model) {
		logger.info("수정 처리, 파라미터 vo={}", vo);

		int cnt=reBoardService.updateReBoard(vo);
		logger.info("수정 처리 결과, cnt={}", cnt);

		String msg="", url="";
		if(cnt>0) {
			msg="글 수정되었습니다.";
			url="/reBoard/detail.do?no="+vo.getNo();
		}else {
			msg="글 수정 실패";
			url="/reBoard/edit.do?no="+vo.getNo();
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}

	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue="0") int no, 
			ModelMap model) {
		logger.info("삭제 화면, 파라미터 no={}", no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url입니다.");
			model.addAttribute("url", "/reBoard/list.do");
			return "common/message";
		}

		return "reBoard/delete";
	}

	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
	public String delete_post(@RequestParam(defaultValue="0") int no, 
			@RequestParam String pwd, Model model) {
		logger.info("삭제 처리, 파라미터 no={}, pwd={}", no, pwd);

		String msg="",url="/reBoard/delete.do?no="+no;
		//비밀번호 체크
		if(reBoardService.checkPwd(no, pwd)) {
			//삭제처리		
			ReBoardVO vo=reBoardService.selectByNo(no);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("no", no+"");
			map.put("step", vo.getStep()+"");
			map.put("groupNo",vo.getGroupNo()+"");
			logger.info("글 삭제시 파라미터 map={}", map);

			reBoardService.deleteReBoard(map);
			
			msg="글삭제 되었습니다.";
			url="/reBoard/list.do";
		}else {
			msg="비밀번호가 일치하지 않습니다";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}
	@RequestMapping("/download.do")
	public ModelAndView download(@RequestParam(defaultValue="0") int no, @RequestParam String fileName, HttpServletRequest request) {
		logger.info("파일 다운로드 수 증가 처리, 파라미터 no={}, fileName={}",no,fileName);

		int cnt=reBoardService.updateDownCount(no);
		logger.info("다운로드 수 증가 결과, cnt={}",cnt);

		Map<String, File>map=new HashMap<String, File>();
		String path=fileUploadUtil.getUploadPath(request);

		File file=new File(path,fileName);
		map.put("file",file);

		ModelAndView mav=new ModelAndView("reBoardDownloadView", map);

		return mav;
	}

}
