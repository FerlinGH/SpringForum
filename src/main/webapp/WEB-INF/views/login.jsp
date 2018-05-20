<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Login Page" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">
<br />

 	<div id="form" align="center" style="width: 400px; margin: auto;">  

		<form:form action="${pageContext.request.contextPath}/authenticateUser"	method="POST">
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
				

			<input align="middle" type="submit" value="Login" />
			<input align="middle" type="button" value="Cancel"
				onclick="window.location.href='${pageContext.request.contextPath}/';" />

		</form:form>
	</div>
	
	</div>   
	
	<%@ include file="snippets/footer.jspf"%>