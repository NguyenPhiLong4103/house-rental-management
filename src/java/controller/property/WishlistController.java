/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOProperty;
import dao.DAOPropertyType;
import dao.DAOTenant;
import dao.DAOWishlist;
import entity.Account;
import entity.Property;
import entity.PropertyType;
import entity.Wishlist;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "WishlistController", urlPatterns = {"/WishlistController"})
public class WishlistController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null || action.isEmpty()) {
            response.sendRedirect("/homepage");
        }
        switch (action) {
            case "add-wishlist":
                addWishlist(request, response);
                break;
            case "delete-from-wishlist":
                deleteProperty(request, response);
                break;
            case "get-wishList":
                getWishlist(request, response);
                break;
            default:
                response.sendRedirect("/homepage");
        }
    }

    private void addWishlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOWishlist daoWish = new DAOWishlist();
        DAOProperty daoPro = new DAOProperty();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
        //get id
        int userID = user.getId();
        //get propertyID
        int propertyID = Integer.parseInt(request.getParameter("propertyID"));
        //add property to wishlist
        daoWish.addToWishlist(userID, propertyID);
        getWishlist(request, response);

    }

    private void getWishlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int userID = user.getId();

        DAOWishlist daoWish = new DAOWishlist();
        DAOProperty daoPro = new DAOProperty();
        DAOPropertyType daopt = new DAOPropertyType();
        List<PropertyType> listPt = daopt.getListPropertyTypes();
        List<Wishlist> wishlistItems = daoWish.getWishlistByUserID(userID);
        List<Property> propertyList = new ArrayList<>();

        for (Wishlist wishlistItem : wishlistItems) {
            Property property = daoPro.getByIDWithImage(wishlistItem.getPropertyID());
            if (property != null) {
                propertyList.add(property);
            }
        }
        request.setAttribute("ptlist", listPt);
        request.setAttribute("wishlist", propertyList);
        request.getRequestDispatcher("Wishlist.jsp").forward(request, response);
    }

    private void deleteProperty(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAOWishlist daoWish = new DAOWishlist();
        HttpSession session = request.getSession();
        Account user = (Account) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        //get id
        int userID = user.getId();
        //get propertyID
        int propertyID = Integer.parseInt(request.getParameter("propertyID"));
        //delete
        daoWish.removeFromWishlist(userID, propertyID);
        getWishlist(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
