<%-- 
    Document   : Application
    Created on : Oct 6, 2023, 12:07:42 PM
    Author     : Dell
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Create Application</title>
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
            <section id="center" class="center_blog clearfix">
                <div class="container">
                    <div class="row">
                        <div class="center_blog_1 text-center clearfix">
                            <div class="col-sm-12">
                                <h2 class="mgt">RENTAL APPLICATION</h2>
                            </div>
                        </div>
                    <c:if test="${application != null}">
                        <div class="submit_1 clearfix">
                            <h4 class="mgt col_1">Application Details</h4>
                            <hr>
                            <div class="submit_1i clearfix">
                                <div class="col-sm-6 space_all">
                                    <div class="submit_1i1 clearfix">
                                        <h5>Application ID</h5>
                                        <input class="form-control" type="number" value="${application.getOrderID()}" disabled>
                                    </div>
                                </div>
                                <div class="col-sm-6 space_right">
                                    <div class="submit_1i1 clearfix">
                                        <h5>Status</h5>
                                        <c:choose>
                                            <c:when test="${application.getStatus() == 0}">
                                                <input class="form-control" type="text" value="Processing" style="color: grey" disabled>
                                            </c:when>
                                            <c:when test="${application.getStatus() == 1}">
                                                <input class="form-control" type="text" value="Accepted" style="color: green" disabled>
                                            </c:when>
                                            <c:when test="${application.getStatus() == 2}">
                                                <input class="form-control" type="text" value="Rejected" style="color: red" disabled>
                                            </c:when> 
                                            <c:otherwise>
                                                <input class="form-control" type="text" value="Canceled" style="color: orange" disabled>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="submit_1 clearfix">
                        <h4 class="mgt col_1">Property Details</h4>
                        <hr>
                        <h5>Property Name</h5>
                        <input class="form-control" value="${property.getName()}" type="text" disabled>
                        <div class="submit_1i clearfix">
                            <div class="col-sm-6 space_all">
                                <div class="submit_1i1 clearfix">
                                    <h5>Type</h5>
                                    <input class="form-control" type="text" value="${propertyType}" disabled>
                                </div>
                            </div>
                            <div class="col-sm-6 space_right">
                                <div class="submit_1i1 clearfix">
                                    <h5>Bedrooms</h5>
                                    <input class="form-control" type="number" value="${property.getNumOfBedroom()}" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="submit_1i clearfix">
                            <div class="col-sm-6 space_all">
                                <div class="submit_1i1 clearfix">
                                    <h5>Price</h5>
                                    <input class="form-control" type="text" value="$${property.getPrice()}" disabled>
                                </div>
                            </div>
                            <div class="col-sm-6 space_right">
                                <div class="submit_1i1 clearfix">
                                    <h5>Area</h5>
                                    <input class="form-control" type="text" value="${property.getArea()}m&#178" disabled>
                                </div>
                            </div>
                        </div>
                        <h5>Address</h5>
                        <input class="form-control" value="${property.getAddress()}" type="text" disabled>
                    </div>
                    <div class="submit_1 clearfix">
                        <h4 class="mgt col_1">Tenant's Contact Information</h4>
                        <hr>
                        <div class="submit_1i clearfix">
                            <div class="col-sm-6 space_all">
                                <div class="submit_1i1 clearfix">
                                    <h5 class="mgt">Name</h5>
                                    <c:choose>
                                        <c:when test="${requestScope.tenant == null}">
                                            <input class="form-control" value="${sessionScope.tenant.getFirstName()} ${sessionScope.tenant.getLastName()}" type="text" disabled>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="form-control" value="${requestScope.tenant.getFirstName()} ${requestScope.tenant.getLastName()}" type="text" disabled>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="col-sm-6 space_right">
                                <div class="submit_1i1 clearfix">
                                    <h5 class="mgt">Phone</h5>
                                    <input class="form-control" value="${requestScope.tenant == null ? sessionScope.tenant.getPhone() : requestScope.tenant.getPhone()}" type="text" disabled>
                                </div>
                            </div>
                        </div>
                        <h5>Email</h5>
                        <input class="form-control" value="${acc == null ? user.getEmail() : acc.getEmail()}" type="text" disabled>
                    </div>
                    <div class="submit_3 clearfix">
                        <h5 class="col-sm-6"><a class="button mgt" href="${pageContext.request.contextPath}/application/view">Back</a></h5>
                        <c:choose>
                            <c:when test="${application == null}">
                                <h5 class="col-sm-6" align="right"><a class="button mgt" href="${pageContext.request.contextPath}/application/add?propertyID=${property.getId()}">Create Application</a></h5>
                            </c:when>
                            <c:when test="${application.getStatus() == 0}">
                                <c:if test="${user.getRole() == 1}">
                                    <h5 class="col-sm-6" align="right"><a class="button mgt" href="${pageContext.request.contextPath}/application/cancel?applicationID=${application.getOrderID()}" style="background: orange">Cancel Application</a></h5>
                                </c:if>
                                <c:if test="${user.getRole() == 2}">
                                    <h5 class="col-sm-6" align="right">
                                        <a class="button mgt" href="${pageContext.request.contextPath}/application/accept?applicationID=${application.getOrderID()}" style="background: green">Accept</a>
                                        <a class="button mgt" href="${pageContext.request.contextPath}/application/reject?applicationID=${application.getOrderID()}" style="background: red">Reject</a>
                                    </h5>
                                </c:if>
                            </c:when>                                    
                        </c:choose>
                    </div>

                </div>
            </div>
        </section>        
        <jsp:include page="footer.jsp"></jsp:include>

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
