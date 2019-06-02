package com.ez.herb.member.model;

public interface MemberService {
	public static final int AVAILABLE_USERID=1;  //중복확인시 사용가능한 아이디
	int NOT_AVAILABLE_USERID=2;  //중복확인시 사용불가능한 아이디

	public static final int LOGIN_OK=1;  //로그인 성공
	public static final int DISAGREE_PWD=2; //비밀번호 불일치
	int NONE_ID=3; //아이디가 존재하지 않는다
	
	public int duplicateUserid(String userid);
	public int procLogin(String userid, String pwd);
	public MemberVO selectMember(String userid);
}
