<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ attribute name="serie" required="true" rtexprvalue="true" type="com.gfx.domain.series.Serie"%>

<div class="container">
	<div class="row">
		<div class="col-md-4">
			<img class="card-img-top" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(serie.getImage())}" alt="${fn:escapeXml(serie.getTitle())}"/>
		</div>
		<div class="col-md-8">
			<h3>${fn:escapeXml(serie.getTitle())} <span class="small-info creation-date">(${fn:escapeXml(serie.getCreationDate())})</span></h3>
			<c:forEach var="genre" items="${serie.getSerieGenres()}">
				<span class="badge badge-secondary">${fn:escapeXml(genre)}</span>
			</c:forEach>
			<p>${fn:escapeXml(serie.getSummary())}</p>
			<sec:authorize access="isAuthenticated()">
			<p>${message}</p>
			<c:if test="${isFavorite == false}">
				<button type="button" class="btn btn-danger" onclick="location.href='/GirlsFlix/serie/${serie.getId()}/addFav'" value="submit">
					<i class="fas fa-heart"></i> Ajouter aux favoris
				</button> </c:if>
			<c:if test="${isFavorite == true}">	
				<button type="submit" class="btn btn-secondary" name = "deleteFav" value="deleteFav">
					<i class="fas fa-trash-alt"></i> Supprimer des favoris
				</button>
			</c:if>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<span>Connectez-vous pour ajouter aux favoris</span>
			</sec:authorize>
		</div>
	</div><br/>
	<div class="row">
		<div class="col-md-12">
			<h4>Saisons & Episodes</h4>
		</div>
		<div class="col-md-12">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<c:forEach var="season" items="${serie.getSeasons()}">
					<li class="nav-item">
			    		<a class="nav-link ${season.getSeasonNb() == 1 ? "active" : ""}" 
			    		id="season${season.getSeasonNb()}-tab" 
			    		data-toggle="tab" href="#season${season.getSeasonNb()}" 
			    		role="tab" 
			    		aria-controls="season${season.getSeasonNb()}" 
			    		aria-selected="true">${season.getSeasonName()}</a>
			  		</li>
				</c:forEach>
			</ul>
			<div class="tab-content" id="myTabContent">
				<c:forEach var="season" items="${serie.getSeasons()}">
					<div class="tab-pane fade ${season.getSeasonNb() == 1 ? "show active" : ""}" id="season${season.getSeasonNb()}" role="tabpanel" aria-labelledby="season${season.getSeasonNb()}-tab">
						<fragments:season season="${season}"/>
					</div>
				</c:forEach>
			</div>
			</div>
	</div>
</div>