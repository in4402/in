package com.ez.herb.login.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private MemberService memberService;
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public void login_get() {
		logger.info("로그인 화면 보여주기");

	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login_post(@RequestParam String userid,@RequestParam String pwd,
			@RequestParam(required=false) String chkSaveId, HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("로그인 처리 파라미터 userid={}, pwd={}",userid,pwd);
		logger.info("로그인 처리 파라미터 chkSaveId={}",chkSaveId);

		String msg="로그인 처리 실패", url="/login/login.do";
		
		int result=memberService.procLogin(userid, pwd);
		logger.info("로그인 처리 결과 - result={}",result);
		
		if(result==MemberService.LOGIN_OK) {
			MemberVO vo=memberService.selectMember(userid);
			logger.info("로그인 처리 - 회원정보 조회결과 vo={}",vo);
			HttpSession session = request.getSession();

			//세션에 저장
			session.setAttribute("userid", userid);
			session.setAttribute("userName", vo.getName());

			//쿠키에 저장
			Cookie ck = new Cookie("ck_userid", userid);						
			if(chkSaveId!=null) {
				ck.setMaxAge(1000*24*60*60);
				ck.setPath("/");
				response.addCookie(ck);
			}else {
				ck.setMaxAge(0);
				ck.setPath("/");
				response.addCookie(ck);					
			}
			msg=vo.getName()+ "님 로그인되었습니다.";
			url="/index.do";
		}else if(result==MemberService.DISAGREE_PWD) {
			msg="비밀번호가 일치하지 않습니다.";
		}else if(result==MemberService.NONE_ID) {
			msg="해당 아이디가 존재하지 않습니다.";			
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "common/message";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃 처리");
		
		session.removeAttribute("userid");
		session.removeAttribute("userName");
		
		return "redirect:/login/login.do";
	}
}
