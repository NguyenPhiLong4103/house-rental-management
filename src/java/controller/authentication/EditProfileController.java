/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dao.DAOAccount;
import dao.DAOAdmin;
import dao.DAOLandlord;
import dao.DAOPropertyType;
import dao.DAOTenant;
import entity.Account;
import entity.Admin;
import entity.Landlord;
import entity.PropertyType;
import entity.Tenant;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import service.UpdateProfileService;
import service.ValidateData;

/**
 *
 * @author duchi
 */
@WebServlet(name = "EditProfileController", urlPatterns = {"/profile"})
public class EditProfileController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            DAOAccount acDao = new DAOAccount();
            DAOTenant tDao = new DAOTenant();
            DAOLandlord lDao = new DAOLandlord();
            DAOAdmin adDao = new DAOAdmin();
            DAOPropertyType daopt = new DAOPropertyType();
            List<PropertyType> listPt = daopt.getListPropertyTypes();
            request.setAttribute("ptlist", listPt);
            UpdateProfileService service = new UpdateProfileService();
            if(session.getAttribute("user")==null){
                response.sendRedirect("login");
            }
            else{
                String submit = request.getParameter("submit");
                //============== View profile ==================
                if(submit==null){
                    Account acc = (Account)session.getAttribute("user");
                    if(acc.getRole()==1){
                        Tenant tenant = tDao.findById(acc.getId());
                        request.setAttribute("user", tenant);
//                        out.print(request.getAttribute("user"));
//                        out.print(tenant);
//                        out.print(acc);
                        RequestDispatcher disp = request.getRequestDispatcher("/ProfileTenant.jsp");
                        disp.forward(request, response);
                    }
                    else if(acc.getRole()==2){
                        Landlord landlord = lDao.findById(acc.getId());
                        request.setAttribute("user", landlord);
                        RequestDispatcher disp = request.getRequestDispatcher("/ProfileLandlord.jsp");
                        disp.forward(request, response);
                    }
                    else{
                        Admin admin= adDao.findById(acc.getId());
                        request.setAttribute("user", admin);
                        RequestDispatcher disp = request.getRequestDispatcher("/ProfileAdmin.jsp");
                        disp.forward(request, response);
                    }
                    
                }
                
                //============== Update profile ====================
                else{
                    Account acc = (Account) session.getAttribute("user");
                    if(acc.getRole()==1){
                         String fname = request.getParameter("fname");
                         String lname=  request.getParameter("lname");
                         String address = request.getParameter("address");
                         String phone= request.getParameter("phone");
                         Tenant tenant = tDao.findById(acc.getId());
                        
                         String mess=service.validateTenant(fname, lname, address, phone);
                         if(!mess.equals("Ok")){
                              request.setAttribute("user", tenant);
                             request.setAttribute("mess", mess);
                             RequestDispatcher disp = request.getRequestDispatcher("/ProfileTenant.jsp");
                             disp.forward(request, response);
                         }
                         else{
                             tenant.setAddress(address);
                             tenant.setFirstName(fname);
                             tenant.setLastName(lname);
                             tenant.setPhone(phone);
                             tDao.updateTenant(tenant);
                             request.setAttribute("user", tenant);
                             request.setAttribute("mess", "Update profile successful!");
                             RequestDispatcher disp = request.getRequestDispatcher("/ProfileTenant.jsp");
                             disp.forward(request, response);
                         }
                         
                    }
                    else if(acc.getRole()==2){
                         String fname = request.getParameter("fname");
                         String lname=  request.getParameter("lname");
                         String address = request.getParameter("address");
                         String phone= request.getParameter("phone");
                         String civil_id = request.getParameter("civil_id");
                         Landlord landlord = lDao.findById(acc.getId());
                        
                         String mess=service.validateLandlord(fname, lname, address, phone,civil_id);
                         if(!mess.equals("Ok")){
                             request.setAttribute("user", landlord);
                             request.setAttribute("mess", mess);
                             RequestDispatcher disp = request.getRequestDispatcher("/ProfileLandlord.jsp");
                             disp.forward(request, response);
                         }
                         else{
                             landlord.setAddress(address);
                             landlord.setFirstName(fname);
                             landlord.setLastName(lname);
                             landlord.setPhone(phone);
                             lDao.updateLanlord(landlord);
                             request.setAttribute("user", landlord);
                             request.setAttribute("mess", "Update profile successful!");
                             RequestDispatcher disp = request.getRequestDispatcher("/ProfileLandlord.jsp");
                             disp.forward(request, response);
                         }
                    }
                    else {
                         String fname = request.getParameter("fname");
                         String lname=  request.getParameter("lname");
                         Admin admin = adDao.findById(acc.getId());
                         
                         String mess =service.validateAdmin(fname, lname);
                         if(!mess.equals("Ok")){
                              request.setAttribute("user", admin);
                             request.setAttribute("mess", mess);
                             RequestDispatcher disp = request.getRequestDispatcher("/ProfileAdmin.jsp");
                             disp.forward(request, response);
                         }
                         else{
                             admin.setFirstName(fname);
                             admin.setLastName(lname);
                             adDao.updateAdmin(admin);
                             request.setAttribute("user", admin);
                             request.setAttribute("mess", "Update profile successful!");
                             RequestDispatcher disp = request.getRequestDispatcher("/ProfileAdmin.jsp");
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
