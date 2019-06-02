package com.ez.herb.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.board.model.BoardService;
import com.ez.herb.board.model.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardEditController {
	@Autowired private BoardService boardService;
	private static final Logger logger=LoggerFactory.getLogger(BoardEditController.class);
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String edit_get(@RequestParam(defaultValue="0") int no, Model model) {
		//1.
		logger.info("수정 화면, 파라미터 no={}",no);
		if(no==0) {
			model.addAttribute("msg", "잘못된 url 입니다.");
			model.addAttribute("url", "/board/list.do");

			return "common/message";
		}
		//2.
		BoardVO vo=boardService.selectByNo(no);
		logger.info("수정 화면, 조회 결과 vo={}",vo);

		model.addAttribute("vo", vo);

		//3.
		return "board/edit";
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String edit_post(@ModelAttribute BoardVO boardVo, Model model) {
		//1.
		logger.info("수정하기 boardVo={}",boardVo);

		//2.
		int cnt=boardService.updateBoard(boardVo);
		String msg="", url="";
		if(cnt>0) {
			msg="수정에 성공하셨습니다.";
			url="/board/detail.do?no="+boardVo.getNo();
		}else {
			msg="수정에 실패하셨습니다.";
			url="/board/edit.do?no="+boardVo.getNo();
		}

		//3.
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}
}
