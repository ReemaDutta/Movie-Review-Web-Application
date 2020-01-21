<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}


input[type=text], input[type=password] {
    width: 20%;
    padding: 12px 22px;
    margin: 10px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;  
    padding-left: 10px;  
    margin-left: 10px;
}

input[type=password] {
  margin-left: 20px;
}

button {
    background-color:  rgba(0,0.5,0,0);
    color: white;
    
    text-decoration: underline;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 10%;
    font-size: 25px;
    padding-left: 0px;
    
}

button:hover {
    opacity: 0.8;
}

.cancelbtn {
    width: auto;
    padding: 10px 18px;
    background-color: #f44336;
}

.imgcontainer {
    text-align: center;
    margin: 24px 0 12px 0;
}


.container {
    padding: 16px;
    color:white;
    font-size: 17px;
}

a{
color:white;
font-size: 25px;
font-family: Arial;
margin-bottom: 10px;
}

span.psw {
    float: right;
    padding-top: 16px;
}

 body{
	background-image: url('https://images4.alphacoders.com/608/thumb-1920-60866.jpg');
	background-size: cover;
	position:absolute;
	left:0;
	right:0;
	min-width: 100%;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
    span.psw {
       display: block;
       float: none;
    }
    .cancelbtn {
       width: 100%;
    }
    

}
</style>
</head>
<body>

<h2>Login Form</h2>
	<c:set var = "contextPath" value = "${pageContext.request.contextPath}" />
	<a href="${contextPath}/user/register">Register a new User</a><br/>
	<form action="${contextPath}/user/login" method = "post">
  	
  	<div class="container user-reg-bg">
    <label for="uname"><b>User Name</b></label>
    <input type="text" placeholder="Enter Username" name="username" required></br>

    <label for="psw"><b>Password </b></label>
    <input type="password" placeholder="Enter Password" name="password" required = "required"><br />
        
    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember" required = "required"> Remember me
    </label>
  	</div><br/>


</form>
</body>
</html>
