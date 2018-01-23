<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${board.title}" /></title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/TopicStyle.css ">

</head>
<body>
	<h2>
		<c:out value="${board.title}" />
	</h2>
	
	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>
	
	<table width="100%" style="border: none;" >
		<tr >
			<td align="left" bgcolor="#c0c0c0" >
				<security:authorize access="hasRole('MEMBER')">
					<!-- New Topic button -->
					<form action="${pageContext.request.contextPath}/topic/new" method="GET">
						<input type="hidden" name="boardId" value="${board.id}" /> 
						<input type="submit" value="Create new topic" />
					</form>
				</security:authorize>
			</td>
			
			<td align="right" bgcolor="#c0c0c0" bordercolor="#c0c0c0">
				<input align="left" type="button" value="Back to Boards"
					onclick="window.location.href='${pageContext.request.contextPath}/';" />
			</td>
		</tr>
	</table>
			
	
	<table width="100%">
		<thead>
			<tr>
				<th align="left">Topic</th>
				<th width="100"></th>
				<th width="100">Messages</th>
				<th width="150">Created By</th>
				<th width="200">Last Reply</th>
		</thead>
		<tbody>
			<c:forEach var="tempTopic" items="${topicsList}">
				<tr>
					<td align="left">
						<c:url var="topicLink" value="topic/show">
							<c:param name="topicId" value="${tempTopic.id}" />
						</c:url> 
						<a href="${topicLink}"> <c:out value="${tempTopic.title}" /></a>
					</td>
					<td width="100">
						<security:authorize access="hasRole('MODERATOR')">
							<!-- Rename Topic button -->
							<form action="${pageContext.request.contextPath}/topic/rename" method="GET">
								<input type="hidden" name="topicId" value="${tempTopic.id}" /> 
								<input type="submit" value="Rename topic" />
							</form>
						</security:authorize>
					
						<security:authorize access="hasRole('ADMIN')">
							<!-- Delete Topic button -->
							<form action="${pageContext.request.contextPath}/topic/delete" method="GET">
								<input type="hidden" name="topicId" value="${tempTopic.id}" /> 
								<input type="hidden" name="boardId" value="${board.id}" /> 
								<input type="submit" value="Delete topic" 
								onclick="if(!(confirm('Are you sure you want to delete this topic?'))) return false"/>
							</form>
						</security:authorize>
					
					</td>
					<td width="100"><c:out value="${tempTopic.size}" /></td>
					<td width="150"><c:out value="${tempTopic.getAuthor().getUsername()}" /></td>
					<td width="200"><c:out value="${tempTopic.lastMessageTime}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>