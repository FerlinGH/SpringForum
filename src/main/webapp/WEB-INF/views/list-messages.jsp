<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="${topic.title}" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">
	<br />
	<security:authorize access="hasRole('MEMBER')">
		<!-- New Message button -->
		<form action="${context}/message/new" method="GET">
			<input type="hidden" name="topicId" value="${topic.id}" /> 
			<p>
				<input type="submit" class="btn btn-success" value="Create new message" />
			</p>
		</form>
	</security:authorize>


	<c:forEach var="tempMessage" items="${messageList}">
		
			<div class="table-responsive">
				<table class="table table-borderless">
					<thead class="thead-light" style="border: 1px; border-style: outset; ">
						<tr>
							<th colspan="6" style="text-align: left;">
								<c:out value="${tempMessage.topicTitle}" />
							</th>
							<th style="text-align: right;">
								<c:out value="${tempMessage.creationTime}" />
							</th>
						</tr>
					</thead>
					
					<tfoot style="background-color: #e9ecef; border: 1px; border-style: outset; ">
						<tr>
						<td style="text-align: left;">
							<security:authorize access="hasRole('ADMIN')">
								<!-- Admins can delete any messages -->
								<form action="${context}/message/delete" method="GET">
									<input type="hidden" name="topicId" value="${topic.id}" /> 
									<input type="hidden" name="messageId" value="${tempMessage.id}" /> 
									<input type="submit" class="btn btn-danger" value="Delete message"
										onclick="if(!(confirm('Are you sure you want to delete this message?'))) return false" />
								</form>
							</security:authorize>

							<security:authorize access="hasRole('MODERATOR')">
							<!-- Moderators can edit any messages -->
								<form action="${context}/message/edit" method="GET">
									<input type="hidden" name="messageId" value="${tempMessage.id}" />
									<input type="submit" class="btn btn-warning" value="Moderate message" />
								</form>
							</security:authorize>
						</td>
						<td /><td />

						<td style="text-align: center;">
							<security:authorize access="hasRole('MEMBER')">
							<!-- Regular members can edit their own messages -->
							<security:authentication property="principal.username" var="currentUserName" />
								<c:choose>
									<c:when test="${anonymousUser}" />
									<c:otherwise>
										<c:if test="${currentUserName eq tempMessage.author.username}">
											<form action="${context}/message/edit" method="GET">
												<input type="hidden" name="messageId" value="${tempMessage.id}" />
												<input type="submit" class="btn btn-warning" value="Edit your message" />
											</form>
										</c:if>
									</c:otherwise>
								</c:choose>
							</security:authorize>
						</td>
						
						<td /><td />
				
						<td style="text-align: right;">
							<button type="button" class="btn btn-primary" 
					onclick="window.location.href='${context}/';">Back to Boards</button>
						</td>
						</tr>
					</tfoot>
					
					
				<tbody style="border: 1px; border-style: outset; ">
					<tr>
						<td width="150" style="text-align: center; vertical-align:bottom; border: 1px; border-style: outset;">
							<i><c:out value="${tempMessage.author.username}" /></i>
						</td>
						<td colspan="6" style="text-align: left;">
							<c:out value="${tempMessage.messageBody}" />
							<br> <br>
							<i><font size="2"><c:out value="${tempMessage.editInfo}" /></font></i>
						</td>
					</tr>
				</tbody>
				</table>
			</div>
		<br>
	</c:forEach>

	
</div>

	<%@ include file="snippets/footer.jspf"%>