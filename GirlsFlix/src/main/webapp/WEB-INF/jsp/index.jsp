<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>GirlsFlix Index Page</title>
	</head>
	<body>
		<fragments:header />
		<center>
			<h2>Welcome to GirlsFlix</h2>
			<ul>
				<li>Série 1</li>
				<li>Série 2</li>
				<li>Série 3</li>
			</ul>
			<ul>
				<c:forEach var="item" items="${columns}">
	               	<li>
	                   <a href="/serie/${item.id}">
						<h3>${fn:escapeXml(item.title)}</h3>
						<p>${fn:escapeXml(item.intro)}</p>
	                   </a>
	               </li>
           		</c:forEach>
			</ul>
		</center>
		<fragments:footer />
	</body>
</html>