/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.application;

import dao.DAOAccount;
import dao.DAOApplication;
import dao.DAOLandlord;
import dao.DAOProperty;
import dao.DAOPropertyType;
import dao.DAOTenant;
import entity.Account;
import entity.Application;
import entity.Property;
import entity.PropertyType;
import entity.Tenant;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ApplicationController extends HttpServlet {

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
        String urlPattern = "/application";
        DAOProperty daoproperty = new DAOProperty();
        DAOApplication daoapplication = new DAOApplication();
        DAOTenant daotenant = new DAOTenant();
        DAOAccount daoaccount = new DAOAccount();
        DAOPropertyType daoPropertyType = new DAOPropertyType();
        DAOLandlord daoLandlord = new DAOLandlord();
        HttpSession session = request.getSession();
        Property property;
        String propertyType;

        List<PropertyType> listPt = daoPropertyType.getListPropertyTypes();
        request.setAttribute("ptlist", listPt);

        String uri = requestURI.substring(requestURI.indexOf(urlPattern) + urlPattern.length() + 1);
        switch (uri) {
            case "view":
                try {
                    Account account = (Account) session.getAttribute("user");
                    List<Application> applications;
                    List<String> nameList = new ArrayList<>();
                    List<Property> properties = new ArrayList<>();
                    String txtSearch = (String) request.getParameter("txtSearch");

                    if (account.getRole() == 1) {
                        applications = daoapplication.getByTenantID(account.getId(), txtSearch == null ? "" : txtSearch);
                    } else {
                        applications = daoapplication.getByLandlordID(account.getId(), txtSearch == null ? "" : txtSearch);
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
                    request.getRequestDispatcher("/ApplicationList.jsp").forward(request, response);
                } catch (Exception ex) {
                    response.sendRedirect("../login");
                }
                break;
            case "create":
                if (session.getAttribute("user") != null) {
                    property = daoproperty.getByID(Integer.parseInt(request.getParameter("propertyID")));
                    propertyType = daoPropertyType.GetTypeNameByID(property.getType());

                    request.setAttribute("propertyType", propertyType);
                    request.setAttribute("property", property);
                    request.getRequestDispatcher("/Application.jsp").forward(request, response);
                } else {
                    response.sendRedirect("../login");
                }
                break;
            case "add":
                Account account;
                if (session.getAttribute("user") != null) {
                    account = (Account) session.getAttribute("user");
                    property = daoproperty.getByID(Integer.parseInt(request.getParameter("propertyID")));
                    daoapplication.create(new Application(
                            0,
                            account.getId(),
                            property.getLandlordID(),
                            property.getId(),
                            0
                    ));

                    response.sendRedirect("view");
                } else {
                    response.sendRedirect("../login");
                }
                break;
            case "cancel":
                Application application;
                if (session.getAttribute("user") != null) {
                    application = daoapplication.getByID(Integer.parseInt(request.getParameter("applicationID")));
                    account = (Account) session.getAttribute("user");
                    if (application.getStatus() == 0 && application.getTenantID() == account.getId()) {
                        daoapplication.changeStatus(application.getOrderID(), 4);
                    }

                    response.sendRedirect(String.valueOf(application.getOrderID()));
                } else {
                    response.sendRedirect("../login");
                }
                break;
            case "accept":
                if (session.getAttribute("user") != null) {
                    application = daoapplication.getByID(Integer.parseInt(request.getParameter("applicationID")));
                    account = (Account) session.getAttribute("user");
                    if (application.getStatus() == 0 && application.getLandlordID() == account.getId()) {
                        daoapplication.changeStatus(application.getOrderID(), 1);
                    }
                    daoapplication.cancelRemaining(application.getPostID());
                    daoproperty.updatePostStatus(application.getPostID(), 0);

                    response.sendRedirect(String.valueOf(application.getOrderID()));
                } else {
                    response.sendRedirect("../login");
                }
                break;
            case "reject":
                if (session.getAttribute("user") != null) {
                    application = daoapplication.getByID(Integer.parseInt(request.getParameter("applicationID")));
                    account = (Account) session.getAttribute("user");
                    if (application.getStatus() == 0 && application.getLandlordID() == account.getId()) {
                        daoapplication.changeStatus(application.getOrderID(), 2);
                    }

                    response.sendRedirect(String.valueOf(application.getOrderID()));
                } else {
                    response.sendRedirect("../login");
                }
                break;
            default:
                try {
                    Account user = (Account) session.getAttribute("user");
                    int applicationID = Integer.parseInt(uri);
                    application = daoapplication.getByID(applicationID);
                    if (user.getId() == application.getTenantID() || user.getId() == application.getLandlordID()) {
                        account = daoaccount.findById(application.getTenantID());
                        Tenant tenant = daotenant.findById(application.getTenantID());
                        property = daoproperty.getByID(application.getPostID());
                        propertyType = daoPropertyType.GetTypeNameByID(property.getType());

                        request.setAttribute("application", application);
                        request.setAttribute("acc", account);
                        request.setAttribute("tenant", tenant);
                        request.setAttribute("property", property);
                        request.setAttribute("propertyType", propertyType);
                        request.getRequestDispatcher("/Application.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("../homepage");
                    }
                } catch (Exception e) {
                    response.sendRedirect("../homepage");
                }
                break;
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
