<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/util/header.jsp" %>
<meta charset="UTF-8">
<title>멀티게시판 구현</title>

	<style>
.sidenav {
			height:100%;
			width: 250px;
			position: fixed;
			z-index:1;
			top: 0;
			left: 0;
			background-color: #f2f2f2;
			overflow-x: hidden;
			transition:0.5s ease-in-out;
			padding-top: 65px;
		}
.sidenav a {
			padding: 8px 8px 8px 32px;
			text-decoration: none;
			font-size: 25px;
			color: #fff;
			display: block;
			transition: 0.2s ease-in-out;
		}
.sidenav a:hover, .offcanvas a:focus {
			color: #000;
		}
.closebtn {
			position: absolute;
			top: 0;
			right: 25px;
			font-size: 36px !important;
			margin-left: 50px;
		}

.openmenu:hover {
			color:rgb(0,154,200);
			transition:0.5s ease-in-out;
		}
.openmenu {
			font-size: 10px;
			cursor:pointer;
			transition:0.5s ease-in-out;
		}
.openmenu > i {
			font-size: 30px;
		}

/* 미디어쿼리 적용 */
@media screen and (max-height:450px) {
.sidenav {
			padding-top:15px;
			}
.sidenav a {
			font-size: 18px;
			}
		}
#leftdiv{
		width:50%;
		height:120px;
		position:relative;
		left:350px;
		}
#rightdiv{
		width:50%;
		height:120px;
		}
		
#login{
width:100%;
padding:160px 600px;

text-align: center;
}

#boardsubject{
text-align: center;
}
	
.box1 {
	background:orange; /* 배경색 오렌지 */
	border-bottom-left-radius: 15px; /* 아래쪽 테두리 왼쪽에 15px만큼 둥글게 */
	border-bottom-right-radius: 15px; /*아래쪽 테두리 오른쪽에 15px만큼 둥글게 */
	border-top-left-radius: 15px; /*위쪽 테두리 왼쪽에 15px만큼 둥글게 */
	border-top-right-radius: 15px; /*위쪽 테두리 오른쪽에 15px만큼 둥글게 */
	cursor: pointer; /* 마우스커서변경 */
	font:20px 'malgun gothic'; /*맑은 고딕 20px */
	text-align: center; /* 글자 가운데 정렬 */
	transition: 2s; /* transition 효과 3초 */
	width: 100px; /* 넓이 100px */
	height: 50px; /* 높이 100px */
	margin:10px; /* 바깥여백40px */
	line-height: 10px; /* 글자높이 100px */
}
.box1:hover {
	transform: rotate(720deg); /* 360도 회전 */
	background:yellow; /* 배경색 파랑 */
    margin-left:80px; /* 바깥여백 70px만큼 */
    transition-duration: 0.7s;
}


