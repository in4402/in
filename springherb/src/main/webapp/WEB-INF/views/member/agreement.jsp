<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>    

<style type="text/css">
	.frame1{
		width:700px;
	}	
</style>
<script type="text/javascript">
	$(function(){
		$("form[name='frmAgree']").submit(function(){
			if(!$("#chkAgree").is(":checked")){
				alert("약관에 동의하셔야 합니다.");
				$("#chkAgree").focus();
				event.preventDefault();
			}
		});
	});
</script>    
<article>
	<h2>회원약관</h2>
	<div class="frame1">
		<iframe src="<c:url value='/member/provision.html'/>" width="700" height="300"></iframe>
	</div>
	<div class="frame1">
		<form name="frmAgree" method="post" 
			action="<c:url value='/member/register.do'/>">
			<div class="align_right">				
				<input type="checkbox" id="chkAgree" name="chkAgree">
				<label for="chkAgree">약관에 동의합니다.</label>	
			</div>
			<div class="align_center">
				<input type="submit" value="확인">
				<input type="reset" value="취소">
			</div>
		</form>
	</div>

</article>

<%@ include file="../inc/bottom.jsp" %>   




 