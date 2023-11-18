/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOProperty;
import dao.DAOPropertyImage;
import dao.DAOPropertyType;
import dao.DAOTransactions;
import entity.Property;
import entity.PropertyImage;
import entity.PropertyType;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duchi
 */
public class PostPropertyService {

    private DAOProperty pDao = new DAOProperty();
    private DAOPropertyImage iDao = new DAOPropertyImage();
    private DAOPropertyType ptDao = new DAOPropertyType();
    private DAOTransactions tDao = new DAOTransactions();

    public void uploadFile(List<Part> fileParts, String path) {
        int postId = pDao.getNewId();
        for (Part p : fileParts) {
            if (p.getSubmittedFileName() != null) {
                String fileName = p.getSubmittedFileName();
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss");
                String formattedDateTime = currentDateTime.format(formatter);
                String tmp = formattedDateTime;
                tmp = tmp.replace('.', '_');
                tmp = tmp.replace(':', '_');
                tmp = tmp.replace('-', '_');
                String uniqueFilename = tmp + fileName;
                uniqueFilename=uniqueFilename.replace(' ', '_');
                try {
                    p.write(path + uniqueFilename);
                } catch (IOException ex) {
                    Logger.getLogger(PostPropertyService.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(p.getName().equals("thumbnail")){
                    //add thumbnail
                    PropertyImage img = new PropertyImage(0, postId, "assets\\"+uniqueFilename, 1);
                    iDao.addNewImage(img);
                }
                else{
                    PropertyImage img = new PropertyImage(0, postId, "assets\\"+uniqueFilename, 0);
                    iDao.addNewImage(img);
                }
            }
        }
    }
    
    public void updateFile(List<Part> fileParts, String path, int postID) {
        
        int count = 0;
        for (Part p : fileParts) {
            if (p.getSubmittedFileName()!=null && !p.getSubmittedFileName().isEmpty() ) {
                String fileName = p.getSubmittedFileName();
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy-HH-mm-ss");
                String formattedDateTime = currentDateTime.format(formatter);
                String tmp = formattedDateTime;
                tmp = tmp.replace('.', '_');
                tmp = tmp.replace(':', '_');
                tmp = tmp.replace('-', '_');
                String uniqueFilename = tmp + fileName;
                uniqueFilename=uniqueFilename.replace(' ', '_');
                try {
                    p.write(path + uniqueFilename);
                } catch (IOException ex) {
                    Logger.getLogger(PostPropertyService.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(p.getName().equals("thumbnail")){
                    //find thumbnail
                    
                    PropertyImage img = iDao.findThumbnailByID(postID);
                    img.setImgURL("assets\\"+uniqueFilename);
                    iDao.updateImage(img);
                    
                }
                else{
                    if(count==0){
                        //delete all img detail
                        iDao.deleteImageByID(postID);
                        count++;
                    }
                   
                    PropertyImage img = new PropertyImage(0, postID, "assets\\"+uniqueFilename, 0); 
                    System.out.println(img);
                    iDao.addNewImage(img);
                }
            }
        }
    }

    public void addNewPost(Property pro) {
        pDao.addProperty(pro);
    }
    
    public void updatePost(Property pro) {
        pDao.updateProperty(pro);
    }
    
    public void addTransactionsPayforPost(int point, int landlordId){
        tDao.addTransactionsPayforPost(point, landlordId);
    }

    public String validatePost(List<Part> parts,String name, String price, String area, String address) {
        String regex = ".*\\.(?i)(jpg|jpeg|png|gif|bmp)$";
        if(!price.matches("^[1-9]\\d*$")){
            return "Price must be an integer number and greater than 0 and smaller than 1000000000VND.";
        }
        if(!area.matches("^[1-9]\\d*$")){
            return "Area must be an integer number and greater than 0 and smaller than 10000square.";
        }
        if(!(name.matches("^(?=.*[a-zA-Z]).*$") && name.length() >= 5 && name.length() <= 20)){
            return "Name of property contains at least one letter and includes 5-20 characters consisting of letters, digits, and hyphens. ";
        }
        if(!(address.matches("^(?=.*[a-zA-Z]).*$") && name.length() >= 5 && name.length() <= 100)){
            return "Address of property contains at least one letter and includes 5-100 characters consisting of letters, digits, and hyphens. ";
        }
        
        for (Part p : parts) {
            if (p.getSubmittedFileName() != null) {
                String fileName = p.getSubmittedFileName();
                if (!fileName.matches(regex)) {
                    return "Post image must be a jpe, jpeg, png , gif or bmp file";
                }
            }
        }
        return "OK";
    }
     public String validateUpdatePost(String name, String price, String area, String address) {
        String regex = ".*\\.(?i)(jpg|jpeg|png|gif|bmp)$";
        if(!price.matches("^[1-9]\\d*$")){
            return "Price must be an integer number and greater than 0.";
        }
        if(!area.matches("^[1-9]\\d*$")){
            return "Area must be an integer number and greater than 0.";
        }
        if(!(name.matches("^(?=.*[a-zA-Z]).*$") && name.length() >= 5 && name.length() <= 20)){
            return "Name of property contains at least one letter and includes 5-20 characters consisting of letters, digits, and hyphens. ";
        }
        if(!(address.matches("^(?=.*[a-zA-Z]).*$") && name.length() >= 5 && name.length() <= 100)){
            return "Address of property contains at least one letter and includes 5-100 characters consisting of letters, digits, and hyphens. ";
        }
        
        return "OK";
    }
     
    public List<PropertyType> getListPropertyTypes() {
        List<PropertyType> list = ptDao.getListPropertyTypes();
        return list;
    }
    
    public static void main(String[] args) {
        PostPropertyService service = new PostPropertyService();
        System.out.println(service.validateUpdatePost("Hieuuu", "123", "132", "aijadsj"));
    }
}
