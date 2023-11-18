/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Property;
import entity.Wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOWishlist extends DBConnect {

    public Wishlist getByID(int id) {
        Wishlist wishlist = null;
        ResultSet rs = getData("SELECT id\n"
                + ",user_id\n"
                + ",property_id \n"
                + "From Wishlist \n"
                + "where id = " + id);
        try {
            if (rs.next()) {
                int wishlistID = rs.getInt("id");
                int userID = rs.getInt("user_id");
                int propertyID = rs.getInt("property_id");
                wishlist = new Wishlist(wishlistID, userID, propertyID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wishlist;
    }

    public int addToWishlist(int userID, int propertyID) {
        Wishlist wish = null;
        int n = 0;
        String sql = "INSERT INTO Wishlist (user_id,property_id) VALUES (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userID);
            pre.setInt(2, propertyID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeFromWishlist(int userID, int propertyID) {
        int n = 0;
        String sql = "DELETE FROM Wishlist WHERE user_id = ? AND property_id = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, userID);
            pre.setInt(2, propertyID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOWishlist.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public List<Wishlist> getWishlistByUserID(int userID) {
        List<Wishlist> wishlistItems = new ArrayList<>();
        String sql = "SELECT DISTINCT user_id, property_id FROM Wishlist WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, userID);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int propertyID = rs.getInt("property_id");
                Wishlist wishlist = new Wishlist(0, userID, propertyID);
                wishlistItems.add(wishlist);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return wishlistItems;
    }

    public boolean isInWishlist(int userID, int propertyID) throws SQLException {
        String sql = "SELECT COUNT(*) \n"
                + "FROM [SWP391].[dbo].[Wishlist]\n"
                + "WHERE [user_id] = " + userID + " and [property_id] = " + propertyID;
        ResultSet rs = getData(sql);
        rs.next();
        return rs.getInt(1) != 0;
    }
}
