/**
 * js/member.js
 */
	$(function(){
		$("#email2").change(function(){
			if($(this).val()=="etc"){
				$("#email3").val("");
				$("#email3").css("visibility","visible");
				$("#email3").focus();
			}
			else{
				$("#email3").css("visibility","hidden");	
			}
		});
		$("#btnChkId").click(function(){
			open("/herb/member/checkUserid.do?userid="+$("#userid").val(),"chk",
				"width=550,height=250,left=0,top=0,location=yes,resizable=yes");
		//js 파일같은경우에는 클라이언트에서 실행되는 파일이기 때문에 jsp관련된 모든것을 
		//사용할수 없기 때문에 컨텍스트 패스를 직접 다 써줘야함
		});//change
		$("#btnZipcode").click(function(){
			open("/herb/zipcode/zipcode.do","zip","width=550,height=700,left=0,top=0,location=yes,resizable=yes");
		});
	});
	function validate_userid(userid){
		var pattern=new RegExp(/^[a-zA-Z0-9_]+$/g);
		return pattern.test(userid);//정규표현식을 만족하면 true, 아니면 false
		/*
			정규식 /^[a-zA-Z0-9_]+$/g
			a에서 z사이의 문자, A~Z사이의 문자, 0에서 9사이의 숫자나 _로 시작하거나 끝나야한다는 의미
			닫기 대괄호 (]) 뒤의 +기호는 이패턴이 한 번 또는 그이상 반복 된다는 의미
		*/
	}
	function validate_phone(ph){
		var pattern=new RegExp(/^[0-9]*$/g);
		return pattern.test(ph);//정규표현식을 만족하면 true, 아니면 false	
		/*
			정규식 /^[0-9-]*$/g
			0에서 9사이의 숫자나 -로 시작하거나 끝나야한다는 의미(^는 시작, $는 끝을의미)
			닫기 대괄호 (]) 뒤의 *기호는 0번 이상 반복
		*/
	}
	
	function validate_pwd(pwd){
		var pattern = new RegExp
		(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{1,15}$/g);
		return pattern.test(pwd);  //
		//0~9까지 a~z, A~Z, 특수문자를 모두 포함하여 1~15글자를 만족해야 하는 정규식 
	}