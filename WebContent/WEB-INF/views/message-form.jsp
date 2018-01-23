<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/FormStyle.css ">

</head>
<body>
	
	<h2>Message Form</h2>
	
	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<div id="form" align="center">
		<form:form action="validateMessage" modelAttribute="message" method="POST">
			<form:hidden path="id" />
			<form:hidden path="topicTitle" />
			<form:hidden path="creationTime" />
			<form:hidden path="creationTimeSec" />
			
			<strong> <font color="red"> <c:out  value="${validationStutus}" /> </font></strong>
			<br>
			
			<table width="100%" align="center">
				<tr>
					<td width="200">
						Current topic name:
					</td>
					<td align="left">
						<strong > <c:out value="${topic.title}" /> </strong>
					</td>
				</tr>
				<tr>
					<td width="200">
						Enter your message:
					</td>
					<td align="left">
						<form:textarea rows="8" cols="151" path="messageBody" placeholder="Enter your message here" />
					</td>
				</tr>
			</table>
			<br>
				
			<input type="hidden" name="topicId" value="${topic.id}">
			<input type="hidden" name="action" value="${action}">
			<input type="submit" value="Save message">
			<input align="right" type="button" value="Back to Boards"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />
		</form:form>
	</div>
</body>
</html>