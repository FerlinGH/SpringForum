<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:if test="${pageContext.request.userPrincipal.name ne anonymousUser}">
		<security:authentication property="principal.username" var="username"/>
		<security:authentication property="principal.authorities" var="roles"/>
	
	</c:if>

	<h2>
		<c:out value="${topic.title}" />
	</h2>

	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<form action="${contextPath}/message/new" method="GET">
		<input type="hidden" name="topicId" value="${topic.id}" /> <input
			type="submit" value="Create new message" />
	</form>

	<c:if test="${pageContext.request.userPrincipal.name ne anonymousUser}">
		<c:out value="Hello ${username}, your roles are: ${roles}" />
	</c:if>

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
		
		<c:choose>
			<c:when test="${anonymousUser}" />
			<c:otherwise>
				<c:if
					test="${pageContext.request.userPrincipal.name eq tempMessage.getAuthor().getUsername()}">
					<form action="${pageContext.request.contextPath}/message/edit" method="GET">
						<input type="hidden" name="messageId" value="${tempMessage.id}" />
						<input type="submit" value="Edit message" />
					</form>
				</c:if>
			</c:otherwise>
		</c:choose>
		<br>
	</c:forEach>
</body>
</html>