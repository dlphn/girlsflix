<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>GirlsFlix</title>
		
		<link rel="icon" type="image/png" href="/GirlsFlix/lib/favicon.png">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">
		<link rel="stylesheet" href="/GirlsFlix/css/style.css" />
	</head>
	<body>
		<fragments:header />
		<div class="main">
			<div class="container">
				<h1>Bienvenue sur <img src="lib/GirlsFlix2.png" alt="GirlsFlix" class="big-logo"/> !</h1>
				<sec:authorize access="isAuthenticated()">
					<h2>Recommandé pour vous</h2>
					<div class="card-columns">
						<c:forEach var="item" items="${recommendations}">
			               	<div class="card">
								<a href="serie/${item.getId()}">
									<img class="card-img-top" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(item.getImage())}" alt="${fn:escapeXml(item.getTitle())}"/>
									<div class="card-body">
										<h5>${fn:escapeXml(item.getTitle())}</h5>
										<p><c:if test="${item.isSoon() == true}">
                                         <span class="badge badge-success">Nouvel épisode bientôt</span>
                                    </c:if></p>
										<c:forEach var="genre" items="${item.getSerieGenres()}">
											<span class="badge badge-secondary">${fn:escapeXml(genre)}</span>
										</c:forEach>
										
									</div>
								</a>
			               </div>
		           		</c:forEach>
		           	</div>
		           	<div>
		           		<p>${recommendationsMsg}</p><br/>
		           	</div>
		           	<h2>Séries populaires</h2>
				</sec:authorize>
				<div class="card-columns">
					<c:forEach var="item" items="${columns}">
		               	<div class="card">
							<a href="serie/${item.getId()}">
								<img class="card-img-top" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(item.getImage())}" alt="${fn:escapeXml(item.getTitle())}"/>
								<div class="card-body">
									<h5>${fn:escapeXml(item.getTitle())}</h5>
									<p><c:if test="${item.isSoon() == true}">
                                         <span class="badge badge-success" style="float:right;">Nouvel épisode bientôt</span>
                                    </c:if></p>
									<c:forEach var="genre" items="${item.getSerieGenres()}">
										<span class="badge badge-secondary">${fn:escapeXml(genre)}</span>
									</c:forEach>
								</div>
							</a>
		               </div>
	           		</c:forEach>
	           	</div>
			</div>
		</div>
		
		<fragments:footer />
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	</body>
</html>