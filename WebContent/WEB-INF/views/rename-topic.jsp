<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rename Topic Form</title>
</head>
<body>

	<h3>Enter new topic name:</h3>
	<form action="setNewTopicName" method="POST">
		<input type="hidden" name="topicId" value="${topicId}" /> 
		<input type="text" name="topicName" value="${topicName}" /> 
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<input type="submit" value="OK"> 
		<input type="button" value="Cancel"
			onclick="window.location.href='${pageContext.request.contextPath }/';" />			
	</form>
</body>
</html>