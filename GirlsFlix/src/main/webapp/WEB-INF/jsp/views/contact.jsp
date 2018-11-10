<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>GirlsFlix - A propos</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/about.css" />
	</head>
	<body>
	
		<fragments:header />
		
		<div class="main" id="about">
			<div class="container">
				<h1>A propos</h1>
				
				<div class="row">
					<div class="col-xs-12 col-md-3 vcenter">
						<img src="lib/GirlsFlix2.png" class="left-img img-responsive" alt="GirlsFlix">
					</div>
					<div class="col-xs-12 col-md-9 vcenter">
				        <div class="about-text-right">
					        <h2>GirlsFlix, kézako ?</h2>
					        <hr>
					        <p>GirlsFlix vous permet d'ajouter vos séries préférées en favoris et de ne jamais rater un nouvel épisode !</p>
				        </div>
				    </div>
				</div>
				
				<div class="row">
					<div class="col-md-12 vcenter">
				        <div class="about-text-center">
					        <h2>Qui est derrière tout ça ?</h2>
					        <hr>
					        <ul>
								<li>Jihane Bennis - <a href="mailto:jihane.bennis@student.ecp.fr">jihane.bennis@student.ecp.fr</a></li>
								<li>Sophie Dambricourt - <a href="mailto:sophie.dambricourt@student.ecp.fr">sophie.dambricourt@student.ecp.fr</a></li>
								<li>Delphine Shi - <a href="mailto:delphine.shi@student.ecp.fr">delphine.shi@student.ecp.fr</a></li>
							</ul>
							<p>Nous sommes 3 étudiantes de l'Ecole CentraleSupélec et avons développé ce projet dans le cadre du cours de Programmation Orientée Objet Avancée.</p>
							
				        </div>
				    </div>
				</div>
				
				<div class="row">
					<div class="col-xs-12 col-md-3 vcenter">
						<img src="https://www.themoviedb.org/assets/1/v4/logos/312x276-primary-blue-fb50dee3bf664c866fd216e6cee64af33d20707ea3091ddc65c5e8aa4c152eb2.png" class="left-img img-responsive" alt="GirlsFlix">
					</div>
					<div class="col-xs-12 col-md-9 vcenter">
				        <div class="about-text-right">
					        <h2>API</h2>
					        <hr>
							<p>Nous utilisons l'API <a href="https://developers.themoviedb.org/3/getting-started/introduction">The Movie Database API</a></p>
				        </div>
				    </div>
				</div>
			</div>
		</div>
		
		<fragments:footer />
		
 		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	</body>
</html>
