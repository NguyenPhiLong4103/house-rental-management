/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.DAOAccount;
import dao.DAOAdmin;
import dao.DAOLandlord;
import dao.DAOProperty;
import dao.DAOTenant;
import dao.DAOTransactions;
import entity.Account;
import entity.Admin;
import entity.Landlord;
import entity.Property;
import entity.Tenant;
import entity.Transactions;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.RegisterService;
import service.ValidateData;
import utils.EncryptionMD5;

@WebServlet(name = "AdminController", urlPatterns = {"/AdminController"})
public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            RegisterService serviceR = new RegisterService();
            DAOTenant daot = new DAOTenant();
            DAOLandlord daol = new DAOLandlord();
            DAOAdmin daoa = new DAOAdmin();
            DAOAccount daoacc = new DAOAccount();
            DAOTransactions daotr = new DAOTransactions();
            DAOProperty daop = new DAOProperty();
            HttpSession session = request.getSession();
            RegisterService rservice = new RegisterService();
            String service = request.getParameter("service");
            ResultSet tRs = daot.getAll();
            ResultSet lRs = daol.getAll();
            ResultSet aRs = daoa.getAll();
            ArrayList<Transactions> addPointTransactions = daotr.getAddPointTransactions();
            ArrayList<Transactions> payForPostTransactions = daotr.getPayforPostTransactions();
            ArrayList<Property> allPropertyList = daop.getAll();
            
            int index;
            if (request.getParameter("index") == null) {
                index = 1;
            } else {
                index = Integer.parseInt(request.getParameter("index"));
            }
            if(session.getAttribute("admin")==null){
                response.sendRedirect("login");
            }else{
            if (service == null) {
                service = "displayAll";
            }
            if (service.equals("displayAll")) {
                request.setAttribute("tRs", tRs);
                request.setAttribute("lRs", lRs);
                request.setAttribute("aRs", aRs);
                request.setAttribute("service", "displayAll");
                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
                disp.forward(request, response);
            }
            if (service.equals("search")) {
                Integer tId=-1, lId=-1, aId=-1;
                if(request.getParameter("tId")!= "" && request.getParameter("tId") != null){
                tId = Integer.parseInt(request.getParameter("tId"));
                }
                if(request.getParameter("lId")!= "" && request.getParameter("lId") != null){
                lId = Integer.parseInt(request.getParameter("lId"));
                }
                if(request.getParameter("aId")!= "" && request.getParameter("aId") != null){
                aId = Integer.parseInt(request.getParameter("aId"));
                }
//                String lId = request.getParameter("lId");
//                String aId = request.getParameter("aId");
//                Tenant ten = daot.findById(tId);
                tRs = daot.findById2(request.getParameter("tId"));
                lRs = daol.findById2(request.getParameter("lId"));
                aRs = daoa.findById2(request.getParameter("aId"));
                if(daot.findById(tId)==null && request.getParameter("tId") != null && request.getParameter("tId") != ""){
                    request.setAttribute("messtrs", "Not found any account!");
                    }
                if(daol.findById(lId)==null && request.getParameter("lId") != null && request.getParameter("lId") != ""){
                    request.setAttribute("messlrs", "Not found any account!");
                    }
                if(daoa.findById(aId)==null && request.getParameter("aId") != null && request.getParameter("aId") != ""){
                    request.setAttribute("messars", "Not found any account!");
                    }                
                if (request.getParameter("tId") == null ||request.getParameter("tId") == "") {
                    tRs = daot.getAll();
                }
                if (request.getParameter("lId") == null ||request.getParameter("lId") == "") {
                    lRs = daol.getAll();
                }
                if (request.getParameter("aId") == null ||request.getParameter("aId") == "") {
                    aRs = daoa.getAll();
                }
                request.setAttribute("tRs", tRs);
                request.setAttribute("lRs", lRs);
                request.setAttribute("aRs", aRs);
                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
                disp.forward(request, response);
            }
            if (service.equals("updateStatus")) {
                String submit = request.getParameter("id");
                if (submit != null) { 
                int id = Integer.parseInt(request.getParameter("id"));
                ResultSet up = (ResultSet) daoacc.findById2(id);                  
                request.setAttribute("up", up);
                RequestDispatcher disp = request.getRequestDispatcher("/AdminUpdateStatus.jsp");
                disp.forward(request, response);
                }else{
                    int uid = Integer.parseInt(request.getParameter("uid"));
                    int ustatus = Integer.parseInt(request.getParameter("ustatus"));
                    daoacc.updateStatus(uid, ustatus);
                    response.sendRedirect("AdminController?service=displayAll");
                }
            }

//            if (service.equals("displayTenant")) {
//                    int count = daot.countTenant();
//                    int endPage = count /3;
//                    if(count % 3 != 0){
//                        endPage++;
//                    }
//                    request.setAttribute("endPage", endPage);
//                tvector = daot.pagingTenant(index);
//                request.setAttribute("tRs", tRs);
//                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
//                disp.forward(request, response);
//            }
//            if (service.equals("displayLandlord")) {
//                request.setAttribute("lRs", lRs);
//                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
//                disp.forward(request, response);
//            }
//            if (service.equals("displayAdmin")) {
//                request.setAttribute("aRs", aRs);
//                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
//                disp.forward(request, response);
//            }
            if (service.equals("displayAddPoint")){
                request.setAttribute("addPointTransactions", addPointTransactions);
                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
                disp.forward(request, response);
            }
            if (service.equals("displayPayforPost")){
                request.setAttribute("payForPostTransactions", payForPostTransactions);
                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
                disp.forward(request, response);
            }
            if(service.equals("ManagePost")) {
                request.setAttribute("allPropertyList", allPropertyList);
                RequestDispatcher disp = request.getRequestDispatcher("/AdminAccount.jsp");
                disp.forward(request, response);
            }
            if (service.equals("addAccount")) {
                       String submit = request.getParameter("submit");
                       if(submit==null){
                        RequestDispatcher disp = request.getRequestDispatcher("/AdminAddAccount.jsp");
                        disp.forward(request, response);
                   }else{
                       String email = request.getParameter("email");
                       String password = request.getParameter("password");
                       String repassword = request.getParameter("repassword");
                       String fname = request.getParameter("fname");
                       String lname = request.getParameter("lname");
                       String validate = rservice.validateRegister(email, fname, lname, password, repassword);
                        if(!validate.equals("Ok")){
                            request.setAttribute("mess", validate);
                        }
                       else{    
                            Account acc = new Account(0, email,EncryptionMD5.encyption(password) , 3, 0);
                            String mess = serviceR.registerAccount(acc, fname, lname);
                            request.setAttribute("mess", mess);
                       }
                         RequestDispatcher disp = request.getRequestDispatcher("/AdminAddAccount.jsp");
                         disp.forward(request, response);
                   }          
            }
                if (service.equals("addPoint")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        int id = Integer.parseInt(request.getParameter("id"));
                        ResultSet up = (ResultSet) daoacc.findById2(id);
                        request.setAttribute("up", up);
                        RequestDispatcher disp = request.getRequestDispatcher("/AdminAddPoint.jsp");
                        disp.forward(request, response);
                    } else {
                        int lid = Integer.parseInt(request.getParameter("lid"));
                        int prePoint = daol.findById(lid).getAccountPoint();
                        int lpoint = Integer.parseInt(request.getParameter("LPoint"));
                        int totalpoint = 0;
                        if (lpoint > 0) {
                            totalpoint = prePoint + lpoint;
                            if (2000000000 - lpoint < prePoint){
                                int id = Integer.parseInt(request.getParameter("id"));
                                ResultSet up = (ResultSet) daoacc.findById2(id);
                                request.setAttribute("up", up);
                                request.setAttribute("message", "Total point must not be more than 2000000000");
                                RequestDispatcher disp = request.getRequestDispatcher("/AdminAddPoint.jsp");
                                disp.forward(request, response);
                            } else {
                                daol.addPointLandlord(lid, totalpoint);
                                session = request.getSession();
                                Admin ad = (Admin) session.getAttribute("admin");
                                Transactions transactions = new Transactions(0, lpoint, ad.getId(), "2", daotr.getCurrentDate(), 0, lid);
                                daotr.addTransactionsAddPoint(transactions);
                                response.sendRedirect("AdminController?service=displayAll");
                            }
                            
                        } else {
                            int id = Integer.parseInt(request.getParameter("id"));
                            ResultSet up = (ResultSet) daoacc.findById2(id);
                            request.setAttribute("up", up);
                            request.setAttribute("message", "Please enter number of point in range greater than 0 and less than 2 000 000 000!");
                            RequestDispatcher disp = request.getRequestDispatcher("/AdminAddPoint.jsp");
                            disp.forward(request, response);
                        }
                    }
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
