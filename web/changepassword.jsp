<%-- 
    Document   : changepassword
    Created on : Nov 28, 2023, 8:29:24 PM
    Author     : THevY
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
            integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
        crossorigin="anonymous"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">

    </head>
    <body>
        <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="./">
                    <img src="img/logo/ATTV.png" alt=""  height="70">

                </a>
                <!--    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                      <span class="navbar-toggler-icon"></span>
                    </button>-->
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
            if (user == null) {%>
                        <h1>You haven't already login. Please comeback to Home and login!!</h1>


                        <% } else {
                            String error = request.getAttribute("error") + "";
                            if (error.equals("null")) {
                                error = "";
                            }
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





                    </form>            
                    <!--</div>--></div>
                         	
            </div>
        </nav>
        <div class="container">
            <h1>Change Password</h1>
            <span style="color: red">
                <%=error%>
            </span>

            <form action="changepassword" method="get">
                <div class="mb-3">
                    <label for="currentpassword" class="form-label">Current Password</label>
                    <input type="password" class="form-control" id="currentpassword" name="currentpassword">
                </div>
                <div class="mb-3">
                    <label for="newpassword" class="form-label">New Password</label>
                    <input type="password" class="form-control" id="newpassword" name="newpassword">
                </div>
                <div class="mb-3">
                    <label for="retype" class="form-label">Re-type New Password</label>
                    <input type="password" class="form-control" id="retype" name="retype">
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
            <%
                if (error.equals("Password already change!!")) {
            %>
            <a href="login.jsp"><button type="button" class="btn btn-primary mt-5">Back to login</button></a>
            <%}%>
        </div>
        <%}%>
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
        </div>
        <!-- End footer -->
    </body>
</html>
