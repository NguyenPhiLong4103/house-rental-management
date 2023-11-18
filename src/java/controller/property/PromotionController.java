/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOLandlord;
import dao.DAOPromotion;
import dao.DAOProperty;
import entity.Account;
import entity.Landlord;
import entity.Promotion;
import entity.Property;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PromotionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null || action.isEmpty()) {
            response.sendRedirect("/homepage");
        }
        switch (action) {
            case "AddPromotion":
                addPromotion(request, response);
                break;
            case "Delete Promotion":
                deletePromotion(request, response);
                break;
            case "GetPromotion":
                getPromotion(request, response);
                break;
            case "Edit Promotion":
                editPromotion(request, response);
                break;
            default:
                response.sendRedirect("/homepage");
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

//    private void addPromotion(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        HttpSession session = request.getSession();
//        DAOPromotion daoprom = new DAOPromotion();
//        DAOProperty daoprop = new DAOProperty();
//        Promotion promotion = (Promotion) session.getAttribute("promotion");
//        if (session.getAttribute("landlord") == null) {
//            response.sendRedirect("login");
//            return;
//        }
//        int promotionID = promotion.getPromotionID();
//        daoprom.addPromotion(promotion);
//        getPromotion(request, response);
//    }
    private void addPromotion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        DAOPromotion daoprom = new DAOPromotion();
        DAOProperty daoprop = new DAOProperty();

        // Retrieve the Post ID from the request parameters
        int postID = Integer.parseInt(request.getParameter("postID"));

        // Retrieve other values from the form fields
        int discount = Integer.parseInt(request.getParameter("discount"));
        String description = request.getParameter("desc");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Create a Promotion instance
        Promotion promotion = new Promotion();
        promotion.setDiscount(discount);
        promotion.setDescription(description);
        promotion.setPromotionStartDate(startDate);
        promotion.setPromotionEndDate(endDate);

        if (session.getAttribute("landlord") == null) {
            response.sendRedirect("login");
            return;
        }

        // Add the promotion and associate it with the Post by updating the Post's promotion_id
        int generatedPromotionID = daoprom.addPromotion(promotion);
        daoprop.updatePostWithPromotion(postID, generatedPromotionID);

        // Redirect to getPromotion or any other appropriate page
        request.getRequestDispatcher("viewPostedLandlord").forward(request, response);
    }

    private void editPromotion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        DAOPromotion daoprom = new DAOPromotion();
        DAOProperty daoprop = new DAOProperty();

        // Retrieve the Post ID from the request parameters
        int postID = Integer.parseInt(request.getParameter("postID"));

        // Retrieve the existing Promotion for the Post
        Property property = daoprop.getByID(postID);
        int promotionID = property.getPromotionID();

        // Retrieve updated values from the form fields
        int discount = Integer.parseInt(request.getParameter("discount"));
        String description = request.getParameter("desc");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // Create a Promotion instance
        Promotion promotion = new Promotion();
        promotion.setPromotionID(promotionID); // Set the existing Promotion ID
        promotion.setDiscount(discount);
        promotion.setDescription(description);
        promotion.setPromotionStartDate(startDate);
        promotion.setPromotionEndDate(endDate);

        if (session.getAttribute("landlord") == null) {
            response.sendRedirect("login");
            return;
        }

        // Update the existing promotion in the database
        daoprom.editPromotion(promotion);

        // Redirect to getPromotion or any other appropriate page
        
        request.setAttribute("postID", postID);
        request.setAttribute("action", "edit");
        request.getRequestDispatcher("PostIDToAddViewController").forward(request, response);
    }

    private void getPromotion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Promotion promotion = (Promotion) session.getAttribute("promotion");
        if (session.getAttribute("landlord") == null) {
            response.sendRedirect("login");
            return;
        }
        int promotionID = promotion.getPromotionID();
        DAOPromotion daoprom = new DAOPromotion();
        DAOProperty daoprop = new DAOProperty();
        List<Property> propertyList = new ArrayList<>();
        List<Promotion> promotionLists = daoprom.getListPromotionById(promotionID);

        for (Promotion promotionList : promotionLists) {
            Property property = daoprop.getByID(promotionList.getPromotionID());
            if (property != null) {
                propertyList.add(property);
            }
        }
    }

    private void deletePromotion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int postID = Integer.parseInt(request.getParameter("postID"));
        DAOPromotion daoprom = new DAOPromotion();
        DAOProperty daoprop = new DAOProperty();
        
        Property property = daoprop.getByID(postID);
        Promotion promotion = daoprom.getPromotionById(property.getPromotionID());
        
        daoprop.SetPromotionIDToNull(postID);
        daoprom.removePromotion(promotion);
        
        request.getRequestDispatcher("viewPostedLandlord").forward(request, response);
    }

}
