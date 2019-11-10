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
			<h2>Ajouter une entreprise</h2>
			<form class="ui form" action="" method="post">
				<div class="field">
					<label>Nom de l'entreprise</label>
					<input type="text" name="nom">
				</div>
				<div class="field">
					<label>Adresse</label>
					<input type="text" name="adresse">
				</div>
				<div class="field">
					<div class="ui checkbox">
						<input type="checkbox" tabindex="0" name="recall">
						<label>Entreprise recontactée</label>
					</div>
				</div>
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>
	</body>
</html>