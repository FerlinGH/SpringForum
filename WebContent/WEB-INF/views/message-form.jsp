<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CssStyle.css ">

</head>
<body>
	<h3>
		<c:out value="${topic.title}" />
	</h3>

	<form:form action="validateMessage" modelAttribute="message" method="POST">
	<form:hidden path="id" />
	<form:hidden path="topicTitle" />
	<form:hidden path="creationTime" />
	<form:hidden path="creationTimeSec" />
		<table>
			<tr>
			<form:textarea path="messageBody" placeholder = "Enter your message here" />
			</tr>
		</table>
		<c:out  value="${validationStutus}" /><br>
		<input type="hidden" name="topicId" value="${topic.id}">
		<input type="hidden" name="action" value="${action}">
		<input type="submit" value="Save message">
	</form:form>

</body>
</html>