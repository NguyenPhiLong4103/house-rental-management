<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.DAOPromotion"%>
<%@page import="entity.Promotion"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Posted Properties</title>
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

        <section id="center" class="center_list clearfix">
            <div class="container">
                <div class="row">
                    <div class="center_list_1 clearfix">
                        <div class="col-sm-9">
                            <div class="center_list_1l clearfix">
                                <div class="center_list_1li clearfix">
                                    <h3>Posted Properties</h3>
                                    <h5>Result: ${sessionScope.totalPosted}</h5>
                                </div>

                                <div class="feature_2 clearfix">
                                    <c:forEach var="o" items="${listPostedProperties}" varStatus="loop">
                                        <div class="col-sm-6 space_left">
                                            <div class="feature_2im clearfix">
                                                <div class="feature_2im1 clearfix">
                                                    <a href="property/${o.id}">
                                                        <img style="width: 410px; height: 320px" src="${listPropertyImages.get(loop.index).getImgURL()}" class="iw" alt="abc">
                                                    </a>
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
                                                <h4 class="mgt bold"><a href="property/${o.id}">${o.name}</a></h4>
                                                <p style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis"><i class="fa fa-map-marker"></i>${o.address}</p>
                                                <h6><i class="fa fa-hotel col_1"></i>${o.numOfBedroom} bedrooms
                                                    <c:if test = "${o.status == 1}">
                                                        <span class="pull-right">
                                                            <a href = "editPosted?pid=${o.id}" class="fa fa-edit"> Edit Post</a> 
                                                        </span>
                                                    </c:if>

                                                </h6>
                                                <h6><i class="fa fa-object-group col_1"></i> ${o.area} m&#178</h6>
                                                <h5 class="bold">
                                                    <c:if test="${o.getPromotion() != null}">
                                                        <del>${o.price} VND</del>
                                                        <a style="color: #ffb200"> ${Integer.valueOf(o.price * (1-(o.getPromotion().discount)/100))} VND</a>
                                                    </c:if>
                                                    <c:if test="${o.getPromotion() == null}">
                                                        <a>${o.price} VND</a>
                                                    </c:if>
                                                    <c:set var="promotion" value="${o.getPromotion()}" />
                                                    <c:if test="${promotion == null && o.status == 1}">
                                                        <span class="pull-right"><a href = "PostIDToAddViewController?postID=${o.id}&action=add" class="fa fa-percent"> Add Promotion</a></span> 
                                                    </c:if>
                                                    <c:if test="${promotion != null && o.status == 1}">
                                                        <span class="pull-right"><a href = "PostIDToAddViewController?postID=${o.id}&action=edit" class="fa fa-edit"> Edit Promotion</a></span>
                                                    </c:if>
                                                </h5>
                                                <c:choose>
                                                    <c:when test = "${o.status == 1}">
                                                        <h5><i style="color: green">Status: Active</i><span class="pull-right"><a style="color: orangered" href="LanlordDisablePost?disablePID=${o.id}&status=0" class="mgt bold" onclick="return confirm('Are you sure you want to disable?')"/> Disable Post<a></span><span class="pull-right"></h5>
                                                    </c:when> 
                                                    <c:when test = "${o.status == 2}">
                                                        <h5><i style="color: red">This post has been banned!</i></h5>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <h5><i style="color: red">Status: Disable</i><span class="pull-right"><a style="color: blue; cursor: pointer;" class="mgt bold" data-toggle="modal" data-target="#durationModal${o.id}">Re-post<a></span></h5>
                                                                    </c:otherwise>
                                                                </c:choose>

                                                                <!-- modal -->
                                                                <div class="modal fade" id="durationModal${o.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 10000;">
                                                                <div class="modal-dialog" role="document">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Choose duration for post</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -25px;">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <form method="post" id="durationTime${o.id}"> 
                                                                                <input type="text" name="postID" value="${o.id}" hidden/>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() < 100}">
                                                                                    <p>Not enough point to re-post</p>
                                                                                </c:if>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 100}">
                                                                                    <input type="radio" id="1month" name="duration" value="1"/>
                                                                                    <label for="1month">1 month - 100 points</label><br>
                                                                                </c:if>    
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 270}">
                                                                                    <input type="radio" id="3month" name="duration" value="3"/>
                                                                                    <label for="3month">3 months - 270 points</label><br>
                                                                                </c:if>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 480}">
                                                                                    <input type="radio" id="6month" name="duration" value="6"/>
                                                                                    <label for="6month">6 months - 480 points</label><br>
                                                                                </c:if>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 840}">
                                                                                    <input type="radio" id="1year" name="duration" value="12"/>
                                                                                    <label for="1year">1 year - 840 points</label><br>
                                                                                </c:if>
                                                                            </form>
                                                                        </div>
                                                                        <div class="modal-footer" style="margin-top: 0;">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            <c:if test="${sessionScope.landlord.getAccountPoint() >= 100}">
                                                                            <button form="durationTime${o.id}" type="submit" class="btn btn-primary">Re-post</button>
                                                                            </c:if>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- modal -->
                                                            <div class="modal fade" id="durationModal${o.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index: 10000;">
                                                                <div class="modal-dialog" role="document">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Choose duration for post</h5>
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close" style="margin-top: -25px;">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <form method="post" id="durationTime${o.id}"> 
                                                                                <input type="text" name="postID" value="${o.id}" hidden/>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() < 100}">
                                                                                    <p>Not enough point to re-post</p>
                                                                                </c:if>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 100}">
                                                                                    <input type="radio" id="1month" name="duration" value="1"/>
                                                                                    <label for="1month">1 month - 100 points</label><br>
                                                                                </c:if>    
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 270}">
                                                                                    <input type="radio" id="3month" name="duration" value="3"/>
                                                                                    <label for="3month">3 months - 270 points</label><br>
                                                                                </c:if>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 480}">
                                                                                    <input type="radio" id="6month" name="duration" value="6"/>
                                                                                    <label for="6month">6 months - 480 points</label><br>
                                                                                </c:if>
                                                                                <c:if test="${sessionScope.landlord.getAccountPoint() >= 840}">
                                                                                    <input type="radio" id="1year" name="duration" value="12"/>
                                                                                    <label for="1year">1 year - 840 points</label><br>
                                                                                </c:if>

                                                                            </form>
                                                                        </div>
                                                                        <div class="modal-footer" style="margin-top: 0;">
                                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            <button form="durationTime${o.id}" type="submit" class="btn btn-primary">Re-post</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <!-- Get and display the promotion -->
                                                            <c:set var="promotion" value="${o.getPromotion()}" />
                                                            <c:choose>
                                                                <c:when test="${promotion ne null}">
                                                                    <h6><i class="fa fa-percent col_1"></i>${promotion.discount}% Off</h6>
                                                                </c:when>

                                                            </c:choose>
                                                            </div>
                                                            </div>
                                                        </c:forEach>
                                                        </div>
                                                        <div class="product_1_last text-center clearfix">
                                                            <div class="col-sm-12">
                                                                <ul>
                                                                    <c:if test="${index > 1}">
                                                                        <li><a href="viewPostedLandlord?index=${index-1}"><i class="fa fa-chevron-left"></i></a></li>
                                                                            </c:if>
                                                                            <c:forEach var="a" begin="1" end="${endpage}">
                                                                                <c:choose>
                                                                                    <c:when test = "${index == a}">
                                                                                <li style="background-color: #20c997"><a href="viewPostedLandlord?index=${a}">${a}</a></li>
                                                                                </c:when> 
                                                                                <c:otherwise>
                                                                                <li><a href="viewPostedLandlord?index=${a}">${a}</a></li>
                                                                                </c:otherwise>
                                                                            </c:choose>                                                      
                                                                        </c:forEach>
                                                                        <c:if test="${index < endpage}">
                                                                        <li><a href="viewPostedLandlord?index=${index+1}"><i class="fa fa-chevron-right"></i></a></li>
                                                                            </c:if>
                                                                </ul>
                                                            </div>
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
                                                                var btn = $(this), oldValue = btn.closest('.number-spinner').find('input').val().trim(),
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

                                                        </body>

                                                        </html>

