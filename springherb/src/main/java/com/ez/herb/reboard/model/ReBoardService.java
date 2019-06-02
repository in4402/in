package com.ez.herb.reboard.model;

import java.util.List;
import java.util.Map;

import com.ez.herb.common.SearchVO;

public interface ReBoardService {
	public int insertReBoard(ReBoardVO vo);
	public List<ReBoardVO> selectAll(SearchVO searchVo);
	public int selectTotalRecord(SearchVO searchVo);
	public int updateReadCount(int no);
	public ReBoardVO selectByNo(int no);
	public int updateReBoard(ReBoardVO vo);
	public int deleteReBoard(Map<String, Object> map);
	public String getFileInfo(String orginalFileName, long fileSize, String contextPath);
	public int updateDownCount(int no);
	public boolean checkPwd(int no, String pwd);
}
