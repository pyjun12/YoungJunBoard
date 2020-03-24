<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%-- readboardList.jsp 의 글제목 15자 이상 부터 ... 출력 
     <c:choose>
		<c:when test="${fn:length(list.boardSubject) gt 15}"><!--게시글이 15글자가 넘으면 그대로 출력  -->
			<c:out value="${fn:substring(list.boardSubject,0,14)}" />...<!-- 0부터 14자 까지 그대로 출력 그뒤 ... 출력 -->
		</c:when>
		<c:otherwise>
			<span class="maljulim"></span>
		</c:otherwise>
	</c:choose></a> 
	
	 --%>