<%-- 
    Document   : ApplicationList
    Created on : Oct 17, 2023, 12:17:53 PM
    Author     : Dell
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Application History</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/submit.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Alata&display=swap" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>    
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
       String mess = (String) request.getAttribute("mess");
        %>
        <div class="container" style="min-height: 60vh">
            <h1>Application History</h1>
            <form method="get" style="margin-top: 15px;">
                <div class="col-md-11" style="padding: 0px">
                    <input class="form-control" type="text" name="txtSearch" placeholder="Search by property name">
                </div>
                <div class="col-md-1">
                    <button class="btn btn-primary"type="submit" style="background-color: #274abb"><i class="fa fa-search"></i>Search</button>
                </div>
            </form>
            <!-- Application List -->
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><h5><b>ID</b></h5></th>
                                    <c:if test="${sessionScope.user.getRole() == 2}">
                            <th><h5><b>Tenant Name</b></h5></th>                            
                                    </c:if>
                                    <c:if test="${sessionScope.user.getRole() == 1}">
                            <th><h5><b>Landlord Name</b></h5></th>                              
                                    </c:if>
                        <th><h5><b>Property Name</b></h5></th>
                        <th><h5><b>Status</b></h5></th>
                        <th><h5><b>View</b></h5></th>
                        <th><h5><b>Review</b></h5></th>

                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${applications}" var="application" varStatus="loop">
                        <tr>
                            <th scope="row">${application.getOrderID()}</th>
                            <td>${nameList.get(loop.index)}</td>       
                            <td><a href="${pageContext.request.contextPath}/property/${properties.get(loop.index).getId()}">${properties.get(loop.index).getName()}</a></td>
                                <c:choose>
                                    <c:when test="${application.getStatus() == 0}">
                                    <td style="color: grey;">Processing</td>
                                </c:when>
                                <c:when test="${application.getStatus() == 1}">
                                    <td style="color: green;">Accepted</td>
                                </c:when>
                                <c:when test="${application.getStatus() == 2}">
                                    <td style="color: red;">Rejected</td>
                                </c:when> 
                                <c:otherwise>
                                    <td style="color: orange;">Canceled</td>
                                </c:otherwise>
                            </c:choose>

                            <td><a href="${pageContext.request.contextPath}/application/${application.getOrderID()}">View</a></td>
                            <c:if test="${sessionScope.user.getRole() == 2}">
                                <% Account acc = (Account)session.getAttribute("user");%>
                                <td><a href="${pageContext.request.contextPath}/AdminReport?service=tenantReport&id=<%=acc.getId()%>&tid=${application.getTenantID()}">Report</a></td>
                            </c:if>
                            <c:if test="${sessionScope.user.getRole() == 1 && application.getStatus() == 1}">
                                <%Account acc = (Account)session.getAttribute("user");%>
                                <td><a href="${pageContext.request.contextPath}/ReviewController?service=review&id=<%=acc.getId()%>&pid=${application.getPostID()}&lid=${application.getLandlordID()}">Review</a></td>     
                                <td><a href="${pageContext.request.contextPath}/AdminReport?service=propertyReport&id=<%=acc.getId()%>&pid=${application.getPostID()}&lid=${application.getLandlordID()}">Report</a></td>
                            </c:if>
                            <c:if test="${sessionScope.user.getRole() == 1 && application.getStatus() != 1}">
                                <%Account acc = (Account)session.getAttribute("user");%> 
                                <td><a><i class="fa fa-exclamation-triangle"></i></a></td>                                
                                <td><a href="${pageContext.request.contextPath}/AdminReport?service=propertyReport&id=<%=acc.getId()%>&pid=${application.getPostID()}&lid=${application.getLandlordID()}">Report</a></td>
                            </c:if>    
                        </tr>
                    </c:forEach>                            
                </tbody>
            </table>
        </div>   
        <jsp:include page="footer.jsp"></jsp:include>
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
    </body>
</html>
