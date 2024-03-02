<%-- 
    Document   : orderDetails
    Created on : Aug 16, 2023, 8:22:26 PM
    Author     : Raiku
--%>

<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="en">
    <head>

        <link href="img/favicon.icon" rel="icon">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
  
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your order detail</title>
        <style type="text/css">
            #order-detail{
                background:#eee;
                display:flex;
                align-items:center;
                justify-content:center;
                flex-direction:column;
                gap: 1rem;
            }
            tr td:first-child {
                text-align: left;
            }
            tr td:last-child {
                text-align: right;
            }
            .card {
                box-shadow: 0 20px 27px 0 rgb(0 0 0 / 5%);
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid rgba(0,0,0,.125);
                border-radius: 1rem;
            }
            .item-name{
                display: flex;
                justify-content:center;
                flex-direction: column;
            }

            .text-reset {
                --bs-text-opacity: 1;
                color: inherit!important;
            }
            a {
                color: #5465ff;
                text-decoration: none;
            }
        </style>
        <link href="css/styleOrders.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="./">
      <img src="img/logo/ATTV.png" alt=""  height="70">
    
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
<!--    <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
      <div class="d-flex">
        <form action="search" method="get"  role="search" style="position: relative;display: flex; justify-content: center;float: right;">
            
            <input  class="form-control me-2"  type="search" name="search" value="${search}" placeholder="Search" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
            
              
        
        
         
        <%
        
        Object obj = session.getAttribute("user");
        User user = null;
        if(obj!=null) {user = (User)obj;}
        if(user==null){
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
                    <li><a class="dropdown-item" href="#">Manage Account</a></li>
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

        <div id="order-detail" class="container-fluid">
            <div class="container">
                <c:set var="order" value="${sessionScope.order}"/>
                <c:set var="user" value="${sessionScope.user}"/>
                <div class="d-flex justify-content-between align-items-center py-3">
                    <h2 class="h5 mb-0"><a href="#" class="text-muted"></a> Order #${order.getOrderId()}</h2>
                </div>

                <div class="row">
                    <div class="col-lg-12">

                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="mb-3 d-flex justify-content-between">
                                    <div>

                                        <span class="me-3">#${order.getOrderId()}</span>
                                        <span class="me-3">Payment Method </span>
                                        <span class="badge rounded-pill bg-info" style="color: white;">COD</span>
                                    </div>
                                    <div class="d-flex">
                                        <i class="bi bi-download"></i> <span class="text">Invoice</span>
                                        <div class="dropdown">
                                            <button class="btn btn-link p-0 text-muted" type="button" data-bs-toggle="dropdown">
                                                <i class="bi bi-three-dots-vertical"></i>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-end">
                                                <li><a class="dropdown-item" href="#"><i class="bi bi-pencil"></i> Edit</a></li>
                                                <li><a class="dropdown-item" href="#"><i class="bi bi-printer"></i> Print</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-borderless">
                                    <tbody>
                                        <c:set var="total" value="${0}"/>
                                        <c:forEach items="${sessionScope.orderdetail}" var="item">
                                            <tr>
                                                <td>
                                                    <div class="d-flex mb-2">
                                                        <div class="item-name flex-shrink-0" style="margin-right: 1rem;">
                                                            <img src="${item.getProduct().getImage()}" alt width="35" class="img-fluid">
                                                        </div>
                                                        <div class="item-name flex-lg-grow-1 ms-3">
                                                            <h6 class="small mb-0"><a href="detail?pid=${item.getProduct().getProductId()}" class="text-reset">${item.getProduct().getProductName()}</a></h6>
                                                        </div>
                                                    </div>  
                                                </td>
                                                <td  class="item-name">${item.getQuantity()}</td>
                                                <td class="text-end">${item.getTotalPayment()}</td>

                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="2">Subtotal</td>
                                            <td class="text-end">${order.getMoneyRemain()}VND</td>
                                        </tr>


                                        <tr class="fw-bold">
                                            <td colspan="2">TOTAL</td>
                                            <td class="text-end">${order.getMoneyRemain()}VND   </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>

                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <h3 class="h6">Payment Method</h3>
                                        <p>COD <br>
                                            Total: ${order.getMoneyRemain()}VND  <span class="badge bg-success rounded-pill" id="statusOrder" style="color: white;"> ${order.getStatus()}</span></p>
                                    </div>
                                    <div class="col-lg-4">
                                        <h3 class="h6">Bill</h3>
                                        <address>
                                            <strong>${user.getName()}</strong><br>
                                            <p class="text-reset">${user.getAddress()} </p><br>
                                            ${user.getEmail()}<br>
                                            <abbr title="Phone">Phone:</abbr> ${user.getTelNum()}
                                        </address>
                                    </div>
                                    <div class="col-lg-4">
                                        <h3 class="h6">Shipping Information</h3>
                                        <address>
                                            <strong>Vietttel Post</strong><br>
                                            ${user.getAddressBuying()}<br>

                                            <abbr title="Phone">Phone:</abbr> ${user.getTelNum()}
                                        </address>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
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

        <script src="js/updateOrderDetail.js"></script>
    </body>
</html>