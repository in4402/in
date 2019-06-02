package com.ez.herb.board.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.herb.common.SearchVO;

@Repository
public class BoardDAOMybatis implements BoardDAO{
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	private String namespace="config.mybatis.mapper.oracle.board.";
	
	public int insertBoard(BoardVO vo) {
		int cnt=sqlSession.insert(namespace+"insertBoard",vo);
		return cnt;
	}
	
	public List<BoardVO> selectAll(SearchVO searchVo) {
		List<BoardVO> list=sqlSession.selectList(namespace+"selectAll", searchVo);
		return list;
	}
	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		int count=sqlSession.selectOne(namespace+"selectTotalRecord", searchVo);
		return count;
	}
	public int updateReadCount(int no) {
		//조회수 증가
		int cnt=sqlSession.update(namespace+"updateReadCount",no);
		return cnt;
	}
	
	public BoardVO selectByNo(int no) {
		//no에 해당하는 글 조회
		BoardVO vo=sqlSession.selectOne(namespace+"selectByNo",no);
		return vo;
	}
	
	public int updateBoard(BoardVO vo) {
		//글 수정
		int cnt=sqlSession.update(namespace+"updateBoard",vo);
		return cnt;
	}
	
	public int deleteBoard(Map<String, Object> map) {
		//글 삭제
		int cnt=sqlSession.delete(namespace+"deleteBoard",map);
		return cnt;
	}
	
	public List<BoardVO> selectMainNotice() {
		//최근 공지사항 6건 조회하기
		List<BoardVO> list=sqlSession.selectList(namespace+"selectMainNotice");
		return list;
	}
}//class




















