<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List" %>
<%@ page import="entity.Promotion" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Prop Find</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/global.css" rel="stylesheet">
        <link href="css/submit.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
        <link href="https://fonts.googleapis.com/css?family=Alata&display=swap" rel="stylesheet">
        <script src="js/jquery-2.1.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body onload="setMinEndDate()">

        <%
            String mess = (String) request.getAttribute("mess");
        %>

        <jsp:include page="header.jsp"/>
        <section id="center" class="center_blog clearfix">
            <div class="container">
                <div class="row">
                    <div class="center_blog_1 text-center clearfix">
                        <div class="col-sm-12">
                            <h2 class="mgt">EDIT PROMOTION</h2>
                            <h5><a href="#">Property</a>  /  Edit Promotion</h5>
                        </div>
                    </div>                 
                    <h4 class="mgt col_1">Promotion Information</h4>
                    <div class="feature_2 clearfix">
                        <%
                        Promotion promo = (Promotion) request.getAttribute("promo");
                        if (promo != null) {
                        %>
<!--                        <h5><span class="bold">Promotion ID: </span><%= promo.getPromotionID() %></h5>-->
                        <h5><span class="bold">Discount: </span><%= promo.getDiscount() %></h5>
                        <h5><span class="bold">Description: </span><%= promo.getDescription() %></h5>
                        <h5><span class="bold">Promotion Start Date: </span><%= promo.getPromotionStartDate() %></h5>
                        <h5><span class="bold">Promotion End Date: </span><%= promo.getPromotionEndDate() %></h5><br>
                        <%
                        }
                        %>
                    </div>

                    <form action="PromotionController"  enctype="multipart/form-data">

                        <h4 class="mgt col_1">Change Information</h4>
<!--                        <p>${statusMessage}</p>-->

                        <hr>
                        <h5>Discount</h5>
                        <input name="discount" value="${promo.discount}" class="form-control" placeholder="Discount (%)" type="number" required="" max="99" min="1">
                        <h5>Promotion Description</h5>
                        <textarea name="desc" placeholder="Promotion Description" class="form-control form_o" required maxlength="750">${promo.description}</textarea>
                        <div class="submit_1i clearfix">
                            <div class="col-sm-6 space_left">
                                <div class="submit_1i1 clearfix">
                                    <h5>Promotion Start Date</h5>
                                    <input value="${promo.promotionStartDate}" type="date" id="startDate" name="startDate" required  min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" onchange="setMinEndDate()">
                                </div>
                            </div>
                            <div class="col-sm-6 space_right">
                                <div class="submit_1i1 clearfix">
                                    <h5>Promotion End Date</h5>
                                    <input type="date" id="endDate" name="endDate" required value="${promo.promotionEndDate}">
                                </div>
                            </div>
                        </div>
                        <div class="submit_3 clearfix">
                            <input type="hidden" name="postID" value="${postID}">
                            <h5 class="mgt"><input name="action" class="button mgt" type="submit" value="Update Promotion" style="border:none"></h5><br><br>

                        </div>
                    </form>
                    <form action="PromotionController" >
                        <div class="col-sm-4 space_all">
                            <input type="hidden" name="postID" value="${postID}">
                            <h5 class="mgt"><input name="action" class="button mgt" type="submit" value="Delete Promotion" style="border:none"></h5>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
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
            function setMinEndDate() {
                var startDate = new Date(document.getElementById("startDate").value);
                var endDateInput = document.getElementById("endDate");

                // Set the minimum value for the end date input to be one day after the selected start date
                startDate.setDate(startDate.getDate() + 1);
                var minDate = startDate.toISOString().split('T')[0];
                endDateInput.min = minDate;

                // Reset the end date if it's less than the new minimum
                if (endDateInput.value < minDate) {
                    endDateInput.value = minDate;
                }
            };
        </script>
    </body>
</body>

</html>
