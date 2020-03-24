<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/util/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글작성</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/util/session.jsp" />

	<form action="/insertreply.do" method="post"
		enctype="multipart/form-data" id="frm" name="writeForm">
		<!-- 게시글 작성 -->
		<!-- 파일 업로드를 위해   enctype="multipart/form-data" 사용  /  전송방식은 post만 가능 -->
		<input type="hidden" name="boardCodeNum" value="${param.boardCodeNum }">
		<input type="hidden" name="currentPage" value="${param.currentPage }">
		<input type="hidden" name="datepicker1" value="${param.datepicker1 }">
		<input type="hidden" name="datepicker2" value="${param.datepicker2 }">
	
		<!-- <input type="hidden" name="currentPage" value="1"> -->
		<h2 align="center">답글작성하기</h2>
		<br> <br>
		<table class="table">
			<!-- 게시글 번호는 시퀀스로 증가  -->
			<!-- 등록시간은 sysdate로  입력-->
			<!-- 조회수는 0으로 입력 -->
			<!-- 추천수도  0으로 입력 -->
			<!-- 그룹번호는 게시글번호 -->
			<!-- 그룹순서는 처음에 0 -->
			<!-- 그룹깊이는 처음에 1 -->

			<tr>
				<th>답글 할 게시글 번호</th>
				<td><input type="text" value="${b.boardGroupNo }"
					name="boardGroupNo" readonly
					style="background-color: white; border: none;"> 답변 순서<input
					type="text" value="${b.boardGroupSeq }" name="boardGroupSeq"
					readonly style="background-color: white; border: none;"> 답변
					계층<input type="text" value="${b.boardGroupDep }"
					name="boardGroupDep" readonly
					style="background-color: white; border: none;"></td>
			</tr>
			<tr>
				<th>B_Parent 번호</th>
				<td><input type="text" value="${b.boardParentNo }"
					name="boardParentNo" readonly
					style="background-color: white; border: none;"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" id="boardSubject" name="boardSubject"
					style="width: 500px;" placeholder="글 제목을 입력해주세요" class="chk"
					required style="background-color:white;border:none;"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" id="userId" name="boardUserId"
					value="${sessionScope.user.userId}" readonly class="chk"
					style="background-color: white; border: none;"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="160" class="chk"
						id="boardContent" name="boardContent" placeholder="내용을 입력해주세요."></textarea>
				</td>
			</tr>
			<tr>
				<th>파일 첨부</th>
				<!--fileAdd_btn(파일추가)를 누르면 #fileIndex가선택되고 type="file과  delete버튼이 생성  -->
				<td id="fileIndex"></td>
			</tr>
			<tr>
				<td>
					<button class="fileAdd_btn" type="button">파일추가</button> <input
					class="write_btn" type="button" value="답변 달기" id="insertBtn">
					<input type="reset" value="초기화">
				</td>
			</tr>
		</table>
	</form>
	<!-- <form action="/moveboard.do" method="get">
		<input type="hidden" name="currentPage" value="1"> <input
			type="submit" id="backBtn" value="목록으로">
	</form> -->
<a href="/boardClick.do?bno=${param.bno }&boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}" id="backBtn">뒤로가기</a>

	<script>
		$(document)
				.ready(
						function() {
							var formObj = $("form[name='writeForm']");
							$(".write_btn")
									.on(
											"click",
											function() {
												if (confirm("답변을 작성 하시겠습니까?") == true) {
													if ($("[name=boardSubject]")
															.val() != ""
															&& $(
																	"[name=boardContent]")
																	.val() != "") {
														formObj
																.attr("action",
																		"/insertreply.do");
														formObj.attr("method",
																"post");
														formObj.submit();
														alert("답변 쓰기  성공!!");
													} else if ($(
															"[name=boardSubject]")
															.val() == "") {
														alert(" 제목을 입력하세요.");
														return false;
													} else if ($(
															"[name=boardContent]")
															.val() == "") {
														alert(" 내용을 입력하세요");
														return false;
													}
												}
											});
							fn_addFile();
						});

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