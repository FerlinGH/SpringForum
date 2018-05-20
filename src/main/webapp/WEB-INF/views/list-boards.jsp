<%@ include file="snippets/header.jspf"%>

<c:set var="pageHeader" value="Spring Forum: Home Page" />
<%@ include file="snippets/navbar.jspf"%>

	<div class="container">
		
		<br/>
		<div class="table-responsive">
			<table class="table table-borderless table-hover">
				<thead class="thead-light" style="border: 1px; border-style: outset; ">
					<tr>
						<th>Board</th>
						<th style="text-align: right;">Topics</th>
					</tr>
				</thead>
				<tbody style="border: 1px;border-style: outset; ">
					<c:forEach var="tempBoard" items="${boards}">
						<tr>
							<td>
								<c:url var="boardLink" value="showBoard">
									<c:param name="boardId" value="${tempBoard.id}" />
								</c:url>
								<a href="${boardLink}"><strong><c:out value="${tempBoard.title}"/></strong></a> 
							</td>
							<td style="text-align: right;">
								<c:out value="${tempBoard.size}" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	

<%@ include file="snippets/footer.jspf"%>