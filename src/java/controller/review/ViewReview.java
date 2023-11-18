/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.review;

import dao.DAOAccount;
import dao.DAOApplication;
import dao.DAOLandlord;
import dao.DAOProperty;
import dao.DAOPropertyType;
import dao.DAOReview;
import dao.DAOTenant;
import entity.Account;
import entity.Application;
import entity.Property;
import entity.Report;
import entity.Review;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE HAI THINH
 */
@WebServlet(name = "ViewReview", urlPatterns = {"/ViewReview"})
public class ViewReview extends HttpServlet {

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
        String service = request.getParameter("service");
        DAOReview rdao = new DAOReview();
                    if (service == null) {
                service = "viewReview";
            }
                if (service.equals("viewReview")) {
                int lid = Integer.parseInt(request.getParameter("id"));
                ResultSet rs = rdao.viewReview(lid);
                ResultSet namell = rdao.getLandlordName(lid);
                ResultSet avg = rdao.viewReview(lid);
                double a = 0;
                int count=0;            
                try {
                    while(avg.next()){
                        count++;
                        a+=avg.getInt("rating");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                a=a/count;
                String b = String.valueOf(a);
                String c = String.valueOf(count);
                request.setAttribute("count", c);               
                request.setAttribute("avg", b);
                request.setAttribute("rs", rs);
                request.setAttribute("namell", namell);
                RequestDispatcher disp = request.getRequestDispatcher("/ViewReview.jsp");
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
