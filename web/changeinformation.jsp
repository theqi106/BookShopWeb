<%-- 
    Document   : changeinformation
    Created on : Nov 29, 2023, 11:57:52 AM
    Author     : THevY
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Information</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <style>
            .red{

                color: red;
            }

        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid row">
                <div class="col-3">
                <a class="navbar-brand" href="./">
                    <img src="img/logo/ATTV.png" alt=""  height="70">

                </a>
                </div>
                
                <!--    <div class="collapse navbar-collapse" id="navbarSupportedContent">-->
                <div class="d-flex col-9">
                    <div class="col-9">
                    <form action="search" method="get"  role="search" style="position: relative;display: flex; justify-content: center;float: right;">

                        <input  class="form-control me-2"  type="search" name="search" value="${search}" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                        </form>  
                        </div>
                        <%

                            Object obj = session.getAttribute("user");
                            User user = null;
                            if (obj != null) {
                                user = (User) obj;
                            }
                            if (user == null) {%>
                        <h1>You haven't already login. Please comeback to Home and login!!</h1>


                        <% } else {

                        %>
                        <div class="container col-3">
                            <%            String error = (request.getAttribute("error") + "").equals("null") ? "" : (request.getAttribute("error") + "");

                                String name = user.getName();

                                String gender = user.getGender();

                                String dob = user.getBirth().toString();

                                String addressUser = user.getAddress();

                                String addressBuy = user.getAddressBuying();

                                String addressReceive = user.getAddressReiceive();

                                String telNum = user.getTelNum();

                                String email = user.getEmail();


                            %>
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0 bg-infor ">
                                <li class="nav-item dropdown dropstart"><a
                                        class="nav-link dropdown-toggle" href="#" role="button"
                                        data-bs-toggle="dropdown" aria-expanded="false"> Hello <b><%=user.getUsername()%></b></a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="showcart">Cart</a></li>
                                        <li><a class="dropdown-item" href="changeinformation.jsp">Change Information</a></li>
                                        <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
                                        <li><a class="dropdown-item" href="order">Orders History</a></li>

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




                        </div>
                              

                </div>
            </div>
        </nav>

        <div class="container">
            <div class="text-center"><h1>Change Infomation</h1></div>

            <form action="./changeinformation" method="">
                <div class="row">
                    <div class="col-md-6"> 


                        <h3>User Information</h3>
                        <!--Name-->
                        <div class="mb-3">
                            <label for="name" class="form-label">Full name</label>
                            <input type="text" class="form-control" id="name" name="name" value="<%=name%>">
                        </div>
                        <!--Gender-->
                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender</label>
                            <select class="form-control" id="gender" name="gender" >
                                <option></option>
                                <option value="male" <%=(gender.equals("male")) ? "selected='selected'" : ""%>>Male</option>
                                <option value="female" <%=(gender.equals("female")) ? "selected='selected'" : ""%>>Female</option>
                            </select>
                        </div>
                        <!--Birth-->
                        <div class="mb-3">
                            <label for="dob" class="form-label">Birthday</label>
                            <input type="date" class="form-control" id="dob" name="dob" value="<%=dob%>">
                        </div>

                    </div> 
                    <!--ADDRESS-->
                    <div class="col-md-6"> 
                        <h3>Address</h3>
                        <div class="mb-3">
                            <label for="addressuser" class="form-label">Address User</label>
                            <input type="text" class="form-control" id="addressuser" name="addressuser" value="<%=addressUser%>">
                        </div>
                        <div class="mb-3">
                            <label for="addressbuy" class="form-label">Address Buying</label>
                            <input type="text" class="form-control" id="addressbuy" name="addressbuy" value="<%=addressBuy%>">
                        </div>

                        <div class="mb-3">
                            <label for="telnum" class="form-label">Phone Number</label>
                            <input type="tel" class="form-control" id="telnum" name="telnum" value="<%=telNum%>">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="<%=email%>" >
                        </div>

                        <hr/>


                        <input class="btn btn-primary form-control" type="submit" value="Save" name="submit" id="submit" >
                    </div>
                </div>
            </form> 

        </div>
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
    <!-- End footer -->
</body>

</html>
