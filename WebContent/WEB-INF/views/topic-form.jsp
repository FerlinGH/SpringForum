<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New topic form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/FormStyle.css ">

</head>
<body>

	<h2>New Topic Form</h2>
	
	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<div id="form" align="center">
		<form:form action="validateTopic" modelAttribute="message" method="POST">
			<form:hidden path="id" />
			<input type="hidden" name="boardId" value="${board.id}" />
			
			<strong> <font color="red"> <c:out  value="${validationStutus}" /> </font></strong>
			<br>
			
			<table width="100%" align="center">
				<tr>
					<td width="150">
						Enter new topic name:
					</td>
					<td>
						<input size="149" type="text" name="topicName" value="${topicName}" align="middle" />
					</td>
				</tr>
				<tr>
					<td width="150">
						Enter your message:
					</td>
					<td align="center">
						<form:textarea rows="8" cols="151" path="messageBody" placeholder="Enter your message here" />
					</td>
				</tr>
			</table>
			<br>
			<input type="submit" value="Save message">
			<input align="right" type="button" value="Back to Boards"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />
		</form:form>
	</div>
</body>
</html>