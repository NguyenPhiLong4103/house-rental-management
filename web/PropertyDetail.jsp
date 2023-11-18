<%-- 
    Document   : PropertyDetail
    Created on : Sep 20, 2023, 5:26:21 PM
    Author     : Dell
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${property.getName()}</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/detail.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Alata&display=swap" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/codebase/dhtmlxcalendar.css"/>
        <script src="${pageContext.request.contextPath}/js/codebase/dhtmlxcalendar.js"></script>
        <style>
            #calendar,
            #calendar2,
            #calendar3 {
                border: 1px solid #dfdfdf;
                font-family: Roboto, Arial, Helvetica;
                font-size: 14px;
                color: #404040;
            }
        </style>
        <script>
            var myCalendar;
            function doOnLoad() {
                myCalendar = new dhtmlXCalendarObject(["cal_1", "cal_2", "cal_3"]);
            }
        </script>
    </head>

    <body onLoad="doOnLoad();">
        <jsp:include page="header.jsp"></jsp:include>
            <section id="center" class="center_detail clearfix">
                <div class="container">
                    <div class="row">
                        <div class="center_detail_1 clearfix" style="padding: 0 20px">
                            <div class="center_detail_1l clearfix">
                                <div class="center_detail_1li clearfix">
                                    <h3 class="mgt">${property.getName()} </h3>
                                <h3><small>${property.getAddress()}</small> 
                                    <c:choose>
                                        <c:when test="${promotion == null}"> <span class="span_2 pull-right" style="margin-top: 6.4px;">${property.getPrice()} VND</span> </c:when>
                                        <c:otherwise><span class="span_2 pull-right" style="margin-top: 6.4px;"><s style="color: grey; font-size: 18px">${property.getPrice()}</s> ${Integer.valueOf(property.getPrice()*(100-promotion.getDiscount())/100)} VND</c:otherwise>
                                            </c:choose> 
                                </h3>
                            </div>
                        </div>
                    </div>
                    <div class="center_detail_1 clearfix">
                        <div class="col-sm-8">                                
                            <div class="center_detail_1li1 clearfix">
                                <h4 class="mgt head_1">Gallery</h4>
                                <div id="main_area">
                                    <!-- Slider -->
                                    <div class="row">
                                        <div class="col-sm-12" id="slider">
                                            <!-- Top part of the slider -->
                                            <div class="row">
                                                <div class="col-sm-12" id="carousel-bounding-box">
                                                    <div class="carousel slide" id="myCarousel">
                                                        <!-- Carousel items -->
                                                        <div class="carousel-inner">
                                                            <c:forEach var="image" items="${propertyImages}" varStatus="loop">
                                                                <c:choose>
                                                                    <c:when test="${loop.index == 0}">
                                                                        <div class="active item">
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <div class="item">
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                        <img src="${pageContext.request.contextPath}/${image.getImgURL()}" class="iw" alt="${property.getName()}" style="width: 720px; height: 450px; object-fit: contain;">
                                                                    </div>
                                                                </c:forEach>               
                                                            </div><!-- Carousel nav -->
                                                            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                                                <span class="glyphicon glyphicon-chevron-left"></span>                                       
                                                            </a>
                                                            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                                                <span class="glyphicon glyphicon-chevron-right"></span>                                       
                                                            </a>                                
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div><!--/Slider-->                                        
                                    </div>
                                </div>
                                <div class="center_detail_1li1 clearfix">
                                    <h4 class="mgt head_1">Description</h4><br><br>
                                    <p>${property.getDescription()}</p>
                                </div>
                                <div class="center_detail_1li2 clearfix">
                                    <h4 class="mgt head_1">Property Details</h4><br><br>
                                    <div class="center_detail_1li2i clearfix">
                                        <div class="col-sm-4 space_left">
                                            <h6><span class="bold">Property ID:</span> ${property.getId()}</h6>
                                        </div>
                                        <div class="col-sm-4 space_left">
                                            <h6><span class="bold">Property Type:</span> ${type}</h6>
                                        </div>
                                        <div class="col-sm-4 space_left">
                                            <h6><span class="bold">Property Status:</span> 
                                                <c:choose>
                                                    <c:when test="${property.getStatus() == 1}">
                                                        Available
                                                    </c:when>
                                                    <c:otherwise>
                                                        Unavailable
                                                    </c:otherwise>
                                                </c:choose>
                                            </h6>
                                        </div>
                                    </div>
                                    <div class="center_detail_1li2i clearfix">
                                        <div class="col-sm-4 space_left">
                                            <h6><span class="bold">Property Price:</span> ${promotion == null ? property.getPrice() : Integer.valueOf(property.getPrice()*promotion.getDiscount()/100)} VND</h6>
                                        </div>
                                        <div class="col-sm-4 space_left">
                                            <h6><span class="bold">Area:</span> ${property.getArea()}m&#178</h6>
                                        </div>
                                        <div class="col-sm-4 space_left">
                                            <h6><span class="bold">Bedrooms:</span> ${property.getNumOfBedroom()}</h6>
                                        </div>
                                    </div>
                                    <div class="center_detail_1li2i clearfix">
                                        <div class="space_left">
                                            <h6><span class="bold">Property Location:</span> ${property.getAddress()}</h6>
                                        </div>
                                    </div><br><br>                                
                                </div>                                                             
                            </div>
                            <div class="col-sm-4">
                                <div class="center_detail_1r clearfix">                                
                                    <div class="center_detail_1ri1 clearfix">
                                        <h4 class="mgt head_1"> Landlord Feedback</h4>
                                        <div class="center_detail_1ri1i1 clearfix">
                                            <a href="${pageContext.request.contextPath}/ViewReview?service=viewReview&id=${landlord.getId()}" style="display: flex; margin-top: 25px;">
                                                <img src="${pageContext.request.contextPath}/assets/default_user_icon.jpg" class="img-circle" alt="abc" style="width: 100px; height: 100px;">
                                                <h5 class="bold mgt" style="align-items: center;display: flex;">${landlord.getFirstName()} ${landlord.getLastName()}</h5>
                                            </a>
                                        </div><br>
                                        <c:if test="${sessionScope.user != null && sessionScope.user.getRole() == 1 && property.getStatus() == 1}">
                                            <div class="clearfix">
                                                <div class="col-sm-10 space_left">
                                                    <h5 class="text-center">
                                                        <c:choose>
                                                            <c:when test="${!submittedApplication}">
                                                                <a class="button block" href="${pageContext.request.contextPath}/application/create?propertyID=${property.getId()}">Submit Application</a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a class="button block">Application submitted</a>
                                                            </c:otherwise>
                                                        </c:choose>                                                  
                                                    </h5>
                                                </div>
                                                <div class="col-sm-2 space_all">
                                                    <h5 class="text-center">
                                                        <a class="button mgt" style="background: white; color: #ff8787; border-radius: 50%; padding: 15px; border: 1px solid #ddd;">
                                                            <c:choose>
                                                                <c:when test="${onWishlist}">                                                            
                                                                    <i class="fa fa-heart" style="margin: 0; cursor: pointer;" onclick="addToWishlist(this)"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="fa fa-heart-o" style="margin: 0; cursor: pointer;" onclick="addToWishlist(this)"></i>
                                                                </c:otherwise>
                                                            </c:choose>                                                        
                                                        </a>
                                                    </h5>
                                                </div>                                            
                                            </div>
                                        </c:if>
                                    </div><br>
                                </div> 
                            </div>
                        </div><br>   
                        <div class="feature_1 clearfix">
                            <div class="col-sm-12">
                                <h4>RECENT <br><span class="col_1">PROPERTIES</span></h4>
                            </div>
                        </div>
                        <div class="feature_2 clearfix">
                            <c:forEach var="recentProperty" items="${recentProperties}" varStatus="loop">                      
                                <div class="col-sm-4">
                                    <div class="feature_2im clearfix">
                                        <div class="feature_2im1 clearfix">
                                            <a href="${pageContext.request.contextPath}/property/${recentProperty.getId()}"><img src="${pageContext.request.contextPath}/${recentPropertyImages.get(loop.index).getImgURL()}" class="iw" alt="${recentProperty.getName()}" style="width: 360px; height: 280px; object-fit: contain;"></a>
                                        </div>
                                    </div>
                                    <div class="feature_2m_last clearfix">
                                        <h4 class="mgt bold"><a href="${pageContext.request.contextPath}/property/${recentProperty.getId()}">${recentProperty.getName()}</a></h4>
                                        <p><i class="fa fa-map-marker"></i> ${recentProperty.getAddress()}</p><br>
                                        <h6><i class="fa fa-hotel col_1"></i> ${recentProperty.getNumOfBedroom()} Bedrooms <span class="pull-right"><i class="fa fa-object-group col_1"></i> ${property.getArea()}m&#178</span></h6>
                                        <c:choose>
                                            <c:when test="${recentProperty.getPromotion() != null}">
                                                <h5 class="bold">
                                                    <s style="color: grey; font-size: 15px;">${recentProperty.getPrice()} VND</s>
                                                    <a style="color: #ffb200"> ${Integer.valueOf(recentProperty.getPrice() / 100 * (100 - recentProperty.getPromotion().getDiscount()))} VND</a>
                                                </h5>
                                            </c:when>
                                            <c:otherwise>
                                                <h5 class="bold"><a>${recentProperty.getPrice()} VND</a></h5>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>
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
                /*****Add to wishlist*****/
                function addToWishlist(element) {
                    if (element.classList.contains('fa-heart-o')) {
                        element.classList.remove('fa-heart-o');
                        element.classList.add('fa-heart');
                        $.ajax({
                            type: 'POST',
                            url: '${pageContext.request.contextPath}/WishlistController?action=add-wishlist&propertyID=${property.getId()}'
                                        });
                                    } else {
                                        element.classList.add('fa-heart-o');
                                        element.classList.remove('fa-heart');
                                        $.ajax({
                                            type: 'POST',
                                            url: '${pageContext.request.contextPath}/WishlistController?action=delete-from-wishlist&propertyID=${property.getId()}'
                                                        });
                                                    }
                                                }
        </script>

        <script type="text/javascript">
            jQuery(document).ready(function ($) {

                $('#myCarousel').carousel({
                    interval: 5000
                });

                $('#carousel-text').html($('#slide-content-0').html());

                //Handles the carousel thumbnails
                $('[id^=carousel-selector-]').click(function () {
                    var id = this.id.substr(this.id.lastIndexOf("-") + 1);
                    var id = parseInt(id);
                    $('#myCarousel').carousel(id);
                });


                // When the carousel slides, auto update the text
                $('#myCarousel').on('slid.bs.carousel', function (e) {
                    var id = $('.item.active').data('slide-number');
                    $('#carousel-text').html($('#slide-content-' + id).html());
                });
            });
        </script>

        <script type="text/javascript">
            $(document).on('click', '.number-spinner button', function () {
                var btn = $(this),
                        oldValue = btn.closest('.number-spinner').find('input').val().trim(),
                        newVal = 0;

                if (btn.attr('data-dir') === 'up') {
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
    </body>

</html>



