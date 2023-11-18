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
                        <div class="col-sm-9">
                            <div class="center_list_1l clearfix">
                                <div class="center_list_1li clearfix">
                                    <h3>Property View</h3>
                                    <h5>Result: ${sessionScope.result}</h5>
                                </div>

                                <div class="feature_2 clearfix">
                                    <c:forEach var="o" items="${listP}">
                                        <div class="col-sm-6 space_left">
                                            <div class="feature_2im clearfix">
                                                <div class="feature_2im1 clearfix">
                                                    <img style="width: 410px; height: 320px" src="${o.propertyImage.imgURL}" class="iw" alt="error">
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
                                                                <% Account acc = (Account)session.getAttribute("user");
                                                                    if(acc!=null){
                                                                %>
                                                                <a href="AdminReport?service=propertyReport&id=<%=acc.getId()%>&pid=${o.id}&lid=${o.landlordID}"><i class="fa fa-flag"></i></a>
                                                                    <%}%>
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
                                                <li><a href="./search?index=${index-1}&txt=<%=request.getParameter("txt")%>&propertyType=<%=request.getParameter("propertyType")%>&sortType=<%=request.getParameter("sortType")%>&price=<%=request.getParameter("price")%>&area=<%=request.getParameter("area")%>&beds=<%=request.getParameter("beds")%>"><i class="fa fa-chevron-left"></i></a></li>
                                            </c:if>
                                            <c:forEach var="a" begin="1" end="${numberPage}">
                                                <c:choose>
                                                    <c:when test = "${index == a}">
                                                        <li style="background-color: #20c997"><a href="./search?index=${a}&txt=<%=request.getParameter("txt")%>&propertyType=<%=request.getParameter("propertyType")%>&sortType=<%=request.getParameter("sortType")%>&price=<%=request.getParameter("price")%>&area=<%=request.getParameter("area")%>&beds=<%=request.getParameter("beds")%>">${a}</a></li>
                                                    </c:when> 
                                                    <c:otherwise>
                                                        <li><a href="./search?index=${a}&txt=<%=request.getParameter("txt")%>&propertyType=<%=request.getParameter("propertyType")%>&sortType=<%=request.getParameter("sortType")%>&price=<%=request.getParameter("price")%>&area=<%=request.getParameter("area")%>&beds=<%=request.getParameter("beds")%>">${a}</a></li>
                                                     </c:otherwise>
                                                </c:choose>                                                      
                                            </c:forEach>
                                            <c:if test="${index < numberPage}">
                                                <li><a href="./search?index=${index+1}&txt=<%=request.getParameter("txt")%>&propertyType=<%=request.getParameter("propertyType")%>&sortType=<%=request.getParameter("sortType")%>&price=<%=request.getParameter("price")%>&area=<%=request.getParameter("area")%>&beds=<%=request.getParameter("beds")%>"><i class="fa fa-chevron-right"></i></a></li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3 space_left">
                            <div class="center_list_1r clearfix">
                                <form action="search" method="get">
                                    <h4 class="mgt head_1">Filter</h4>
                                    <div class="center_main_1r clearfix">
                                        <input style="display: none" class="form-control" value="<%=request.getParameter("txt")%>" name="txt">
                                        <select style="display: none" class="form-control" name="propertyType">
                                            <option value="" <%= (request.getParameter("propertyType") == null || request.getParameter("propertyType").equals("")) ? "selected" : "" %>>All</option>
                                            <c:forEach var="c" items="${ptlist}">
                                                <option value="${c.typeId}" ${propertyType == c.typeId ? 'selected' : ''}>${c.typeName}</option>
                                            </c:forEach>
                                        </select>
                                        <h5 class="col">Sort Type</h5>
                                        <select class="form-control" name="sortType">
                                            <option value="p.id asc" ${sortType == "p.id asc" ? 'selected' : ''}>Any</option>
                                            <option value="area asc" ${sortType == "area asc" ? 'selected' : ''}>Area small to large</option>
                                            <option value="area desc" ${sortType == "area desc" ? 'selected' : ''}>Area large to small</option>
                                            <option value="price asc" ${sortType == "price asc" ? 'selected' : ''}>Price low to high</option>
                                            <option value="price desc" ${sortType == "price desc" ? 'selected' : ''}>Price high to low</option>
                                        </select>
                                        <h5 class="col">Price</h5>
                                        <select class="form-control" name="price">
                                            <option value="" ${price == "" ? 'selected' : ''}>Any</option>
                                            <option value="0 and 1000000" ${price == "0 and 1000000" ? 'selected' : ''}><1000000 VND</option>    
                                            <option value="1000000 and 2000000" ${price == "1000000 and 2000000" ? 'selected' : ''}>1000000 - 2000000 VND</option>
                                            <option value="2000000 and 3000000" ${price == "2000000 and 3000000" ? 'selected' : ''}>2000000 - 30000000 VND</option>
                                        </select>
                                        <h5 class="col">Area</h5>
                                        <select class="form-control" name="area">
                                            <option value="" ${beds == "" ? 'selected' : ''}>Any</option>
                                            <option value="0 and 15" ${area == "0 and 15" ? 'selected' : ''}><15m&#178</option>
                                            <option value="15 and 20" ${area == "15 and 20" ? 'selected' : ''}>15-20m&#178</option>    
                                            <option value="20 and 30" ${area == "20 and 30" ? 'selected' : ''}>20-30m&#178</option>
                                            <option value="30 and 1000" ${area == "30 and 1000" ? 'selected' : ''}>>30m&#178</option>
                                        </select>
                                        <div class="center_main_1ri clearfix">
                                            <div class="col-sm-6 space_left">
                                                <h5 class="col">Beds</h5>
                                                <select class="form-control" name="beds" id="beds">
                                                    <option value="" <%= (request.getParameter("beds") == null || request.getParameter("beds").equals("")) ? "selected" : "" %>>Any</option>
                                                    <c:forEach var="bed" items="${bedlist}">
                                                        <option value="${bed}" ${beds == bed ? 'selected' : ''}>${bed}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <button class="button_1 block" style="margin: auto; margin-top: 5px; border-radius: 10% " type="submit">Filter</button>
                                    </div>
                                </form>
                            </div><br>


                        </div>
                    </div>
                </div>
            </div>
        </section>

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

