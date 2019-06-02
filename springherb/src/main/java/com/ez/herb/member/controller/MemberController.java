package com.ez.herb.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ez.herb.member.model.MemberService;
import com.ez.herb.member.model.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger=LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/register.do",method=RequestMethod.GET)
	public String register_get() {
		logger.info("회원가입 화면 보여주기");
		
		return "member/register";
	}
	@RequestMapping(value="/checkUserid.do")
	public String checkUserid(@RequestParam String userid,Model model) {
		logger.info("아이디 중복확인 파라미터 userid={}",userid);
		int result=0;
		if(userid!=null&&!userid.isEmpty()) {
			result=memberService.duplicateUserid(userid);
		}
		logger.info("아이디 중복확인 결과, result={}",result);
		
		model.addAttribute("result", result);
		model.addAttribute("available", MemberService.AVAILABLE_USERID); 
		model.addAttribute("not_available", MemberService.NOT_AVAILABLE_USERID);
		
		return "member/checkUserid";
	}
	
	@RequestMapping(value="/memberEdit.do",method=RequestMethod.GET)
	public String  memberEdit_get(HttpSession session,Model model) {
		logger.info("회원정보 수정 화면 보여주기");
		
		String userid=(String) session.getAttribute("userid");
		logger.info("userid={}",userid);
		/*
		if(userid==null||userid.isEmpty()) {
			model.addAttribute("msg", "먼저 로그인 하세요");
			model.addAttribute("url", "/login/login.do");
			
			return "common/message";
		}
		*/
		MemberVO vo=memberService.selectMember(userid);
		logger.info("회원정보 조회 결과, vo={}",vo);
		
		model.addAttribute("vo", vo);
		
		return "member/memberEdit";
	}
	
}
