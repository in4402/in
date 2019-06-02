package com.ez.herb.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger=LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("preHandle()-컨트롤러 수행 전 실행");
		HttpSession session=request.getSession();
		String userid=(String) session.getAttribute("userid");
		
		if(userid==null||userid.isEmpty()) {
			request.setAttribute("msg", "먼저 로그인하세요!!!");
			request.setAttribute("url", "/login/login.do");
			
			RequestDispatcher dispacher=request.getRequestDispatcher("/WEB-INF/views/common/message.jsp");
			dispacher.forward(request, response);
			
			return false;//다음 컨트롤러를 수행하지 않음
		}
		return true;//다음 컨트롤러 수행
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle()-컨트롤러 수행 후 실행");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion()-뷰를 통해 클라이언트에 응답을 전송한 후 실행");
	}
	
}
