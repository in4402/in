package com.ez.herb.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOMybatis implements MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlsession;

	private String namespace="config.mybatis.mapper.oracle.member.";

	public String selectPwd(String userid) { 
		//로그인
		String pwd=sqlsession.selectOne(namespace+"selectPwd",userid);
		return pwd;
	}

	public MemberVO selectMember(String userid)  { 
		//아이디에 해당하는 회원 레코드 조회하기
		MemberVO vo=sqlsession.selectOne(namespace+"selectMeber",userid);
		return vo;
	}
	public int duplicateUserid(String userid) {
		//아이디 중복확인
		int count=sqlsession.selectOne(namespace+"selectDuplicate",userid);
		return count;
	}
	/*
	 * public int insertMember(MemberVO vo) throws SQLException { //회원가입 처리
	 * Connection con=null; PreparedStatement ps=null;
	 * 
	 * try { //[1][2] con con=pool.getConnection();
	 * 
	 * //[3] ps String sql="insert into member(no, userid, name, pwd, email,hp, " +
	 * "zipcode, address, addressdetail)" +
	 * " values(member_seq.nextval, ?,?,?,?,?,?,?,?)"; ps=con.prepareStatement(sql);
	 * ps.setString(1, vo.getUserid()); ps.setString(2, vo.getName());
	 * ps.setString(3, vo.getPwd()); ps.setString(4, vo.getEmail()); ps.setString(5,
	 * vo.getHp()); ps.setString(6, vo.getZipcode()); ps.setString(7,
	 * vo.getAddress()); ps.setString(8, vo.getAddressDetail());
	 * 
	 * //[4] exec int cnt=ps.executeUpdate();
	 * System.out.println("회원가입 결과 cnt="+cnt+", 입력값 vo="+vo);
	 * 
	 * return cnt; }finally { pool.dbClose(ps, con); }
	 * 
	 * }
	 * 
	 * 
	 * public int updateMember(MemberVO vo) throws SQLException { //회원정보 수정 처리
	 * Connection con=null; PreparedStatement ps=null;
	 * 
	 * try { //[1][2] con con=pool.getConnection();
	 * 
	 * //[3] ps String sql="update member set email=?,hp=?, " +
	 * "zipcode=?, address=?, addressdetail=?" + " where userid=?";
	 * ps=con.prepareStatement(sql); ps.setString(1, vo.getEmail()); ps.setString(2,
	 * vo.getHp()); ps.setString(3, vo.getZipcode()); ps.setString(4,
	 * vo.getAddress()); ps.setString(5, vo.getAddressDetail()); ps.setString(6,
	 * vo.getUserid());
	 * 
	 * //[4] exec int cnt=ps.executeUpdate();
	 * System.out.println("회원정보 수정 결과 cnt="+cnt+", 입력값 vo="+vo);
	 * 
	 * return cnt; }finally { pool.dbClose(ps, con); }
	 * 
	 * }
	 * 
	 * public int withdrawMember(String userid) throws SQLException { //회원탈퇴 처리
	 * Connection con=null; PreparedStatement ps=null;
	 * 
	 * try { con=pool.getConnection();
	 * 
	 * String sql="update member" + " set outdate=sysdate" + " where userid=?";
	 * ps=con.prepareStatement(sql); ps.setString(1, userid);
	 * 
	 * int cnt=ps.executeUpdate();
	 * System.out.println("회원탈퇴 결과 cnt="+cnt+", 입력값 userid="+userid);
	 * 
	 * return cnt; }finally { pool.dbClose(ps, con); }
	 * 
	 * }
	 */

}










