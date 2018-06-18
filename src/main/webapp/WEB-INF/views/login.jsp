<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Login Page" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">
<br />

 	<div id="form" align="center" style="width: 400px; margin: auto;">  

		<form:form action="${context}/authenticateUser"	method="POST">
			<!--  Check for login error -->
			<c:if test="${param.error != null}">
				<strong> <font color="red">Invalid username/password </font> </strong>
			</c:if>
			<br>
			
			<div class="form-group">
			<span class="fas fa-user"></span>
				<input type="text" name="username" placeholder="Login" />
			</div>
			
			<div class="form-group">
			<span class="fas fa-key"></span>
				<input type="password" name="password" placeholder="Password">
			</div>
				

			<input align="middle" class="btn btn-success" type="submit" value="Login" />
			<button type="button" class="btn btn-primary" 
					onclick="window.location.href='${context}/';">Cancel</button>

		</form:form>
	</div>
	
	</div>   
	
	<%@ include file="snippets/footer.jspf"%>