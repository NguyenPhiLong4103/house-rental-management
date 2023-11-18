<%-- 
    Document   : Registration
    Created on : Sep 21, 2023, 4:12:11 PM
    Author     : duchi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="Muhamad Nauval Azhar">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta name="description" content="This is a login page template based on Bootstrap 5">
	<title>Registration Page</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
        <%
        String mess = (String) request.getAttribute("mess");
        %>
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="text-center my-5">
					</div>
					<div class="card shadow-lg">
						<div class="card-body p-5">
							<h1 class="fs-4 card-title fw-bold mb-4">Register</h1>
							<form action="register" method="POST" class="needs-validation" novalidate="" autocomplete="off">
								<div class="mb-3">
									<label class="mb-2 text-muted" for="fname">First Name</label>
									<input id="fname" type="text" class="form-control" name="fname" value="" required autofocus>
									<div class="invalid-feedback">
										First Name is required	
									</div>
								</div>

                                                                <div class="mb-3">
									<label class="mb-2 text-muted" for="lname">Last Name</label>
									<input id="lname" type="text" class="form-control" name="lname" value="" required autofocus>
									<div class="invalid-feedback">
										Last Name is required	
									</div>
								</div>
                                                            
								<div class="mb-3">
									<label class="mb-2 text-muted" for="email">E-Mail Address</label>
									<input id="email" type="email" class="form-control" name="email" value="" required>
									<div class="invalid-feedback">
										Email is invalid
									</div>
								</div>

								<div class="mb-3">
									<label class="mb-2 text-muted" for="password">Password</label>
									<input id="password" type="password" class="form-control" name="password" required>
								    <div class="invalid-feedback">
								    	Password is required
							    	</div>
                                                                </div>
                                                                    
                                                                 <div class="mb-3">
									<label class="mb-2 text-muted" for="rpassword">Re-Password</label>
									<input id="rpassword" type="password" class="form-control" name="repassword" required>
								<div class="invalid-feedback">
								    	Re-Password is required
							    	</div>
                                                                </div>
                                                                <div class="mb-3">
                                                       
									<label class="mb-2 text-muted" >Choose role</label>
                                                                        <br>
                                                                        <input id="" type="radio" name="role" value="1" checked="checked"> Tenant 
                                                                        <input id="" type="radio"  name="role" value="2" required> Landlord
		
							    	</div>
                                                                        
								

								<p class="form-text text-muted mb-3">
									By registering you agree with our terms and condition.
								</p>

								<div class="align-items-center d-flex">
									<input name="submit" type="submit" class="btn btn-primary ms-auto" value="Register">
									
								</div>
							</form>
						</div>
						<div class="card-footer  border-0">
							<div class="text-center">
								Already have an account? <a href="login" class="text-dark">Login</a>
							</div>
						</div>
					</div>
					<div class="text-center mt-5 text-muted">
						
					</div>
				</div>
			</div>
		</div>
	</section>
<%
          if (mess != null) {
%>
<script>
    setTimeout(function() {
        alert("<%= mess %>");
    }, 100);
</script>
<%
}
        %>
	<script src="js/login.js"></script>
</body>
</html>