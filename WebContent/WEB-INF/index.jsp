<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Index</title>
		
		<c:import url="inc/header.jsp" />
		
	</head>
	<body>
		<c:import url="inc/menu.jsp" />
		<div class="ui main text container">​
			<c:choose>
				<c:when test="${!empty sessionScope.message}">
					<div class="ui warning message">
						<c:out value="${sessionScope.message}" />
					</div>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${!empty sessionScope.liste }">
					<span class="left"><h2>Liste des entreprises</h2></span><span class="right">Total: <c:out value="${sessionScope.liste.size()}" /></span>
					<table class="ui celled table">
						<thead>
							<tr>
								<th>Nom</th>
								<th>Adresse</th>
								<th>Ajouté le</th>
								<th>Relancée ?</th>
								<th>Embauche ?</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.liste }" var="mapEnt" varStatus="boucle">
								<tr>
									<td><a href="<c:url value='/edit?id=${mapEnt.value.uuid }' />"><c:out value="${mapEnt.value.name }" /></a></td>
									<td><c:out value="${mapEnt.value.address }" /></td>
									<td><c:out value="${mapEnt.value.dateAdded }" /></td>
									<td><c:out value="${mapEnt.value.recall }" /></td>
									<td><c:out value="${mapEnt.value.isHiring }" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>				
					<span class="left"><a href="<c:url value='/export' />">Exporter</a></span><span class="right"><a href="<c:url value='/export?pdf' />">Exporter en PDF</a></span>
				</c:when>
				<c:otherwise>
					<h2>Aucune entreprise pour le moment !</h2>
				</c:otherwise>
			</c:choose>
		</div>
	</body>
	<div class="footer">
		<c:import url="inc/footer.jsp" />
	</div>
</html>