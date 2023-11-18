<%-- 
    Document   : ForgotPassword
    Created on : Sep 28, 2023, 5:26:18 PM
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
	<title>Bootstrap 5 Login Page</title>
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
							<h1 class="fs-4 card-title fw-bold mb-4">Forgot Password</h1>
							<form action="forgot" method="POST" class="needs-validation" novalidate="" autocomplete="off">
								<div class="mb-3">
									<label class="mb-2 text-muted" for="email">E-Mail Address</label>
									<input id="email" type="email" class="form-control" name="email" value="" required autofocus>
									<div class="invalid-feedback">
										Email is invalid
									</div>
								</div>

								<div class="d-flex align-items-center">
									<input type="submit" class="btn btn-primary ms-auto" name="submit" value="Send Link">
										
									
								</div>
							</form>
						</div>
						<div class="card-footer py-3 border-0">
							<div class="text-center">
								Remember your password? <a href="login" class="text-dark">Login</a>
							</div>
						</div>
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

