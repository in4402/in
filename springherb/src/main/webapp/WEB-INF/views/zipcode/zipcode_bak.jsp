<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>zipcode/zipcode.jsp</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>">
<style type="text/css">
	table{
		width: 470px;
	}
	#divTable{
		margin-top: 20px;
	}
	.error{
		color: red;
		font-weight: bold;
		display: none;
	}
</style>
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$("#dong").focus();
		
		$("form[name='frmZip']").submit(function(){
			if($("#dong").val().length<1){
				$(".error").show();
				$("#dong").focus();
				event.preventDefault();
			}
		});
	});
	
	function setZipcode(zipcode, address){
		$(opener.document).find("#zipcode").val(zipcode);
		$(opener.document).find("input[name='address']").val(address);
		
		self.close();
	}
	
</script>
</head>
<body>
	<h1>우편번호 검색</h1>
	<p>찾고 싶으신 주소의 동(읍, 면)을 입력하세요</p>
	<form name="frmZip" method="post" 
		action="<c:url value='/zipcode/zipcode.do'/>">
		<label for="dong">지역명</label>
		<input type="text" name="dong"  id="dong" value="${param.dong}">
		<input type="submit" value="찾기">
		<span class="error">지역명을 입력하세요</span>		
	</form>
	
	<c:if test="${list != null }">	
		<div id="divTable">
			<table class="box2" 
			summary="우편번호 검색 결과에 관한 표로써 우편번호, 주소에 대한 정보를 제공합니다.">
				<colgroup>
					<col style="width:20%">
					<col style="width:*">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">우편번호</th>
						<th scope="col">주소</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty list }">					
						<tr class="align_center">
							<td colspan="2">해당 주소가 존재하지 않습니다.</td>
						</tr>
					</c:if>	
					<c:if test="${!empty list }">					
						<!-- 반복시작 -->
						<c:forEach var="vo" items="${list }">
							<c:set var="addr" 
								value="${vo.sido } ${vo.gugun } ${vo.dong }"/>
							<c:set var="bunji" value="${vo.startbunji }" />
							<c:if test="${!empty vo.endbunji }">
								<c:set var="bunji" 
									value="${bunji } ~ ${vo.endbunji }" />
							</c:if>
							<tr>
								<td>${vo.zipcode}</td>
								<td>
								<a href="#" 
							onclick="setZipcode('${vo.zipcode}','${addr}')">
									${addr} ${bunji}
								</a>
								</td>
							</tr>
						</c:forEach>	
						<!-- 반복끝 -->
					</c:if>	
				</tbody>
			</table>
		</div>	
	</c:if>
</body>
</html>









