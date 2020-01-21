<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">	
<style>
<%@include file="/WEB-INF/Styling/style.css"%>
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>	
  	<p id="error"></p>
  	<ul id="search_results"></ul>
  	
	<c:set var = "contextPath" value = "${pageContext.request.contextPath}" /> 
	<form action = "${contextPath}/user">
	
	<div class = "topright"  >	
	
	<!--<c:out value = "${pageContext.request.requestURI}" />-->
			<c:if test = "${sessionScope.user eq null }" >		
			<input type = "submit" class = "button" value = "Login" name = "type" />	
			<input type = "submit" class = "button" value = "Register" name = "type"  />
			</c:if>	

			<c:if test = "${sessionScope.user ne null}" >    
			<input type = "submit" class = "button" value = "User Profile" name = "type" />
			<input type = "submit" class = "button" value = "MyMovies" name = "type" />
			<input type = "submit" class = "button" value = "Logout" name = "type" />
			</c:if>
	
			<c:if test = "${sessionScope.user ne null && sessionScope.userid eq 'admin'}" >	
			<input type = "submit" class = "button" value = "admin" name = "type" />
			</c:if>	
	
			<c:if test = "${sessionScope.user ne null &&  not fn:endsWith(pageContext.request.requestURI, '/moviereview1/WEB-INF/views/movies.jsp')}">
		    <input type = "button" class = "button" value = "Back" onclick="history.back()"/>
		    </c:if>
    
		    <c:if test = "${sessionScope.user ne null &&  not fn:endsWith(pageContext.request.requestURI, '/moviereview1/WEB-INF/views/movies.jsp')}">
		    <input type = "submit" class = "button" value = "Home" name = "type" />
		    </c:if>
       </div>
		    <c:if test = "${sessionScope.user ne null && fn:endsWith(pageContext.request.requestURI, '/moviereview1/WEB-INF/views/movies.jsp')}" >
			<div class="row">
			<div class="col-xs-3">
			<div class="input-group">
			 <input type="text" class="form-control" style="font-size:15px; margin-left:500px;margin-top:0px;position:static; padding-bottom:0;
			 		width:400px;height:40px" placeholder="Search For the movie of your choice" name="query" id = "query"  />
		        <div class="input-group-btn">
		          <button class="btn btn-primary" style="font-size:12px;margin-right:500px;position:static;margin-top:0px;" id = "submit" type="submit" value="search" name = "type">
		            <span style="font-size:27px;padding-right:10px;padding-top:20px;position:static;margin-top:-18px;" class="glyphicon glyphicon-search"></span>
		          </button>
				</div>
		     </div>	
			</div>
			</div>
		   </c:if>
			
		    <c:if test = "${sessionScope.user eq null && fn:endsWith(pageContext.request.requestURI, '/moviereview1/WEB-INF/views/movies.jsp')}" >
			<div class="row">
			<div class="col-xs-3">
			<div class="input-group">
			 <input type="text" class="form-control" style="font-size:15px; margin-left:550px; position:static;margin-top:0px; padding-bottom:0;
			 			width:400px;height:40px" placeholder="Search For the movie of your choice" name="query" id = "query"  />
		        <div class="input-group-btn">
		          <button class="btn btn-primary" style="font-size:12px;margin-right:550px;margin-top:0px;position:static;padding-bottom:0px;" type="submit"  id = "submit" value="search" name = "type">
		            <span style="font-size:29px;padding-right:10px;padding-top:20px;margin-top:-20px;position:static;" class="glyphicon glyphicon-search"></span>
		          </button>
				</div>
		     </div>	
			</div>
		   </c:if>
    		
	

</form>	
</body>
</html>