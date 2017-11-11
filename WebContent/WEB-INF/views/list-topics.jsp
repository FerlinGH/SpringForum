<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${board.title}" /></title>
</head>
<body>
	<h2>
		<c:out value="${board.title}" />
	</h2>
	<hr>
	<br>
	<table>
		<thead>
			<tr>
				<th>Topic</th>
				<th>Messages</th>
				<th>Created By</th>
				<th>Last Reply</th>
		</thead>
		<tbody>
			<c:forEach var="tempTopic" items="${topicsList}">
				<tr>
					<td><c:url var="topicLink" value="topic/show">
							<c:param name="topicId" value="${tempTopic.id}" />
						</c:url> 
						<a href="${topicLink}"> <c:out value="${tempTopic.title}" /></a>
					</td>
					<td><c:out value="${tempTopic.size}" /> </td>
					<td><c:out value="${topicsMap[tempTopic.id]}" /> </td>
					<td><c:out value="${tempTopic.lastMessageTime}" /> </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>