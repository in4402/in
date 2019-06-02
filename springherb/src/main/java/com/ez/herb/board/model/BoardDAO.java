package com.ez.herb.board.model;

import java.util.List;
import java.util.Map;

import com.ez.herb.common.SearchVO;

public interface BoardDAO {
	public int insertBoard(BoardVO vo);
	public List<BoardVO> selectAll(SearchVO searchVo);
	public int selectTotalRecord(SearchVO searchVo);
	public int updateReadCount(int no);
	public BoardVO selectByNo(int no);
	public int updateBoard(BoardVO vo);
	public int deleteBoard(Map<String, Object> map);
	public List<BoardVO> selectMainNotice();
}
