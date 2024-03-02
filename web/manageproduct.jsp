<%-- 
    Document   : manageproduct
    Created on : Nov 25, 2023, 6:52:28 PM
    Author     : THevY
--%>

<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="css/manager.css" rel="stylesheet" type="text/css"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <style>
            .p{
                width: 150px;
                height: 150px;
            }
        </style>
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
        if(obj!=null) {user = (User)obj;}
        if(user==null){
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
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title " >
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6 " style="display: flex; justify-content: center">
                            <a data-bs-target="#addEmployeeModal"  class="btn btn-success " data-bs-toggle="modal" ><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
<!--                            <a href="delete?pid=${p.productId}" class="btn btn-danger " data-bs-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						-->
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover" id="p">
                    <thead>
                        <tr>
<!--                            <th>
                                <span class="custom-checkbox">
                                    <input  id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>-->
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    
                        <c:forEach items="${requestScope.product}" var="p">
                            <tr>
<!--                                <td>
                                    <span class="custom-checkbox">
                                        <input id="checkbox1" name="options[]" >
                                        <label </label>
                                    </span>
                                </td>-->
                                <td>${p.productId}</td>
                                <td>${p.productName}</td>
                                <td>
                                    <img class="p" src="${p.image}">
                                </td>
                                <td>${p.price} VND</td>
                                <td>
                                    <a href="./loadproduct?pid=${p.productId}"  class="edit" data-bs-toggle="modal"><i class="material-icons" data-bs-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="./delete?pid=${p.productId}" class="delete" data-bs-toggle="modal"><i class="material-icons" data-bs-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    
                    </tbody>
                </table>
                <div style="display: flex;justify-content: center">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <c:forEach  begin="1" end="${endP}" var="p">
                        <li class="page-item ${tagg==p?"active":""}"><a class="page-link" href="?index=${p}">${p}</a></li>
                        </c:forEach>

                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </nav>

        </div>
            <a href="./"><button type="button" class="btn btn-primary">Back to home</button></a>

        </div>
        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add" method="">
                        <div class="modal-header">						
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Book Id</label>
                                <input name="id" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input type="text" name="quantity" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${requestScope.category}" var="o">
                                        <option value="${o.categoryId}">${o.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div> //
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
        
        
    <script src="js/manager.js" type="text/javascript"></script>
</body>
</html>
