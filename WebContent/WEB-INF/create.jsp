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
		<div class="ui main text container">
			<c:if test="${!empty sessionScope.message}">
				<div class="ui warning message">
					<c:out value="${sessionScope.message}" />
				</div>
			</c:if>
			<h2>Ajouter une entreprise</h2>
			<form class="ui form" action="" method="post">
				<div class="field">
					<label for="name">Nom de l'entreprise</label>
					<input id="name" type="text" name="nom" required>
				</div>
				<div class="field">
					<label for="address">Adresse</label>
					<input id="address" type="text" name="adresse" required>
				</div>
				<div class="field">
					<div class="ui checkbox">
						<input id="recontact" type="checkbox" tabindex="0" name="recall">
						<label for="recontact">Entreprise recontactée</label>
					</div>
				</div>
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>
	</body>
	<div class="footer">
		<c:import url="inc/footer.jsp" />
	</div>
</html>