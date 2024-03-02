    <%-- 
    Document   : login
    Created on : Nov 21, 2023, 10:13:36 PM
    Author     : THevY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width ,initial-scale=1, shrink-to-fit=no">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
        <style>
            .red{

                color: red;
            }

        </style>
        <%
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath();
        %>
        <link href="<%=url%>/css/signin.css" rel="stylesheet">
    </head>
    <body>
        <main class="form-signin w-100 m-auto">
            <form action="login" method="post">
                <div class="text-center">
                <img class="mb-4" src="<%=url%>/img/logo/ATTV.png" alt="" width="72" >
                
                <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
                </div>
                <%
                String error = request.getAttribute("error")+"";
                if(error.equals("null")){
                error="";
                }
                %>
                <div class="text-center"><span class="red"><%=error%></span></div>
                <div class="text-center">                  
                    <span class="red">      </span>
                </div>
                <div class="form-floating">
                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter username...">
                    <label for="username">Username</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password...">
                    <label for="password">Password</label>
                </div>

                <div class="form-check text-start my-3">
                    <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        Remember me
                    </label>
                </div>
                <button class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
                <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2023</p>
                <a href="register.jsp">Create a new account</a>
            </form>
        </main>
    </body>
</html>
