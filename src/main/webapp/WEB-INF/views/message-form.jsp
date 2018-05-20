<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Message Form" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

<h3> <c:out value="${topic.title}" /> </h3>
	
	<div id="form-group" >
		<form:form action="validateMessage" modelAttribute="message" method="POST">
			<form:hidden path="id" />
			<form:hidden path="topicTitle" />
			<form:hidden path="creationTime" />
			<form:hidden path="creationTimeSec" />
			
			<strong> <font color="red"> <c:out  value="${validationStutus}" /> </font></strong>
			<br />
		
			<label for="message">Enter your message</label>
			<form:textarea class="form-control" rows="8" path="messageBody" placeholder="Enter your message here" id="message" />
				
			<input type="hidden" name="topicId" value="${topic.id}">
			<input type="hidden" name="action" value="${action}">
			<input type="submit" value="Save message">
			<input align="right" type="button" value="Back to Boards"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />
		</form:form>
	</div>
	
	</div>
	
	<%@ include file="snippets/footer.jspf"%>