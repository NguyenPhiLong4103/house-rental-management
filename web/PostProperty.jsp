<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

    <body>
        <%
            String mess = (String) request.getAttribute("mess");
        %>

        <jsp:include page="header.jsp"/>
        <section id="center" class="center_blog clearfix">
            <div class="container">
                <div class="row">
                    <div class="center_blog_1 text-center clearfix">
                        <div class="col-sm-12">
                            <h2 class="mgt">POST PROPERTY</h2>
                            <h5><a href="#">Home</a>  /  Post Property</h5>
                        </div>
                    </div>
                    <form action="post" method="post" enctype="multipart/form-data">
                    <div class="submit_1 clearfix">
                        <h4 class="mgt col_1">Property Information</h4>
                        <hr>
                        <h5>Property Title</h5>
                        <input name="name" class="form-control" placeholder="Property Title" type="text" required="" maxlength="20">
                        <h5>Address</h5>
                        <input name="address" class="form-control" placeholder="Property Address" type="text" required="">
                        <div class="submit_1i clearfix">
                            <div class="col-sm-4 space_left">
                                <div class="submit_1i1 clearfix">
                                    <h5>Type</h5>
                                    <select name="type" class="form-control" required>
<!--                                        <option value="1">Nha Tro</option>
                                        <option value="2">Chung cu</option>-->
                                        <c:forEach var="c" items="${ptlist}">
                                            <option value="${c.typeId}">${c.typeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 space_all">
                                <div class="submit_1i1 clearfix">
                                    <h5>Number Of Bedroom</h5>
                                    <select name="bedroom" class="form-control" required>
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-4 space_right">
                                <div class="submit_1i1 clearfix">
                                    <h5>Duration For Post </h5>
                                    <select name="duration" class="form-control" required>
                                        <option value="1">1 month - 100 points</option>
                                        <option value="2">3 months - 270 points</option>
                                        <option value="3">6 months - 480 points</option>
                                        <option value="4">1 year - 840 points</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="submit_1i clearfix">
                            <div class="col-sm-6 space_left">
                                <div class="submit_1i1 clearfix">
                                    <h5>Price</h5>
                                    <input name="ppprice" class="form-control" placeholder="VND" type="number" required min="1" max="2000000000">
                                </div>
                            </div>
                            <div class="col-sm-6 space_right">
                                <div class="submit_1i1 clearfix">
                                    <h5>Area</h5>
                                    <input name="pparea" class="form-control" placeholder="Sqft" type="number" required min="1" max="10000">
                                </div>
                            </div>
                        </div>
                        <h5>Thumbnail Image</h5>
                        <input name="thumbnail" class="form-control" placeholder="Property Title" type="file" required="">
                        <h5>Detail Image</h5>
                        <input name="detail" class="form-control" placeholder="Property Title" type="file" multiple="" required="">
                        <h5>Property Description</h5>
                        <textarea name="desc" placeholder="Property Description" class="form-control form_o" required maxlength="750"></textarea>
                    </div>

                    <div class="submit_3 clearfix">
                        <h5 class="mgt"><input name="submit" class="button mgt" type="submit" value="Post Property" style="border:none"></h5>
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
    </body>

</html>
