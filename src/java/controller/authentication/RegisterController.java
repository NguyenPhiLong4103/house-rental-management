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
import static java.lang.System.out;
import service.RegisterService;
import service.ValidateData;
import utils.EncryptionMD5;

/**
 *
 * @author duchi
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
               HttpSession session = request.getSession();
               RegisterService service = new RegisterService();
               Account account = (Account)session.getAttribute("user");
               if(account.getRole()==1 || account.getRole()==2){
                   response.sendRedirect(request.getContextPath() +"/homepage");
               }else{
                   String submit = request.getParameter("submit");
                   if(submit==null){
                        RequestDispatcher disp = request.getRequestDispatcher("/Registration.jsp");
                        disp.forward(request, response);
                   }else{
                       String email = request.getParameter("email");
                       String password = request.getParameter("password");
                       String repassword = request.getParameter("repassword");
                       String fname = request.getParameter("fname");
                       String lname = request.getParameter("lname");
                       String role = request.getParameter("role");
                       String mess = service.validateRegister(email,fname, lname, password, repassword);
                       out.print(email +" "+ fname +" "+ lname +" "+ password+" "+ repassword+" "+ role + " "+ mess+"<br>"+mess);
                        if(!mess.equals("Ok")){
                            request.setAttribute("mess", mess);
                        }
                       else{    
                            Account acc = new Account(0, email,EncryptionMD5.encyption(password) , Integer.parseInt(role), 0);
                            out.print(acc);
                            mess = service.registerAccount(acc, fname, lname);
                            request.setAttribute("mess", mess);
                       }
                         RequestDispatcher disp = request.getRequestDispatcher("/Registration.jsp");
                         disp.forward(request, response);
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
