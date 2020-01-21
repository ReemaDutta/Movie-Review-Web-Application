<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<style>
			<%@include file="/WEB-INF/Styling/RegisterStyle.css"%>
		</style>

		<!-- Website Font style -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
		
		<title>Registration for New User</title>
	</head>
	<body>
		
		
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		${user.username}
		${requestScope.user.username}
		<div class="container user-reg-mar user-reg-bg1">
			<div class="row main">
				<div class="panel-heading">
	               <div class="panel-title text-center">
	               		<h1 class="title">Registration for New User</h1>
	               		<hr />
	               	</div>
	            </div> 
				<div class="main-login main-center">
					<form:form action="${contextPath}/user/register" commandName="user" 
					   class="form-horizontal" method="post">
						
						<div class="form-group">
							<label for="firstname" class="cols-sm-2 control-label font-col">First Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<form:input path="firstName" class="form-control" placeholder="Enter your First Name"/>
									<font color="red"><form:errors path="firstName" /></font></td>
								</div>
							</div>
						</div>
						
							<div class="form-group">
							<label for="lastname" class="cols-sm-2 control-label font-col">Last Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									<form:input path="lastName" class="form-control" placeholder="Enter your Last Name"/>
									<font color="red"><form:errors path="lastName" /></font></td>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label font-col">Your Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<form:input path="email" class="form-control" name="email" id="emailAddress"  placeholder="Enter your Email"/>
									<font color="red"><form:errors path="email" /></font></td>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="username" class="cols-sm-2 control-label font-col">Username</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
									<form:input path="username" class="form-control" name="username" id="username"  placeholder="Enter your Username" />
									<font color="red"><form:errors path="username"  /></font></td>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label font-col">Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<form:password path="password" class="form-control" name="password" id="password"  placeholder="Enter your Password"/>
									<font color="red"><form:errors path="password" /></font></td>
								</div>
							</div>
						</div>
						
						<div class="form-group ">
							<input type="submit" value = "Register" class="btn btn-primary btn-lg btn-block login-button"></button>
						</div>
						<div class="form-group ">
							<input type="submit"  value = "Login" class="btn btn-primary btn-lg btn-block login-button"></button>
						</div>

					
					</form:form>
				</div>
			</div>
		</div>

		<script type="text/javascript" src="assets/js/bootstrap.js"></script>
	</body>
</html>