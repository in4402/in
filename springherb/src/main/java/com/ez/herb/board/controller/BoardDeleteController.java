package com.ez.herb.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.board.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardDeleteController {
	
	private static final Logger logger=LoggerFactory.getLogger(BoardDeleteController.class);
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String delete_get(@RequestParam(defaultValue="0") int no,Model model) {
		//1.
		logger.info("삭제화면 no={}",no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/board/list.do");
			
			return "common/message";
		}
		//2.
		//3.
		//4.
		return "board/delete";
	}
	@RequestMapping(value="/delete.do", method=RequestMethod.POST)
	public String delete_post(@RequestParam(defaultValue="0") int no, @RequestParam String pwd, Model model) {
		//1.
		logger.info("삭제 처리  파라미터 no={}, pwd={}", no, pwd);
		
		//2.
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("no", no);//modelattribute 어노테이션으로 하면 set까지 전부 되지만 map을 파라미터로 전해주기 위해서는
		map.put("pwd", pwd);//map.put() 즉 받아온 no와 pwd를 map에  set을 해줘야함
		
		int cnt=boardService.deleteBoard(map);
		logger.info("글 삭제 결과, cnt={}",cnt);
		
		String msg="",url="";
		if(cnt>0) {
			msg="삭제에 성공하셨습니다.";
			url="/board/list.do";
		}else {
			msg="삭제에 실패하셨습니다.";
			url="/board/delete.do?no="+no;
		}
		//3.
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "common/message";
	}
}
