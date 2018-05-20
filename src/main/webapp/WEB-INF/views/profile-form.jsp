<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Registration Form" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

	<div align="center" style="width: 700px; margin: auto;">

		<form:form  class="form-group" action="validateProfile" modelAttribute="forumMember" method="POST">
			<form:hidden path="id" />
			<strong> <font color="red"> <c:out value="${message}" /> </font> </strong>
			<br> 
						
			 	<label for="login" class="font-weight-bold">Choose your nickname</label>
			    <form:input path="username" class="form-control" id="login" required="required"/>
			
			 	<label for="password1" class="font-weight-bold">Create your password </label>
			    <input type="password" name="passwordCandidate1" class="form-control" id="password1" required="required"/>
			
			 	<label for="password2" class="font-weight-bold">Re-enter your password </label>
			    <input type="password" name="passwordCandidate2" class="form-control" id="password2" required="required"/>			
			
			 	<label for="firstname" class="font-weight-bold">First Name </label>
			    <input type="text" name="firstName" value="${firstName}" class="form-control" id="firstname" required="required"  />
				
			 	<label for="lastname" class="font-weight-bold">Last Name </label>
			    <input type="text" name="lastName" value="${lastName}" class="form-control" id="lastname" required="required"  />			
			
			 	<label for="email" class="font-weight-bold">Email </label>
			    <input type="text" name="email" value="${email}" class="form-control" id="email" required="required"  />
		
			<input type="submit" value="Create Profile" />
			<input type="button" value="Cancel"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />
		</form:form>
	</div>
	
	</div>
<%@ include file="snippets/footer.jspf"%>