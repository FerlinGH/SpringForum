<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CssStyle.css ">

<style>
.failed {
	color: red;
}
</style>
</head>
<body>
	<h3 align="center">Login Page</h3>

	<form:form action="${pageContext.request.contextPath}/authenticateUser"
		method="POST">
		<!--  Check for login error -->
		<c:if test="${param.error != null}">
			<i class="failed">Invalid username/password</i>
		</c:if>

		<table align="center">
			<tr>
				<td>User name:</td>
				<td><input type="text" name="username"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td />
				<td><input align="middle" type="submit" value="Login" /> <input
					align="middle" type="button" value="Cancel"
					onclick="window.location.href='${pageContext.request.contextPath}';" />
		</table>
	</form:form>
</body>
</html>