<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file = "user.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movies Liked By User</title>
<style>
<%@include file="/WEB-INF/Styling/style.css"%>
</style>
</head>
<body>
<div class = "welcome1">
<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />
<!-- <a href = "${contextPath}/movie?id=${ja.getJSONObject(index).get("id")}"> -->
Your Movie Collection - <b>${sessionScope.username}</b>
</div>

<c:if test = "${ja.length() > 0}" >
<c:forEach begin = "0" end = "${ja.length()-1}" var = "index">	
	<a href = "${contextPath}/movie?id=${ja.getJSONObject(index).get("id")}">
	<img class = "img1" src="http://image.tmdb.org/t/p/w185/${ja.getJSONObject(index).get("poster_path")}">	
	</a>
</c:forEach>
</c:if>

<div class = "message" >
${requestScope.message}
</div>

</body>
</html>