<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:choose>
	<c:when test="${pageContext.request.userPrincipal.name != null}">
		Welcome back, ${pageContext.request.userPrincipal.name}! You can
		<a href="${pageContext.request.contextPath}/logout">
			Logout
		</a>
	</c:when>
	<c:otherwise>
		Greetings, Guest!
		<a href="${pageContext.request.contextPath}/showLoginPage">
			Login
		</a>
			or 
		<a href="${pageContext.request.contextPath}/forumMember/create">
			Create new profile
		</a>
	</c:otherwise>
</c:choose>
	
</body>
</html>