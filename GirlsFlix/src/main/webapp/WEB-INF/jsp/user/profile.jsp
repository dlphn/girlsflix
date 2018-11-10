<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>GirlsFlix - Mon profil</title>
		
		<link rel="icon" type="image/png" href="/GirlsFlix/lib/favicon.png">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="/GirlsFlix/css/style.css" />
	</head>
	<body>
	
		<fragments:header />
		
		<div class="main">
			<div class="container">
				<h2>Mon profil</h2>
				<p> ${message} </p>
				<form:form method="POST" modelAttribute="user">
					<div class="row">
					    <form:label path="login" for="inputLogin" class="col-sm-3 col-form-label">Login</form:label>
					    <div class="col-sm-9">
					      	<form:input class="form-control form-control-plaintext" id="inputLogin" path="login" required="required"/>
					    </div>
				    </div>
				    <div class="row">
					    <form:label path="pseudo" for="inputPseudo" class="col-sm-3 col-form-label">Pseudo</form:label>
					    <div class="col-sm-9">
					      	<form:input class="form-control" id="inputPseudo" path="pseudo" required="required"/>
					    </div>
				    </div>
				    <div class="row">
					    <form:label path="firstName" for="inputFirstName" class="col-sm-3 col-form-label">Prénom</form:label>
					    <div class="col-sm-9">
					      	<form:input class="form-control" id="inputFirstName" path="firstName"/>
					    </div>
				    </div>
				    <div class="row">
					    <form:label path="lastName" for="inputLastName" class="col-sm-3 col-form-label">Nom</form:label>
					    <div class="col-sm-9">
					      	<form:input class="form-control" id="inputLastName" path="lastName"/>
					    </div>
				    </div>
				    <fieldset class="row">
					    <div class="row">
							<form:label path="gender" class="col-form-label col-sm-3">Je suis :</form:label>
							<div class="col-sm-9">
								<div class="form-check">
								    <form:radiobutton name="gridRadios" path="gender" value="MALE"/> Un homme
							  	</div>
							  <div class="form-check">
								    <form:radiobutton name="gridRadios" path="gender" value="FEMALE"/> Une femme 
							  </div>
							  <div class="form-check">
	                   				<form:radiobutton name="gridRadios" path="gender" value="OTHER"/> Autre  
							  </div>
							</div>
					    </div>
					</fieldset>
					<div class="row">
					    <form:label path="affinities" class="col-sm-3">Sélectionnez vos préférences</form:label>
					    <div class="col-sm-9">
				    		<c:forEach var="genre" items="${genres}">
				    			<div class="form-check">
				    				<form:checkbox path="affinities" value="${genre.name}"/> ${genre.name}
				    			</div>
				    		</c:forEach>
					    </div>
				  	</div>
					<div class="row">
						<div class="col-sm-3">
							<button type="submit" class="btn btn-lg btn-primary btn-block">Modifier</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
		
		<fragments:footer />
		
 		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	</body>
</html>