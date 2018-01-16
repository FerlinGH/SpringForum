<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${board.title}" /></title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CssStyle.css ">

</head>
<body>
	<h2>
		<c:out value="${board.title}" />
	</h2>
	<hr>
	<br>
	
	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>
	
	<security:authorize access="hasRole('MEMBER')">
		<!-- New Topic button -->
		<form action="${pageContext.request.contextPath}/topic/new" method="GET">
			<input type="hidden" name="boardId" value="${board.id}" /> 
			<input type="submit" value="Create new topic" />
		</form>
	</security:authorize>
	
	
	
	<div id="topic-table">
		<table>
			<thead>
				<tr>
					<th>Topic</th>
					<th></th>
					<th>Messages</th>
					<th>Created By</th>
					<th>Last Reply</th>
			</thead>
			<tbody>
				<c:forEach var="tempTopic" items="${topicsList}">
					<tr>
						<td><c:url var="topicLink" value="topic/show">
								<c:param name="topicId" value="${tempTopic.id}" />
							</c:url> <a href="${topicLink}"> <c:out value="${tempTopic.title}" /></a>
						</td>
						<td>
						<security:authorize access="hasRole('MODERATOR')">
							<!-- Rename Topic button -->
							<form action="${pageContext.request.contextPath}/topic/rename" method="GET">
								<input type="hidden" name="topicId" value="${tempTopic.id}" /> 
								<input type="submit" value="Rename topic" />
							</form>
						</security:authorize>
						
						<security:authorize access="hasRole('ADMIN')">
							<!-- Delete Topic button -->
							<form action="${pageContext.request.contextPath}/topic/delete" method="GET">
								<input type="hidden" name="topicId" value="${tempTopic.id}" /> 
								<input type="hidden" name="boardId" value="${board.id}" /> 
								<input type="submit" value="Delete topic" />
							</form>
						</security:authorize>
						
						</td>
						<td><c:out value="${tempTopic.size}" /></td>
						<td><c:out value="${tempTopic.getAuthor().getUsername()}" /></td>
						<td><c:out value="${tempTopic.lastMessageTime}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>