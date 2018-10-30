<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>GirlsFlix Series Page</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/series.css" />
	</head>
	<body>
	
		<fragments:header />
		
		<div class="main">
			<div class="container">
				<h2>Toutes les séries</h2>
				<form>
					<div class="form-row">
						<div class="col-md-9">
					      	<div class="input-group mb-2">
					        	<input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Rechercher">
					        	<div class="input-group-append input-btn">
					          		<div class="input-group-text input-btn-txt"><i class="fas fa-search"></i></div>
					        	</div>
					      	</div>
					    </div>
					    <div class="col-md-3">
					    	<label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
					      	<select class="custom-select mr-sm-2" id="selectGenre">
						        <option selected value="">Genres</option>
					      		<c:forEach var="item" items="${genres}">
					      			<option value="${item.name}">${fn:escapeXml(item.name)}</option>
					      		</c:forEach>
					      	</select>
					    </div>
					</div>
				</form>
				<div>
					<ul class="list-group">
						<c:forEach var="item" items="${series}">
							<a href="serie/${item.getId()}">
								<li class="list-group-item">
									<img class="list-img" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(item.getImage())}" alt="${fn:escapeXml(item.getTitle())}"/>
									${fn:escapeXml(item.getTitle())}
								</li>
							</a>
						</c:forEach>
					</ul>
				</div>
				<nav id="pagination">
					<ul class="pagination justify-content-center">
				    	<li class="page-item disabled">
				      		<a class="page-link" href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						        <span class="sr-only">Previous</span>
						    </a>
				    	</li>
					    <li class="page-item"><a class="page-link" href="#">1</a></li>
					    <li class="page-item"><a class="page-link" href="#">2</a></li>
					    <li class="page-item"><a class="page-link" href="#">3</a></li>
					    <li class="page-item">
					    	<a class="page-link" href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						        <span class="sr-only">Next</span>
						    </a>
					    </li>
				  </ul>
				</nav>
			</div>
		</div>
		
		<fragments:footer />
		
 		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	</body>
</html>