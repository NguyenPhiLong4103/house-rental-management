<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <link href="css/listing.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Alata&display=swap" rel="stylesheet">
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <%
String mess = (String) request.getAttribute("rpmess");
        %>

        <section id="center" class="center_list clearfix">
            <div class="container">
                <div class="row">
                    <div class="center_list_1 clearfix">
                        <div class="center_list_1l clearfix">
                            <div class="center_list_1li clearfix">
                                <h3>All Property</h3>
                                <h5>Result: ${sessionScope.result}</h5>
                            </div>
                            <div class="feature_2 clearfix">
                                <c:forEach var="o" items="${listP}">
                                    <div class="col-sm-4 space_left">
                                        <div class="feature_2im clearfix">
                                            <div class="feature_2im1 clearfix">
                                                <a href="property/${o.id}"><img style="max-width: 100%; height: 320px" src="${o.propertyImage.imgURL}" class="iw" alt="abc"></a>
                                            </div>
                                            <div class="feature_2im2 clearfix">
                                                <h6 class="mgt"><a class="bg_1">Featured</a></h6>
                                                <c:set var="promotion" value="${o.getPromotion()}" />
                                                <c:choose>
                                                    <c:when test="${promotion ne null}">
                                                        <h6 class="pull-right mgt"><a class="bg_2">${promotion.discount}% Off</a></h6>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="feature_2m_last clearfix">
                                            <h4 class="mgt bold"><a href="./property/${o.id}">${o.name}</a></h4>
                                            <p style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis"><i class="fa fa-map-marker"></i>${o.address}</p>
                                            <h6><i class="fa fa-hotel col_1"></i>${o.numOfBedroom} bedrooms</h6>
                                            <h6><i class="fa fa-object-group col_1"></i>${o.area}m&#178</h6>
                                            <h5 class="bold">
                                                <div>
                                                    <c:if test="${o.getPromotion() != null}">
                                                        <del>${o.price} VND</del>
                                                        <a style="color: #ffb200"> ${Integer.valueOf(o.price * (1-(o.getPromotion().discount)/100))} VND</a>
                                                    </c:if>
                                                    <c:if test="${o.getPromotion() == null}">
                                                        <a>${o.price} VND</a>
                                                    </c:if>
                                                    <span class="pull-right">
                                                        <c:if test="${sessionScope.user!=null}">
                                                            <% Account acc = (Account)session.getAttribute("user");%>
                                                            <a href="AdminReport?service=propertyReport&id=<%=acc.getId()%>&pid=${o.id}&lid=${o.landlordID}"><i class="fa fa-flag"></i></a>
                                                            </c:if>
                                                            <c:if test="${sessionScope.tenant!=null}">
                                                            <a href="WishlistController?action=add-wishlist&propertyID=${o.id}"><i class="fa fa-heart-o"></i></a>
                                                            </c:if>
                                                    </span> 
                                                </div>
                                            </h5>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="product_1_last text-center clearfix">
                                <div class="col-sm-12">
                                    <ul>
                                        <c:if test="${index > 1}">
                                            <li><a href="./property-list?index=${index-1}"><i class="fa fa-chevron-left"></i></a></li>
                                                </c:if>
                                                <c:forEach var="a" begin="1" end="${numberPage}">
                                                    <c:choose>
                                                        <c:when test = "${index == a}">
                                                    <li style="background-color: #20c997"><a href="./property-list?index=${a}">${a}</a></li>
                                                    </c:when> 
                                                    <c:otherwise>
                                                    <li><a href="./property-list?index=${a}">${a}</a></li>
                                                    </c:otherwise>
                                                </c:choose>                                                      
                                            </c:forEach>
                                            <c:if test="${index < numberPage}">
                                            <li><a href="./property-list?index=${index+1}"><i class="fa fa-chevron-right"></i></a></li>
                                                </c:if>
                                    </ul>
                                </div>
                            </div>
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

