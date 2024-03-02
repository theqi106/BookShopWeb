<%-- 
    Document   : cart
    Created on : Nov 28, 2023, 7:43:49 AM
    Author     : THevY
--%>

<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Store</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"></head>
    <style>.center{
            width: 150px;
            margin: 40px auto;

        }</style>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="./">
                    <img src="img/logo/ATTV.png" alt=""  height="70">

                </a>
                
                <!--    <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
                <div class="d-flex">
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
                                    <li><a class="dropdown-item" href="#">Cart</a></li>
                                        <c:if test="${sessionScope.user.isAdmin==0}">
                                        <li><a class="dropdown-item" href="changeinformation.jsp">Change Information</a></li>
                                        <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
                                        <li><a class="dropdown-item" href="order">Orders History</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.user.isAdmin==1}">
                                        <li><a class="dropdown-item" href="#">Manage Account</a></li>
                                        <li><a class="dropdown-item" href="manage">Manage Product</a></li>
                                        
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
        <form action="checkout">         
            <div class="shopping-cart">
                <div class="px-4 px-lg-0">

                    <div class="pb-5">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                    <!-- Shopping cart table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="p-2 px-3 text-uppercase">Sản Phẩm</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Đơn Giá</div>
                                                    </th>
                                                    
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Số Lượng</div>
                                                    </th>
                                                    
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Xóa</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                                <c:set var="cartTotal" value="0" scope="session"/>
                                                <c:forEach items="${sessionScope.cartItem}" var="c">
                                                    <tr id="Product${c.productId.productId}">
                                                        <th scope="row">
                                                            <div class="p-2">
                                                                <img src="${c.productId.image}" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                                <div class="ml-3 d-inline-block align-middle">
                                                                    <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${c.productId.productName}</a></h5><span class="text-muted font-wei${c.productId.price}ght-normal font-italic"></span>
                                                                </div>
                                                            </div>
                                                        </th>

                                                        <c:set var="cartTotal" value="${cartTotal + c.productId.price * c.quantity }"/>
                                                        <td class="align-middle"><strong name="price" id="price${c.productId.productId}">${c.productId.price}</strong></td>
                                            
                                                        <td class="align-items-center " >
                                                            <div class="center">
                                                                
                                                                <div class="input-group">
                                                                    <span class="input-group-btn">
                                                                        <button  type="button" onclick="cal(${c.productId.productId},${c.cartItemId},'minus')" name="calminus${c.productId.productId}" class="btn btn-danger btn-number"  data-type="minus" data-field="quantity">
                                                                            <span class="glyphicon glyphicon-minus">-</span>
                                                                        </button>
                                                                    </span>
                                                                    <input type="text"  class="form-control input-number" name="quantity" id="${c.productId.productId}" value="${c.quantity}" min="1" max="100">
                                                                    <span class="input-group-btn">
                                                                        <button type="button" name="caladd${c.productId.productId}" onclick="cal(${c.productId.productId},${c.cartItemId},'add')" class="btn btn-success btn-number" data-type="plus" data-field="quantity">
                                                                            <span class="glyphicon glyphicon-plus">+</span>
                                                                        </button>
                                                                    </span>
                                                                </div>
                                                            </div>

                                                        </td>
                                            
                                                        <td class="align-middle"><a  onclick="deleteCartItem(${c.productId.productId})"  class="text-dark">
                                                                <button type="button" class="btn btn-danger">Delete</button>
                                                            </a>
                                                        </td>
                                                    </tr> 
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- End -->
                                </div>
                            </div>
                            <div class="row py-5 p-4 bg-white rounded shadow-sm">

                                <div class="col-lg-6">
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Thành tiền</div>

                                    <div class="p-4">
                                        <ul class="list-unstyled mb-4">
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Tổng tiền hàng</strong><strong id="totali">${cartTotal}</strong></li>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Phí vận chuyển</strong><strong>Free ship</strong></li>
                                                <c:set var="cartTotal" value="${cartTotal*10/100 }"/>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong  class="text-muted">VAT</strong><strong id="vat">${cartTotal}</strong></li>
                                                <c:set var="cartTotal" value="${cartTotal*11 }"/>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong  class="text-muted">Tổng thanh toán</strong>
                                                <h5 id="tota" class="font-weight-bold">${cartTotal}</h5>
                                            </li>
                                            <input name="total" value="${cartTotal}" type="hidden">
                                        </ul><button class="btn btn-dark rounded-pill py-2 btn-block">Mua hàng</button>
                                    </div>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
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


            <div class="modal fade zoom formModal" id="successModal" style="z-index: 1500;" tabindex="0" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content card">
                        <div class="modal-header alert-success">
                            <h5 class="m-0">Success</h5>
                        </div>
                        <div class="modal-body messageModal" id="deleteCartStatus">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade zoom formModal" style="z-index: 1500;" id="errorModal" tabindex="0" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content card">
                        <div class="modal-header alert-danger">
                            <h5 class="m-0">Error</h5>
                        </div>
                        <div class="modal-body messageModal" id="errorMessage">
                        </div>
                    </div>
                </div>
            </div>     
        </form>

        <script src="js/DeleteCartItem.js"></script>    
        <script src="js/minus-plus-button.js"></script>

    </body>

</html>