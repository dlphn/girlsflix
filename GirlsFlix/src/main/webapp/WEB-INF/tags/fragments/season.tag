<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="season" required="true" rtexprvalue="true" type="com.gfx.domain.series.Season"%>

<div class="row container">
	<div class="col-md-2">
		<c:if test="${not empty season.getImage()}">
			<img class="card-img-top" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(season.getImage())}" alt="${fn:escapeXml(season.getSeasonName())}"/>
		</c:if>
	</div>
	<div class="col-md-10">
		<h5>${season.getSeasonName()}</h5>
		<p>${season.getEpisodeCount()} épisodes</p>
		<p>${season.getSummary()}</p>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<ul class="list-group">
			<c:forEach var="episode" items="${season.getEpisodes()}">
				<li class="list-group-item">
		  			<div class="row">
		  				<div class="col-sm-2">
							<c:if test="${not empty episode.getImage()}">
		  						<img class="card-img-top" src="https://image.tmdb.org/t/p/w500/${fn:escapeXml(episode.getImage())}" alt="${fn:escapeXml(episode.getName())}"/>
							</c:if>
		  				</div>
		  				<div class="col-sm-10">
		  					<h6>S${episode.getSeasonNb()}E${episode.getEpisodeNb()} - ${episode.getName()} <span class="small-info diffusion-date">${episode.getReleaseDate()}</span></h6>
		  					<p>${episode.getSummary()}</p>
		  					<p>Note : ${episode.getRating()}</p>
		  				</div>
		  			</div>
		  		</li>
			</c:forEach>
		</ul>
	</div>
</div>