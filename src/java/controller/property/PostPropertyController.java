/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOLandlord;
import entity.Account;
import entity.Landlord;
import entity.Property;
import entity.PropertyType;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.catalina.User;
import service.PostPropertyService;

/**
 *
 * @author duchi
 */
@WebServlet(name = "PostPropertyController", urlPatterns = {"/post"})
@MultipartConfig()   // 50MB
public class PostPropertyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            PostPropertyService service = new PostPropertyService();
            DAOLandlord dao = new DAOLandlord();
            List<PropertyType> listPropertyType = service.getListPropertyTypes();
            request.setAttribute("ptlist", listPropertyType);
            int point = 0;
            if (session.getAttribute("landlord") == null) {
                response.sendRedirect("login");
            } else {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    RequestDispatcher disp = request.getRequestDispatcher("/PostProperty.jsp");
                    disp.forward(request, response);
                } else {
                    Landlord landlord = (Landlord) session.getAttribute("landlord");
                    Account user = (Account) session.getAttribute("user");
                    String name = request.getParameter("name");
                    String desc = request.getParameter("desc");
                    String typeId = request.getParameter("type");
                    String numOfBed = request.getParameter("bedroom");
                    String duration = request.getParameter("duration");
                    String price = request.getParameter("ppprice");
                    String area = request.getParameter("pparea");
                    String address= request.getParameter("address");
                        //========================DURATION=====================
                        LocalDate date = LocalDate.now();
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String startDate = date.format(dateFormatter);
                        String endDate = null;
                        if(duration.equals("1")){
                            point = 100;
                            LocalDate futureDate = LocalDate.now().plusMonths(1);
                            endDate = futureDate.format(dateFormatter);
                        }
                        if(duration.equals("2")){
                            point = 270;
                            LocalDate futureDate = LocalDate.now().plusMonths(2);
                            endDate = futureDate.format(dateFormatter);
                        }
                        if(duration.equals("3")){
                            point = 480;
                            LocalDate futureDate = LocalDate.now().plusMonths(3);
                            endDate = futureDate.format(dateFormatter);
                        }
                        if(duration.equals("4")){
                            point = 840;
                            LocalDate futureDate = LocalDate.now().plusMonths(4);
                            endDate = futureDate.format(dateFormatter);
                        }
                        
                        //===================== VALIDATE POST =========================
                         String mess= service.validatePost((List<Part>) request.getParts(),name,price,area,address);
                        if(!mess.equals("OK")){
                            request.setAttribute("mess", mess);
                            request.setAttribute("ptlist", listPropertyType);
                            RequestDispatcher disp = request.getRequestDispatcher("/PostProperty.jsp");
                            disp.forward(request, response);
                        }
                        //======================= CHECK POINT HERE ===========================
                       
                        else if(landlord.getAccountPoint() < point){
                            mess = "You do not have enough point.";
                            request.setAttribute("mess", mess);
                            RequestDispatcher disp = request.getRequestDispatcher("/PostProperty.jsp");
                            disp.forward(request, response);
                        }
                        else{
                            //=================== ADD POST ================================
                            Property p = new Property(0, name, Integer.parseInt(price)
                                    , Integer.parseInt(typeId), Integer.parseInt(area),
                                    Integer.parseInt(numOfBed),address, desc,user.getId() , 1, 0, startDate, endDate);
                            service.addNewPost(p);
                            service.addTransactionsPayforPost(point, user.getId());
                            //update session
                            Landlord updateLandlord = dao.findById(user.getId());
                            session.setAttribute("landlord", updateLandlord);
                            
                            //==================== UPLOAD IMAGE AND ADD TO DB =======================
                            String tmp = request.getServletContext().getRealPath("");
                            String path = tmp.substring(0, tmp.indexOf("build\\web\\"))+"web\\assets\\";
                            List<Part> listParts = (List<Part>) request.getParts();
                            service.uploadFile(listParts, path);
                            //==================== REDIRECT ===========================
                            mess = "Up post successfully.";
                            request.setAttribute("mess", mess);
                            RequestDispatcher disp = request.getRequestDispatcher("/PostProperty.jsp");
                            disp.forward(request, response);
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
