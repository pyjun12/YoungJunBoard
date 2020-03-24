<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/views/util/header.jsp" %>
 
<c:choose>
		<c:when test="${empty sessionScope.user }">
			<!-- Session값이 비었을경우 (로그인 전) -->
 	 		               
		</c:when>
		<c:otherwise><br><br><br>
			<!-- Session값이 존재할경우 (로그인한 경우) -->
			<input type="hidden" id="sessionId" value="${sessionScope.user.userId}" >
			
			<!-- 파일 다운로드 -->
			<form name="readForm" role="form" method="post">
				<input type="hidden" id="FILE_NUM" name="FILE_NUM" value="">
			</form>
			
			
			<!-- 		
			function fn_fileDown(fileNum) {
			var formObj = $("form[name='readForm']");
			$("#FILE_NUM").attr("value", fileNum);
			formObj.attr("action", "/fileDown.do");
			formObj.submit();
			} 
		-->

		<form action="/searchBoard.do" method="get">
			<input type="hidden" value="${param.boardCodeNum}" name="boardCodeNum">
			<table class="table" align="center">
				
					<tr>
						<td colspan="9">
							<span style="color: red;">
								총 게시물 수  : ${total }
							</span>
							<div align="center">
								<div>
								<span style="text-align:left; padding: 0;">
									<select name="selectpage">
										<option value="5"<c:if test="${board==5}">selected="selected"</c:if>>5줄보기</option>
										<option value="10"<c:if test="${board==10}">selected="selected"</c:if>>10줄보기</option>
										<option value="15"<c:if test="${board==15}">selected="selected"</c:if>>15줄보기</option>
										<option value="20"<c:if test="${board==20}">selected="selected"</c:if>>20줄보기</option>
									</select>
									 <input type="submit" value="선택" class="btn">
								</span>&nbsp;&nbsp;&nbsp;
								     <input type="text"  id="datepicker1" name="datepicker1" style="width: 100px" value="${param.datepicker1}">
								       ~ 
								     <input  type="text" name="datepicker2" id="datepicker2" style="width: 100px" value="${param.datepicker2 }">
									<select name="select" style="height: 28px">
										<option value="작성자" <c:if test="${boardselect=='작성자'}">selected="selected"</c:if> >작성자</option>
										<option value="제목" <c:if test="${boardselect=='제목'}">selected="selected"</c:if>>제목</option>
										<option value="내용" <c:if test="${boardselect=='내용'}">selected="selected"</c:if>>내용</option>
									</select> 
									<input type="hidden" name="currentPage" value="1"> 
									<input type="text" name="search"  placeholder="검색" value="${boardsearch}">
									<input type="submit" value="검색" class="btn">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									
									<c:if test="${sessionScope.user.userId eq 'admin' && param.boardCodeNum eq 2 }">
										<a href="/writeBoard.do?boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}">
										<img src="../../images/wtt.png" width="60px" height="40px;">
										</a>
									</c:if> <!-- 공지사항의 admin일 시만 글작성 가능 -->
										
									<c:if test="${param.boardCodeNum ne 2 }">
										<a href="/writeBoard.do?boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}">
										<img src="../../images/wtt.png" width="60px" height="40px;">
										</a>
									</c:if> <!-- 공지사항이 아닌곳만 글쓰기 가능 -->
								</div>
							</div>
						</td>
					</tr>
					<c:choose>
						<c:when test="${total == 0 }">
							<tr>
								<th colspan="1">번호</th>
								<th colspan="3">제목</th>
								<th colspan="1">작성자</th>
								<th colspan="1">등록일</th>
								<th colspan="1">조회수</th>
								<th colspan="1">추천수</th>
								<th align="center" colspan="1">댓글수</th>
							</tr>
								<td style="text-align: center" colspan="9">게시글이 비었습니다.</td>
						</c:when>
						<c:otherwise>
							<tr>
								<th colspan="1">번호</th>
								<th colspan="3">제목</th>
								<th colspan="1">작성자</th>
								<th colspan="1">등록일</th>
								<th colspan="1">조회수</th>
								<th colspan="1">추천수</th>
								<th align="center" colspan="1">댓글수</th>
							</tr>
						<c:forEach items="${list}" var="list" varStatus="i"> <!--게시글 전체조회한  model값 -->
					<tr>
						<td colspan="1">&nbsp; ${No - i.index - (start - 1)}</td> <!-- 게시글앞에 번호 -->
						<td style=" overflow:hidden; white-space:nowrap; text-overflow:ellipsis" colspan="3">
					
							<c:if test="${list.boardRegdate>=nowday }">
								<img src="../../images/nnew.jpg" width="45px" height="30px;">
							</c:if> <!-- 현재날짜와비교하여 new 표시 -->
							 
							<c:if test="${list.boardGroupDep > 0}">
								<c:forEach begin="1" end="${list.boardGroupDep}">&nbsp;&nbsp;<!-- 답변글일경우 글 제목 앞에 공백을 준다. -->
								</c:forEach>
                       			 RE :  <!-- 답글에 RE : 붙이기 -->
                    		</c:if> 
                    			<a href="/boardClick.do?bno=${list.bno}&boardCodeNum=${param.boardCodeNum}&currentPage=${param.currentPage}&datepicker1=${param.datepicker1}&datepicker2=${param.datepicker2}">
                    			<c:out value="${list.boardSubject}"/></a> 
                    		
							<c:if test="${list.fileox>0 }"><!-- 파일이 있을시 표시 + 파일 다운로드 -->
								<a href="#" onclick="fn_fileDown('${list.fileNum}'); return false;">  <!-- 파일갯수 카운트세서 가져오기 -->
								<img src="../../images/file.jpg" width="20px" height="20px;">
								</a>
							</c:if> 
							<c:if test="${list.boardViewcheck >=100 }"> <!-- 조회수가 100이상이면 HOT -->
								<img src="../../images/Hot_Logo.jpg" width="30px" height="30px;">
							</c:if>
							<c:if test="${list.boardRecommend >=30 }"> <!-- 추천수가 30이상이면 왕표시 -->
								<img src="../../images/croun.jpeg" width="30px" height="30px;">
							</c:if>
						</td>

						
						<td style="width:10%;"><!-- 작성자 아이디 -->${list.boardUserId}
							<input type="hidden" id="boardUserIdChk"  value="${list.boardUserId}">
							<c:if test="${list.boardUserId eq 'admin'}">     <!-- 작성자가 admin 일 시 관리자 이모티콘 -->
								<img src="../../images/rhksflwk.png" width="30px" height="30px;">
							</c:if>
							<c:if test="${list.boardUserId ne 'admin'}">     <!-- 작성자가 admin이 아닐 시 관리자 이모티콘 -->
								<img src="../../images/dlfqks.png" width="30px" height="30px;">
							</c:if>
						</td>

						<!--작성일 -->
						<td style="width:10%;">
							<c:set var="now" value="<%=new java.util.Date()%>" /> <!-- 오늘날짜 불러오기 --> 
							<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today" /> <!-- now변수의값을 문자열로변경 -->
							<fmt:formatDate value="${list.boardRegdate}" pattern="yyyy-MM-dd" var="boardregdate" /> <!-- boardregdate를 문자열로변경 -->
							
							 <c:if test="${today==boardregdate }"><!--now변수와같다면 -->
    							${list.boardRegdateymdhms }
						     </c:if> 
						     <c:if test="${today!=boardregdate }"> 	<!-- now변수와 같지 않다면-->
							<fmt:formatDate value="${list.boardRegdate}" pattern="yyyy-MM-dd"  />
							</c:if>
						</td>

						<!-- 조회수 -->
						<td colspan="1">&nbsp;&nbsp;&nbsp;
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${list.boardViewcheck}" /> <!-- 천단위마다 콤마찍기 -->
						</td>

						<!-- 추천수 -->
						<td colspan="1">&nbsp;&nbsp;&nbsp;
							 <fmt:formatNumber type="number" maxFractionDigits="3" value="${list.boardRecommend}" />
						</td>

						<!-- 댓글수  -->
						<td colspan="1">&nbsp;&nbsp;&nbsp; 
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${list.commentCount }" />
						</td>
					</tr>
				</c:forEach>
						
						</c:otherwise>
					</c:choose>
					<tr>
						<td colspan="9">
							<div>
								<a href="/back.do">◀목록으로</a>
							</div>
						</td>
					</tr>
			</table>
		</form><hr>
		
			<div style="text-align: center;">
				${pageNavi}
			</div>
	
			
			<hr><br><br><br>
		</c:otherwise>
	</c:choose>
	
	
	

	<script>
		<jsp:include page="/WEB-INF/views/util/datepicker.jsp"/>
		//jsp파일을 부를땐 <script>로 감싸주기  xxxx 
    	
		$("#logout").click(function() {
			if (confirm("정말 로그아웃 하시겠습니까?")) {
				alert("로그아웃 완료")
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
