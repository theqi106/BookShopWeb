<%-- 
    Document   : detail
    Created on : Nov 23, 2023, 10:18:32 PM
    Author     : THevY
--%>

<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>

        <meta charset="UTF-8">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!--         <link href="css/style.css" rel="stylesheet" type="text/css"/>-->
        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
            
    </head>
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
                                    <li><a class="dropdown-item" href="showcart">Cart</a></li>
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
        <div class="row mt-4">
            <div class="col-sm-3">
                <div class="card bg-light mb-3">
                    <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                    <ul class="list-group category_block">
                        <c:forEach items="${requestScope.category}" var="o">
                            <li class="list-group-item text-white ${tag == o.categoryId ? "active" : ""}"><a style=" color: black; text-decoration: none " href="category?cid=${o.categoryId}">${o.categoryName}</a></li>
                            </c:forEach>
                    </ul>
                </div>

            </div>
            <div class="col-sm-9 mt-4 mb-4">
                <div class="container">
                    <div class="card">
                        <div class="row">
                            <aside class="col-sm-5 border-right">
                                <article class="gallery-wrap"> 
                                    <div class="img-big-wrap">
                                        <div> <a href="#"><img src="${detail.image}"></a></div>
                                    </div> <!-- slider-product.// -->

                                </article> <!-- gallery-wrap .end// -->
                            </aside>
                            <aside class="col-sm-7">
                                <article class="card-body p-5">
                                    <h3 class="title mb-3">${detail.productName}</h3>

                                    <p class="price-detail-wrap"> 
                                        <span class="price h3 text-warning"> 
                                            <span class="currency"></span><span class="num">${detail.price}VNĐ</span>
                                        </span> 
                                        <!--<span>/per kg</span>--> 
                                    </p> <!-- price-detail-wrap .// -->
                                    <dl class="item-property">
                                        <dt>Description</dt>
                                        <dd><p>${detail.descripe} </p></dd>
                                    </dl>
                                    <!--                                        <dl class="param param-feature">
                                                                                <dt>Model#</dt>
                                                                                <dd>12345611</dd>
                                                                            </dl>   item-property-hor .// 
                                                                            <dl class="param param-feature">
                                                                                <dt>Color</dt>
                                                                                <dd>Black and white</dd>
                                                                            </dl>   item-property-hor .// 
                                                                            <dl class="param param-feature">
                                                                                <dt>Delivery</dt>
                                                                                <dd>Russia, USA, and Europe</dd>
                                                                            </dl>   item-property-hor .// -->

                                    <hr>
<!--                                    <div class="row">
                                        <div class="col-sm-5">
                                            <dl class="param param-inline">
                                                <dt>Quantity: </dt>
                                                <dd>
                                                    <select class="form-control form-control-sm" style="width:70px;">
                                                        <c:forEach begin="1" end="${detail.quantity}" var="q">
                                                            <option>${q}</option>
                                                        </c:forEach>
                                                    </select>
                                                </dd>
                                            </dl>   item-property .// 
                                        </div>  col.// 

                                    </div>  row.// 
                                    <hr>-->
<!--                                    <a href="#" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>-->
                                    <a onclick="addToCart(${detail.productId})" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                                </article> <!-- card-body.// -->
                            </aside> <!-- col.// -->
                        </div> <!-- row.// -->
                    </div> <!-- card.// -->


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
    <script src="js/AddToCart.js"></script>
</body>
</html>
