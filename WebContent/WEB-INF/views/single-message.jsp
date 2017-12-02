<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/BootstrapMin.css" var="BootstrapMin" />
<link href="${BootstrapMin}" rel="stylesheet" />
</head>
<body>
	<div align="left">
		<h4>
			<c:out value="${param.title}" />
		</h4>
		<c:out value="${param.creator}" />
		<c:out value="${param.time}" />
		<br>
		<c:out value="${param.body}" />
		<br> <br> End of message
		<hr>
	</div>
</body>
</html>