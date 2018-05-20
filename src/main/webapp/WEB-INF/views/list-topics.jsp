<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="${board.title}" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

		<br/>
	<div class="table-responsive">	
	<table class="table table-borderless">
		<tr >
			<td align="left" >
				<security:authorize access="hasRole('MEMBER')">
					<!-- New Topic button -->
					<form action="${pageContext.request.contextPath}/topic/new" method="GET">
						<input type="hidden" name="boardId" value="${board.id}" /> 
						<input type="submit" value="Create new topic" />
					</form>
				</security:authorize>
			</td>
			
			<td align="right">
				<input align="left" type="button" value="Back to Boards"
					onclick="window.location.href='${pageContext.request.contextPath}/';" />
			</td>
		</tr>
	</table>
	</div>
			
	<div class="table-responsive">	
	<table class="table table-borderless table-striped table-hover">
		<thead class="thead-light" style="border: 1px; border-style: outset; ">
			<tr>
				<th align="left">Topic</th>
				<th width="100"></th>
				<th width="100" style="text-align: center;">Messages</th>
				<th width="150" style="text-align: center;">Created By</th>
				<th width="200">Last Reply</th>
		</thead>
		<tbody style="border: 1px;border-style: outset; ">
			<c:forEach var="tempTopic" items="${topicsList}">
				<tr>
					<td align="left">
						<c:url var="topicLink" value="topic/show">
							<c:param name="topicId" value="${tempTopic.id}" />
						</c:url> 
						<a href="${topicLink}"> <c:out value="${tempTopic.title}" /></a>
					</td>
					<td>
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
					<td align="center"><c:out value="${tempTopic.size}" /></td>
					<td align="center"><c:out value="${tempTopic.getAuthor().getUsername()}" /></td>
					<td><c:out value="${tempTopic.lastMessageTime}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>
	</div>

	<%@ include file="snippets/footer.jspf"%>