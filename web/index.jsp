<%-- 
    Document   : index
    Created on : Nov 20, 2023, 10:58:42 PM
    Author     : THevY
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book Store</title>
        <link href="img/favicon.ico" rel="icon">
        

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body{
                overflow-x: hidden;
            }
           
            
        </style>
        <link href="css/style.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        
        <!-- NaV -->
        <div style="width: 100%">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid row">
                    <div class="col-3">
                        <a class="navbar-brand" href="./">
                            <img src="img/logo/ATTV.png" alt=""  height="70">

                        </a>
                    </div>
                    <!--                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                            <span class="navbar-toggler-icon"></span>
                                        </button>-->
                    <!--    <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
                    <div class="d-flex col-6">
                        <form action="search" method="get"  role="search" style="position: relative;display: flex; justify-content: center; float: right;">

                            <input  class="form-control me-2"  type="search" name="search" value="${search}" placeholder="Search" aria-label="Search">
                            <button class="btn btn-outline-success" type="submit">Search</button>










                        </form>            
                    </div>
                    <div class="d-flex flex-row-reverse col-3" >
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
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0  bg-infor ">
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
                    </div>
                </div>
            </nav>
            <!-- End Nav-->
            <!--Page content-->
            <div class="container">
                <div class="row">
                    <!--Menu Left-->
                    <div class="col-lg-3 col-md-12 col-sm-12  ">
                        <div class="list-group">
                            <div class=" btn btn-secondary text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block">
                                <c:forEach items="${requestScope.category}" var="o">
                                    <a style=" color: white; text-decoration: none " href="category?cid=${o.categoryId}">    <li class="list-group-item text-black ${tag == o.categoryId ? "active" : ""}">${o.categoryName}</li></a>
                                    </c:forEach>

                            </ul>
                        </div>
                    </div>
                    <!--Menu Left-->

                    <!--SLider and Products-->
                    <!--SLider -->
                    <div class="col-lg-9 col-md-12  bg-warning">
                        <div id="carouselExampleIndicators" class="carousel slide">
                            <div class="carousel-indicators">
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                            </div>
                            <div class="carousel-inner" style="height: 320px" > //320 //150
                                <div class="carousel-item active" >
                                    <img src="img/slider/ATTV BOOKSTORE (3).png" class="d-block w-100"  alt="...">
                                </div>
                                <div class="carousel-item" >
                                    <img src="img/slider/ATTV BOOKSTORE (4).png" class="d-block w-100"  alt="...">
                                </div>
                                <div class="carousel-item" >
                                    <img src="img/slider/ATTV BOOKSTORE (5).png" class="d-block w-100"  alt="...">
                                </div>
                            </div>

                        </div>
                    </div>
                    <!--End SLider-->

                    <!--Products-->


                    <!--Products-->

<!--                    <div class="col-lg-3 ">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a style="text-decoration: none" href="./">Home</a></li>
                                <li class="breadcrumb-item"><a style="text-decoration: none" href="#">Category</a></li>
                                <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                            </ol>
                        </nav>
                    </div>-->





                </div>
                <!--End SLider and Products-->
            </div>

            <div class="row mt-4">
                <c:forEach items="${requestScope.product}" var="p">




                    <!--                <div class="col-sm-9">-->
                    <!--                    <div class="row">-->

                    <div class="col-lg-3 col-md-6 mb-4" height="5000px" width="350px" >
                        <div class="card">
                            <img class="card-img-top" src="${p.image}" alt="Card image cap" height="350px">
                            <div class="card-body">
                                <h4 class="card-title show_txt"><a href="detail?pid=${p.productId}" title="View Product">${p.productName}</a></h4>
                                <p class="card-text show_txt">${p.descripe}</p>
                                <div class="row">
                                    <div class="col">
                                        <p class="btn btn-danger btn-block">${p.price} VNĐ</p>
                                    </div>
                                    <div class="col">
                                        <a onclick="addToCart(${p.productId})" class="btn btn-success btn-block">Add to cart</a>
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>

                    <!--</div>-->

                    <!--</div>-->

                </c:forEach>


            </div>
            <div style="display: flex;justify-content: center">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <!--                        <li class="page-item">
                                                    <a class="page-link" href="#" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                        <span class="sr-only">Previous</span>
                                                    </a>
                                                </li>-->
                        <c:forEach  begin="1" end="${endP}" var="p">
                            <li class="page-item ${tagg==p?"active":""}" ><a class="page-link" href="?index=${p}">${p}</a></li>
                            </c:forEach>

                        <!--                        <li class="page-item">
                                                    <a class="page-link" href="#" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                        <span class="sr-only">Next</span>
                                                    </a>
                                                </li>-->
                    </ul>
                </nav>

            </div>
            <!-- Footer -->
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
            <div class="modal fade zoom formModal" id="successModal" style="z-index: 1500;" tabindex="0" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content card">
                        <div class="modal-header alert-success">
                            <h5 class="m-0">Success</h5>
                        </div>
                        <div class="modal-body messageModal" id="addCartStatus">
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
            <!-- End footer -->
            <!-- End footer -->
            <script src="js/AddToCart.js"></script>
        </div>
    </body>
</html>
