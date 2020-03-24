<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ include file="/WEB-INF/views/util/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.js" integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI=" crossorigin="anonymous"></script>
<style>
#container{
text-align:center;
width: 700px;
height: 500px;
margin:0 auto;  
/* margin:0 auto; 넣으니까 정렬됨  */
}

</style>
</head>
<body>

<div id="container">
 	<form  action="/insertUserData.do" method="get" name="insertform"> <!-- 회원가입 -->
    <table align="center" border="1" class="table"> <br><br><br>
   
      	<h1 align="center">회원가입</h1> <br><br>
      	<tr>
      		<td>
      			아이디
      		</td>
      		<td>
      			<input type="text" id="userId" name="userId" placeholder="아이디를 입력해주세요">
      			<input type="hidden" id="idChk" value="">
      			<button type="button" onclick="fn_idChk();">중복확인</button>
      			<span id="idRegCheckMsg" class="checkspan"></span><br>
      		</td>
      </tr>
      <tr>
      		<td>
      			패스워드
      		</td>
      		<td>
      			<input type="password" id="pw" name="userPassword" id="pw1" onkeyup="fn_pwChk()"   placeholder="비밀번호를 입력해주세요">
 				<span id="idRegCheckMsg2" class="checkspan"></span><br>     		
      		</td>
      </tr>
      <tr>
      		<td>
      			패스워드확인
      		</td>
      		<td>
      			<input type="password" id="pw" name="ReuserPassword" id="pw2"  onkeyup="fn_pwChk()" placeholder="비밀번호를 입력해주세요">
      			<span id="idRegCheckMsg2" class="checkspan"></span><br>
      		</td>
      </tr>
      <tr>
      		<td>
      			이름
      		</td>
      		<td>
      			<input type="text" id="userName" name="userName" placeholder="이름을 입력해주세요">
      		</td>
      </tr>
      <tr>
      		<td colspan="2" align="right">
      			<a href="/back.do">뒤로가기</a>
      			<input type="submit" id="gogo" value="회원가입">
      			<input type="reset" value="초기화">
    		</td>
      </tr>
   </table>
  </form>
</div>

<script>
//아이디 중복체크
function fn_idChk(){
$.ajax({
	url : "/idChk.do",
	type : "get",
	dataType : "json",
	data : {"userId" : $("#userId").val()},
	success : function(data){
		if(data.result == 0){ // /idChk.do 에서 받아온 result값이 0이면
			$("#idChk").attr("value", 1);
			$("#idRegCheckMsg").html("사용가능한 아이디입니다.");
			$("input[name=userId]").css('border', '2px solid blue');
		}else if(data.result == 1){ // /idChk.do 에서 받아온 result값이 1이면
			$("#idChk").attr("value", 2);
			  $("#idRegCheckMsg").html('이미 사용중인 ID 입니다.');
			  $("input[name=userId]").css('border', '2px solid red');
		}
	}
});
}
//비밀번호 중복체크
function fn_pwChk(){ 
	if($("[name=userPassword]").val()==null && $("[name=ReuserPassword]").val()==null){
		  $("#idRegCheckMsg2").html("비밀번호를 입력해주세요.");
	
	}else if($("[name=userPassword]").val()==$("[name=ReuserPassword]").val() && $("[name=userPassword]").val() != "" && $("[name=userRePassword]").val() != ""){
             $("#idRegCheckMsg2").html("비밀번호가 동일합니다");
             $("[name=ReuserPassword]").css('border', '2px solid blue');
             $("[name=userPassword]").css('border', '2px solid blue');
     }else if($("[name=userPassword]").val() != $("[name=ReuserPassword]").val() && $("[name=userRePassword]").val() != "" && $("[name=userPassword]").val() != ""){
    	  $("#idRegCheckMsg2").html("비밀번호 값이 서로 다릅니다.");
          $("[name=ReuserPassword]").css('border', '2px solid red');
          $("[name=userPassword]").css('border', '2px solid red');
     }
}
//회원가입 클릭시 invalid
 $(document).ready(function() {
	 var formObj = $("form[name='insertform']");
	 $("#gogo").on("click", function() {
		 if (confirm("회원 가입 하시겠습니까?") == true) {
				if($("#idChk").val() == 2){
					alert("회원가입 실패 아이디 중복");
					return false;
 				} else if($("[name=userPassword]").val()!=$("[name=ReuserPassword]").val()){
					alert("회원가입 실패 비밀번호가 다릅니다.");
					return false;	
 				} else if($("[name=userId]").val()==""){
					alert("아이디를 입력안하고 회원가입 불가");
	 				return false;
 				} else if($("[name=userPassword]").val()==""){
 					alert("패스워드를 입력안하고 회원가입 불가");
 					return false;
 				} else if ($("[name=userName]").val()==""){
 					alert("이름을 입력하세요");
 	 				return false;
				} else if($("[name=userPassword]").val()==$("[name=ReuserPassword]").val()){
					formObj.attr("action", "/insertUserData.do");
					formObj.attr("method", "post");
					formObj.submit();
					alert("회원가입 완료!!!");
			}
		} 
		return false;
	 })
 });


</script>
</body>
</html>