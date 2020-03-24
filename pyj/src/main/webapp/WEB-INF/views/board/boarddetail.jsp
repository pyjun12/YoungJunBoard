<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/util/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 상세보기</title>
<style>
#middle {
	align: center;
	hight: 50px;
	width: 1500px;
}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/util/session.jsp"/>
	<c:choose>
		<c:when test="${empty sessionScope.user }">
			<!-- Session값이 비었을경우 (로그인 전) -->
 			
		</c:when>
		
		<c:otherwise>
			<!-- Session값이 존재할경우 (로그인한 경우) -->
			<h2 align="center">게시글 상세보기 &nbsp;</h2>

			<input type="hidden" name="boardParentNo" value="${board.bno }">
			
			<!-- 파일 다운로드 -->
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="FILE_NUM" name="FILE_NUM" value="">
			</form>

			<div>
				<div id="middle">
					<table class="table">
						<tr>
							<th><span style="font-size: 15px;">원본게시글</span></th>
							<td >
								${board.boardGroupNo}
							</td>
						</tr>
						<tr>
							<th><span style="font-size: 15px;">게시글번호</span></th>
							<td>${board.bno}</td>
						</tr>
						
						<tr>
							<th><span style="font-size: 15px;">게시글 제목</span></th>
							<td>
								<c:out value="${board.boardSubject }"/>
								
							</td>
						</tr>
						<tr>
							<th>글쓴이</th>
							<td>${board.boardUserId }</td>
						</tr>
						<tr>
							<th>등록일자</th>
							<td>
								<input type="text" value="${board.boardRegdate}"
								style="width: 127px; background-color: white; text-align: center; border: none;"
								readonly>
							</td>
						</tr>
						<tr>
							<th>조회수/추천수</th>
							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									value="${board.boardViewcheck }" />/ ${board.boardRecommend }</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<c:forEach var="file" items="${file}">
									<a href="#" onclick="fn_fileDown('${file.FILE_NUM}'); return false;">${file.FILE_ORINAME}</a>
									(${file.FILE_SIZE}kb)<br>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea rows="10" cols="160" readonly>${board.boardContent}</textarea>
							</td>
						</tr>
						<tr>
				  		  
					<!-- 추천수 클릭시+1 -->
						<td align="left" colspan="2">
							<form name="form1" action="/good.do?bno=${param.bno}&boardCodeNum=${param.boardCodeNum }&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}" method="post">
								<button type="button" id="goodBtn" class="btn">
									<img src="../../images/ddabong.jpg" width="60px" height="40px;">추천
								</button> 
							<input type="hidden" name="bno" value="${board.bno }">
							<input type="submit" style="display: none">
							</form>
						</td>
						
					</tr>
						<!-- 게시판리스트로 이동 -->
						<div style="text-align: center; font-size: 20px;">
							<a href="/searchBoard.do?selectpage=5&select=작성자&search=&currentPage=${param.currentPage }&boardCodeNum=${param.boardCodeNum}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}">◀목록으로</a>
						</div>
					
					<form name="form2" method="post">
							<!-- 게시글 수정/삭제  -->
						<c:if test="${board.boardUserId==sessionScope.user.userId }">
							<td align="right" colspan="2">
								<input type="hidden" name="boardCodeNum" value="${param.boardCodeNum}"> 
								<button type="button" id="updateBtn" class="btn">수정하기</button>
								<button type="button" id="deleteBtn" class="btn" name="deleteBtn"  onclick="fn_delboardOK();">삭제하기</button> 
								<input type="submit" style="display: none">
								
							</td>
							</c:if>
							<div align="right">
								<input type="hidden" name="boardSubject" value="${board.boardSubject }"> 
								<input type="hidden" name="boardUserId" value="${board.boardUserId }"> 
								<input type="hidden" name="boardRegdate" value="${board.boardRegdate}">
								<input type="hidden" id="boardGroupNo" name="boardGroupNo" value="${board.boardGroupNo }"> 
								<input type="hidden" name="boardGroupSeq" value="${board.boardGroupSeq }"> 
								<input type="hidden" id="boardGroupDep" name="boardGroupDep"value="${board.boardGroupDep }"> 
								<input type="hidden" name="boardParentNo" value="${board.bno }" onclick="fn_delboard();"> 
								<input type="hidden" name="currentPage" value="1">
								
								<c:if test="${param.boardCodeNum eq 3 && sessionScope.user.userId eq 'admin'}">
									<button type="button" id="replyBtn">답글달기</button>
								</c:if><!-- QnA게시판은  admin만 답글달기 가능 -->
								<c:if test="${param.boardCodeNum ne 3 && param.boardCodeNum ne 2 }">
									<button type="button" id="replyBtn">답글달기</button>
								</c:if> <!--  QnA게시판이 아닌곳에서는 답글달기가능 -->
							</div>
						</form>
					</table>
				</div>
				<c:if test="${param.boardCodeNum ne 3 }">  <!-- QnA에는 댓글을 막는다 -->
					<div style="text-align: center;" id="footer">
						<table align="center" class="table" >
							<form name="form3" method="post"><!-- 댓글 달기 -->
								<tr>
									<th colspan="1">
										작성자:<input type="text" name="commentBoardUserId" value="${sessionScope.user.userId}"
										style=" color: red;width: 150px; background-color: white; font-size: 20px; text-align: center; border: none;" readonly>
									</th>
									<td colspan="2">
										<input type="hidden" name="commentBoardNum" value="${board.bno }"> 
										<input type="text" name="commentContent"  placeholder="댓글을 입력해주세요." 
										required style="width:400px;  border: none; font-size: 20px;">
										<span><input type="button" id="comBtn" class="btn" value="댓글달기" ></span>
										<input type="submit" style="display: none">
									</td>
								</tr>
						</form>
					<c:forEach items="${com}" var="com">
						<form name="form4" action="/delcomment.do?boardCodeNum=${param.boardCodeNum }&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}" method="post">
							<!--  댓글  삭제 -->
							<input type="hidden" value="${param.boardCodeNum}" name="boardCodeNum">
							<tr>
								<th colspan="1" style="color:blue;"> ${com.commentBoardUserId } : </th>
								<td colspan="1" style="font-size: 15px;">
									<input type="hidden" name="bno" value="${board.bno }">
									<input type="hidden" name="commentNum" value="${com.commentNum }">
									
									<c:out value="${com.commentContent}" />
								</td>
								<td colspan="1" style="font-size: 15px;">
									&nbsp; &nbsp; &nbsp; &nbsp;
									day :<input type="text" value="${com.commentRegTime}" style="width: 118px; 
									background-color: white; text-align: center; border: none;" readonly> 
									<c:if test="${com.commentBoardUserId==sessionScope.user.userId }">
											<input type="submit" id="delcomBtn" name="delcomsubmit" value="X">
									</c:if>
								</td>
							</tr>
						</form>
					</c:forEach>
					</table>
				</div>
			</c:if>
			</div>
			<br><br><br><hr>
		</c:otherwise>
	</c:choose>

	<script>
	
	var link="?boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&bno=${param.bno}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}";
	var link2="?boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}";
		  
	
	function fn_delboardOK(){ //답변이잇는지 체크
		 	$.ajax({
		 	url:"/delchk.do",
			 type:"get",
			 dataType:"json",
		 	data : {"boardGroupNo":$("[name=boardGroupNo]").val(),"boardGroupDep":$("[name=boardGroupDep]").val(),"bno":$("[name=bno]").val()},
		 	success:function(data){
		 		if(data.result == 0){ //답변이 없을시 삭제
		 			 if (confirm("삭제하시면 되돌릴 수 없습니다. 정말 삭제 하시겠습니까?")==true) {
		 				alert("삭제 완료되었습니다.");
		 				document.form2.action = "/delete.do"+link;
						document.form2.submit();
		 			 }
				}else{ // 1 답변이있을시 삭제불가
					alert("답변이 있어서 삭제불가 관리자에게 문의하세요 *_* ");
				 return false;
				 }
		 	  }
		 }); 
	   } 
		$("#goodBtn").click(function() {
			if (confirm("해당 글을 추천하시겠습니까?")) {
				document.form1.submit();
				alert("해당 글을 추천하였습니다.")
			}
		});

		$("#updateBtn").click(function() {
			if (confirm("수정 하시겠습니까? 확인을 누르시면 이동 합니다.")) {
				document.form2.action = "/updatemove.do"+link;
				document.form2.method = "post";
				document.form2.submit();
			}
		});

		$("#replyBtn").click(function() {
			if (confirm("답글을 다시겠습니까?")) {
				document.form2.action = "/replymove.do"+link;
				document.form2.submit();
			}
		});
		$("#logout").click(function() {
			if (confirm("정말 로그아웃 하시겠습니까?")) {
				alert("로그아웃 완료")
				return true;
			}
			return false;
		});
		$("#comBtn").click(function() {
			if (confirm("댓글 을 작성 하시겠습니까?")) {
				document.form3.action = "/insertcommnet.do"+link2;
				document.form3.submit();
			}
		});
		$("#delcomBtn").click(function() {
			if (confirm("삭제하시면 되돌릴 수 없습니다. 정말 삭제 하시겠습니까?")) {
				alert("댓글 삭제 완료")
				return true;
			}
			return false;
		});

		function fn_fileDown(fileNum) {
			var formObj = $("form[name='readForm']");
			$("#FILE_NUM").attr("value", fileNum);
			formObj.attr("action", "/fileDown.do");
			formObj.submit();
		}
	</script>


</body>
</html>