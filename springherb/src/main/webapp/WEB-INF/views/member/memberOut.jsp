<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<%@include file="../login/loginCheck.jsp" %>
<script type="text/javascript">
	$(function(){
		$("#pwd").focus();
		
		$("input[type='submit']").click(function(){
			if($("#pwd").val().length<1){
				$(".error").show();
				$("#pwd").focus();
				event.preventDefault();
			}else{
				if(!confirm("삭제하시겠습니까?")){
					event.preventDefault();					
				}
			}//if
		});
	});
</script>
<style type="text/css">
	.error{
		color: red;
		font-weight: bold;
		display: none;
	}
</style>
<article class="simpleForm">
	<form name="frmOut" method="post" action="memberOut_ok.jsp">
		<fieldset>
			<legend>회원탈퇴</legend>
			<p class="p">회원탈퇴하시겠습니까?</p>
			<div>
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd">
				<span class="error">비밀번호를 입력하세요</span>
			</div>
			<div class="align_center">
				<input type="submit" value="회원탈퇴">
				<input type="reset" value="취소">
			</div>
		</fieldset>
	</form>
</article>

<%@ include file="../inc/bottom.jsp" %>








