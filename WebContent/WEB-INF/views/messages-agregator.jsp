<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${topic.title}" /></title>
</head>
<body>

	<h2>
		<c:out value="${topic.title}" />
	</h2>

	<c:forEach var="tempMessage" items="${messageList}">
		<c:import url="single-message.jsp">
			<c:param name="title" value="${tempMessage.title}" />
			<c:param name="creator" value="${messagesMap[tempMessage.id]}" />
			<c:param name="time" value="${tempMessage.creationTime}" />
			<c:param name="body" value="${tempMessage.messageBody}" />
		</c:import>
	</c:forEach>
</body>
</html>