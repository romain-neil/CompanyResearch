<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Importation</title>
		
		<c:import url="inc/header.jsp" />
		
	</head>
	<body>
		<c:import url="inc/menu.jsp" />
		<div class="ui main text container">
			<h2>Importer une liste</h2>
			<form class="ui form" method="post" enctype="multipart/form-data">
				<div class="ui action input">
					<input type="file" name="fichier">
				</div>
				<div class="field">
					<label>Séparateur</label>
					<select class="ui fluid search dropdown" name="separator">
						<option value="1">,</option>
						<option value="2">;</option>
					</select>
				</div>
				<button class="ui button" type="submit">Importer le fichier</button>
			</form>
		</div>
	</body>
</html>