<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${topic.title}" /></title>
<spring:url value="/resources/CssStyle.css" var="style" />
<link href="${style}" rel="stylesheet" />
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />


	<h2>
		<c:out value="${topic.title}" />
	</h2>
	
	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<form action="${contextPath}/message/new" method="GET">
		<input type="hidden" name="topicId" value="${topic.id}" /> 
		<input type="submit" value="Create new message" />
	</form>

	<c:forEach var="tempMessage" items="${messageList}">
		<h4>
			<c:out value="${tempMessage.topicTitle}" />
		</h4>
		<c:out value="${tempMessage.getAuthor().getUsername()}" />
		<c:out value="${tempMessage.creationTime}" />
		<hr>
		<c:out value="${tempMessage.messageBody}" />
		<br>
		<br>
	</c:forEach>
</body>
</html>