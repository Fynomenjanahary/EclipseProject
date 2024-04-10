<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FORM</title>
    <link rel="stylesheet" href="./connexion.css">
</head>
<body>
    <div class="box" style="height: 700px;width: 450px;">
		<span class="b"></span>
		<form method="post" action="http://localhost:8080/SpendDumbly/Add">
			<h2>Ajouter un dépense</h2>
			<div class="inputbox">Date : 
				<input type="datetime" required="required" name="date">
			</div>
			
			<div class="inputbox">
				Montant : 
				<input id="pswd" type="number" required="required" name="montant">
			</div>
			
			<div class="inputbox">
			 	Categorie : 
				<select name="categorie">
				<option value="nourriture">Nourriture</option>
				<option value="vetement">Vetement</option>
				<option value="facture">Facture</option>
				<option value="loyer">Loyer</option>
				<option value="autre">Autre</option>
				</select>
			</div>
			
			<div class="inputbox">
				Description
				<input id="text" type="text" required="required" name="description">
			</div>
			<input type="hidden" name="user" value="<%= request.getParameter("user")%>">
			<input type="hidden" name="pass" value="<%= request.getParameter("pass")%>">
			
			<input type="submit" value="Ajouter">
		</form>
    </div>
		
	</form>
</body>
</html>