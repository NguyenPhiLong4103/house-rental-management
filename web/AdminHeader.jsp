<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<%@page import="entity.Property" %>
<%@page import="entity.Admin" %>
<%@page import="entity.Transactions" %>
<%@page import="java.util.ArrayList" %>

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
                    <div class="sidebar pe-4 pb-3">
                <nav class="navbar bg-secondary navbar-dark">
                    <a href="AdminController" class="navbar-brand mx-4 mb-3">
                        <h3 class="text-primary"><i class="fa fa-user me-2"></i> Admin</h3>
                    </a>
                    <div class="navbar-nav w-100">
                        <a href="AdminController" class="nav-item nav-link <%if(request.getAttribute("lRs")!=null){%>active<%}%>"><i class="fa fa-user-edit me-2"></i>Accounts</a>

                        <div class="nav-item dropdown">
                            <a href="AdminController" class="nav-link dropdown-toggle <%if(request.getAttribute("addPointTransactions")!=null ||request.getAttribute("payForPostTransactions")!=null){%>active<%}%>" data-bs-toggle="dropdown"><i class="fa fa-file-alt me-2"></i>Transactions</a>
                            <div class="dropdown-menu bg-transparent border-0">
                                <a href="AdminController?service=displayAddPoint" class="dropdown-item">- Add Point Transactions</a>
                                <a href="AdminController?service=displayPayforPost" class="dropdown-item">- Pay for Post Transactions</a>
                            </div>
                        </div>
                        <a href="AdminController?service=ManagePost" class="nav-item nav-link <%if(request.getAttribute("allPropertyList")!=null){%>active<%}%>"><i class="fa fa-th me-2"></i>Post</a>
                        </div>
                    </div>
                </nav>
            </div>
            <!-- Sidebar End -->


            <!-- Content Start -->
            <div class="content">
                <!-- Navbar Start -->
                <nav class="navbar navbar-expand bg-secondary navbar-dark sticky-top px-4 py-0">
                    <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                        <h2 class="text-primary mb-0"><i class="fa fa-user-edit"></i></h2>
                    </a>
                    <a href="#" class="sidebar-toggler flex-shrink-0">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="navbar-nav align-items-center ms-auto">
                        <div class="nav-item dropdown">
                            <a href="homepage" class="nav-item nav-link">
                                <i class="fa fa-home me-lg-2"></i>
                                <span class="d-none d-lg-inline-flex">Home</span>
                            </a>       
                        </div>
                        <div class="nav-item dropdown">
                            <a href="AdminReport" class="nav-item nav-link">
                                <i class="fa fa-envelope me-lg-2"></i>
                                <span class="d-none d-lg-inline-flex">Report</span>
                            </a>       
                        </div>

                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">   
                                <i class="fa fa-user me-lg-2"></i>
                                <span class="d-none d-lg-inline-flex">${sessionScope.admin.getFirstName()} ${sessionScope.admin.getLastName()}</span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-end bg-secondary border-0 rounded-0 rounded-bottom m-0">
                                <a href="profile" class="dropdown-item">Profile</a>
                                <a href="logout" class="dropdown-item">Log Out</a>
                            </div>
                        </div>
                    </div>
                </nav>
    </body>
</html>
