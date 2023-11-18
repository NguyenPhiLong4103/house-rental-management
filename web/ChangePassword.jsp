
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Account,entity.Landlord" %>
<%@page import="java.sql.ResultSet,java.util.Vector" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Prop Find</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/global.css" rel="stylesheet">
	<link href="css/submit.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
	<link href="https://fonts.googleapis.com/css?family=Alata&display=swap" rel="stylesheet">
	<script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </head>
  
<body>


<jsp:include page="header.jsp"/>
 <%
        String mess = (String) request.getAttribute("mess");
    %>
<section id="center" class="center_blog clearfix">
 <div class="container">
  <div class="row">
   <div class="center_blog_1 text-center clearfix">
    <div class="col-sm-12">
	 <h2 class="mgt">EDIT PROFILE</h2>
	</div>
   </div>
   <div class="submit_1 clearfix">
    <h4 class="mgt col_1">Change Password</h4>
	<form>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <div class="submit_1i1 clearfix">
                <h5>Old Password</h5>
                <input class="form-control" placeholder="Enter Your Old Password" type="password" name="oldpassword">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <div class="submit_1i1 clearfix">
                <h5>New Password</h5>
                <input class="form-control" placeholder="Enter Your New Password" type="password" name="newpassword">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <div class="submit_1i1 clearfix">
                <h5>Re-New Password</h5>
                <input class="form-control" placeholder="Enter Your Re-New Password" type="password" name="repassword">
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3 text-center">
    <div class=" submit_3 clearfix">
        <h5 class="mgt"><input style="border:none" type="submit" class="button mgt" name="submit" value="Change"></h5>
    </div>
        </div><!-- comment -->
    </div>
</form>


  </div>
   </div>


</section>

<jsp:include page="footer.jsp"/>
<script>
$(document).ready(function(){

/*****Fixed Menu******/
var secondaryNav = $('.cd-secondary-nav'),
   secondaryNavTopPosition = secondaryNav.offset().top;
	$(window).on('scroll', function(){
		if($(window).scrollTop() > secondaryNavTopPosition ) {
			secondaryNav.addClass('is-fixed');	
		} else {
			secondaryNav.removeClass('is-fixed');
		}
	});	
	
});
</script>
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
