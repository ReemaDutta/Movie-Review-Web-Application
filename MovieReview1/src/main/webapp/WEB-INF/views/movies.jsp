<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "user.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies</title>
<style>
<%@include file="/WEB-INF/Styling/style.css"%>
</style>

</head>
<body>
<div class = "page1" >
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />
			
			<div class = "welcome">
			<c:if test = "${sessionScope.user ne null}">
			Welcome ${sessionScope.username}
			<hr width= 95% align = "left" size = "3">
			</c:if>			
			</div>
		
			<!-- action = "${contextPath}/movie" method = "GET" -->
			<form action = "${contextPath}/movie" method = "GET">		
				
				<div class = "h1style">
				<h1>Upcoming Popular Movies</h1>
				<hr width= 93% height = 1%  align = "left" size = "3">
				</div>
				
				<!--<c:out value = "${movieArray.length()-1}" />-->
				<c:forEach begin = "0" end = "${upcomingMovieArray.length()-1}" var = "index">
					<a href = "${contextPath}/movie?id=${upcomingMovieArray.getJSONObject(index).get("id")}">
					<img src="http://image.tmdb.org/t/p/w185/${upcomingMovieArray.getJSONObject(index).get("poster_path")}">	
					</a>
				</c:forEach>
			
				<c:if test = "${movieSArray ne null}">				
				<div class = "h1style">
				<h1>Search Movie List</h1>
				<hr width= 93% height = 1% align = "left" size = "3">
				</div>
				<!--<c:out value = "${movieArray.length()-1}" />-->
				<c:forEach begin = "0" end = "${movieSArray.length()-1}" var = "index">
					<a href = "${contextPath}/movie?id=${movieSArray.getJSONObject(index).get("id")}">
					<img src="http://image.tmdb.org/t/p/w185/${movieSArray.getJSONObject(index).get("poster_path")}">	
					</a>
				</c:forEach>
				</c:if>
				
				<div class = "h1style">
				<h1>Currently Running Movies</h1>	
				<hr width= 93% height = 1% align = "left" size = "3">					
				</div>
				
				
				
				
				<!--<c:out value = "${movieArray.length()-1}" />-->
				<c:forEach begin = "0" end = "${movieArray.length()-1}" var = "index">
					<a href = "${contextPath}/movie?id=${movieArray.getJSONObject(index).get("id")}">
					<img src="http://image.tmdb.org/t/p/w185/${movieArray.getJSONObject(index).get("poster_path")}">	
					</a>
				</c:forEach>
				
				
				
				<div class = "h1style">
				<h1>Most Popular Movies</h1>
				<hr width= 93% height = 1% align = "left" size = "3">				
				</div>
				
				
				<!--<c:out value = "${movieArray.length()-1}" />-->
				<c:forEach begin = "0" end = "${popularMovieArray.length()-1}" var = "index">
					<a href = "${contextPath}/movie?id=${popularMovieArray.getJSONObject(index).get("id")}">
					<img src="http://image.tmdb.org/t/p/w185/${popularMovieArray.getJSONObject(index).get("poster_path")}">	
					</a>
				</c:forEach>
				
							
			
				
			
	
	</form>
	</div>
</body>
</html>