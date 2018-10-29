<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fragments" tagdir="/WEB-INF/tags/fragments" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>GirlsFlix Register</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<link rel="stylesheet" href="css/style.css" />
	</head>
	<body>
	
		<fragments:header />
		
		<div class="main">
			<div class="container">
				<h2>Créez-vous un compte GirlsFlix</h2>
				<form:form method="POST" modelAttribute="user">
				<table>
				<tr>
				    <td><form:label path="login">Login</form:label></td>
				    <td><form:input path="login"/></td>
				</tr>
				<tr>
                    <td><form:label path="pseudo">Pseudo</form:label></td>
                    <td><form:input path="pseudo"/></td>
                </tr>
                <tr>
                    <td><form:label path="firstName">Prénom</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">Nom</form:label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
                <tr>
                    <td><form:label path="password">Mot de Passe </form:label></td>
                    <td><form:password path="password"/></td>
                </tr>
                <tr>
                    <td><form:label path="gender">Je suis : </form:label></td>
                    <td>
                    <%-- <c:forEach items="genderTypes" var="genderVal">
                    <form:radiobutton path="gender" value="${genderVal}"/> </c:forEach> --%>
                    <form:radiobutton path="gender" value="Gender.MALE"/> Un homme    
                   <form:radiobutton path="gender" value="Gender.FEMALE"/> Une femme    
                    <form:radiobutton path="gender" value="Gender.OTHER"/> Autre  
                    </td>
                </tr>
                 <tr>
                    <td><form:label path="affinities"> Séléctionner vos préférences :</form:label></td>
                   <!--  TODO : Finda way to display the checkboxes one per row -->
                    <td><form:checkboxes items="${serieTypesList}" path="affinities" /></td>
                 </tr>   
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
				</table>
				</form:form>
				
			</div>
		</div>
		
		<fragments:footer />
		
 		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		
	</body>
</html>