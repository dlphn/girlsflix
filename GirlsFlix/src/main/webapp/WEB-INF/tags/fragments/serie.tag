<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>

<%@ attribute name="serie" required="true" rtexprvalue="true" type="com.gfx.domain.series.Serie"%>

<div class="container">
	<div class="row">
		<div class="col-md-4">
			<img class="card-img-top" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(serie.getImage())}" alt="${fn:escapeXml(serie.getTitle())}"/>
		</div>
		<div class="col-md-8">
			<h3>${fn:escapeXml(serie.getTitle())} <span class="small-info creation-date">(${fn:escapeXml(serie.getCreationDate())})</span></h3>
			<c:forEach var="genre" items="${serie.getSerieGenre()}">
				<span class="badge badge-secondary">${fn:escapeXml(genre)}</span>
			</c:forEach>
			<p>${fn:escapeXml(serie.getSummary())}</p>
			<c:if test="${not empty loggedIn}">Ajouter aux favoris</c:if>
			<c:if test="${empty loggedIn}">Connectez-vous pour ajouter aux favoris</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h4>Saisons & Episodes</h4>
		</div>
		<div class="col-md-12">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
		  		<li class="nav-item">
		    		<a class="nav-link active" id="season1-tab" data-toggle="tab" href="#season1" role="tab" aria-controls="season1" aria-selected="true">Saison 1</a>
		  		</li>
			  	<li class="nav-item">
			   		<a class="nav-link" id="season2-tab" data-toggle="tab" href="#season2" role="tab" aria-controls="season2" aria-selected="false">Saison 2</a>
			  	</li>
		  		<li class="nav-item">
		    		<a class="nav-link" id="season3-tab" data-toggle="tab" href="#season3" role="tab" aria-controls="season3" aria-selected="false">Saison 3</a>
		  		</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="season1" role="tabpanel" aria-labelledby="season1-tab">
					<fragments:season nb="1"/>
				</div>
				<div class="tab-pane fade" id="season2" role="tabpanel" aria-labelledby="season2-tab">
					<fragments:season nb="2"/>
				</div>
				<div class="tab-pane fade" id="season3" role="tabpanel" aria-labelledby="season3-tab">
					<fragments:season nb="3"/>
				</div>
			</div>
			</div>
	</div>
</div>