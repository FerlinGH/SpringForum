<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New message form</title>
</head>
<body>
	<h3>
		<c:out value="${topic.title}" />
	</h3>

	<form:form action="validateMessage" modelAttribute="message" method="POST">
	<form:hidden path="id" />
		<table>
			<tr>
				<td>Creator:</td>
				<td><c:out value="${pageContext.request.userPrincipal.name}" />
				</td>
			</tr>
			<tr>
			<form:textarea path="messageBody" placeholder = "Enter your message here" />
			</tr>
		</table>
		<c:out  value="${validationStutus}" /><br>
		<input type="hidden" name="topicId" value="${topic.id}">
		<input type="submit" value="Save message">
	</form:form>

</body>
</html>