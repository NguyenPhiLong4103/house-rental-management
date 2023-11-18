/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Report;
import entity.Review;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DAOReview extends DBConnect {

    public List<Review> findByPropertyID(int propertyID) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT [user_id]\n"
                + "      ,[rating]\n"
                + "      ,[review]\n"
                + "  FROM [dbo].[Review]\n"
                + "  WHERE [property_id] = " + propertyID;
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Review review = new Review();
                review.setUserID(rs.getInt("user_id"));
                review.setRating(rs.getInt("rating"));
                review.setReviewDescription(rs.getString("review"));
                reviews.add(review);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reviews;
    }
        public int sendReview(Review rv) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Review]\n"
                + "           ([user_id]"
                + "           ,[property_id]"
                + "           ,[rating]"
                + "           ,[review])\n"
                + "     VALUES\n" 
                + "           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, rv.getUserID());
            pre.setInt(2, rv.getPropertyID());
            pre.setInt(3, rv.getRating());
            pre.setString(4, rv.getReviewDescription());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
            public int updateReview(int rating ,int uid, int pid, String des) {
        int n = 0;
        String sql = "UPDATE [dbo].[Review]\n"
                + "   SET [rating] = ?\n"
                + "      ,[review] = ?\n"
                + " WHERE user_id = ? and property_id =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, rating);
            pre.setString(2, des);
            pre.setInt(3, uid);
            pre.setInt(4, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
        public ResultSet viewReview(int id) {
        String sql = "SELECT a.id, a.landlord_id, a.name, b.*, l.first_name, l.last_name, c.first_name as tfirst_name, c.last_name as tlast_name\n" +
"FROM (SELECT p.id, p.landlord_id, p.name FROM Post p WHERE p.landlord_id = "+id+") AS a\n" +
"JOIN Review b ON a.id = b.property_id\n" +
"JOIN Landlord l ON a.landlord_id = l.id\n" +
"JOIN Tenant c on b.user_id = c.id";
        ResultSet rs = getData(sql);
        return rs;
    }
        public ResultSet getLandlordName(int id){
        String sql = "select first_name, last_name from Landlord where id = " + id;
        ResultSet rs = getData(sql);
            return rs;
        }
                public ResultSet avgRating(int id){
        String sql = "SELECT a.id, a.landlord_id, a.name, b.*, l.first_name, l.last_name, c.first_name as tfirst_name, c.last_name as tlast_name\n" +
"FROM (SELECT p.id, p.landlord_id, p.name FROM Post p WHERE p.landlord_id = "+id+") AS a\n" +
"JOIN Review b ON a.id = b.property_id\n" +
"JOIN Landlord l ON a.landlord_id = l.id\n" +
"JOIN Tenant c on b.user_id = c.id ";
        ResultSet rs = getData(sql);
            return rs;
        }
            public static void main(String[] args) {
        DAOReview dao = new DAOReview();
        //System.out.println(dao.loginAccount("landlord2@gmail.com","12345678"));
//        Account acc= new Account(0, "test@gmail.com", EncryptionMD5.encyption("12345678"), 1, 0);
//        Review rv = new Review(0, 2, 2, 4, "Tại sao viết ở đây lại được");
//        dao.sendReview(rv);
//        System.out.println(dao.updateStatus(2, 3));
//dao.updateReview(3, 2, 3, "test");
    }
}
