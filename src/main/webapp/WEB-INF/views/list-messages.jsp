<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${topic.title}" /></title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/MessageStyle.css ">

</head>
<body>

	<h2>
		<c:out value="${topic.title}" />
	</h2>

	<div id="user-info" align="right">
		<c:import url="snippets/user-info.jsp" />
	</div>

	<security:authorize access="hasRole('MEMBER')">
		<!-- New Message button -->
		<form action="${pageContext.request.contextPath}/message/new" method="GET">
			<input type="hidden" name="topicId" value="${topic.id}" /> 
			<input type="submit" value="Create new message" />
		</form>
	</security:authorize>


	<c:forEach var="tempMessage" items="${messageList}">
		<div id="message">
			<div id="message-header">
				<table width="100%">
					<tr>
						<td align="left">
							<strong><c:out value="${tempMessage.topicTitle}" /></strong>
						</td>
						<td width="150" align="right">
							<c:out value="${tempMessage.creationTime}" />
						</td>
					</tr>
					<tr>
						<td align="left">
							<i><c:out value="${tempMessage.author.username}" /></i>
						</td>
						<td />
					</tr>
				</table>
			</div>
			<br>

			<div id="message-body">
				<c:out value="${tempMessage.messageBody}" />
				<br> <br>
				<i><font size="2"><c:out value="${tempMessage.editInfo}" /></font></i>
			</div>

			<div id="message-options">
				<table width="100%">
					<tr>
						<td align="left">
							<security:authorize access="hasRole('ADMIN')">
								<!-- Admins can delete any messages -->
								<form action="${pageContext.request.contextPath}/message/delete" method="GET">
									<input type="hidden" name="topicId" value="${topic.id}" /> 
									<input type="hidden" name="messageId" value="${tempMessage.id}" /> 
									<input type="submit" value="Delete message"
										onclick="if(!(confirm('Are you sure you want to delete this message?'))) return false" />
								</form>
							</security:authorize>
						</td>

						<td align="center">
							<security:authorize access="hasRole('MODERATOR')">
							<!-- Moderators can edit any messages -->
								<form action="${pageContext.request.contextPath}/message/edit" method="GET">
									<input type="hidden" name="messageId" value="${tempMessage.id}" />
									<input type="submit" value="Moderate message" />
								</form>
							</security:authorize>
						</td>

						<td align="center">
							<security:authorize access="hasRole('MEMBER')">
							<!-- Regular members can edit their own messages -->
							<security:authentication property="principal.username" var="currentUserName" />
								<c:choose>
									<c:when test="${anonymousUser}" />
									<c:otherwise>
										<c:if test="${currentUserName eq tempMessage.author.username}">
											<form action="${pageContext.request.contextPath}/message/edit" method="GET">
												<input type="hidden" name="messageId" value="${tempMessage.id}" />
												<input type="submit" value="Edit your message" />
											</form>
										</c:if>
									</c:otherwise>
								</c:choose>
							</security:authorize>
						</td>
				
						<td align="right">
							<input align="right" type="button" value="Back to Boards"
								onclick="window.location.href='${pageContext.request.contextPath}/';" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<br>
	</c:forEach>
</body>
</html>