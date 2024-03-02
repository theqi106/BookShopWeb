/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
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
public class ChangeInformationController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
       
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String dob= request.getParameter("dob");
		String addressUser = request.getParameter("addressuser");
		String addressBuy = request.getParameter("addressbuy");
		String addressReceive = request.getParameter("addressreceive");
		String telNum = request.getParameter("telnum");
		String email = request.getParameter("email");
		int isAdmin = 0;
		
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("dob", dob);
		request.setAttribute("addressuser", addressUser);
		request.setAttribute("addressbuy", addressBuy);
		request.setAttribute("addressreceive", addressReceive);
		request.setAttribute("telnum", telNum);
		request.setAttribute("email", email);
		request.setAttribute("isAdmin", isAdmin);
                
                String url = "";
                String error = "" ;
                UserDAO userDAO = new UserDAO();
                
               
                
                
                request.setAttribute("error", error);

                
                if(error.length()>0){
                    url = "/register.jsp";
                } else{
                    
                    Object obj = request.getSession().getAttribute("user");
                    User user = null;
                    if (obj != null) {
                        user = (User) obj;
                    }
                    if (user != null) {
                
                String userId = user.getUserId() ; //+"" de bien no thanh 1 chuoi rong
                User u =  new User(userId, "", "", name, gender, addressBuy, addressReceive, addressBuy, Date.valueOf(dob), telNum, email,isAdmin );
                userDAO.updateInfor(u);
                User u2 = userDAO.selectById(u);
                
                request.getSession().setAttribute("user", u2);
                url = "/changesuccess.jsp";
                }
                    request.setAttribute("error", error);
               RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
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
        processRequest(request, response);
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
