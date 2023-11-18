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
@WebServlet(name = "ReviewController", urlPatterns = {"/ReviewController"})
public class ReviewController extends HttpServlet {

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
        DAOTenant daotenant = new DAOTenant();
        DAOAccount daoaccount = new DAOAccount();
        DAOPropertyType daoPropertyType = new DAOPropertyType();
        DAOLandlord daoLandlord = new DAOLandlord();
         DAOProperty daoproperty = new DAOProperty();
            DAOApplication daoapplication = new DAOApplication();
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("user");
                    List<Application> applications;
                    List<String> nameList = new ArrayList<>();
                    List<Property> properties = new ArrayList<>();
            if(session.getAttribute("user")==null){
               response.sendRedirect("login");
            }else{
                    if (account.getRole() == 1){
                        applications = daoapplication.getByTenantID2(account.getId());
                    }else{
                        applications = daoapplication.getByLandlordID2(account.getId());
                    }
                    for (Application application : applications) {
                        if (account.getRole() == 2) {
                            nameList.add(daotenant.getFullnameByID(application.getTenantID()));
                        } else {
                            nameList.add(daoLandlord.getFullnameByID(application.getLandlordID()));
                        }
                        properties.add(daoproperty.getByID(application.getPostID()));
                    }

                    request.setAttribute("applications", applications);
                    request.setAttribute("nameList", nameList);
                    request.setAttribute("properties", properties);
            if (service == null) {
                service = "review";
            }
            if (service.equals("review")) {
                String id = request.getParameter("id");
                String pid = request.getParameter("pid");
                String lid = request.getParameter("lid");
                if (id != null && pid != null && lid != null) {
                    request.setAttribute("id", id);
                    request.setAttribute("property_id", pid);
                    request.setAttribute("lid", lid);
                    ResultSet rs= rdao.getData("select*from Review where user_id="+id+" and property_id = "+pid);
                    
                    try {
                        if(!rs.next()){
                            RequestDispatcher disp = request.getRequestDispatcher("/SendReview.jsp");
                            disp.forward(request, response);
                        }
                        else{
                            request.setAttribute("already", "Update review");
                            RequestDispatcher disp = request.getRequestDispatcher("/SendReview.jsp");
                            disp.forward(request, response);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else {
                    if(request.getParameter("already")==null){
                    int rating = Integer.parseInt(request.getParameter("rating"));
                    int rid = Integer.parseInt(request.getParameter("rid"));
                    int proid = Integer.parseInt(request.getParameter("pid"));
                    String des = request.getParameter("des");
                    Review rv = new Review(0, rid, proid, rating, des);
                    rdao.sendReview(rv);
                    }else{
                    int rating = Integer.parseInt(request.getParameter("rating"));
                    int rid = Integer.parseInt(request.getParameter("rid"));
                    int proid = Integer.parseInt(request.getParameter("pid"));
                    String des = request.getParameter("des");
                    rdao.updateReview(rating, rid, proid, des);
                    }
                    response.sendRedirect("homepage?reviewmess=Send review successfully!");
                }
            }
            if (service.equals("viewReview")) {
                int lid = Integer.parseInt(request.getParameter("id"));
                ResultSet rs = rdao.viewReview(lid);
                ResultSet namell = rdao.getLandlordName(lid);
                ResultSet avg = rdao.avgRating(lid);
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
