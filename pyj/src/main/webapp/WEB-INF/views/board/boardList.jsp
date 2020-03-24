<%@page import="org.core.board.model.vo.Board"%>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%@ include file="/WEB-INF/views/util/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style>

table{
table-layout:fixed;
}
/* css 말줄임 하기위해 */
</style>
</head>
<body>

  	 <jsp:include page="/WEB-INF/views/util/session.jsp"/>
	 <h1 align="center">${boardCodeName}</h1><br>

<c:if test="${total > 0 }">
	<jsp:include page="/WEB-INF/views/util/readboardList.jsp"/>
</c:if>
	
<c:if test="${total == 0 }">
	<jsp:include page="/WEB-INF/views/util/readboardList.jsp"/>
</c:if>
	
</body>
</html>