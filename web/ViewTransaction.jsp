<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : ViewTransactionHistory
    Created on : Oct 16, 2023, 10:52:29 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="container" style="min-height: 60vh">
        <h1>Transaction History</h1>
        <br>
        <!-- Filter Form -->
        <form method="get" action="ViewTransaction" class="form-inline">
            <div class="form-group">
                <label for="typeSelect">Type of transaction:</label>
                <select class="form-control" id="typeSelect" name="typeTransaction">
                    <option value="" <%=(request.getParameter("typeTransaction") != null && request.getParameter("typeTransaction").equals(""))?"selected":""%>>All</option>
                    <option value="2" <%=(request.getParameter("typeTransaction") != null && request.getParameter("typeTransaction").equals("2"))?"selected":""%>>Add point</option>
                    <option value="1" <%=(request.getParameter("typeTransaction") != null && request.getParameter("typeTransaction").equals("1"))?"selected":""%>>Pay for post</option>
                </select>
                <select class="form-control" id="typeSelect" name="sortType">
                    <option value="transaction_date desc, transaction_id desc " <%=(request.getParameter("sortType") != null && request.getParameter("sortType").equals("transaction_date desc, transaction_id desc"))?"selected":""%>>Newest First</option>
                    <option value="transaction_date, transaction_id asc" <%=(request.getParameter("sortType") != null && request.getParameter("sortType").equals("transaction_date, transaction_id asc"))?"selected":""%>>Oldest First</option>
                </select>

                <button type="submit" class="btn btn-primary" style="background-color: #274abb">Search</button>
            </div>
        </form>
        <br>
        <!-- Transaction Table -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Amount</th>
                    <th>Post</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="o" items="${listTrans}">
                    <tr>
                        <td>${o.date}</td>
                        <c:if test="${o.type == 1}"><td>Pay for post</td></c:if>
                        <c:if test="${o.type == 2}"><td>Add point</td></c:if>
                        <c:if test="${o.type == 1}"><td style="color: red">- ${o.amount}</td></c:if>
                        <c:if test="${o.type == 2}"><td style="color: green">+ ${o.amount}</td></c:if>
                            <td>
                            <c:if test="${o.postID > 0}"><a href="./property/${o.postID}">View Post</a></c:if>
                            </td>
                        </tr>    
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
