<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Registration Form" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

	<div align="center" style="width: 700px; margin: auto;">

		<form:form  class="form-group" action="validateProfile" modelAttribute="forumMember" method="POST">
			<form:hidden path="id" />
				
				<fieldset class="form-group">		
			 	<form:label path="username" class="font-weight-bold">Choose your nickname</form:label>
			    <form:input path="username" class="form-control" id="login" required="required"/>
			    <form:errors path="username" cssClass="text-danger"/> 
			    </fieldset>
			    	
			    <fieldset class="form-group">		
			 	<form:label path="password" class="font-weight-bold">Create your password </form:label>
			    <form:input path="password" class="form-control" type="password" id="password" required="required"/>
			    <form:errors path="password" cssClass="text-danger"/> 
				</fieldset>
			
				<fieldset class="form-group">
			 	<form:label path="confirmPassword" class="font-weight-bold">Re-enter your password </form:label>
			    <form:input path="confirmPassword" class="form-control" type="password" id="confirmPassword" required="required"/>			
				<form:errors path="password" cssClass="text-danger"/> 
				</fieldset>
			
				<fieldset class="form-group">
			 	<form:label path="firstName" class="font-weight-bold">First Name </form:label>
			    <form:input path="firstName" class="form-control" id="firstname" required="required"  />
			    <form:errors path="firstName" cssClass="text-danger"/>
				</fieldset>
				
				<fieldset class="form-group">
			 	<form:label path="lastName" class="font-weight-bold">Last Name </form:label>
			    <form:input path="lastName" class="form-control" id="lastname" required="required"  />	
			    <form:errors path="lastName" cssClass="text-danger"/>	
				</fieldset>
			
				<fieldset class="form-group">
			 	<form:label path="email" class="font-weight-bold">Email </form:label>
			    <form:input path="email" class="form-control" id="email" required="required"  />
				<form:errors path="email" cssClass="text-danger"/>
				</fieldset>
		
			<input type="submit" class="btn btn-success" value="Create Profile" />
			<input type="button" class="btn btn-primary" value="Cancel"
				onclick="window.location.href='${context}/';" />
		</form:form>
	</div>
	
	</div>
<%@ include file="snippets/footer.jspf"%>