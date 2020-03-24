<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ include file="/WEB-INF/views/util/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
</head>
<body>

<input type="text" id="userId" name="userId">

<!-- 아이디 중복 체크  -->
<button type="button" onclick="fn_pyj();" >제출</button> 

 <!-- 게시글 삭제 -->
 <!--
<input type="text" id="boardCodeNum"name="boardCodeNum" value="1">
<input type="text" id="currnetPage" name="currnetPage" value="1">
<input type="text" id="datepicker1" name="datepicker1" value="2020-02-20">
<input type="text" id="datepicker2" name="datepicker2" value="2020-03-20">
<button type="button" onclick="fn_delboard();"></button>
 -->
<script>

/* function delboard(){
	$.ajax({
		url:"/delboard.do"
		type : "get"
		dataType:"json"
		data:{"boardCodeNum" : $("#boardCodeNum").val(),"currentPage" : $("#currentPage").val,"datepicker1" : $("#datepicker1").val(),"datepicker2" : $("#datepicker2").val()}
		success:function(ab){
			if(a.result==0 ){ //삭제안되었으면 
				alert("삭제 실패");
			}else{ //삭제 되었으면
				alert("삭제 성공 ")
			}
			
		}
	
	})
	
} */






function fn_pyj(){
	$.ajax({
		url:"/idChk.do",
		type:"get",
		dataType:"json",
		data:{"userId" : $("#userId").val()},
		success:function(a){
			if(a.result==0){
				alert("0입니다");
			}else{
				alert("1입니다.");
			}
		}
	})
	
}


</script>

</body>
</html>