<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="${forumMember.username}'s page" />
<%@ include file="snippets/navbar.jspf"%>

<div class="container">

	<div class="table-responsive">
		<table class="table table-borderless">
			<tboby>
			<tr>
				<td><c:out value="${forumMember.username}" /></td>
				<td><form:form class="form-group" action="validateProfile"
						modelAttribute="forumMember" method="POST">
						<form:hidden path="id" />
						<form:hidden path="username" />
						<form:hidden path="password" />
						<form:hidden path="confirmPassword" />

						<fieldset class="form-group">
							<form:label path="firstName" class="font-weight-bold">First Name </form:label>
							<form:input path="firstName" class="form-control" id="firstname"
								required="required" />
							<form:errors path="firstName" cssClass="text-danger" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="lastName" class="font-weight-bold">Last Name </form:label>
							<form:input path="lastName" class="form-control" id="lastname"
								required="required" />
							<form:errors path="lastName" cssClass="text-danger" />
						</fieldset>

						<fieldset class="form-group">
							<form:label path="email" class="font-weight-bold">Email </form:label>
							<form:input path="email" class="form-control" id="email"
								required="required" />
							<form:errors path="email" cssClass="text-danger" />
						</fieldset>

						<input type="hidden" name="action" value="${action}" />
						<input type="submit" class="btn btn-success"
							value="Update Profile" />
						<input type="button" class="btn btn-primary"
							value="Back to Boards"
							onclick="window.location.href='${context}/';" />
					</form:form></td>
			</tr>
			</tboby>
		</table>
	</div>


</div>

<%@ include file="snippets/footer.jspf"%>

