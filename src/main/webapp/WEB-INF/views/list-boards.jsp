<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to SpringForum!</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/BoardStyle.css ">

</head>
<body>

	<h2>Spring Forum: Home Page</h2>


	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<div id="boards-table">
		<table width="100%">
			<thead>
				<tr>
					<th align="left">Board</th>
					<th width="60" align="center">Topics</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tempBoard" items="${boards}">
					<tr>
						<td align="left">
							<br> 
							<c:url var="boardLink" value="showBoard">
								<c:param name="boardId" value="${tempBoard.id}" />
							</c:url>
							<a href="${boardLink}"><strong><c:out value="${tempBoard.title}"/></strong></a> 
							<br>
						</td>
						<td width="60" align="center">
							<c:out value="${tempBoard.size}" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>