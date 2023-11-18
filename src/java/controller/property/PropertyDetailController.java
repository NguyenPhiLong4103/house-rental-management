/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOApplication;
import dao.DAOLandlord;
import dao.DAOPromotion;
import dao.DAOProperty;
import dao.DAOPropertyImage;
import dao.DAOPropertyType;
import dao.DAOWishlist;
import entity.Account;
import entity.Landlord;
import entity.Property;
import entity.PropertyImage;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class PropertyDetailController extends HttpServlet {

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
        String requestURI = request.getRequestURI();
        String urlPattern = "/property";
        try {
            int postID = Integer.parseInt(requestURI.substring(requestURI.indexOf(urlPattern) + urlPattern.length() + 1));
            DAOProperty daoproperty = new DAOProperty();
            Property property = daoproperty.getByID(postID);
            if (property == null) {
                response.sendRedirect("../property-list");
            } else {
                DAOLandlord daolandlord = new DAOLandlord();
                DAOPropertyImage daopropertyimg = new DAOPropertyImage();
                DAOWishlist daoWishlist = new DAOWishlist();
                DAOPromotion daoPromotion = new DAOPromotion();
                DAOApplication daoApplication = new DAOApplication();
                DAOPropertyType daoPropertyType = new DAOPropertyType();
                HttpSession session = request.getSession();

                Landlord landlord = daolandlord.findById(property.getLandlordID());
                List<PropertyImage> propertyImages = daopropertyimg.findByPostID(postID);
                List<Property> recentProperties = daoproperty.getNRecentProperties(4);
                List<PropertyImage> recentPropertyImages = new ArrayList<>();
                for (int i = 0; i < recentProperties.size(); i++) {
                    if (recentProperties.get(i).getId() == property.getId()) {
                        recentProperties.remove(recentProperties.get(i));
                    }
                    if (i < recentProperties.size()) {
                        recentPropertyImages.add(daopropertyimg.getThumbnailByID(recentProperties.get(i).getId()));
                    }
                }
                if (recentProperties.size() > 3) {
                    recentProperties.remove(recentProperties.size() - 1);
                    recentPropertyImages.remove(recentProperties.size() - 1);
                }
                Account account = (Account) session.getAttribute("user");

                request.setAttribute("ptlist", daoPropertyType.getListPropertyTypes());
                request.setAttribute("property", property);
                request.setAttribute("type", daoPropertyType.GetTypeNameByID(property.getType()));
                request.setAttribute("landlord", landlord);
                request.setAttribute("propertyImages", propertyImages);
                request.setAttribute("recentProperties", recentProperties);
                request.setAttribute("recentPropertyImages", recentPropertyImages);
                request.setAttribute("onWishlist", daoWishlist.isInWishlist(account != null ? account.getId() : 0, postID));
                request.setAttribute("promotion", daoPromotion.getPromotionById(property.getPromotionID()));
                if (account != null) {
                    request.setAttribute("submittedApplication", daoApplication.isApplicationSubmitted(postID, account.getId()));
                }
                request.getRequestDispatcher("/PropertyDetail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("../property-list");
        } catch (SQLException ex) {
            Logger.getLogger(PropertyDetailController.class.getName()).log(Level.SEVERE, null, ex);
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
