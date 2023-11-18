/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAOReport;
import entity.Report;
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

/**
 *
 * @author LE HAI THINH
 */
@WebServlet(name = "AdminReport", urlPatterns = {"/AdminReport"})
public class AdminReport extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            DAOReport rdao = new DAOReport();
            ResultSet rRs = rdao.getAll();
            HttpSession session = request.getSession();
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                if (session.getAttribute("admin") == null) {
                    response.sendRedirect("login");
                } else {
                    request.setAttribute("rRs", rRs);
                    request.setAttribute("service", "displayAll");
                    RequestDispatcher disp = request.getRequestDispatcher("/AdminReport.jsp");
                    disp.forward(request, response);
                }
            }
            if (service.equals("updateStatus")) {
                if (session.getAttribute("admin") == null) {
                    response.sendRedirect("login");
                } else {
                    String submit = request.getParameter("id");
                    if (submit != null) {
                        int id = Integer.parseInt(request.getParameter("id"));
                        ResultSet up = (ResultSet) rdao.findById(id);
                        request.setAttribute("up", up);
                        RequestDispatcher disp = request.getRequestDispatcher("/AdminUpdateReport.jsp");
                        disp.forward(request, response);
                    } else {
                        int uid = Integer.parseInt(request.getParameter("uid"));
                        String ustatus = request.getParameter("ustatus");
                        rdao.updateStatus(uid, ustatus);
                        response.sendRedirect("AdminReport?service=displayAll");
                    }
                }
            }
            if (service.equals("systemReport")) {
                String id = request.getParameter("id");
                if (id != null) {
                    request.setAttribute("reporter_id", id);
                    RequestDispatcher disp = request.getRequestDispatcher("/SendReportSystem.jsp");
                    disp.forward(request, response);
                } else {
                    int rid = Integer.parseInt(request.getParameter("rid"));
                    String cat = request.getParameter("cat");
                    String des = request.getParameter("des");
                    Report rp = new Report(0, null, null, rid, cat, des, "Unread");
                    rdao.sendReportSystem(rp);
                    response.sendRedirect("homepage");
                }
            }
            if (service.equals("propertyReport")) {
                String id = request.getParameter("id");
                String pid = request.getParameter("pid");
                String lid = request.getParameter("lid");
                if (id != null && pid != null && lid != null) {
                    if (id.equals(lid)) {
                        response.sendRedirect("search?txt=&propertyType=&sortType=&price=&area=&beds=&rpmess=You can not report yourself");
                    } else {
                        request.setAttribute("reporter_id", id);
                        request.setAttribute("property_id", pid);
                        request.setAttribute("reported_id", lid);
                        RequestDispatcher disp = request.getRequestDispatcher("/SendReportProperty.jsp");
                        disp.forward(request, response);
                    }
                } else {
                    int rid = Integer.parseInt(request.getParameter("rid"));
                    int proid = Integer.parseInt(request.getParameter("pid"));
                    int landid = Integer.parseInt(request.getParameter("lid"));
                    String cat = request.getParameter("cat");
                    String des = request.getParameter("des");
                    Report rp = new Report(0, landid, proid, rid, cat, des, "Unread");
                    rdao.sendReportProperty(rp);
                    response.sendRedirect("homepage");
                }
            }
            if (service.equals("tenantReport")) {
                String id = request.getParameter("id");
                if (id != null) {
                    int tid = Integer.parseInt(request.getParameter("tid"));
                    request.setAttribute("reporter_id", id);
                    request.setAttribute("tid", tid);
                    RequestDispatcher disp = request.getRequestDispatcher("/SendReportTenant.jsp");
                    disp.forward(request, response);
                } else {
                    int rid = Integer.parseInt(request.getParameter("rid"));
                    Integer tenant = Integer.parseInt(request.getParameter("tenantid"));
                    String cat = request.getParameter("cat");
                    String des = request.getParameter("des");
                    Report rp = new Report(0, tenant, null, rid, cat, des, "Unread");
                    rdao.sendReportTenant(rp);
                    response.sendRedirect("homepage");
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
