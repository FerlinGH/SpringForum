<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="New Topic Form" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

<br />
		
	<div >
		<form:form  class="form-group" action="validateTopic" modelAttribute="message" method="POST">
			<form:hidden path="id" />
			<input type="hidden" name="boardId" value="${board.id}" />
			
			<strong> <font color="red"> <c:out  value="${validationStutus}" /> </font></strong>
			<br>
			
			<label for="topicname" class="font-weight-bold">Enter new topic name</label>
			<input type="text" name="topicName" value="${topicName}" class="form-control" id="topicname" required="required"/>
			
			<label for="message" class="font-weight-bold">Enter your message</label>
			<form:textarea class="form-control" rows="8" path="messageBody" placeholder="Enter your message here" id="message" />
					
			<br>
			<input type="submit" value="Create Topic">
			<input align="right" type="button" value="Back to Boards"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />
		</form:form>
	</div>
	
	</div>
<%@ include file="snippets/footer.jspf"%>