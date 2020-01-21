<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
<%@ include file = "/WEB-INF/Styling/AdminStyle.css" %>
</style>
</head>
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />
<form action = "${contextPath}/movie-admin" method ="post">   
<body class = "user-reg-bg">
	<h1 style="color:white; margin-left:600px; margin-top:50px;">
		List of Active Users
	</h1>
	<ul class = "list-group" style="margin-top:75px;">
	
	<li class="list-group-item header-font" style="background-color:black;">
		<div class = "row" style="margin-left:75px;">
		<div class = "column login-register">
			<b><i>UserName</i></b>
		</div>
			<div class = "column login-register" >
			<b><i>FirstName</i></b>
		</div>
		<div class = "column login-register">
				<b><i>LastName</i></b>
		</div>
		
		<div class = "column login-register">
			
		</div>
		</div>
		</li>		
	
	
		<c:forEach items = "${requestScope.userlist}" var = "item" >

		<li class="list-group-item" style="background-color:black;">
		<div class = "row" style="margin-left:75px;">
		<div class = "column login-register">
			<input type = "text" class="user-font" value = "${item.username}" readonly>
		</div>
			<div class = "column login-register" >
			<input type = "text" class="user-font"  value = "${item.firstName}" readonly>
		</div>
		<div class = "column login-register">
				<input type = "text" class="user-font"  value = "${item.lastName}" readonly>
		</div>
		
		<div class = "column login-register">
			<a href = "./user/delete.htm?username=${item.username}">
				<span class="glyphicon glyphicon-remove">
				</span>
			</a>
		</div>
		</div>
		</li>			
		
	</c:forEach>
	</ul>
	<div class = "button">
	<input type ="submit" value ="Excel" class = "button1" name= "action"  />	
	<input type ="submit" value ="PDF" class = "button1" name = "action"  />   	
	</div>	
	<form class = "button">
 	 <input class = "button1 " type="button" value="Go back!" onclick="history.back()">
	</form>
	</form>
	
	
</body>
</html>