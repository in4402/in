package com.ez.herb.reboard.model;

import java.util.List;
import java.util.Map;

import com.ez.herb.common.SearchVO;

public interface ReBoardDAO {
	public int insertReBoard(ReBoardVO vo);
	public List<ReBoardVO> selectAll(SearchVO searchVo);
	public int selectTotalRecord(SearchVO searchVo);
	public int updateReadCount(int no);
	public ReBoardVO selectByNo(int no);
	public int updateReBoard(ReBoardVO vo);
	public int deleteReBoard(Map<String, Object> map);
	public int updateDownCount(int no);
	public String selectPwd(int no);
	
}










