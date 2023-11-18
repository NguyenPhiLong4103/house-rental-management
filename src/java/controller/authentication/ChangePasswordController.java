/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import entity.Account;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ChangePasswordService;
import utils.EncryptionMD5;

/**
 *
 * @author duchi
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/change"})
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();        
            ChangePasswordService service = new ChangePasswordService();
            if(session.getAttribute("user")==null){
                response.sendRedirect("login");
            }
            else{
                String submit = request.getParameter("submit");
                if(submit==null){
                     RequestDispatcher disp = request.getRequestDispatcher("/ChangePassword.jsp");
                     disp.forward(request, response);
                }
                else{
                    String opassword = request.getParameter("oldpassword");
                    String npassword = request.getParameter("newpassword");
                    String rpassword = request.getParameter("repassword");
                    Account acc = (Account)session.getAttribute("user");
                    String cpassword = acc.getHashedPassword();
                    
                    String mess = service.checkChange(opassword, cpassword, npassword, rpassword);
                    out.print(mess);
                    if(!mess.equals("Change Password Successfully!")){
                 
                        request.setAttribute("mess", mess);
                        RequestDispatcher disp = request.getRequestDispatcher("/ChangePassword.jsp");
                        disp.forward(request, response);
                    }
                    else{
                        service.changePassword(acc, npassword);
                        request.setAttribute("mess", mess);
                        RequestDispatcher disp = request.getRequestDispatcher("/ChangePassword.jsp");
                        disp.forward(request, response);
                    }
                }
            }
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