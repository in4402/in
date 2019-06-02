<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>메인 페이지 - 최근 공지사항</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />
<style type="text/css">
	.divNotice{
		width:310px;
	}
	.more{
		margin:0 0 0 160px;
	}
	.divLine img{
		width:300px;
	}
	.divLine{
		padding: 5px;
	}
	.divNotice table{
		width:300px;
	}
</style>
</head>
<body>
<div class="divNotice">
	<div>
		<img src="${pageContext.request.contextPath }/images/notice2.JPG" alt="공지사항이미지">
		<span class="more">
			<a href="<c:url value='/board/list.do'/>">
				<img src="${pageContext.request.contextPath }/images/more.JPG" alt="more이미지">
			</a>
		</span>
	</div>
	<div class="divLine">
		<img src="${pageContext.request.contextPath }/images/Line.JPG" alt="line이미지">
	</div>
	<div>
		<table summary="메인페이지에 포함되는 공지사항 관한 표로써, 최근 공지사항 6건을 보여주는 표입니다.">
			<tbody>
				<c:if test="${empty list }">
					<tr class="align_center">
						<td>공지사항이 없습니다.<td>
					</tr>
				</c:if>
				<c:if test="${!empty list }">
			
				<!-- 반복시작 -->
					<c:forEach var="vo" items="${list }">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath }/images/dot.JPG" alt="공지사항이미지"> 
								<a href="<c:url value='/board/detail.do?no=${vo.no}'/>">${vo.title }</a>
							<td>
						</tr>
					</c:forEach>
				</c:if>
				<!-- 반복 끝 -->
			</tbody>
		</table>
	</div>
</div>
</body>
</html>