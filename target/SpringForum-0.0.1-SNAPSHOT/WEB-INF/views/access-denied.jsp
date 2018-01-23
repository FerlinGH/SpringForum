<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Access Denied</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/FormStyle.css ">

</head>
<body>

	<h2>Access Denied</h2>

	<div id="form" align="center" style="width: 200px; margin: auto;">
		<h3>FORBIDDEN</h3>
		Access denied for the page you requested (ERROR 403). 
		<br><br>
		<input type="button" value="Back to Boards"
			onclick="window.location.href='${pageContext.request.contextPath}/';" />
	</div>
</body>
</html>