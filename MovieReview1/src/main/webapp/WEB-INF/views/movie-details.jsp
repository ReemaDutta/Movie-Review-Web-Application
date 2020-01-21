<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.my.moviereview1.MovieDetailsController" %>
<%@include file = "user.jsp" %>
<!-- Latest compiled and minified JavaScript -->


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

<style>
<%@ include file = "/WEB-INF/Styling/style.css" %>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movie Reviews</title>
</head>
<body>

<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />
<!-- <div class = "welcome2">
<c:if test= "${sessionScope.user ne null}">
Welcome ${sessionScope.username}
</c:if>
</div> -->

<div class = "bg-color-half-black">
<h1 class = "welcome2">Movie Reviews</h1>
<div class = "titlestyle">
${movie.get("title")}  
</div>
<div class = "tagstyle">
${movie.get("tagline")}
</div>
<div class = "imagestyle">
<img src = "http://image.tmdb.org/t/p/w185/${movie.get("poster_path")}" >
</div>
<h3 class = "overviewstyle">
${movie.get("overview")}
</h3>
<br/>
<br/>
</div>
<div class = "releasestyle">
Release Date
${movie.get("release_date")}
</div>
<div class = "ratingstyle">
Rating:
${movie.get("vote_average")}/10
</div>
<div class = "popstyle">
Popularity
${movie.get("popularity")}
</div>


<c:if test= "${sessionScope.user eq null}">
<div class = "link">
<a href = "${contextPath}/user/login">Please login to like Movies and to add it in your Collection</a>
</div>
</c:if>

<c:if test = "${sessionScope.user ne null}">
<c:if test = "${sessionScope.flag}">
<div class = "heartstyle">
<a href = "${contextPath}/movie-details?id=${movie.get("id")}">
<span class = "glyphicon glyphicon-heart color-pink"></span>
</a>
</div>
</c:if>

<c:if test = "${sessionScope.flag eq false}">
<div class = "heartstyle">
<a href = "${contextPath}/movie-details?id=${movie.get("id")}">
<span class = "glyphicon glyphicon-heart-empty"></span>
</a>
</div>
</c:if>
</c:if>
</body>
</html>