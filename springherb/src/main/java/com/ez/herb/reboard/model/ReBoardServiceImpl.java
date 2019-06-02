package com.ez.herb.reboard.model;

import java.awt.PageAttributes.OriginType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ez.herb.common.SearchVO;

@Service
public class ReBoardServiceImpl implements ReBoardService {
	@Autowired
	private ReBoardDAO reBoardDao;
			
	public int insertReBoard(ReBoardVO vo) {
		return reBoardDao.insertReBoard(vo);
	}

	public List<ReBoardVO> selectAll(SearchVO searchVo){
		return reBoardDao.selectAll(searchVo);
	}

	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		return reBoardDao.selectTotalRecord(searchVo);
	}
	
	public int updateReadCount(int no){
		return reBoardDao.updateReadCount(no);
	}
			
	public ReBoardVO selectByNo(int no){
		return reBoardDao.selectByNo(no);		
	}
		
	public int updateReBoard(ReBoardVO vo){
		return reBoardDao.updateReBoard(vo);		
	}
	
	
	public int deleteReBoard(Map<String, Object> map) {
		return reBoardDao.deleteReBoard(map);
	}
	@Override
	public int updateDownCount(int no) {
		return reBoardDao.updateDownCount(no);
	}

	@Override
	public String getFileInfo(String originalFileName, long fileSize, String contextPath) {
		String result="";
		if(originalFileName!=null&&!originalFileName.isEmpty()) {
			result="<img src='"+contextPath+"/images/file.gif' alt='파일 이미지'/>";
			result+=originalFileName+"("+fileSize/1000f+"KB)";
		}
		return result;
	}

	@Override
	public boolean checkPwd(int no, String pwd) {
		String dbPwd=reBoardDao.selectPwd(no);
		if(dbPwd.equals(pwd)) {
			return true;//비밀번호 일치
		}
		return false;//비밀번호 불일치
	}
	
	/*
	public List<ReBoardVO> selectMainNotice() throws SQLException{
		return reBoardDao.selectMainNotice();		
	}

	*/
	
}





