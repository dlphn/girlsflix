<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            
            <title>Récapitulatif d'inscription</title>
            
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style.css" />
</head>

<body>
    <fragments:header />
    <div class="main">
       
        <div class="container">
<h3>Récapitulatif d'inscription</h3>
<div class="row" align="center">
    <li> Login: ${login} </li>
</div>
<div class="row" align="center">
    <li> Pseudo : ${pseudo} </li> </div>
<div class="row" align="center">
    <li> Prénom : ${firstName} </li> </div>
<div class="row" align="center">
    <li> Nom: ${lastName} </li></div>
<div class="row" align="center">
    <li> Vos Préférences : </li></div>
    <c:if test="${not empty affinities}">
        <c:forEach items="${affinities}" var="affinity">
            <div class="row" align="center">
                 <li>${affinity}</li></div>
         </c:forEach>
    </c:if>
    <c:if test="${empty affinities}">
        <p> Pas de préférences pour le moment. </p>
    </c:if>
</div>
<c:url value="/login" var="loginUrl"/>
                <form action="${loginUrl}" method="post" class="form-signin">
                <div class="form-group row">
                       
                        <input type="hidden" class="form-control" id="username" name="username" placeholder="Login" value="${login}"
                            required autofocus > 
                       
                        <input type="hidden" class="form-control" id="password" name="password" value= "${password}" placeholder="Mot de passe" required>
                    </div>
                    <input type="hidden"
                        name="${_csrf.parameterName}"
                        value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">
                    <i class="fas fa-user"></i>Connexion</button>
                    
                </form>
</div>

<fragments:footer />
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        
    </body>
</html>