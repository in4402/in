package com.ez.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.board.model.BoardService;
import com.ez.herb.board.model.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardDetailController {
	private static final Logger logger=LoggerFactory.getLogger(BoardDetailController.class);
	@Autowired private BoardService boardService;
	
	@RequestMapping("/countUpdate.do")
	public String countUpdate(@RequestParam(defaultValue="0") int no, Model model) {
		//1.
		logger.info("조회수 증가 처리, 파라미터 no{}",no);
		
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		
		//2.
		int cnt=boardService.updateReadCount(no);
		logger.info("조회수 증가 결과, cnt={}",cnt);
		
		String msg="", url="";
		if(cnt>0) {
			url="/board/detail.do?no="+no;
		}else {
			msg="조회수 증가 실패";
			url="/board/list.do";
		}
		
		//3.
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
	
	@RequestMapping("/detail.do")
	public String detail(@RequestParam(defaultValue="0") int no, ModelMap model) {
		//1.
		logger.info("글 상세 보기, 파라미터 no={}",no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		//2.
		BoardVO boardVo=boardService.selectByNo(no);
		logger.info("글 상세보기 결과, boardVo={}",boardVo);
		
		//3.
		model.addAttribute("vo", boardVo);
		
		return "board/detail";
	}
}
