<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to SpringForum!</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CssStyle.css ">

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Spring Forum: Home Page</h2>
		</div>
	</div>

	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<div id="container">
		<div id="content">
			<table>
				<thead>
					<tr>
						<th align="left">Board</th>
						<th>Topics</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tempBoard" items="${boards}">
						<tr>
							<td align="left"><br> <c:url var="boardLink"
									value="showBoard">
									<c:param name="boardId" value="${tempBoard.id}" />
								</c:url> <a href="${boardLink}"><strong> <c:out
											value="${tempBoard.title}" /></strong></a> <br></td>
							<td><c:out value="${tempBoard.size}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>