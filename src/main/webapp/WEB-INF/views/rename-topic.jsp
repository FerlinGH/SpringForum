<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Rename Topic Form" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">
 <br />
	
	<div id="form-group">			

	<form action="setNewTopicName" method="POST" >
		<input type="hidden" name="topicId" value="${topicId}" /> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<label for="topicname" class="font-weight-bold">Enter new topic name:</label>
		<input  type="text" name="topicName" class="form-control" id="topicname" value="${topicName}" required="required" /> 
		
		<br>
		
		<input type="submit" value="OK"> 
		<input type="button" value="Cancel"
			onclick="window.location.href='${pageContext.request.contextPath }/';" />			
	</form>
	</div>
	
	</div>
	
	<%@ include file="snippets/footer.jspf"%>