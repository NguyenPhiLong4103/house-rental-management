/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOLandlord;
import dao.DAOProperty;
import dao.DAOPropertyImage;
import dao.DAOPropertyType;
import dao.DAOTransactions;
import entity.Account;
import entity.Landlord;
import entity.Property;
import entity.PropertyImage;
import entity.PropertyType;
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
 * @author MSI
 */
@WebServlet(name = "PostedPropertiesLandlord", urlPatterns = {"/viewPostedLandlord"})
public class PostedPropertiesLandlord extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.getAttribute("landlord") == null) {
            response.sendRedirect("login");
        } else {
            DAOProperty dao = new DAOProperty();
            DAOPropertyImage daopropertyimg = new DAOPropertyImage();
            DAOPropertyType daopt = new DAOPropertyType();
            String paramIndex = request.getParameter("index") == null ? "1" : request.getParameter("index");
            Landlord ll = (Landlord) session.getAttribute("landlord");
            int totalPosted = dao.getTotalPostedByLandlordID(ll.getId());
            int endpage = totalPosted / 6;
            if (totalPosted % 6 != 0) {
                endpage++;
            }
            request.setAttribute("endpage", endpage);
            List<PropertyType> listPt = daopt.getListPropertyTypes();
            List<Property> listPostedProperties = dao.getPostedPropertiesByLandlordID(Integer.valueOf(paramIndex), ll.getId());
            List<PropertyImage> listPropertyImages = new ArrayList<>();
            for (int i = 0; i < listPostedProperties.size(); i++) {
                listPropertyImages.add(daopropertyimg.getThumbnailByID(listPostedProperties.get(i).getId()));
            }
            session.setAttribute("totalPosted", totalPosted);
            request.setAttribute("ptlist", listPt);
            request.setAttribute("listPropertyImages", listPropertyImages);
            request.setAttribute("listPostedProperties", listPostedProperties);
            request.setAttribute("index", paramIndex);
            request.setAttribute("endpage", endpage);
            request.getRequestDispatcher("PostedPropertiesLandlord.jsp").forward(request, response);
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
        DAOProperty daoProperty = new DAOProperty();
        DAOTransactions daoTransaction = new DAOTransactions();
        DAOLandlord daoLandlord = new DAOLandlord();
        HttpSession session = request.getSession();

        int duration = Integer.parseInt(request.getParameter("duration"));
        int propertyID = Integer.parseInt(request.getParameter("postID"));
        Property property = daoProperty.getByID(propertyID);
        property.setStatus(1);
        property.setEndDate(java.time.LocalDate.now().plusMonths(duration).toString());
        daoProperty.updateProperty(property);
        daoTransaction.addTransactionsPayforRePost(duration == 1 ? 100 : (duration == 3 ? 270 : (duration == 6 ? 480 : 840)), property.getLandlordID() ,propertyID);
        session.setAttribute("landlord", daoLandlord.findById(((Account)session.getAttribute("user")).getId()));
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
