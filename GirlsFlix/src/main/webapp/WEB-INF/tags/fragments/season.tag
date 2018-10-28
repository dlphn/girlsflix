<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%-- <%@ attribute name="season" required="true" rtexprvalue="true" type="com.gfx.domain.series.Season"%> --%>
<%@ attribute name="nb" required="true" rtexprvalue="true" type="String"%>

<div class="row">
	<div class="col-md-2">
		<p>Image</p>
	</div>
	<div class="col-md-10">
		<h5>Saison ${nb}</h5>
		<p>X épisodes <span class="badge badge-success">EN COURS</span>/<span class="badge badge-danger">TERMINEE</span></p>
		<p>Synopsis</p>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<ul class="list-group">
	  		<li class="list-group-item">
	  			<div class="row">
	  				<div class="col-sm-2">
	  					image
	  				</div>
	  				<div class="col-sm-10">
	  					<h6>S0${nb}E01 - Titre <span class="small-info diffusion-date">2018-01-01</span></h6>
	  					<p>Synopsis</p>
	  					<p>Note</p>
	  				</div>
	  			</div>
	  		</li>
	  		<li class="list-group-item">
	  			<div class="row">
	  				<div class="col-sm-2">
	  					image
	  				</div>
	  				<div class="col-sm-10">
	  					<h6>S0${nb}E02 - Titre <span class="small-info diffusion-date">2018-01-01</span></h6>
	  					<p>Synopsis</p>
	  					<p>Note</p>
	  				</div>
	  			</div>
	  		</li>
	  		<li class="list-group-item">
	  			<div class="row">
	  				<div class="col-sm-2">
	  					image
	  				</div>
	  				<div class="col-sm-10">
	  					<h6>S0${nb}E03 - Titre <span class="small-info diffusion-date">2018-01-01</span></h6>
	  					<p>Synopsis</p>
	  					<p>Note</p>
	  				</div>
	  			</div>
	  		</li>
		</ul>
	</div>
</div>