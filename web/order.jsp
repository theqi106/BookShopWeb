<%-- 
    Document   : order
    Created on : Dec 6, 2023, 12:59:00 PM
    Author     : THevY
--%>

<%@page import="model.User"%>
<%-- 
    Document   : orderManager
    Created on : Aug 17, 2023, 2:20:43 AM
    Author     : Raiku
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <link href="img/favicon.ico" rel="icon">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Manager</title>

        <link href="css/styleOrders.css" rel="stylesheet">
    </head>
    <body>
        <div class="row w-100">
        
            <div class="col-12">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="./">
                            <img src="img/logo/ATTV.png" alt=""  height="70">

                        </a>
                        
                        <!--    <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
                        <div class="d-flex " >
                            <form action="search" method="get"  role="search" style="position: relative;display: flex; justify-content: center;float: right;">

                                <input  class="form-control me-2"  type="search" name="search" value="${search}" placeholder="Search" aria-label="Search">
                                <button class="btn btn-outline-success" type="submit">Search</button>





                                <%

                                    Object obj = session.getAttribute("user");
                                    User user = null;
                                    if (obj != null) {
                                        user = (User) obj;
                                    }
                                    if (user == null) {
                                %>
                                <a class="btn btn-primary "  href="login.jsp" style="white-space: nowrap;">
                                    Login
                                </a>  
                                <%
                                } else {
                                %>
                                <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
                                    <li class="nav-item dropdown dropstart"><a
                                            class="nav-link dropdown-toggle" href="#" role="button"
                                            data-bs-toggle="dropdown" aria-expanded="false"> Hello <b><%=user.getUsername()%></b></a>
                                        <ul class="dropdown-menu">
                                            
                                                <c:if test="${sessionScope.user.isAdmin==0}">
                                                    <li><a class="dropdown-item" href="showcart">Cart</a></li>
                                                <li><a class="dropdown-item" href="changeinformation.jsp">Change Information</a></li>
                                                <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
                                                <li><a class="dropdown-item" href="order">Orders History</a></li>
                                                </c:if>
                                                <c:if test="${sessionScope.user.isAdmin==1}">
                                                <li><a class="dropdown-item" href="manageaccount">Manage Account</a></li>
                                                <li><a class="dropdown-item" href="manage">Manage Product</a></li>
                                                <li><a class="dropdown-item" href="manageorder">Manage Orders</a></li>

                                            </c:if>

                                            <li><hr class="dropdown-divider"></li>
                                            <li><a class="dropdown-item" href="logout">Logout</a></li>
                                        </ul></li>
                                </ul>	
                        <!--       <button type="button" class="btn btn-outline-primary ">Hello <b><%=user.getUsername()%></b></button> 
                                <a class="btn btn-primary ml-3"  href="logout" >
                                    Logout
                                </a> 
                                 <a class="btn btn-success btn-sm ml-3" href="show">
                                                    <i class="fa fa-shopping-cart"></i> Cart
                                                    <span class="badge badge-light">3</span>
                                                </a>-->
                                <%}%>




                            </form>            
                            <!--</div>--></div>
                    </div>
                </nav>
            
        </div>
                                </div>

        <div id="status-error"></div>
        <div class="row">
        <div class="container col-sm-9 col-lg-9 w-100" style="display: flex; justify-content: center">
            <form action="OrderDetailController">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Customer ID</th>
                            <th>Address</th>
                            <th>Address Receive</th>
                            <th>Status</th>
                            <th>Total Amount</th>
                            <th>Detail</th>
                                <c:if test="${sessionScope.user.isAdmin==1}">
                                <th>Action</th>
                                <th></th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody id="table-body">

                        <c:forEach items="${sessionScope.orderList}" var="item">
                            <tr>

                                <td>${item.getOrderId()}</td>
                                <td>${item.getUserId().getUserId()}</td>
                                <td>${item.getAddress()}</td>
                                <td>${item.getAddressReiceive()}</td>
                                <td>${item.getStatus()}</td>
                                <td>${item.getMoneyRemain()}</td>
                                <td><button name="orderId" id="orderId" value="${item.getOrderId()}">View Detail</button></td>
                                <c:if test="${sessionScope.user.isAdmin==1}">
                                    <td><button ><a href="deleteorder?orderid=${item.getOrderId()}">Delete</a></button></td>
                                    <td><button ><a href="loadstatus?orderid=${item.getOrderId()}&status=${item.getStatus()}">Edit</a></button></td>
                                </c:if>
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>
            </form>
        </div>
            </div>

        <!--        <div class="col-12 pb-1">
                    <nav aria-label="Page navigation">
                        <ul id="all-pages" class="pagination justify-content-center mb-3">
                            <li class="page-item disabled">
                                <button class="page-link" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </button>
                            </li>
        <c:forEach begin="1" end="${totalOrders}" var="o">
            <li class="page-item">
                <button onclick="changePage(${o})" class="page-link active">${o}</button>
            </li>
        </c:forEach>
        <li class="page-item">
            <button class="page-link" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
            </button>
        </li>
    </ul>
</nav>
</div>-->

        <script src="js/orderManager.js"></script>


        <!-- Footer -->
        <footer class="py-3 my-4">
            <ul class="nav justify-content-center border-bottom pb-3 mb-3">
                <li class="nav-item"><a href="#"
                                        class="nav-link px-2 text-muted">Home</a></li>
                <li class="nav-item"><a href="#"
                                        class="nav-link px-2 text-muted">Policy</a></li>
                <li class="nav-item"><a href="#"
                                        class="nav-link px-2 text-muted">Privacy</a></li>
                <li class="nav-item"><a href="#"
                                        class="nav-link px-2 text-muted">Contact us</a></li>
                <li class="nav-item"><a href="#"
                                        class="nav-link px-2 text-muted">Blogs</a></li>
            </ul>
            <p class="text-center text-muted">© 2023 ATTV.vn, Inc</p>
        </footer>
        <!-- End footer -->

    </body>
</html>
