/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Landlord;
import entity.Tenant;
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
public class DAOTenant extends DBConnect {

    public Tenant findById(int id) {
        Tenant user = null;
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Tenant]\n"
                + "  WHERE [id] = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                user = new Tenant(id, fName, lName, address, phone);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBannedAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public ResultSet findById2(String id) {
        String sql = "SELECT U.id, U.email, U.hashed_password, T.first_name, T.last_name, T.address, T.phone, U.status\n"
                + "FROM Users U\n"
                + "INNER JOIN Tenant T ON U.id = T.id where T.id = " + id;
        ResultSet rs = getData(sql);
        return rs;
    }

    public ResultSet getAll() {
        String sql = "SELECT U.id, U.email, U.hashed_password, T.first_name, T.last_name, T.address, T.phone, U.status\n"
                + "FROM Users U\n"
                + "INNER JOIN Tenant T ON U.id = T.id;";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int addTenant(Tenant tenant) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Tenant]\n"
                + "           ([id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "         )\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, tenant.getId());
            pre.setString(2, tenant.getFirstName());
            pre.setString(3, tenant.getLastName());
            pre.setString(4, tenant.getAddress());
            pre.setString(5, tenant.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTenant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public String getFullnameByID(int id) {
        String fullname = null;
        String sql = "SELECT [first_name]\n"
                + "      ,[last_name]\n"
                + "  FROM [dbo].[Tenant]\n"
                + "  WHERE [id] = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                fullname = rs.getString("first_name") + ' ' + rs.getString("last_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOTenant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fullname;
    }

    public int updateTenant(Tenant tenant) {
        int n = 0;
        String sql = "UPDATE [dbo].[Tenant]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, tenant.getFirstName());
            pre.setString(2, tenant.getLastName());
            pre.setString(3, tenant.getAddress());
            pre.setString(4, tenant.getPhone());
            pre.setInt(5, tenant.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTenant.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOTenant dao = new DAOTenant();
        ResultSet tenant = dao.findById2("3");
        System.out.println(tenant);
                System.out.println(tenant);

//        ResultSet rs = dao.getAll();
//        try {
//            while (rs.next()) {
//                System.out.println(rs.getString(2));
//            }
//
////        Admin admin = dao.findByEmailAndPassword("admin@gmail.com", "123456");
////        System.out.println(admin);
////         System.out.println(dao.findByEmail("admin@gmail.co"));
//        } catch (SQLException ex) {
//            System.out.println("12331");
//            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
