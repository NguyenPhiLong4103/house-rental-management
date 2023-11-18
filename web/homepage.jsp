<%-- 
    Document   : homepage
    Created on : Sep 20, 2023, 7:22:55 PM
    Author     : NGOCDUC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Account" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Prop Find</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/global.css" rel="stylesheet">
        <link href="css/index.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Alata&display=swap" rel="stylesheet">
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp"/>
        <%
       String reviewmess = (String) request.getAttribute("reviewmess");
        %>
        <section id="center" class="center_home clearfix">
            <div class="center_main clearfix">
                <div class="w3-content w3-section clearfix">
                    <img class="mySlides w3-animate-top" src="assets/background1.jpg" alt="abc" style="width: 100%; display: none;">
                    <img class="mySlides w3-animate-bottom" src="assets/background2.jpg" alt="abc" style="width: 100%; display: block;">
                    <img class="mySlides w3-animate-top" src="assets/background3.jpg" alt="abc" style="width: 100%; display: none;">
                </div>
                <div class="center_main_1 clearfix">
                    <div class="col-sm-8"></div>
                    <div class="col-sm-4">
                    </div>
                </div>
            </div>
        </section>

        <section id="feature">
            <div class="container">
                <div class="row">
                    <div class="feature_1 clearfix">
                        <div class="col-sm-12">
                            <h4 class="mgt">RECENTLY <br><span class="col_1">PROPERTIES</span></h4>
                        </div>
                    </div>
                    <div class="feature_2 clearfix">
                        <div id="carousel-example" class="carousel slide" data-ride="carousel">
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">
                                <div class="item active">
                                    <c:forEach var = "property" items = "${listProperty}" varStatus = "loop">
                                        <div class="col-sm-4" style="">
                                            <div class="feature_2im clearfix">
                                                <div class="feature_2im1 clearfix">
                                                    <a href="property/${property.getId()}"><img src="${listPropertyImages.get(loop.index).getImgURL()}" class="iw" alt="${property.getName()}" style="width: 360px; height: 280px;"></a>
                                                </div>
                                                <div class="feature_2im2 clearfix">
                                                    <h6 class="mgt"><a class="bg_1">Featured</a></h6>
                                                    <c:set var="promotion" value="${property.getPromotion()}" />
                                                    <c:choose>
                                                        <c:when test="${promotion ne null}">
                                                            <h6 class="pull-right mgt"><a class="bg_2">${promotion.discount}% Off</a></h6>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                            <div class="feature_2m_last clearfix">
                                                <h4 class="mgt bold"><a href="property/${property.getId()}">${property.name}</a></h4>
                                                <p style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis"><i class="fa fa-map-marker"></i> ${property.address} </p><br>
                                                <h6><i class="fa fa-hotel col_1"></i> ${property.numOfBedroom} Bedrooms</h6>
                                                <h6><i class="fa fa-object-group col_1"></i> ${property.area} m&#178</h6><br>
                                                <!--<h5 class="bold"> ${property.price} VND<span class="pull-right"><i class="fa fa-exchange"></i> <i class="fa fa-share-alt"></i> <a href="WishlistController?action=add-wishlist&propertyID=${property.id}"><i class="fa fa-heart-o"></i></a></span></h5>-->
                                                <c:if test="${property.getPromotion() != null}">
                                                    <del>${property.price} VND</del>
                                                    <a style="color: #ffb200"> ${Integer.valueOf(property.price * (1-(property.getPromotion().discount)/100))} VND</a>
                                                </c:if>
                                                <c:if test="${property.getPromotion() == null}">
                                                    <a>${property.price} VND</a>
                                                </c:if>
                                                    <span class="pull-right">
                                                            <c:if test="${sessionScope.user!=null}">
                                                                <% Account acc = (Account)session.getAttribute("user");
                                                                    if(acc!=null){
                                                                %>
                                                                <a href="AdminReport?service=propertyReport&id=<%=acc.getId()%>&pid=${property.id}&lid=${property.landlordID}"><i class="fa fa-flag"></i></a>
                                                                    <%}%>
                                                            </c:if>
                                                            <c:if test="${sessionScope.tenant!=null}">
                                                                <a href="WishlistController?action=add-wishlist&propertyID=${property.id}"><i class="fa fa-heart-o"></i></a>
                                                            </c:if>
                                                        </span>
                                            </div>
                                        </div>
                                    </c:forEach> 
            
                                </div>
                            </div>
                        </div>
                        <div class="feature_2_last text-center clearfix">
                            <div class="col-sm-12">
                                <h5 class="text-center mgt"><a class="button mgt" href="${pageContext.request.contextPath}/property-list">View All <i class="fa fa-long-arrow-right"></i></a></h5>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>

        <script>
            var myIndex = 0;
            carousel();

            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1
                }
                x[myIndex - 1].style.display = "block";
                setTimeout(carousel, 2500);
            }
        </script>

        <script type="text/javascript">
            $(document).on('click', '.number-spinner button', function () {
                var btn = $(this),
                        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
                        newVal = 0;

                if (btn.attr('data-dir') == 'up') {
                    newVal = parseInt(oldValue) + 1;
                } else {
                    if (oldValue > 1) {
                        newVal = parseInt(oldValue) - 1;
                    } else {
                        newVal = 1;
                    }
                }
                btn.closest('.number-spinner').find('input').val(newVal);
            });
        </script>

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
                  if (reviewmess != null) {
        %>
        <script>
            setTimeout(function () {
                alert("<%= reviewmess %>");
            }, 100);
        </script>
        <%
        }
        %>
    </body>

</html>
