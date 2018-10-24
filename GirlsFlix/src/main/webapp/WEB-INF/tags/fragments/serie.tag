<%@ tag body-content="empty" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="serie" required="true" rtexprvalue="true" type="com.gfx.domain.series.Serie"%>

<div class="container">
	<p>image</p>
	<h1>${fn:escapeXml(serie.getTitle())}</h1>
	<p>${fn:escapeXml(serie.getSummary())}</p>
</div>