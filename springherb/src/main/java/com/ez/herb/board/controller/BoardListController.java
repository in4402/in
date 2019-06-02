package com.ez.herb.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ez.herb.board.model.BoardService;
import com.ez.herb.board.model.BoardVO;
import com.ez.herb.common.PaginationInfo;
import com.ez.herb.common.SearchVO;
import com.ez.herb.common.Utility;

@Controller
public class BoardListController {
	@Autowired
	private BoardService boardService;
	private static final Logger logger=LoggerFactory.getLogger(BoardListController.class);
	@RequestMapping("/board/list.do")
	public String list(@ModelAttribute SearchVO searchVo, Model model) {
		logger.info("글목록, 파라미터 searchVo={}",searchVo);
		
		PaginationInfo pagingInfo=new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(searchVo.getCurrentPage());
		
		searchVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		searchVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		
		List<BoardVO> list=boardService.selectAll(searchVo);
		logger.info("글목록 조회, 결과 list.size={}, 세팅 후 searchVo={}",list.size(), searchVo);
		
		//전체레코드 개수 조회
		int totalRecord=boardService.selectTotalRecord(searchVo);
		logger.info("글목록 조회, totalRecord={}",totalRecord);
		pagingInfo.setTotalRecord(totalRecord);
		
		model.addAttribute("alist", list);
		model.addAttribute("pageVo", pagingInfo);
		
		return "board/list";
	}
}
