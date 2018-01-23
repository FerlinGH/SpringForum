<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Profile Form</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/FormStyle.css ">

</head>
<body>

	<h2>Registration Form</h2>
	<br>

	<div id="form" align="center" style="width: 700px; margin: auto;">

		<h3>Please fill all the fields</h3>
		<form:form action="validateProfile" modelAttribute="forumMember" method="POST">
			<form:hidden path="id" />
			<strong> <font color="red"> <c:out value="${message}" /> </font> </strong>
			<br> <br>
			<table width="500" align="center">
				<tr>
					<td width="200">Choose your nickname:</td>
					<td><form:input path="username" size="40" /></td>
				</tr>
				<tr>
					<td width="200">Create your password:</td>
					<td><input type="password" name="passwordCandidate1" size="40" /></td>
				</tr>
				<tr>
					<td width="200">Re-enter your password:</td>
					<td><input type="password" name="passwordCandidate2" size="40" /></td>
				</tr>
				<tr>
					<td width="200">First Name:</td>
					<td><input type="text" name="firstName" value="${firstName}"
						size="40" /></td>
				</tr>
				<tr>
					<td width="200">Last Name:</td>
					<td><input type="text" name="lastName" value="${lastName}"
						size="40" /></td>
				</tr>
				<tr>
					<td width="200">Email:</td>
					<td><input type="text" name="email" value="${email}" size="40" /></td>
				</tr>
				<tr>
			</table>
			<br>

			<input type="submit" value="Create Profile" />
			<input type="button" value="Cancel"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />
		</form:form>
	</div>
</body>
</html>