<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>GirlsFlix Series Page</title>
	</head>
	<body>
		<fragments:header />
		<center>
			<h2>Toutes les séries</h2>
			<ul>
				<c:forEach var="item" items="${series}">
	               	<li>
	                   <a href="GirlsFlix/serie/${item.getId()}">
						<h3>${fn:escapeXml(item.getTitle())}</h3>
						<p>${fn:escapeXml(item.getPicture())}</p>
	                   </a>
	               </li>
           		</c:forEach>
			</ul>
		</center>
		<fragments:footer />
	</body>
</html>