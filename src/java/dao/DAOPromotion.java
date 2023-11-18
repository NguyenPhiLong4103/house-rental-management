/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Promotion;
import entity.Property;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOPromotion extends DBConnect {

    DAOProperty daoProperty = new DAOProperty();
    public Promotion getPromotionById(int promotionID) {
        Promotion promotion = null;
        String sql = "SELECT * FROM Promotions WHERE promotion_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, promotionID);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int discount = rs.getInt("discount");
                String description = rs.getString("descriptions");
                String promotionStartDate = rs.getString("promotion_start_date");
                String promotionEndDate = rs.getString("promotion_end_date");
                promotion = new Promotion(promotionID, discount, description, promotionStartDate, promotionEndDate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promotion;
    }

    public int addPromotion(Promotion prom) {
        int generatedPromotionID = 0; // Initialize with a default value
        String sql = "INSERT INTO Promotions (discount, descriptions, promotion_start_date, promotion_end_date) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, prom.getDiscount());
            pre.setString(2, prom.getDescription());
            pre.setString(3, prom.getPromotionStartDate());
            pre.setString(4, prom.getPromotionEndDate());
            pre.executeUpdate();

            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedPromotionID = generatedKeys.getInt(1); // Get the generated promotion ID
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedPromotionID;
    }

    public List<Promotion> getListPromotion() {
        List<Promotion> promotion = new ArrayList<>();
        String sql = "SELECT * FROM Promotions";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("promotion_id");
                int discount = rs.getInt("discount");
                String description = rs.getString("descriptions");
                String promotionStartDate = rs.getString("promotion_start_date");
                String promotionEndDate = rs.getString("promotion_end_date");
                Promotion prom = new Promotion(id, discount, description, promotionStartDate, promotionEndDate);
                promotion.add(prom);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promotion;
    }

    
    public int updatePromotion(Promotion prom) {
        int n = 0;
        String sql = "UPDATE Promotions \n"
                + "SET discount = ?,\n"
                + "descriptions = ?,\n"
                + "promotion_start_date = ?,\n"
                + "promotion_end_date = ?\n"
                + "WHERE promotion_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, prom.getDiscount());
            pre.setString(2, prom.getDescription());
            pre.setString(3, prom.getPromotionStartDate());
            pre.setString(4, prom.getPromotionEndDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void editPromotion(Promotion prom) {
        String sql = "UPDATE Promotions SET discount=?, descriptions=?, promotion_start_date=?, promotion_end_date=? WHERE promotion_id=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, prom.getDiscount());
            pre.setString(2, prom.getDescription());
            pre.setString(3, prom.getPromotionStartDate());
            pre.setString(4, prom.getPromotionEndDate());
            pre.setInt(5, prom.getPromotionID()); // Set the existing Promotion ID

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Promotion> getListPromotionById(int promotionID) {
        List<Promotion> promotion = new ArrayList<>();
        String sql = "SELECT * FROM Promotions Where promotion_id=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, promotionID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int discount = rs.getInt("discount");
                String description = rs.getString("descriptions");
                String promotionStartDate = rs.getString("promotion_start_date");
                String promotionEndDate = rs.getString("promotion_end_date");
                Promotion prom = new Promotion(promotionID, discount, description, promotionStartDate, promotionEndDate);
                promotion.add(prom);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promotion;
    }

    public int removePromotion(Promotion prom) {
        int n = 0;
        Property p = getPropertyByPromotion(prom.getPromotionID());
        if(p!=null){
            removePromotionInpost(p);
        }
        String sql = "DELETE FROM Promotions WHERE promotion_id=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, prom.getPromotionID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }    
    
    public int removePromotionInpost(Property p){
        int n = 0;
        String sql = "update Post set promotion_id = null where promotion_id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, p.getPromotionID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Property getPropertyByPromotion(int id){
        String sql ="select * from post where promotion_id = " + id;
        ResultSet rs = getData(sql);
        try {
            if(rs.next()){
                int pid = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int type = rs.getInt("type");
                int area = rs.getInt("area");
                int numOfBedroom = rs.getInt("NumOfBedrooms");
                String address = rs.getString("address");
                String description = rs.getString("description");
                int landlordID = rs.getInt("landlord_id");
                int status = rs.getInt("status");
                int promotionID = rs.getInt("promotion_id");
                String startDate = rs.getString("post_start_date");
                String endDate = rs.getString("post_end_date");
                Property property = new Property(pid, name, price, type, area, numOfBedroom, address, description, landlordID, status, promotionID, startDate, endDate);            
                return property;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        
         
    }
}
