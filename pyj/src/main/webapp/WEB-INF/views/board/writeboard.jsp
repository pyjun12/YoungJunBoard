<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/util/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>

<jsp:include page="/WEB-INF/views/util/session.jsp"/>

	<form action="/insertBoard.do" method="post"
		enctype="multipart/form-data" id="frm" name="writeForm">
		<!-- 게시글 작성 -->
		<!-- 파일 업로드를 위해   enctype="multipart/form-data" 사용  /  전송방식은 post만 가능 -->
		<input type="hidden" name="currentPage" value="1">
		<h2 align="center">게시글 작성하기</h2>
		<br><br>
		
		<table class="table">
			<!-- 게시글 번호는 시퀀스로 증가  -->
			<!-- 등록시간은 sysdate로  입력-->
			<!-- 조회수는 0으로 입력 -->
			<!-- 추천수도  0으로 입력 -->
			<!-- 그룹번호는 게시글번호 -->
			<!-- 그룹순서는 처음에 0 -->
			<!-- 그룹깊이는 처음에 0 -->
			<!-- 부모번호는 게시글번호 -->
			<%-- <input type="hidden" name="boardCodeNum" value="${param.boardCodeNum}"> --%>
				
			<tr>
				<th colspan="1">제목</th>
				<td colspan="1">
					 <input type="text" id="boardSubject" name="boardSubject"
					style="width: 500px;" placeholder="글 제목을 입력해주세요"  class="chk"
					required style="background-color:white;border:none;"> 
				</td>
			</tr>
			<tr>
				<th colspan="1">작성자</th>
				<td colspan="1">
					<input type="text" id="userId" name="userId"
					value="${sessionScope.user.userId}" readonly class="chk"
					style="background-color: white; border: none;">
				</td>
			</tr>
			<tr>
				<th colspan="1">내용</th>
				<td colspan="1">
					<textarea rows="10" cols="160" class="chk" id="boardContent" name="boardContent" required></textarea> 
				</td>
			</tr>
			<tr>
				<th colspan="1">파일 첨부</th>
				<!--fileAdd_btn(파일추가)를 누르면 #fileIndex가선택되고 type="file과  delete버튼이 생성  -->
				<td id="fileIndex" colspan="1"></td>
				
			</tr>
			<tr>
				<td colspan="2">
					<button class="fileAdd_btn" type="button">파일추가</button> <input
					class="write_btn" type="button" value="글쓰기" id="insertBtn">
					<input type="reset" value="초기화">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center">
				 	<a href="/searchBoard.do?selectpage=5&select=작성자&search=&currentPage=${param.currentPage }&boardCodeNum=${param.boardCodeNum}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}">◀목록으로</a>
				</td>
			</tr>
		</table>
	</form>
	
	
	
	

	<script>

	 	 $(document).ready(function() {
			var formObj = $("form[name='writeForm']");
			
			$(".write_btn").on("click", function() {
				if (confirm("글 작성 하시겠습니까?") == true) {
					if($("[name=boardSubject]").val() != "" && $("[name=boardContent]").val() != ""){
						formObj.attr("action", "/insertBoard.do?boardCodeNum=${param.boardCodeNum}&selectpage=5&select=작성자&currentPage=1&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}");
						formObj.attr("method", "post");
						formObj.submit();
						alert("글쓰기 성공!!");
					}else if ($("[name=boardSubject]").val() == ""){
						alert(" 제목을 입력하세요.");
						return false;
					}else if($("[name=boardContent]").val() == ""){
						alert(" 내용을 입력하세요");
						return false;
					}
				}
			});
			fn_addFile();
		})  

		$("#logout").click(function() {
			if (confirm("로그아웃 하시겠습니까?")) {
				$("[name=submitlogout]").click();
				alert("로그 아웃 완료")
			}
			return false;
		});
	 	 
	 	<jsp:include page="/WEB-INF/views/util/fileadd.jsp"/> //파일추가버튼클릭시 파일추가와 삭제 버튼생성
	 	
	 	
	 	
	function checkFile(f){
	 		var file=f.files;
	 		if(!/\.(gif|jpg|jpeg|png|txt|hwp|ppt)$/i.test(file[0].name)) alert('gif, jpg, png , txt, hwp ,ppt 파일만 선택해 주세요.\n\n현재 파일 : ' + file[0].name);
	 			
	 		else return ; //체크를 통과했다면 종료 
	 		f.outerHTML = f.outerHTML; // 조건에 맞지않으면 폼을 새로 써준다. 
	 	}
		
	
	 	
	</script>
</body>
</html>