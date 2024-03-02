<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
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
                    <li><a class="dropdown-item" href="#">Cart</a></li>
                    <c:if test="${sessionScope.user.isAdmin==0}">
                    <li><a class="dropdown-item" href="changeinformation.jsp">Change Information</a></li>
                    <li><a class="dropdown-item" href="changepassword.jsp">Change Password</a></li>
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