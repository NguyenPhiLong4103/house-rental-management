
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
        <%
            Account acc = (Account) session.getAttribute("user");
            Landlord landlord =(Landlord) request.getAttribute("user");
            String mess = (String) request.getAttribute("mess");
        %>

        <jsp:include page="header.jsp"/>

        <section id="center" class="center_blog clearfix">
            <div class="container">
                <div class="row">
                    <div class="center_blog_1 text-center clearfix">
                        <div class="col-sm-12">
                            <h2 class="mgt">PERSONAL PROFILE</h2>
                        </div>
                    </div>
                    <div class="submit_1 clearfix">
                        <h4 class="mgt col_1">Personal Information</h4>
                        <hr>
                        <form action="profile">
                            <div class="submit_1i clearfix">
                                <div class="col-sm-6 space_left">
                                    <div class="submit_1i1 clearfix">
                                        <h5 class="mgt">First Name</h5>
                                        <input class="form-control" value="<%=landlord.getFirstName()%>" type="text" name="fname">
                                    </div>
                                </div>
                                <div class="col-sm-6 space_right">
                                    <div class="submit_1i1 clearfix">
                                        <h5 class="mgt">Last Name</h5>
                                        <input class="form-control" value="<%=landlord.getLastName()%>" type="text" name="lname">
                                    </div>
                                </div>
                            </div>
                            <div class="submit_1i clearfix">
                                <div class="col-sm-6 space_left">
                                    <div class="submit_1i1 clearfix">
                                        <h5>Address</h5>
                                        <input class="form-control" placeholder="Enter Your Address" <%if(landlord.getAddress()!=null){%>
                                               value="<%=landlord.getAddress()%>"
                                               <%}%> type="text" name="address">
                                    </div>
                                </div>
                                <div class="col-sm-6 space_right">
                                    <div class="submit_1i1 clearfix">
                                        <h5>Phone</h5>
                                        <input class="form-control" placeholder="Enter Your Phone" <%if(landlord.getPhone()!=null){%>
                                               value="<%=landlord.getPhone()%>"
                                               <%}%> type="text" name="phone">
                                    </div>
                                </div>    
                            </div>
                            <div class="submit_1i clearfix">
                                <div class="col-sm-6 space_right">
                                    <div class="submit_1i1 clearfix">
                                        <h5>Civil ID</h5>
                                        <input class="form-control" placeholder="Enter Your Civil ID" <%if(landlord.getCivil_id()!=null){%>
                                               readonly=""
                                               value="<%=landlord.getCivil_id() %>"

                                               <%}%> type="text" name="civil_id">
                                    </div>
                                </div>
                                <div class="col-sm-6 space_right">
                                    <div class="submit_1i1 clearfix">
                                        <h5>Email</h5>
                                        <input class="form-control" value="<%=acc.getEmail()%>" type="text" name="email" disabled>
                                    </div>
                                </div>
                            </div>
                            <div class="submit_3 clearfix">
                                <h5 class="mgt"><input style="border:none" type="submit" class="button mgt" name="submit" value ="Update Profile"></h5>
                            </div>
                        </form>
                    </div>
                </div>


                <div class="submit_1 clearfix">
                    <h4 class="mgt col_1">VIEW POSTED PROPERTIES</h4>
                    <hr>

                    <div class="submit_3 clearfix">
                        <h5 class="mgt"><a class="button mgt" href="viewPostedLandlord">View Posted Properties</a></h5>
                    </div>
                </div>
                                    
                                    
                <div class="submit_1 clearfix">
                    <h4 class="mgt col_1">VIEW TRANSACTION HISTORY</h4>
                    <hr>

                    <div class="submit_3 clearfix">
                        <h5 class="mgt"><a class="button mgt" href="./ViewTransaction">View Transaction History</a></h5>
                    </div>
                </div>
                
                
                <div class="submit_1 clearfix">
                    <h4 class="mgt col_1">CHANGE PASSWORD</h4>
                    <hr>

                    <div class="submit_3 clearfix">
                        <h5 class="mgt"><a class="button mgt" href="change">Change Password</a></h5>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>
    <script>
        $(document).ready(function () {

            /*****Fixed Menu******/
            var secondaryNav = $('.cd-secondary-nav'),
                    secondaryNavTopPosition = secondaryNav.offset().top;
            $(window).on('scroll', function () {
                if ($(window).scrollTop() > secondaryNavTopPosition) {
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
        setTimeout(function () {
            alert("<%= mess %>");
        }, 100);
    </script>
    <%
    }
    %>
</body>

</html>