</style>
</head>
<body>
	<br>

	<a href="/ajax.do">ajax 테스트 (id중복 확인 ) </a>


	<c:choose>
		<c:when test="${empty sessionScope.user }">
			<!-- Session값이 비었을경우 (로그인 전) -->
			

			<form action="/login.do" method="post">
				<!-- post로 바꿀것  -->
			<div class="col-md-12" id="login">
			<h2>CoreMethod 게시판</h2>
				<table>
					<tr>
						<td ><strong>아이디:</strong></td>
						<td><input type="text" name="userId" class="form-control" required><br></td>
					</tr>
					<tr>
						<td><strong>비밀번호:</strong></td>
						<td><input type="password" class="form-control"  name="userPassword" required><br></td>
					</tr>
					<tr>
						<td><a href="/insertUser.do">회원가입 </a></td>
						<td><input type="submit" id="loginBtn" class="box1" value="로그인" >
						</td>
					</tr>
				</table>
				</div>
			</form>
		</c:when>
		<c:otherwise>
		
			<c:if test="${boardtotal == null}">
			정보가 없습니다. 로그인 후 이용해주세요<br><a href="/logout.do">로그인하러 가기</a>
			</c:if>
			

			
			<!-- Session값이 존재할경우 (로그인한 경우) -->
			<c:if test="${boardtotal != null}">
			<form action="/logout.do">
				<div align="right">${sessionScope.user.userName}(${sessionScope.user.userId})님환영
					합니다.&nbsp;&nbsp;&nbsp;
					<button type="button" id="logout">로그아웃</button>
				</div>

				<input type="submit" name="submitlogout" style="display: none">
			</form>
			<br>
			<br>
			<form action="/deleteuser.do" method="get">
				<div align="right">
					<button type="button" id="delBtn">회원탈퇴</button>
					<input type="submit" id="gologin" name="submit"
						style="display: none">
				</div>
			</form>
			<br>
			<br>
			<br>
			<br>
				<h2 style="text-align: center">CoreMethod게시판에 오신걸 환영합니다</h2><br><br>
			<div id="mysidenav" class="sidenav" align="center" style="display:none">
				<a href="#" class="closebtn" onclick='closeNav()'>닫기</a><hr>
				<font size="4"><strong>M E N U</strong></font>
						<hr>
						<strong>
							<font size="3"> ↓ 게시판 목록  ↓</font>
						</strong><br><hr>
						<c:forEach var="MasterBoardlist" items="${MasterBoardlist}">
							<input type="button" class="btn" value="${MasterBoardlist.boardCodeName}"
							onclick="location.href='/searchBoard.do?datepicker1=${monthDate }&datepicker2=${toDay }&selectpage=5&select=작성자&search=&currentPage=1&boardCodeNum=${MasterBoardlist.boardCodeNumber}'">
						</c:forEach>
					</div>	
					
				

		<div class="content col-md-12">
				<div class="col-md-6" id="leftdiv">
					<table border=2 class="table" style="width:400px; height: 245px" >
						<tr>
							<th>총회원 수 :</th>
							<td colspan=2>${usertotal } 명</td>
						</tr>
						<tr>
							<th>총 게시물 수 :</th>
							<td colspan="2">${boardtotal }개</td>
						</tr>
						<tr>
							<th>오늘 게시물 수 :</th>
							<td colspan=2>${todayBoard }개</td>
						</tr>
						<tr>
							<th>5일 내의 등록한 게시물 수:</th>
							<td colspan=2>${total5Board }개</td>
						</tr>
						<tr>
							<th>최대 게시물 등록자 :</th>
							<td colspan=2>${userMax }님</td>
						</tr>
					</table>
				</div><!-- 내용 -->
				<div class="col-md-6" id="rightdiv">
					<table border=2 class="table" style="width:350px ;height: 245px">
						
						<tr>
							<th id="boardsubject" style="text-align: center">기존게시판
									<span class="openmenu" onclick='openNav()' style="text-align:right;" >
									<i class="fa fa-angle-double-left fa-5" aria-hidden="true"></i> 
									          &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span style="color: red">게시판 더보기 + </span>
									</span>
								
							</th>
						</tr>
						<c:forEach items="${list5}" var="list5">	<!--기존게시판 최근5개 게시물 가져오기 -->
						<tr>
							<td>
								<a href="/boardClick.do?bno=${list5.bno}&currentPage=1&boardCodeNum=1&datepicker1=${monthDate }&datepicker2=${toDay }">
								<c:choose>
									<c:when test="${fn:length(list5.boardSubject) gt 15}">
										<c:out value="${fn:substring(list5.boardSubject,0,14)}" />...
									</c:when>
									<c:otherwise>
										<c:out value="${list5.boardSubject}" />
									</c:otherwise>
								</c:choose></a>
								
							</td>
						</tr>
					</c:forEach>
				</table>
				
			</div><!-- 내용 -->
		</div><!-- 헤더 -->
	</c:if>
		</c:otherwise>
	</c:choose>

	<script>
$("#delBtn").click(function() { 
	if(confirm("정말로 탈퇴하시겠습니까?")){
		 $("[name=submit]").click();
		 alert("회원탈퇴 완료")
	}
	return false;
});

$("#logout").click(function() { 
	if(confirm("로그아웃 하시겠습니까?")){
		 $("[name=submitlogout]").click();
		 alert("로그 아웃 완료")
	}
	return false;
});

function go(){
	document.going.submit();
}
		function openNav() {
			document.getElementById('mysidenav').style.display = 'block';
			document.getElementById('mysidenav').style.width = '250px';
			
		}
		function closeNav() {
			document.getElementById('mysidenav').style.width = '0';
		}

</script>
</body>
</html>