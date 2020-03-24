

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/util/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>

<body>

<c:if test="${list.boardUserId != sessionScope.user.userId }"> <!-- 본인이아니면 url로 수정불가  -->
	<script>
		alert("본인글이 아닙니다. 잘못된 접근입니다.");
		location.href = "/boardClick.do?bno=${param.bno }&boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}";
	</script>
</c:if>

	<c:choose> 
		<c:when test="${empty sessionScope.user  }"> 
			<!-- Session값이 비었을경우 (로그인 전) -->
 			로그인 정보가 없습니다. 다시 로그인 후 이용해주세요 <a href="logout.do">로그인하러 가기</a>
		</c:when>
		
		<c:otherwise>
			<!-- Session값이 존재할경우 (로그인한 경우) -->
			<h2 align="center">게시글 수정화면</h2>
			<br><br>
			
			<form method="post" role="form" id="frm" name="updateForm" enctype="multipart/form-data">
				<!-- 게시글 수정  -->
				<table class="table" align="center">
					<tr>
						<th>게시글번호</th>
						<td>
							<input type="text" value="${list.bno}" name="bno" readonly style="background-color: white; border: none;">
						</td>
					</tr>
					<tr>
						<th>게시글 제목</th>
						<td>
							<input type="text" style="width: 500px;"
							value="${list.boardSubject }" name="boardSubject"
							style="background-color:white;border:none;">
						</td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td>${list.boardUserId }</td>
					</tr>
					<tr>
						<th>등록일자</th>
						<td>
							<input type="text" value="${list.boardRegdate}"
							style="width: 124px; background-color: white; text-align: center; border: none;"
							readonly readonly>
						</td>
					</tr>
					<tr>
						<th>조회수/추천수</th>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${list.boardViewcheck }" />/ ${list.boardRecommend }
						</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td id="fileIndex">
							<c:forEach var="file" items="${file}" varStatus="status">
								<div>
									<input type="hidden" id="FILE_NUM" name="FILE_NUM_${var.index}"value="${file.FILE_NUM }"> 
									<input type="hidden"id="FILE_NAME" name="FILE_NAME" value="FILE_NUM_${var.index}">
									<a href="#" id="fileName" onclick="return false;">${file.FILE_ORINAME}</a>(${file.FILE_SIZE}kb)
									<button id="fileDel${status.count }" name="fileDel" type="button" value="제거">제거</button>
									<input type="hidden" value="${file.FILE_NUM }" name="fno"><br>
								</div>
							</c:forEach>
						</td>
					</tr>
					<tr>
							<th>내용</th>
							<td>
								<textarea rows="10" cols="160" name="boardContent">${list.boardContent}</textarea>
							</td>
						</tr>
					<tr>
						<td colspan="2" align="center">
							<button type="button" class="fileAdd_btn">파일추가</button> <input
							class="update_btn" type="submit" value="저장" id="updateBtn">
						</td>
					</tr>
				</table>
			</form>

			<a href="/boardClick.do?bno=${param.bno }&boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}" id="backBtn">뒤로가기</a>
			
		</c:otherwise>
	</c:choose>

	<script>
		 $("#updateBtn").click(function() {
			if (confirm("정말로 수정하시겠습니까?")==true) {
				$("[name=updatesubmit]").click();
				alert("수정이 완료되었습니다.")
			}
				return false;
			
		}); 

		$("#backBtn").click(function() {
			if (confirm("목록으로 가시면 현재입력내용을 잃어버립니다.그래도 목록으로 가시겠습니까?")) {
				return true;
			}
			return false;
		});

		$("#logout").click(function() {
			if (confirm("정말 로그아웃 하시겠습니까?")) {
				alert("로그아웃 완료")
				return true;
			}
			return false;
		});

		<jsp:include page="/WEB-INF/views/util/deletefile.jsp"/> //파일 삭제시 db 삭제
		
		$("#updatgBtn").click(function() {
			if (confirm("수정을 완료하시겠습니까?")) {
				return true;
			}
			return false;
		});

		$(document).on("click", "#fileDel", function() {
			$(this).parent().remove();
		})
		
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