package com.ez.herb.member.model;

public interface MemberDAO {
	public int duplicateUserid(String userid);
	public MemberVO selectMember(String userid);
	public String selectPwd(String userid);
}
