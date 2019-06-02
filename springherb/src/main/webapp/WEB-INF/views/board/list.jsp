<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<title>자유게시판 글 목록 - 허브몰</title>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
<script type="text/javascript">	
	$(document).ready(function(){
		$(".box2 tbody tr").mouseover(function(item){
			$(this).css({"background":"black","color":"white"});
		}).mouseout(function(item){
			$(this).css("background","");		
		});
	});
	function pageFunc(currPage){
		$("input[name='currentPage']").val(currPage);
		$("form[name='frmPage']").submit();//click 하면 submit메서드로 강제로 submit 시킴****** 
	}
</script>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }	
</style>	
</head>	
<body>

<h2>자유게시판</h2>
<c:if test="${!empty param.keyword}">
	<p>검색어 :${param.keyword},</p><p>${pageVo.totalRecord} 건 검색되었습니다.</p>
</c:if>

<!-- 페이징 처리를 위한 form -->
<form action="<c:url value='/board/list.do'/>" name="frmPage" method="post">
	<input type="text" name="searchCondition" value="${param.searchCondition}">
	<input type="text" name="searchKeyword" value="${param.searchKeyword }">
	<input type="text" name="currentPage">
</form>

<div class="divList">
<table class="box2"
	 	summary="기본 게시판에 관한 표로써, 번호, 제목, 작성자, 작성일, 조회수에 대한 정보를 제공합니다.">
	<caption>기본 게시판</caption>
	<colgroup>
		<col style="width:10%;" />
		<col style="width:45%;" />
		<col style="width:10%;" />
		<col style="width:25%;" />
		<col style="width:10%;" />		
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">번호</th>
	    <th scope="col">제목</th>
	    <th scope="col">작성자</th>
	    <th scope="col">작성일</th>
	    <th scope="col">조회수</th>
	  </tr>
	</thead> 
	<tbody>  
	  <!--게시판 내용 반복문 시작  -->	
	<c:if test="${empty alist}">
		<tr  style="text-align:center">
			<td colspan="5">해당 게시물이 없습니다</td>
		</tr>
	</c:if>
	<c:if test="${!empty alist}">
		<c:forEach var="vo" items="${alist }">
			<!-- 게시판 내용 반복문 시작 -->
			<tr style="text-align:center">
				<td>${vo.no }</td>
				<td style="text-align:left">
					<a href
						="<c:url value='/board/countUpdate.do?no=${vo.no}'/>">
						<!-- 제목이 긴경우 일부만 보여주기 -->
						<c:if test="${fn:length(vo.title)>30}">
							${fn:substring(vo.title,0,30) }...
						</c:if>
						<c:if test="${fn:length(vo.title)<=30}">
							${vo.title }
						</c:if>
					</a>
					<!-- 24시간 이내의 글인 경우 new 이미지 보여주기 -->
					<c:if test="${vo.newImgTerm<24 }">
						<img src=" <c:url value='/images/new.gif'/>" alt="new이미지">
					</c:if>
				<td>${vo.name }</td>
				<td><fmt:parseDate value="${vo.regdate }" pattern="yyyy-MM-dd"/></td>
				<td>${vo.readcount }</td>		
			</tr> 
		</c:forEach>
	</c:if> 
	  <!--반복처리 끝  -->
	</tbody>
</table>	   
</div>
<div class="divPage">
	<!-- 페이지 번호 추가 -->		
	<!-- 이전 블럭으로 이동 -->
	<c:if test="${pageVo.firstPage>1 }">
		<a href="#" onclick="pageFunc(${pageVo.firstPage-1})">	
			<img src="<c:url value='images/first.JPG'/>" alt="이전 블럭 이미지">
		</a>
	</c:if>		
	<!-- [1][2][3][4][5][6][7][8][9][10] -->
	<c:forEach var="i" begin="${pageVo.firstPage }" end="${pageVo.lastPage }">
		<c:if test="${i==pageVo.currentPage }">
			<span style="color:red; font-size:15px;">[${i }]</span>
		</c:if>
		<c:if test="${i!=pageVo.currentPage}">
			<a href="#" onclick="pageFunc(${i})">[${i }]</a>
		</c:if>
	</c:forEach>
	<!-- 다음 블럭으로 이동 -->
	<c:if test="${pageVo.lastPage<pageVo.totalPage }">
		<a href="#" onclick="pageFunc(${pageVo.lastPage+1})">
			<img src="<c:url value='/images/last.JPG'/>" alt="다음 블럭 이미지">
		</a>
	</c:if>
	<!--  페이지 번호 끝 -->
</div>
<!-- 검색 시작 중요!!!!!!!!!!post로 넘겨서 파라미터를 읽어오면 name에 해당하는 value를 읽어옴!!!!!!!!!!ㄴ -->
<div class="divSearch">
   	<form name="frmSearch" method="post" action='<c:url value="/board/list.do"/>'>
        <select name="searchCondition">
            <option value="title" 
            	<c:if test="${'title'==param.searchCondition }">
            		selected="selected"
            	</c:if>
            >제목</option>
            <option value="content"
            	<c:if test="${'content'==param.searchCondition}">
	            </c:if>
            >내용</option>
            <option value="name"
            	<c:if test="${'name'==param.searchCondition}">
	            	selected="selected"
	            </c:if>
            >작성자</option>
        </select>   
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="${param.searchKeyword }">   
		<input type="submit" value="검색">
    </form>
</div>

<div class="divBtn">
    <a href='<c:url value="/board/write.do"/>' >글쓰기</a>
</div>
</body>
</html>

