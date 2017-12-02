<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${topic.title}" /></title>
</head>
<h2>
	<c:out value="${topic.title}" />
</h2>
<br>

<c:forEach var="tempMessage" items="${messageList}">
	<h4>
		<c:out value="${tempMessage.title}" />
	</h4>
	<c:out value="${messagesMap[tempMessage.id]}" />
	<c:out value="${tempMessage.creationTime} " />
	<br>
	<c:out value="${tempMessage.messageBody}" />
	<br><br>
	<hr>
	<br>
</c:forEach>
<body>
</body>
</html>