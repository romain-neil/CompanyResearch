<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edition</title>
		
		<c:import url="inc/header.jsp" />
		
	</head>
	<body>
		<c:import url="inc/menu.jsp" />
		<div class="ui main text container">
			<h2>Modifier une entreprise</h2>
			<form class="ui form" action="" method="post">
				<div class="field">
					<label for="name">Nom de l'entreprise</label>
					<input id="name" type="text" name="nom" value="<c:out value='${ent.name }' />">
				</div>
				<div class="field">
					<label for="address">Adresse</label>
					<input id="address" type="text" name="adresse" value="<c:out value='${ent.address }' />">
				</div>
				<div class="field">
					<div class="ui checkbox">
						<input type="checkbox" tabindex="0" name="recall">
						<label>Entreprise recontactée</label>
					</div>
				</div>
				<div class="field">
					<div class="ui checkbox">
						<input type="checkbox" tabindex="1" name="isHiring">
						<label>L'entreprise recrute</label>
					</div>
				</div>
				<div class="field">
					<input type="email" name="email" value="<c:out value='${ent.email}' />">
					<label>Adresse de contact</label>
				</div>
				<div class="field">
					<input type="tel" name="phone" value="<c:out value='${ent.phone}' />">
					<label>Telephone</label>
				</div>
				<input name="uuid" value="<c:out value='${ent.uuid }' />" hidden="hidden">
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>
	</body>
</html>