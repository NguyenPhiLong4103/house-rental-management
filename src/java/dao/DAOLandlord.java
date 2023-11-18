/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Landlord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.EncryptionMD5;

/**
 *
 * @author duchi
 */
public class DAOLandlord extends DBConnect {

    public Landlord findById(int id) {
        Landlord user = null;
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Landlord]\n"
                + "  WHERE [id] = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String civil_id = rs.getString("civil_id");
                int accountPoint = rs.getInt("account_points");
                user = new Landlord(id, fName, lName, address, phone, civil_id, accountPoint);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBannedAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int addLandlord(Landlord landlord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Landlord]\n"
                + "           ([id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[civil_id]\n"
                + "           ,[account_points])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, landlord.getId());
            pre.setString(2, landlord.getFirstName());
            pre.setString(3, landlord.getLastName());
            pre.setString(4, landlord.getAddress());
            pre.setString(5, landlord.getPhone());
            pre.setString(6, landlord.getCivil_id());
            pre.setInt(7, landlord.getAccountPoint());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLandlord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ResultSet getAll() {
        String sql = "SELECT U.id, U.email, U.hashed_password,\n"
                + "  L.first_name, L.last_name, L.address,\n"
                + " L.phone, L.civil_id, U.status, L.account_points\n"
                + "FROM Users U\n"
                + "INNER JOIN Landlord L ON U.id = L.id";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addPointLandlord(int id, int point) {
        int n = 0;
        String sql = "UPDATE [dbo].[Landlord]\n"
                + "   SET [account_points] = ?\n"
                + " WHERE [id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, point);
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLandlord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateLanlord(Landlord landlord) {
        int n = 0;
        String sql = "UPDATE [dbo].[Landlord]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[civil_id] = ?\n"
                + "      ,[account_points] = ?\n"
                + " WHERE id =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, landlord.getFirstName());
            pre.setString(2, landlord.getLastName());
            pre.setString(3, landlord.getAddress());
            pre.setString(4, landlord.getPhone());
            pre.setString(5, landlord.getCivil_id());
            pre.setInt(6, landlord.getAccountPoint());
            pre.setInt(7, landlord.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOLandlord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ResultSet findById2(String id) {
        String sql = "SELECT U.id, U.email, U.hashed_password,\n"
                + "  L.first_name, L.last_name, L.address,\n"
                + " L.phone, L.civil_id, U.status, L.account_points\n"
                + "FROM Users U\n"
                + "INNER JOIN Landlord L ON U.id = L.id where L.id = " + id;
        ResultSet rs = getData(sql);
        return rs;
    }
    
    public String getFullnameByID(int id) {
        String fullname = null;
        String sql = "SELECT [first_name]\n"
                + "      ,[last_name]\n"
                + "  FROM [dbo].[Landlord]\n"
                + "  WHERE [id] = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                fullname = rs.getString("first_name") + ' ' + rs.getString("last_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLandlord.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fullname;
    }

    public static void main(String[] args) {
        DAOLandlord dao = new DAOLandlord();
        // System.out.println(dao.findById(3));
        System.out.println(dao.getAll());
    }
}
