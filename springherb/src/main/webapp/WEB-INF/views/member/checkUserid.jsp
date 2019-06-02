<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkUserid.jsp</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$("#userid").focus();
		
		$("form[name='frmChk']").submit(function(){
			if($("#userid").val().length<1){
				alert("아이디를 입력하세요");
				$("#userid").focus();
				event.preventDefault();
			}	
		});
		
		$("#btUse").click(function(){
			//opener.document.frm1.userid.value=document.frmChk.userid.value;
			
			$(opener.document).find("#userid").val($("#userid").val());
			$(opener.document).find("#chkId").val("Y"); //중복확인 했다는 표시
			
			//$(opener.document).find("#userid").attr("readonly", "readonly");
			$(opener.document).find("#userid").prop("readonly", true);
			
			self.close();
		});
		
	});
	
</script>
</head>
<body>
	<h1>아이디 중복검사</h1>
	<form name="frmChk" method="post" 
		action="<c:url value='/member/checkUserid.do'/>">
		<div>
			<label for="userid">아이디 </label>
			<input type="text" name="userid" id="userid" 
				value="${param.userid}">		
			<input type="submit" value="아이디확인">
			<% 
			/* pageContext.setAttribute("available", 
					MemberService.AVAILABLE_USERID); 
			pageContext.setAttribute("not_available", 
					MemberService.NOT_AVAILABLE_USERID); */
			%>			
			<c:if test="${result== available}">
				<input type="button" id="btUse" value="사용하기">
				<p>사용가능한 아이디입니다. [사용하기]버튼을 클릭하세요.</p>
			</c:if>		
			<c:if test="${result== not_available}">
				<p>이미 등록된 아이디입니다. 다른 아이디를 입력하세요</p>
			</c:if>			
		</div>
	</form>
	
	
</body>
</html>














