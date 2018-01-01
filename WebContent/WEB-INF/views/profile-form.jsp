<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Profile Form</title>
</head>
<body>
	<h3>Please fill all the fields</h3>
	<form:form action="validateProfile" modelAttribute="forumMember"
		method="POST">
		<form:hidden path="id" />
		<p>
			<strong> <c:out value="${message}"/> </strong>
		</p>
		<table>
			<tr>
				<td>Choose your nickname:</td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td>Create your password:</td>
				<td><input type="password" name="passwordCandidate1" /></td>
			</tr>
			<tr>
				<td>Re-enter your password:</td>
				<td><input type="password" name="passwordCandidate2" /></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" value="${firstName}" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" value="${lastName}" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" value="${email}" /></td>
			</tr>
			<tr>
				<td />
				<td>
					<input type="submit" value="Create Profile" />
				 	<input type="button" value="Cancel"
						onclick="window.location.href='${pageContext.request.contextPath}';" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>