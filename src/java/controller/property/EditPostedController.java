/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.property;

import dao.DAOLandlord;
import dao.DAOProperty;
import dao.DAOPropertyImage;
import entity.Account;
import entity.Landlord;
import entity.Property;
import entity.PropertyImage;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.apache.catalina.User;
import service.PostPropertyService;

/**
 *
 * @author duchi
 */
@WebServlet(name = "PostPropertyController", urlPatterns = {"/editPosted"})
@MultipartConfig()   // 50MB
public class EditPostedController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOPropertyImage iDao = new DAOPropertyImage();
            HttpSession session = request.getSession();
            PostPropertyService service = new PostPropertyService();
            DAOLandlord dao = new DAOLandlord();
            DAOProperty daop = new DAOProperty();
            int oldpid = Integer.parseInt(request.getParameter("pid"));
            Property oldP = (Property) daop.getByID(oldpid);
            request.setAttribute("oldP", oldP);
            List<PropertyType> listPropertyType = service.getListPropertyTypes();
            int point = 0;
            if (session.getAttribute("landlord") == null) {
                response.sendRedirect("login");
            } else {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.setAttribute("ptlist", listPropertyType);
                    RequestDispatcher disp = request.getRequestDispatcher("/EditPostedProperty.jsp");
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
                    String address = request.getParameter("address");
                    //========================DURATION=====================
                    LocalDate oEndDate = LocalDate.parse(oldP.getEndDate());
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String startDate = oldP.getStartDate();
                    String nEndDate = null;
                    if (duration.equals("0")) {
                        point = 0;
                        LocalDate futureDate = oEndDate;
                        nEndDate = futureDate.format(dateFormatter);
                    }
                    if (duration.equals("1")) {
                        point = 100;
                        LocalDate futureDate = oEndDate.plusMonths(1);
                        nEndDate = futureDate.format(dateFormatter);
                    }
                    if (duration.equals("2")) {
                        point = 270;
                        LocalDate futureDate = oEndDate.plusMonths(2);
                        nEndDate = futureDate.format(dateFormatter);
                    }
                    if (duration.equals("3")) {
                        point = 480;
                        LocalDate futureDate = oEndDate.plusMonths(3);
                        nEndDate = futureDate.format(dateFormatter);
                    }
                    if (duration.equals("4")) {
                        point = 840;
                        LocalDate futureDate = oEndDate.plusMonths(4);
                        nEndDate = futureDate.format(dateFormatter);
                    }

                    //===================== VALIDATE POST =========================
                    String mess = service.validateUpdatePost(name, price, area, address);
                    if (!mess.equals("OK")) {
                        request.setAttribute("mess", mess);
                        request.setAttribute("ptlist", listPropertyType);
                        RequestDispatcher disp = request.getRequestDispatcher("/EditPostedProperty.jsp");
                        disp.forward(request, response);
                    } //======================= CHECK POINT HERE ===========================
                    else if (landlord.getAccountPoint() < point) {
                        mess = "You do not have enough point.";
                        request.setAttribute("mess", mess);
                        request.setAttribute("ptlist", listPropertyType);
                        RequestDispatcher disp = request.getRequestDispatcher("/EditPostedProperty.jsp");
                        disp.forward(request, response);
                    } else {
                        //=================== UPDATE POST ================================
                        Property p = new Property(oldpid, name, Integer.parseInt(price),
                                Integer.parseInt(typeId), Integer.parseInt(area),
                                Integer.parseInt(numOfBed), address, desc, user.getId(), 1, 0, startDate, nEndDate);
                        service.updatePost(p);
                        if (point != 0) {
                            service.addTransactionsPayforPost(point, user.getId());
                        }
                        //update session
                        Landlord updateLandlord = dao.findById(user.getId());
                        session.setAttribute("landlord", updateLandlord); 
//                        
//                        if (listParts==null) {
//                            out.print("1");
//                        } else {
//                            out.print(listParts.size());
//                        }
//                        for (Part x : listParts) {
//                          if(!x.getSubmittedFileName().isEmpty()){
//                              out.print(x.getSubmittedFileName()+".<br>");
//                          }
//                            
//                            }
                        //==================== UPLOAD IMAGE AND ADD TO DB =======================
                        String tmp = request.getServletContext().getRealPath("");
                        String path = tmp.substring(0, tmp.indexOf("build\\web\\")) + "web\\assets\\";
                        List<Part> listParts = (List<Part>) request.getParts();
                        int postID = Integer.parseInt(request.getParameter("pid"));
//                        PropertyImage img = iDao.getThumbnailByID(postID);
//                        out.print(postID + " "+ img);
                        service.updateFile(listParts, path, postID);
//                        ==================== REDIRECT ===========================
                        mess = "Edit post successfully.";
                        request.setAttribute("mess", mess);
                        request.setAttribute("ptlist", listPropertyType);
                        RequestDispatcher disp = request.getRequestDispatcher("/EditPostedProperty.jsp");
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
