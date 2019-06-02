<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css"href="<c:url value='/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/mystyle.css'/>" />

<title>자유게시판 글쓰기 - 허브몰</title>
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[type='submit']").click(function(){
			if($("#title").val()==''){
				alert("제목을 입력하세요");
				$("#title").focus();
				return false;
			}
			else if($("#name").val().length==0){
				alert("이름을 입력하세요");
				$("#name").focus();
				return false;
			}
			else if($("#pwd").val()==''){
				alert("비밀번호를 입력하세요");
				$("#pwd").focus();
				return false;
			}
			
			var email=$("#email").val();
			if(email.indexOf("@")==-1||email.indexOf(".")==-1
					||email.indexOf("@")>email.indexOf(".")){
				alert("이메일 형식이 다릅니다");
				$("#email").focus();
				return false;
			}
			//return true; //생략가능
		}); //
		//indexof로 email형식 넣기
	});
</script>

</head>
<body>
<div class="divForm">
<form name="frmWrite" method="post" action="<c:url value='/board/write.do'/>" >
 <fieldset>
	<legend>글쓰기</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title"  />
        </div>
        <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" />
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" />
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" />
        </div>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40"></textarea>
        </div>
        <div class="center">
            <input type = "submit" value="등록"/>
            <input type = "Button" value="글목록" onclick="location.href='<c:url value="/board/list.do"/>'" />         
        </div>
    </fieldset>
</form>
</div>   
</body>
</html>