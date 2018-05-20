<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Access Denied" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

	<br/>

	<div id="form" align="center" style="width: 200px; margin: auto;">
		<h3>FORBIDDEN</h3>
		Access denied for the page you requested (ERROR 403). 
		<br><br>
		<input type="button" value="Back to Boards"
			onclick="window.location.href='${pageContext.request.contextPath}/';" />
	</div>
	
</div>

<%@ include file="snippets/footer.jspf"%>