<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to SpringForum!</title>
</head>
<body>
	<h2>Spring Forum: Home Page</h2>
	<hr>
	<br>
	<table>
		<caption>Available boards</caption>
		<thead>
			<tr>
				<th>Board</th>
				<th>Topics</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
			<c:forEach var="tempBoard" items="${boards}">
				<tr>
					<td><c:url var="boardLink" value="showBoard">
							<c:param name="boardId" value="${tempBoard.id}" />
						</c:url> 
						<a href="${boardLink}"> <c:out value="${tempBoard.title}" /></a>
					</td>
					<td><c:out value="${tempBoard.size}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>