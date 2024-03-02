<%-- 
    Document   : success.jsp
    Created on : Nov 21, 2023, 8:27:55 PM
    Author     : THevY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script src="//ajax.aspnetcdn.com/ajax/jQuery/jquery-2.1.1.js" type="text/javascript"></script>

<script type="text/javascript">
     $(window).load(function(){
         $('#myModal').modal('show');
      });
</script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    
        </head>
    <body>
        <div class="modal zoom fade show"  id="myModal" style="z-index: 1500;" tabindex="0" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content card">
                    <div class="modal-header alert-success">
                        <h5 class="m-0">Success</h5>
                    </div>
                    <div class="modal-body messageModal">
                        Congratulations, your action is successful.
                    </div>
                    <div class="modal-footer">
                    <a href="login.jsp"><button type="button" class="btn btn-primary">Back to Login</button></a>
                </div>
                </div>
                
            </div>
        </div>
    </body>
</html>
