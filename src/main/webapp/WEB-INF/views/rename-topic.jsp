<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rename Topic Form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/FormStyle.css ">

</head>
<body>

	<h2>Rename Topic Form</h2>
	
	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<div id="form" align="center" style="width: 700px; margin: auto;">			

	<h3>Enter new topic name:</h3>
	<form action="setNewTopicName" method="POST" >
		<input type="hidden" name="topicId" value="${topicId}" /> 
		<input  size="80" type="text" name="topicName" value="${topicName}" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<br><br>
		
		<input type="submit" value="OK"> 
		<input type="button" value="Cancel"
			onclick="window.location.href='${pageContext.request.contextPath }/';" />			
	</form>
	</div>
</body>
</html>