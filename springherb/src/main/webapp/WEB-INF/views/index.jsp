<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/top.jsp" %>

<article id="centerCon">
	<img src="images/herb.JPG" alt="herb이미지">
</article>
<article id="rightCon">
	<!-- 공지사항 include -->
	<c:import url="/board/noticeMain.do"></c:import>
</article>
<article id="listCon">
	<h2>상품 목록</h2>
	<!-- 상품 목록 include -->
	
</article>


<%@ include file="inc/bottom.jsp" %>





