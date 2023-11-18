<%-- 
    Document   : AdminAccount
    Created on : Sep 22, 2023, 11:24:42 AM
    Author     : LE HAI THINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Tenant" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector" %>
<%@page import="entity.Landlord" %>
<%@page import="entity.Admin" %>
<%@page import="entity.Report" %>


<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <title>Administrator</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min_1.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style_1.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-fluid position-relative d-flex p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->
      <jsp:include page="AdminHeader.jsp"/>

                <!-- Table Start -->

                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <%
                        ResultSet rRs = (ResultSet)request.getAttribute("rRs"); 
                        if(rRs!=null){
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Report</h6>
                                <!--SEARCH 
                                <form class="d-none d-md-flex ms-4_4" action="AdminController" method="post">
                                    <input class="form-control bg-dark border-0" type="text" placeholder="Search by id" name="tId">
                                    
                                        <input type="hidden" name="service" value="search">
                                    
                                </form>
                                -->
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Reporter ID</th>
                                                <th scope="col">Property ID</th>
                                                <th scope="col">Reported ID</th>
                                                <th scope="col">Categories ID</th>
                                                <th scope="col">Description</th>
                                                <th scope="col">Status</th>                                                                                                
                                                <th scope="col">Update</th>
                                            </tr>
                                        </thead>
                                        <%

while(rRs.next()){              
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=rRs.getInt("report_id")%></td>
                                                <td><%=rRs.getInt("reporter_id")%></td>
                                                <td><%=rRs.getInt("property_id")%></td>
                                                <td><%=rRs.getInt("reported_id")%></td>
                                                <td><%=rRs.getString("categories")%></td>
                                                <td><%=rRs.getString("description")%></td>
                                                <td><%=rRs.getString("status")%></td>
                                                <td><a href="AdminReport?service=updateStatus&id=<%=rRs.getInt("report_id")%>">Update</a></td>
                                            </tr>
                                        </tbody>
                                        <%
    }
                                        %>
                                    </table>
                                </div>

                                    <!-- endpage -->

                            </div>

                        </div>
                        <%}%>
                    </div>

                </div>
                <!-- Table End -->


                <!-- Footer Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="bg-secondary rounded-top p-4">
                        <div class="row">
                            <div class="col-12 col-sm-6 text-center text-sm-start">
                                &copy; <a>House rental - Group 1</a>, All Right Reserved. 
                            </div>
                            <div class="col-12 col-sm-6 text-center text-sm-end">
                                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                                Designed By <a>ThinhLH</a>
                                <br>Distributed By: <a target="_blank">Group 1</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Footer End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/chart/chart.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>
