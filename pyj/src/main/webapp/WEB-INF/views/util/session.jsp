<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/util/header.jsp" %>

	<c:if test="${empty sessionScope.user }">
	로그인 후 이용해주세요<a href="/logout.do">로그인하러 가기</a>
	</c:if>

	<div align="right">
		${sessionScope.user.userName}(${sessionScope.user.userId})님
		환영합니다.&nbsp;&nbsp;&nbsp; <a href="/logout.do" id="logout">로그아웃</a>
		
	</div>
