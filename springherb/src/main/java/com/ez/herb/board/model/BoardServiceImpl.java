package com.ez.herb.board.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.herb.common.SearchVO;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAOMybatis boardDao;

	public int insertBoard(BoardVO vo){
		return boardDao.insertBoard(vo);
	}
	public List<BoardVO> selectAll(SearchVO searchVo) {
		return boardDao.selectAll(searchVo);		
	}
	
	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return boardDao.selectTotalRecord(searchVo);
	}
	public int updateReadCount(int no) {
		return boardDao.updateReadCount(no);
	}
	public BoardVO selectByNo(int no) {
		return boardDao.selectByNo(no);
	}
	public int updateBoard(BoardVO vo) {
		return boardDao.updateBoard(vo);
	}
	public int deleteBoard(Map<String, Object> map) {
		return boardDao.deleteBoard(map);
	}
	public List<BoardVO> selectMainNotice(){
		return boardDao.selectMainNotice();		
	}
}





