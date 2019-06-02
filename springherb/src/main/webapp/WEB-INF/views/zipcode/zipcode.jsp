<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>zipcode/zipcode.jsp</title>
<link rel="stylesheet" type="text/css" 
href="<c:url value='/css/mainstyle.css'/>">
<style type="text/css">
	.box2{
		width:470px;
	}
	#divZip1, #divCount{
		margin: 10px 0;
	}
	#divPage{
		margin:5px 0;
		text-align: center;
	}
</style>
<script type="text/javascript" src="<c:url value='/jquery/jquery-3.3.1.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/paging.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$("#dong").focus();
		
		$("form[name='frmZip']").submit(function(){			
			if($("#dong").val().length<1){
				$(".error").show();
				$("#dong").focus();
				event.preventDefault();
			}
			
			$.send(1);
			event.preventDefault();
		});
	});
	
	$.send=function(curPage){
		$("#currentPage").val(curPage);
		//alert($("#frmZip").serialize());
		
		$.ajax({
			 url:"<c:url value='/sample/getAddrApi.do'/>",
			 type:"post",
			 dataType:"xml",
			 data:$("#frmZip").serialize(),
			 success:function(xmlStr){
				 var errorCode=$(xmlStr).find("errorCode").text();
				 var errorMessage=$(xmlStr).find("errorMessage").text();
				 if(errorCode!=0){
					 //error
					 alert("에러:"+ errorCode+"-"+errorMessage);
				 }else{
					 //화면 처리
					 $.makeList(xmlStr);
				 }
			 },
			 error:function(xhr, status, error){
				 alert("에러:"+ error);
			 }
		});
	}
	
	$.makeList=function(xmlStr){
		$("#divZip1").html("");
		
		var totalCount=$(xmlStr).find("totalCount").text();
		//alert(totalCount);
		
		if(totalCount==0){
			$("#divCount").html("<p>해당하는 주소가 존재하지 않습니다.</p>");
			
			return;	
		}
		
		$("#divCount").html("<p>도로명 주소 검색 결과 : ("+ totalCount+") 건</p>");
				
		var tableEl=$("<table class='box2'></table>");
		var trEl
=$("<tr><th style='width:20%'>우편번호</th><th style='width:80%'>도로명주소</th></tr>");
		tableEl.append(trEl);
		
		$(xmlStr).find("juso").each(function(idx, item){
			var trEl2=$("<tr></tr>");
			
			var zipNo=$(this).find("zipNo").text();
			var addr=$(item).find("roadAddr").text();
			
			var tdEl1=$("<td></td>").html(zipNo);
			var anchor = $("<a href='#'></a>").html(addr)
				.attr("onclick", "setZipcode('"+zipNo+"','"+addr+"')");
			var tdEl2=$("<td></td>").html(anchor);
			
			trEl2.append(tdEl1);
			trEl2.append(tdEl2);
			tableEl.append(trEl2);			
		});
				
		$("#divZip1").append(tableEl);
		
		//페이징 처리
		var blockSize=10;		
		pagination($("#currentPage").val(),$("#countPerPage").val(),
			blockSize,totalCount);
		
		$("#divPage").html("");
		
		//이전 블럭으로 이동
		if(firstPage>1){	
			var anchor=$("<a href='#'></a>")
	.html("<img src='<c:url value='/images/first.JPG'/>' alt='이전 블럭 이미지'>")
			.attr("onclick","$.send("+ (firstPage-1) +")");
			
			$("#divPage").append(anchor);
		}
		
		//[1][2][3][4][5][6][7][8][9][10]
		var pageEl="";
		/* alert("firstPage="+firstPage+", lastPage="+lastPage
				+", totalPage="+ totalPage+", currentPage="+currentPage); */
		
		for(var i=firstPage;i<=lastPage;i++){
			if(i<=totalPage){
				if(i==currentPage){
					pageEl 
  = $("<span style='color:blue;font-weight: bold;font-size: 1.0em'></span>")
					.html(i);
				}else{
					pageEl=$("<a href='#'></a>").html("["+i+"]")
						.attr("onclick", "$.send("+i+")");
				}
				
				$("#divPage").append(pageEl);
			}
		}//for	
	
		//다음 블럭으로 이동
		if(lastPage<totalPage){
			var anchor=$("<a href='#'></a>")
	.html('<img src="<c:url value="/images/last.JPG" />" alt="다음 블럭 이미지">')
				.attr("onclick","$.send("+ (lastPage+1)+")");

			$("#divPage").append(anchor);
		}
		
	}
	
	function setZipcode(zipcode, address){
		$(opener.document).find("#zipcode").val(zipcode);
		$(opener.document).find("input[name='address']").val(address);
		
		self.close();
	}
	
</script>
</head>
<body>
	<h2>도로명 주소 검색</h2>
	<p>도로명주소, 건물명 또는 지번을 입력하세요</p>
	<p style="color:#006AD5">검색어 예 : 도로명(반포대로 58), 건물명(독립기념관),지번(삼성동 25)</p>
	<form name="frmZip" id="frmZip"  method="post" >
		<input type="hidden" name="currentPage" id="currentPage" value="1"/>				<!-- 요청 변수 설정 (현재 페이지) -->
	  	<input type="hidden" name="countPerPage" id="countPerPage" value="10"/>				<!-- 요청 변수 설정 (페이지당 출력 개수) -->
	  	<input type="hidden" name="confmKey" value="U01TX0FVVEgyMDE3MTIxODE3Mzc0MTEwNzU1Njg="/>		<!-- 요청 변수 설정 (승인키) -->
	  
		<label for="dong">지역명</label>
		<input type="text" name="keyword" id="dong"
		 	value="${param.keyword }">	
		<input type="submit" id="btSearch" value="찾기">	
	</form>
	
	<div id="divCount"></div>
	
	<div id="divZip1"></div>
	
	<div id="divPage"></div>	
</body>
</html>









