<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New topic form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CssStyle.css ">

</head>
<body>
	<form:form action="validateTopic" modelAttribute="message"
		method="POST">

		<form:hidden path="id" />
		<input type="hidden" name="boardId" value="${board.id}" />
		<table>
			<tr>
				<td>Enter new topic name:</td>
				<td><input type="text" name="topicName" value="${topicName}" /></td>
			</tr>
			<tr>
				<td>Creator:</td>
				<td><c:out value="${pageContext.request.userPrincipal.name}" /></td>
			</tr>
			<tr>
				<td>Enter your message:</td>
				<td><form:textarea path="messageBody"
						placeholder="Enter your message here" /></td>
			</tr>
		</table>
		<c:out value="${validationStutus}" />
		<br>
		<input type="submit" value="Save message">
	</form:form>

</body>
</html>