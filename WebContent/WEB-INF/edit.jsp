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
					<label>Nom de l'entreprise</label>
					<input type="text" name="nom" value="<c:out value='${ent.name }' />">
				</div>
				<div class="field">
					<label>Adresse</label>
					<input type="text" name="adresse" value="<c:out value='${ent.address }' />">
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
				<input name="uuid" value="<c:out value='${ent.uuid }' />" hidden="hidden">
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>
	</body>
</html>