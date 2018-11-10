<%@ tag body-content="empty" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top border-bottom shadow-sm" id="menu">
	<a class="navbar-brand" href="/GirlsFlix"><img src="/GirlsFlix/lib/GirlsFlix.png" alt="GirlsFlix" height="50px"/></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarText">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<a class="nav-link" href="/GirlsFlix/series">Toutes les séries</a>
			</li>
			<li class="nav-item">
			  	<a class="nav-link" href="/GirlsFlix/serie-surprise">Série surprise</a>
			</li>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item">
					<a class="nav-link" href="/GirlsFlix/favoris">Mes favoris</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/GirlsFlix/notifications">Notifications</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/GirlsFlix/profil">Mon profil</a>
				</li>
			</sec:authorize>
		</ul>
		<span class="navbar-text">
			<sec:authorize access="!isAuthenticated()">
				<a class="nav-link" href="/GirlsFlix/login">Connexion</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a class="nav-link" href="/GirlsFlix/logout">Déconnexion</a>
			</sec:authorize>
		</span>
	</div>
</nav>