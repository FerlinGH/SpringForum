<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${topic.title}" /></title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CssStyle.css ">

</head>
<body>

	<h2>
		<c:out value="${topic.title}" />
	</h2>

	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<security:authorize access="hasRole('MEMBER')">
		<!-- New Message button -->
		<form action="${pageContext.request.contextPath}/message/new" method="GET">
			<input type="hidden" name="topicId" value="${topic.id}" />
			<input type="submit" value="Create new message" />
		</form>
	</security:authorize>
	

	<c:forEach var="tempMessage" items="${messageList}">
		<h4>
			<c:out value="${tempMessage.topicTitle}" />
		</h4>
		<c:out value="${tempMessage.getAuthor().getUsername()}" />
		<c:out value="${tempMessage.creationTime}" />
		<hr>
		<c:out value="${tempMessage.messageBody}" />
		<br>
		<c:out value="${tempMessage.editInfo}" />


		<security:authorize access="hasRole('ADMIN')">
			<!-- Admins can delete any messages -->
			<form action="${pageContext.request.contextPath}/message/delete"
				method="GET">
				<input type="hidden" name="topicId" value="${topic.id}" />
				<input type="hidden" name="messageId" value="${tempMessage.id}" />
				<input type="submit" value="Delete message" 
					onclick="if(!(confirm('Are you sure you want to delete this message?'))) return false"/>
			</form>
		</security:authorize>

		<security:authorize access="hasRole('MODERATOR')">
			<!-- Moderators can edit any messages -->
			<form action="${pageContext.request.contextPath}/message/edit"
				method="GET">
				<input type="hidden" name="messageId" value="${tempMessage.id}" />
				<input type="submit" value="Moderate message" />
			</form>
		</security:authorize>

		<security:authorize access="hasRole('MEMBER')">
			<!-- Regular members can edit their own messages -->
			<security:authentication property="principal.username" var="currentUserName" />
			<c:choose>
			<c:when test="${anonymousUser}" />
			<c:otherwise>
				<c:if
					test="${currentUserName eq tempMessage.author.username}">
					<form action="${pageContext.request.contextPath}/message/edit" method="GET">
						<input type="hidden" name="messageId" value="${tempMessage.id}" />
						<input type="submit" value="Edit your message" />
					</form>
				</c:if>
			</c:otherwise>
		</c:choose>
		</security:authorize>

	
	</c:forEach>
</body>
</html>