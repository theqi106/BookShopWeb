/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import util.Encode;

/**
 *
 * @author THevY
 */
public class ChangePasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String currentPassword = request.getParameter("currentpassword");
       String newPassword  = request.getParameter("newpassword");
       String retypeNewPassword  = request.getParameter("retype");
       
       String currentPasswordSHA1 = Encode.toSHA1(currentPassword);
       String error = "";
       String url="/changepassword.jsp";
       HttpSession session = request.getSession();
       Object obj = session.getAttribute("user");
        User user = null;
        
        if(obj!=null) {user = (User)obj;}
        
        if(user==null){
        error = "You haven't login !!";
       
        } else{
        if(!currentPasswordSHA1.equals(user.getPassword())){
        error = "Your current password is incorrect!!";
       
        } else{
        if(!newPassword.equals(retypeNewPassword)){
        error = "Your retype password is incorrect";
        
        } else{
            if(currentPassword.equals(newPassword)){
            error= "Your new password is same with your current password!!";
            } else{
            String newPasswordSHA1 = Encode.toSHA1(newPassword);
            user.setPassword(newPasswordSHA1);
            UserDAO uDAO = new UserDAO();
            if(uDAO.changePassword(user)){
            error = "Password already change!!";
            
                
            } else{
            error = "Password hasn't already change!!";
            
            }
        }
        }
        }
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
