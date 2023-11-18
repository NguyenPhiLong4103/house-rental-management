<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : AdminAccount
    Created on : Sep 22, 2023, 11:24:42 AM
    Author     : LE HAI THINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Tenant" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.util.Vector" %>
<%@page import="entity.Landlord" %>
<%@page import="entity.Property" %>
<%@page import="entity.Admin" %>
<%@page import="entity.Transactions" %>
<%@page import="java.util.ArrayList" %>
<%@page import="dao.DAOLandlord" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <title>Administrator</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min_1.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style_1.css" rel="stylesheet">
    </head>

    <body>
      
        <div class="container-fluid position-relative d-flex p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-dark position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <jsp:include page="AdminHeader.jsp"/>



                <!-- Table Start -->

                <div class="container-fluid pt-4 px-4">
                    <div class="row g-4">
                        <%
                        ResultSet lRs = (ResultSet)request.getAttribute("lRs"); 
                        ResultSet tRs = (ResultSet)request.getAttribute("tRs"); 
                        ResultSet aRs = (ResultSet)request.getAttribute("aRs"); 
                        ArrayList<Transactions> addPointTransactions = (ArrayList<Transactions>)request.getAttribute("addPointTransactions");
                        ArrayList<Transactions> payForPostTransactions = (ArrayList<Transactions>)request.getAttribute("payForPostTransactions");
                        ArrayList<Property> allPropertyList = (ArrayList<Property>)request.getAttribute("allPropertyList");
                        String address="", phone="", civil="";
                        if(tRs!=null){
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Tenant Account</h6>
                                <form class="d-none d-md-flex ms-4_4" action="AdminController" method="post">
                                    <input min="0" max="2000000000" class="form-control bg-dark border-0" type="number" placeholder="Search by id" name="tId">                                  
                                        <input type="hidden" name="service" value="search">
                                    
                                </form>


                                <div class="table-responsive">
                                    <table class="table">
                                                                        <%String messtrs= (String)request.getAttribute("messtrs");
if(messtrs!=null){
                                %>
                       
                                <h6 style="margin-top: 20px"><i style="margin-right: 5px" class="fa fa-exclamation-triangle"></i> <%=messtrs%></h6>
                                <%}else{
                                %>
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">First Name</th>
                                                <th scope="col">Last Name</th>
                                                <th scope="col">Address</th>
                                                <th scope="col">Phone</th>
                                                <th scope="col">Status</th>                                                                                                
                                                <th scope="col">Update</th>
                                            </tr>
                                        </thead>

                                        <%
                   
                    while(tRs.next()){
                        if(tRs.getString("address")!=null){
                            address=tRs.getString("address");       
                        }else{
                            address="";
                                            }
                        if(tRs.getString("phone")!=null){
                            phone = tRs.getString("phone");
                        }else{
                            phone ="";
                                            }
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=tRs.getInt("id")%></td>
                                                <td><%=tRs.getString("email")%></td>
                                                <td><%=tRs.getString("first_name")%></td>
                                                <td><%=tRs.getString("last_name")%></td>
                                                <td><%=address%></td>
                                                <td><%=phone%></td>
                                                <td><%
                                                if(tRs.getInt("status")==0){%><a>Unverified</a><%}
                                                if(tRs.getInt("status")==1){%><a>Verified</a><%}
                                                if(tRs.getInt("status")==2){%><a>Ban</a><%}
                                                if(tRs.getInt("status")==3){%><a>Active</a><%}
                                                if(tRs.getInt("status")==4){%><a>Disable</a><%}
                                                %></td>
                                                <td><a href="AdminController?service=updateStatus&id=<%=tRs.getInt("id")%>">Update</a></td>
                                            </tr>
                                        </tbody>
                                        <%
    }
}
                                        %>
                                    </table>
                                </div>

                                    <!-- endpage -->

                            </div>

                        </div>
                        <%
                        }if(lRs!=null){
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Landlord Account</h6>
                                <form class="d-none d-md-flex ms-4_4" action="AdminController" method="post">
                                    <input min="0" max="2000000000" class="form-control bg-dark border-0" type="number" placeholder="Search by id" name="lId">
                                    
                                        <input type="hidden" name="service" value="search">
                                    
                                </form>
                                <div class="table-responsive">
                                    <table class="table">
         <%String messlrs= (String)request.getAttribute("messlrs");
if(messlrs!=null){
                                %>
                                <a><i class="fa fa-exclamation-triangle"></i></a>
                                <h6><%=messlrs%></h6>
                                <%}else{
                                %>
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">First Name</th>
                                                <th scope="col">Last Name</th>
                                                <th scope="col">Address</th>
                                                <th scope="col">Phone</th>
                                                <th scope="col">Civil ID</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Point</th>
                                                <th scope="col">Update</th>
                                                <th scope="col">Add point</th>
                                            </tr>
                                        </thead>
                                        <%
while(lRs.next()){        
                        if(lRs.getString("address")!=null){
                            address= (String)lRs.getString("address");       
                        }else{
                            address="";
                                            }
                        if(lRs.getString("phone")!=null){
                            phone = lRs.getString("phone");
                        }else{
                            phone="";
                                            }
                        if(lRs.getString("civil_id")!=null){
                            civil = lRs.getString("civil_id");           
                                            }else{
                            civil="";
                                            }
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=lRs.getInt("id")%></td>
                                                <td><%=lRs.getString("email")%></td>
                                                <td><%=lRs.getString("first_name")%></td>
                                                <td><%=lRs.getString("last_name")%></td>
                                                <td><%=address%></td>
                                                <td><%=phone%></td>
                                                <td><%=civil%></td>
                                                <td><% if(lRs.getInt("status")==0){%><a>Unverified</a><%}
                                if(lRs.getInt("status")==1){%><a>Verified</a><%}
                                if(lRs.getInt("status")==2){%><a>Ban</a><%}
                                if(lRs.getInt("status")==3){%><a>Active</a><%}
                                if(lRs.getInt("status")==4){%><a>Disable</a><%}
                                %></td>
                                                <td><%=lRs.getInt("account_points")%></td>
                                                <td><a href="AdminController?service=updateStatus&id=<%=lRs.getInt("id")%>">Update</a></td>
                                                <td><a href="AdminController?service=addPoint&id=<%=lRs.getInt("id")%>">Add</a></td>
                                            </tr>
                                        </tbody>
                                        <%
    }
}
                                        %>
                                    </table>
                                </div>

                            </div>
                        </div>
                        <%
                            }if(aRs!=null){
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Admin Account</h6>
                                <form class="d-none d-md-flex ms-4_4" action="AdminController" method="post">
                                    <input min="0" max="2000000000" class="form-control bg-dark border-0" type="number" placeholder="Search by id" name="aId">
                                    
                                        <input type="hidden" name="service" value="search">
                                    
                                </form>
                                <div class="table-responsive">
                                    <table class="table">
         <%String messars= (String)request.getAttribute("messars");
if(messars!=null){
                                %>
                                <a><i class="fa fa-exclamation-triangle"></i></a>
                                <h6><%=messars%></h6>
                                <%}else{
                                %>                                        
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">First Name</th>
                                                <th scope="col">Last Name</th>
                                                <th scope="col">Status</th>
                                                
                                            </tr>
                                        </thead>
                                        <%
while(aRs.next()){              
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=aRs.getInt("id")%></td>
                                                <td><%=aRs.getString("email")%></td>
                                                <td><%=aRs.getString("first_name")%></td>
                                                <td><%=aRs.getString("last_name")%></td>
                                                <td><% if(aRs.getInt("status")==0){%><a>Unverified</a><%}
                                if(aRs.getInt("status")==1){%><a>Verified</a><%}
                                if(aRs.getInt("status")==2){%><a>Ban</a><%}
                                if(aRs.getInt("status")==3){%><a>Active</a><%}
                                if(aRs.getInt("status")==4){%><a>Disable</a><%}
                                %></td>
                                               
                                            </tr>
                                        </tbody>
                                        <%
    }
}
                                        %>
                                    </table>
                                </div>
                                    <div>
                                        <a href="AdminController?service=addAccount" type="button" class="btn btn-primary m-2">Add account</a>
                            </div>
                            </div>      
                        </div>
                        <%
                            } if (addPointTransactions!=null) {
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Add Point to Landlord Transactions</h6>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Admin ID</th>
                                                <th scope="col">Landlord ID</th>
                                                <th scope="col">Landlord Name</th>
                                                <th scope="col">Number of Point</th>
                                                <th scope="col">Date</th>
                                            </tr>
                                        </thead>
                                        <%
for(int i = 0; i < addPointTransactions.size(); i++){
    int receiverID = addPointTransactions.get(i).getReceiverID();
    DAOLandlord daoll = new DAOLandlord();
    Landlord ll = daoll.findById(receiverID);
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=addPointTransactions.get(i).getId()%></td>
                                                <td><%=addPointTransactions.get(i).getPayerID()%></td>
                                                <td><%=receiverID%></td>
                                                <td><%=ll.getFirstName() + ll.getLastName()%></td>
                                                <td><%=addPointTransactions.get(i).getAmount()%></td>
                                                <td><%=addPointTransactions.get(i).getDate()%></td>
                                            </tr>
                                        </tbody>
                                        <%
    }
                                        %>
                                    </table>
                                </div>
                            </div>
                                    
                        </div>
                        <%
                            } if (payForPostTransactions!=null) {
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Pay for Post Transactions</h6>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Landlord ID</th>
                                                <th scope="col">Landlord Name</th>
                                                <th scope="col">Post ID</th>
                                                <th scope="col">Amount</th>
                                                <th scope="col">Date</th>
                                            </tr>
                                        </thead>
                                        <%
for(int i = 0; i < payForPostTransactions.size(); i++){  
    int payerID = payForPostTransactions.get(i).getPayerID();
    DAOLandlord daoll = new DAOLandlord();
    Landlord ll = daoll.findById(payerID);
                                        %>
                                        <tbody>
                                            <tr>
                                                <td><%=payForPostTransactions.get(i).getId()%></td>
                                                <td><%=payerID%></td>
                                                <td><%=ll.getFirstName() + ll.getLastName()%></td>
                                                <td><%=payForPostTransactions.get(i).getPostID()%></td>
                                                <td><%=payForPostTransactions.get(i).getAmount()%></td>
                                                <td><%=payForPostTransactions.get(i).getDate()%></td>
                                            </tr>
                                        </tbody>
                                        <%
    }
                                        %>
                                    </table>
                                </div>
                            </div>
                                    
                        </div>
                        <%
                            } if (allPropertyList!=null) {
                        %>
                        <div class="col-12">
                            <div class="bg-secondary rounded h-100 p-4">
                                <h6 class="mb-4">Manage Post</h6>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">ID</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Type</th>
                                                <th scope="col">Landlord ID</th>
                                                <th scope="col">Landlord</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Promotion ID</th>
                                                <th scope="col">Start Date</th>
                                                <th scope="col">End Date</th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        <c:forEach var="o" items="${allPropertyList}">
                                            <tr>
                                                <td>${o.id}</td>
                                                <td>${o.price}</td>
                                                <td>${o.getPropertyTypeName()}</td>
                                                <td>${o.landlordID}</td>
                                                <td>${o.getLandlordName()}</td>
                                                <c:choose>
                                                    <c:when test="${o.status==1}">
                                                        <td><a style="color: green" href="./UpdatePostStatus?status=2&id=${o.id}" onclick="return confirm('Are you sure to disable this post?')">Enable</a></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <!--<td><a style="color: red" href="./UpdatePostStatus?status=1&id=${o.id}">Disable</a></td>-->
                                                        <td><a style="color: red">Disable</a></td>
                                                    </c:otherwise> 
                                                </c:choose> 
                                                <c:if test="${o.promotionID != 0}">
                                                    <td>${o.promotionID}</td>
                                                </c:if>
                                                <c:if test="${o.promotionID==0}">
                                                    <td></td>
                                                </c:if>
                                                <td>${o.startDate}</td>
                                                <td>${o.endDate}</td>
                                            <tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                                    
                        </div>
                        <%
                            } 
                        %>
                    </div>

                </div>
                <!-- Table End -->

                <!-- The style in above is style="text-align: right" -->

                <!-- Footer Start -->
                <div class="container-fluid pt-4 px-4">
                    <div class="bg-secondary rounded-top p-4">
                        <div class="row">
                            <div class="col-12 col-sm-6 text-center text-sm-start">
                                &copy; <a>House rental</a>, All Right Reserved. 
                            </div>
                            <div class="col-12 col-sm-6 text-center text-sm-end">
                                <!--/*** This template is free as long as you keep the footer author’s credit link/attribution link/backlink. If you'd like to use the template without the footer author’s credit link/attribution link/backlink, you can purchase the Credit Removal License from "https://htmlcodex.com/credit-removal". Thank you for your support. ***/-->
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Footer End -->
            </div>
            <!-- Content End -->


            <!-- Back to Top -->
            
        </div>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/chart/chart.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/tempusdominus/js/moment.min.js"></script>
        <script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
        <script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>
