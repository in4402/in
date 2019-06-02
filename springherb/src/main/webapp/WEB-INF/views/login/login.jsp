<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<script type="text/javascript">
	$(function(){
		$("#userid").focus();
		
		$("input[type='submit']").click(function(){
			$(".inforbox").each(function(idx, item){
				if($(this).val().length<1){
					alert($(this).prev().text() +"을(를) 입력하세요");
					$(this).focus();
					event.preventDefault(); //이벤트 진행 막기					
					return false;  //each 탈출
				}	
			});
			
		});
		
	});
</script>

<article class="simpleForm">
	<form name="frmLogin" method="post" 
		action="<c:url value='/login/login.do'/>">
		<fieldset>
			<legend>로그인</legend>
			<div>
				<label for="userid" class="label">아이디</label>
				<input type="text" name="userid" id="userid" class="inforbox"
					value="${cookie.ck_userid.value}">
			</div>
			<div>
				<label for="pwd" class="label">비밀번호</label>
				<input type="password" name="pwd" id="pwd" class="inforbox">
			</div>
			<div class="align_center">
				<input type="submit" value="로그인">
				<input type="checkbox" name="chkSaveId" id="saveId"
					<c:if test="${!empty cookie.ck_userid }">
						checked="checked"
					</c:if>
				>
				<label for="saveId">아이디 저장하기</label>
			</div>
		</fieldset>
	</form>
</article>

<%@ include file="../inc/bottom.jsp" %>








