package com.ez.herb.member.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//db작업 이외의 로직을 넣는 클래스
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDao;

	public int duplicateUserid(String userid) {
		int result=0;
		int count=memberDao.duplicateUserid(userid);

		if(count>0) {
			//해당 아이디가 이미 존재하는 경우
			result=NOT_AVAILABLE_USERID; //사용불가
		}else {
			//존재하지 않는 경우
			result=AVAILABLE_USERID; //사용가능
		}

		return result;
	}

	public int procLogin(String userid, String pwd) {
		int result=0;
		String dbPwd=memberDao.selectPwd(userid);
		if(dbPwd==null||dbPwd.isEmpty()) {
			result=NONE_ID;
		}else {
			if(dbPwd.equals(pwd)) {
				result=LOGIN_OK;
			}else {
				result=DISAGREE_PWD;
			}
		}//if
		return result;
	}
	public MemberVO selectMember(String userid) {
		return memberDao.selectMember(userid);
	}
	/*
	public int insertMember(MemberVO vo) throws SQLException { 
		int cnt=memberDao.insertMember(vo); 
		return cnt; 
	}

	public int procLogin(String userid, String pwd) throws SQLException { 
		return memberDao.procLogin(userid, pwd); 
	}

	public MemberVO selectMember(String userid) throws SQLException { 
		return memberDao.selectMember(userid); 
	}

	public int updateMember(MemberVO vo) throws SQLException { 
		return memberDao.updateMember(vo); 
	}

	public int withdrawMember(String userid) throws SQLException { 
		return memberDao.withdrawMember(userid); 
	}
	*/
}








