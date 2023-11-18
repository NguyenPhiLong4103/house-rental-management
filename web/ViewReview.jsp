<%-- 
    Document   : ViewReview
    Created on : Oct 23, 2023, 10:58:10 AM
    Author     : LE HAI THINH
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Account" %>
<%@page import="java.sql.ResultSet" %>
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
        <style>
            .content-feedback{
                width: 100%;
                overflow-wrap: break-word;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <%
        String mess = (String) request.getAttribute("mess");
        ResultSet namell = (ResultSet)request.getAttribute("namell");
        String avg = (String)request.getAttribute("avg");
        String count = (String)request.getAttribute("count");
        while(namell.next()){
        %>
            <div class="container"  style="min-height: 60vh">
                <h1>Feedback for: <%=namell.getString("first_name")%> <%=namell.getString("last_name")%></h1>
                <%}%>
                <!-- Application List -->
                <table class="table table-striped">
                    <thead>
                        <%if(avg!="NaN"){%>
                        <h6>Overall: <%=avg%>/5</h6>
                        <%}%>
                        <h6>Total comment: <%=count%></h6>
                        <hr>                    </thead>
                    <tbody>
                        <%        ResultSet rs = (ResultSet)request.getAttribute("rs");
                       
              int i = 0;
              while(rs.next()){
              i++;
                        %>  
                        <h4><%=i%>. Reviewer: <%=rs.getString("tfirst_name")%> <%=rs.getString("tlast_name")%></h4>
                        <h6>Property name: <%=rs.getString("name")%></h6>
                        <h6>Rating: <%=rs.getString("rating")%>/5</h6>
                        <h6 class="content-feedback">Comment: <%=rs.getString("review")%></h6>
                        <hr>                        <%}%>
                    </tbody>
                </table>
            </div>   
   <jsp:include page="footer.jsp"/>     
<%
          if (mess != null) {
%>
<script>
    setTimeout(function() {
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
