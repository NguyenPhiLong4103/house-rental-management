<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="Muhamad Nauval Azhar">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta name="description" content="This is a login page template based on Bootstrap 5">
	<title>Bootstrap 5 Login Page</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
</head>

<body>
    <%
        String mess = (String) request.getAttribute("mess");
        Cookie[] cookies = request.getCookies();
                                    String cuser =null, cpass=null,crem=null;
                                    if (cookies != null) {
                                        for (Cookie cookie : cookies) {
                                            if (cookie.getName().equals("cuser")) {
                                                cuser = cookie.getValue();
                                            }
                                            if (cookie.getName().equals("cpass")) {
                                                cpass = cookie.getValue();
                                            }
                                            if (cookie.getName().equals("crem")) {
                                                crem = cookie.getValue();
                                            }
                                        }
                                    }
    %>
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-sm-center h-100">
				<div class="col-xxl-4 col-xl-5 col-lg-5 col-md-7 col-sm-9">
					<div class="text-center my-5">
					</div>
					<div class="card shadow-lg">
						<div class="card-body p-5">
							<h1 class="fs-4 card-title fw-bold mb-4">Login</h1>
                                                        <form action="login" method="POST" class="needs-validation" novalidate="" autocomplete="off">
								<div class="mb-3">
									<label class="mb-2 text-muted" for="email">E-Mail Address</label>
									<input id="email" type="email" class="form-control" name="email" <%
                                                   if(cuser!=null){
                                                   %>
                                                   value="<%=cuser%>"
                                                   <%
                                                   }
                                               %> required autofocus>
									<div class="invalid-feedback">
										Email is invalid
									</div>
								</div>

								<div class="mb-3">
									<div class="mb-2 w-100">
										<label class="text-muted" for="password">Password</label>
										<a href="forgot" class="float-end">
											Forgot Password?
										</a>
									</div>
									<input id="password" type="password" class="form-control" name="password" <%
                                                   if(cpass!=null){
                                                   %>
                                                   value="<%=cpass%>"
                                                   <%
                                                   }
                                               %> required>
								    <div class="invalid-feedback">
								    	Password is required
							    	</div>
								</div>

								<div class="d-flex align-items-center">
									<div class="form-check">
										<input type="checkbox" <%
                                                   if(crem!=null){
                                                   %>
                                                   checked
                                                   <%
                                                   }
                                               %> name="remember" id="remember" class="form-check-input" value="ON">
										<label for="remember" class="form-check-label">Remember Me</label>
									</div>
									<input name="submit" type="submit" class="btn btn-primary ms-auto" value="Submit">
										
									
								</div>
							</form>
						</div>
						<div class="card-footer py-3 border-0">
							<div class="text-center">
								Don't have an account? <a href="register" class="text-dark">Create One</a>
							</div>
						</div>
					</div>
					<div class="text-center mt-5 text-muted">
					
					</div>
				</div>
			</div>
		</div>
	</section>

	<script src="js/login.js"></script>
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
</body>
</html>