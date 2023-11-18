/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAOAccount;
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
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import service.BanAccountService;
import utils.SendMail;

/**
 *
 * @author duchi
 */
@WebServlet(name = "BanAccount", urlPatterns = {"/ban"})
public class BanAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            DAOAccount daoacc = new DAOAccount();
            BanAccountService service = new BanAccountService();
            if(session.getAttribute("admin")==null){
                response.sendRedirect("login");
            }
            else{
                String submit = request.getParameter("submit");
                String id = request.getParameter("id");
                String time = request.getParameter("time");
                     int idInt = Integer.parseInt(request.getParameter("id"));
                    ResultSet up = (ResultSet) daoacc.findById2(idInt);                  
                    request.setAttribute("up", up);
                if(submit==null){     
                    RequestDispatcher disp = request.getRequestDispatcher("/AdminBan.jsp");
                    disp.forward(request, response);
                }
                else{
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                    // Parse the input string to a LocalDateTime object
                    LocalDateTime dateTime = LocalDateTime.parse(time, inputFormatter);

                    // Create a custom DateTimeFormatter for the desired format
                    DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

                    // Format the LocalDateTime object to the desired format
                    String formattedDateTime = dateTime.format(customFormatter);
                    out.print(id +" "+time+""+formattedDateTime);
                    Account acc = daoacc.findById(Integer.parseInt(id));
                    String desc = request.getParameter("desc");
                    String mess= service.banUser(Integer.parseInt(id), time);
                    if(mess.equals("User is banned!")){
                        SendMail.send(acc.getEmail(), "You are banned in our system", desc);
                    }
                    request.setAttribute("mess", mess);
                    RequestDispatcher disp = request.getRequestDispatcher("/AdminBan.jsp");
                    disp.forward(request, response);
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
