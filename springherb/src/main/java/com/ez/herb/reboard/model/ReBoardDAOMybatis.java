package com.ez.herb.reboard.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ez.herb.common.SearchVO;

@Repository
public class ReBoardDAOMybatis implements ReBoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.reBoard.";
	
	public int insertReBoard(ReBoardVO vo) {
		//글쓰기		
		int cnt=sqlSession.insert(namespace+"insertReBoard", vo);
		return cnt;
	}
	
	
	public List<ReBoardVO> selectAll(SearchVO searchVo){
		//글 전체 조회
		List<ReBoardVO> list
			=sqlSession.selectList(namespace+"selectAll", searchVo);
		
		return list;
	}


	@Override
	public int selectTotalRecord(SearchVO searchVo) {
		int count=sqlSession.selectOne(namespace+"selectTotalRecord", 
				searchVo);
		
		return count;
	}
	
	public int updateReadCount(int no) {
		//조회수 증가
		int cnt=sqlSession.update(namespace+"updateReadCount", no);
		
		return cnt;
	}
		
	public ReBoardVO selectByNo(int no) {
		//no에 해당하는 글 조회
		ReBoardVO vo=sqlSession.selectOne(namespace+"selectByNo", no);
		return vo;
	}
	
		
	public int updateReBoard(ReBoardVO vo) {
		//글 수정
		int cnt=sqlSession.update(namespace+"updateReBoard", vo);
		return cnt;
	}
	
	
	public int deleteReBoard(Map<String, Object> map) {
		//글 삭제
		int cnt=sqlSession.delete(namespace+"deleteReBoard", map);
		return cnt;
	}


	@Override
	public int updateDownCount(int no) {
		int cnt=sqlSession.update(namespace+"updateDownCount",no);
		return cnt;
	}


	@Override
	public String selectPwd(int no) {
		return sqlSession.selectOne(namespace+"selectPwd",no);
	}
	
	
	/*
	public List<ReBoardVO> selectMainNotice() throws SQLException{
		//최근 공지사항 6건 조회하기
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ReBoardVO> list=new ArrayList<>();
		try {
			con=pool.getConnection();
			
			String sql="select *" + 
					" from" + 
					" (" + 
					"   select * from reBoard order by no desc " + 
					" )" + 
					" where rownum<=6";
			ps=con.prepareStatement(sql);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				ReBoardVO vo = new ReBoardVO();
				vo.setContent(rs.getString("content"));
				vo.setName(rs.getString("name"));				
				vo.setPwd(rs.getString("pwd"));
				vo.setTitle(rs.getString("title"));
				vo.setEmail(rs.getString("email"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRegdate(rs.getTimestamp("regdate"));	
				vo.setNo(rs.getInt("no"));
				
				list.add(vo);
			}
			System.out.println("메인 공지사항 결과 list.size="+list.size());
			
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
	}
	*/
	
}//class




















