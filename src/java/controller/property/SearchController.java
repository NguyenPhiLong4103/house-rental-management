/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOProperty;
import dao.DAOPropertyType;
import entity.Property;
import entity.PropertyType;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author MSI
 */
@WebServlet(name = "SearchController", urlPatterns = {"/search"})
public class SearchController extends HttpServlet {

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
        DAOProperty dao = new DAOProperty();
        DAOPropertyType daopt = new DAOPropertyType();
        HttpSession session = request.getSession();

        String txt = request.getParameter("txt");
        String propertyType = request.getParameter("propertyType");
        String sortType = request.getParameter("sortType");
        String beds = request.getParameter("beds");
        String price = request.getParameter("price");
        String area = request.getParameter("area");

        //
        String mess = request.getParameter("rpmess");
        if(mess !=null){
            request.setAttribute("rpmess", mess);
        }
        //ko có index thì là trang đầu tiên
        String paramIndex = request.getParameter("index") == null ? "1" : request.getParameter("index");

        //mỗi trang 6 property
        List<Property> listProperty = dao.searchProperty(txt, propertyType, price, sortType, beds, area, Integer.valueOf(paramIndex), 6);
        int totalRows = dao.getTotalRowsCondition(txt, propertyType, price, beds, area);
        int numberPage = (int) Math.ceil((double) totalRows / 6);
        List<PropertyType> listPt = daopt.getListPropertyTypes();
        List<String> bed = dao.getNumOfBedrooms();

        session.setAttribute("result", totalRows);

        request.setAttribute("propertyType", propertyType);
        request.setAttribute("sortType", sortType);
        request.setAttribute("beds", beds);
        request.setAttribute("price", price);
        request.setAttribute("area", area);

        request.setAttribute("bedlist", bed);
        request.setAttribute("ptlist", listPt);
        request.setAttribute("index", paramIndex);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("listP", listProperty);
        request.getRequestDispatcher("PropertyList.jsp").forward(request, response);

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
