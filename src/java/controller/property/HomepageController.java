/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import com.sun.net.httpserver.HttpServer;
import dao.DAOProperty;
import dao.DAOPropertyImage;
import dao.DAOPropertyType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Dell
 */
@WebServlet(name = "PropertyController", urlPatterns = {"/homepage"})
public class HomepageController extends HttpServlet {

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
        DAOProperty daoproperty = new DAOProperty();
        DAOPropertyImage daopropertyimg = new DAOPropertyImage();
        DAOPropertyType daoPt = new DAOPropertyType();
        List<Property> listProperty = daoproperty.getNRecentProperty(6);
        List<PropertyImage> listPropertyImages = new ArrayList<>();
        List<PropertyType> listPropertyType = daoPt.getListPropertyTypes();
        for (int i = 0; i < listProperty.size(); i++) {
            listPropertyImages.add(daopropertyimg.getThumbnailByID(listProperty.get(i).getId()));
        }
        List<String> bed = daoproperty.getNumOfBedrooms();
        String mess = request.getParameter("reviewmess");
        if(mess !=null){
            request.setAttribute("reviewmess", mess);
        }
        request.setAttribute("bedlist", bed);
        request.setAttribute("ptlist", listPropertyType);
        request.setAttribute("listProperty", listProperty);
        request.setAttribute("listPropertyImages", listPropertyImages);
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
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
