<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>자유게시판 상세보기 - 허브몰</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm {
		width: 500px;
		}
</style>  
</head>
<% pageContext.setAttribute("newLine","\r\n"); %>
<c:set var="content" value="${fn:replace(vo.content,newLine,'<br>')}" />
<body>
	<h2>글 상세보기</h2>
	<div class="divForm">
		<div class="firstDiv">
			<span class="sp1">제목</span> <span>${vo.title }</span>
		</div>
		<div>
			<span class="sp1">작성자</span> <span>${vo.name }</span>
		</div>
		<div>
			<span class="sp1">등록일</span> <span><fmt:formatDate value="${vo.regdate}" pattern="yyyy-MM-dd"/></span>
		</div>
		<div>
			<span class="sp1">조회수</span> <span>${vo.readcount }</span>
		</div>
		<div class="lastDiv">			
			<p class="content">${content }</p>
		</div>
		<div class="center">
			<a href='<c:url value="/board/edit.do?no=${param.no }"/>'>수정</a> |
        	<a href='<c:url value="/board/delete.do?no=${param.no }"/>'>삭제</a> |
        	<a href='<c:url value="/board/list.do"/>'>목록</a>			
		</div>
	</div>
	<table class="box2" summary="댓글다는 표">
		<colgroup>
			<col style="width:20%">
			<col style="width:50%">
			<col style="width:30%">
		</colgroup>
		<tbody>
			<!-- 반복시작 -->
			<c:forEach var="cvo" items="${alist }">
				<tr>
					<td>${cvo.name }</td>
					<td>${cvo.content }</td>
					<td><fmt:formatDate value="${cvo.regdate }" pattern="yyyy-MM-dd"/> </td>
				</tr>
			</c:forEach>
			<!-- 반복끝 -->
		</tbody>
	</table>
	<h3>댓글</h3>
	<br>
	<div style="border:1px solid #aaa; width:500px; height:200px;">
		<form action="<c:url value='comment/comment_register.jsp'/>" method="post" name="frmComment">
			<fieldset>
				<div style="text-align: center;">
					<input type="hidden" name="bdno" id="bdno" value="${param.no }"><!-- 확인부분 -->
					<label>이름</label>
					<input type="text" name="name" id="name">
					<label>비밀번호</label>
					<input type="password" name="pwd" id="pwd"><br><br>
					<textarea style="width:450px; height:100px;" name="content"></textarea><br>
				</div>
				<br>
				<div style="text-align: right">
					<input type="submit" value="확인" class="btn">
				</div>
			</fieldset>	
		</form>
	</div>
</body>
</html>