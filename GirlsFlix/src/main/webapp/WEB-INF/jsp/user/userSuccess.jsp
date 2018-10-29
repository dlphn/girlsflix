<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Récapitulatif d'inscription</title>
</head>
<body>

Hello

<dl>
<dt>Login: ${login} ${notUsed}</dt>
<dt>Pseudo : ${pseudo}  </dt>
<dt>Prénom : ${firstName}</dt>
<dt>Nom: ${lastName} </dt>

<dt>Vos Préférences :</dt>
<c:forEach items="${affinities}" var="affinity">
 <li>${affinity}</li>
 </c:forEach>
</dl>
</body>
</html>

