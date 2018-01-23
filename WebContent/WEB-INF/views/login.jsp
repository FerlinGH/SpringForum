<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/FormStyle.css ">


</head>
<body>
	<h2>Login Page</h2>

	<div id="form" align="center" style="width: 400px; margin: auto;">

		<form:form action="${pageContext.request.contextPath}/authenticateUser"	method="POST">
			<!--  Check for login error -->
			<c:if test="${param.error != null}">
				<strong> <font color="red">Invalid username/password </font> </strong>
			</c:if>
			<br>

			<table align="center">
				<tr>
					<td>User name:</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<br>

			<input align="middle" type="submit" value="Login" />
			<input align="middle" type="button" value="Cancel"
				onclick="window.location.href='${pageContext.request.contextPath}';" />

		</form:form>
	</div>
</body>
</html>