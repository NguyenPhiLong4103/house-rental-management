<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Account" %>
<section id="top">
    <div class="container">
        <div class="row">
            <div class="top_1 clearfix">
                <div class="col-sm-6">
                    <div class="top_1l clearfix">
                        <ul>
                            <li><i class="fa fa-phone"></i> (+84) 867 561 383</li>
                            <li><i class="fa fa-map-marker"></i>   FPT University HL, VN</li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="top_1r pull-right clearfix">

                        <ul>
                            <c:if test="${sessionScope.user==null}">
                                <li><a class="col" href="${pageContext.request.contextPath}/login"><i class="fa fa-user"></i> Login</a></li>
                                </c:if>
                                <c:if test="${sessionScope.user!=null}">
                                    <c:if test = "${sessionScope.tenant!=null}">
                                    <li>
                                        <a class="col" href="${pageContext.request.contextPath}/profile"><i class="fa fa-info"></i> ${sessionScope.tenant.getFirstName()} ${sessionScope.tenant.getLastName()}</a>                     
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.tenant!=null}">
                                    <li><a class="col" href="${pageContext.request.contextPath}/WishlistController?action=get-wishList"><i class="fa fa-heart-o"></i> Wishlist</a></li>
                                </c:if>
                                <c:if test = "${sessionScope.landlord!=null}">
                                    <li>
                                        <a class="col" href="${pageContext.request.contextPath}/profile"><i class="fa fa-info"></i> ${sessionScope.landlord.getFirstName()} ${sessionScope.landlord.getLastName()}<sup>${sessionScope.landlord.getAccountPoint()}</sup></a>                     
                                    </li>
                                </c:if>
                                <c:if test = "${sessionScope.admin!=null}">
                                    <li>
                                        <a class="col" href="${pageContext.request.contextPath}/AdminController"><i class="fa fa-user"></i>Admin</a>                     
                                    </li>
                                </c:if>
                            </c:if>
                            <c:if test="${sessionScope.user!=null}">
                                <% Account acc = (Account)session.getAttribute("user");%>
                                <li><a class="col" href="${pageContext.request.contextPath}/AdminReport?service=systemReport&id=<%=acc.getId()%>"><i class="fa fa-flag"></i> Report</a></li>
                                </c:if>
                                <c:if test="${sessionScope.user!=null}">
                                <li><a class="col" href="${pageContext.request.contextPath}/logout"><i class="fa fa-user"></i> Logout</a></li>
                                </c:if>

                            <c:if test="${sessionScope.user==null}">
                                <li><a class="col" href="${pageContext.request.contextPath}/register"><i class="fa fa-sign-in"></i> Register</a></li>
                                </c:if>  
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<section id="header" class="clearfix cd-secondary-nav">
    <nav class="navbar">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/homepage"> <i class="fa fa-home"></i> <span> HRMS</span> </a>
            </div>
            <!-- Brand and toggle get grouped for better mobile display -->
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav nav_1">
                    <li><a class="m_tag" style="margin-right: 10px" href="${pageContext.request.contextPath}/homepage">Home</a></li>
                    <li class="dropdown">
                        <a class="m_tag" style="margin-right: 10px" href="${pageContext.request.contextPath}/property-list">Property Listing</a>
                    </li>
                    <c:if test="${sessionScope.user != null}">
                        <li>
                            <a class="m_tag" style="margin-right: 10px" href="${pageContext.request.contextPath}/application/view">Application History</a>
                        </li>
                    </c:if>
                    <li>
                        <form method="GET" action="${pageContext.request.contextPath}/search" class="form-inline" style="padding-top: 10px">
                            <div class="form-group">
                                <input class="form-control" type="text" name="txt" value="${param.txt}" placeholder="Search here">
                            </div>

                            <div class="form-group ml-3">
                                <select class="form-control" name="propertyType">
                                    <option value="" >All</option>
                                    <c:forEach var="c" items="${ptlist}">
                                        <option value="${c.typeId}">${c.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <select style="display: none" class="form-control" name="sortType">
                                <!--<option value="${param.sortType}"></option>-->
                                <option value=""></option>
                            </select>
                            <select style="display: none" class="form-control" name="price">
                                <!--<option value="${param.price}"></option>-->
                                <option value=""></option>
                            </select>
                            <select style="display: none" class="form-control" name="area">
                                <!--<option value="${param.area}"></option>-->
                                <option value=""></option>
                            </select>
                            <select style="display: none" class="form-control" name="beds" id="beds">
                                <!--<option value="${param.beds}"></option>-->
                                <option value=""></option>
                            </select>
                            <button type="submit" class="btn btn-primary ml-3" style="background-color: #274abb">Search</button>
                        </form>
                    </li>
                </ul>   
                <c:if test = "${sessionScope.landlord!=null}">
                    <ul class="nav navbar-nav navbar-right nav_2">
                        <li><a class="m_tag button mgt" href="${pageContext.request.contextPath}/post">Post Property</a></li>
                    </ul>
                </c:if>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>

</section>