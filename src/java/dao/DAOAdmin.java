/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Admin;
import entity.BannedAccount;
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
public class DAOAdmin extends DBConnect {

    public Admin findByEmailAndPassword(String email, String password) {
        Admin user = null;
        String hashPassword = EncryptionMD5.encyption(password);
        String sql = "select id,email, hashed_password from Admin where email = ? and hashed_password = ?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2, hashPassword);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                user = new Admin(rs.getInt("id"), email, rs.getString("hashed_password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int addAdmin(Admin admin) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Admin]\n"
                + "           ([id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, admin.getId());
            pre.setString(2, admin.getFirstName());
            pre.setString(3, admin.getLastName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Admin findById(int id) {
        Admin admin = null;
        String sql = "select * from Admin where id = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                admin = new Admin(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public ResultSet findById2(String id) {
        String sql = "SELECT U.id, U.email, U.hashed_password, A.first_name, A.last_name\n"
                + "FROM Users U\n"
                + "INNER JOIN Admin A ON U.id = A.id where A.id = " + id;
        ResultSet rs = getData(sql);
        return rs;
    }

    public ResultSet getAll() {
        String sql = "SELECT U.id, U.email, U.hashed_password, A.first_name, A.last_name, U.status\n"
                + "FROM Users U\n"
                + "INNER JOIN Admin A ON U.id = A.id";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int updateAdmin(Admin admin) {
        int n = 0;
        String sql = "UPDATE [dbo].[Admin]\n"
                + "   SET [first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, admin.getFirstName());
            pre.setString(2, admin.getLastName());
            pre.setInt(3, admin.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
        ResultSet rs = dao.getAll();
        double a= 1.19, t=0, d=0;
        while(d!=2){
        t=t+a;
        a=a*0.95;
        d++;
        }
         System.out.println("d = " + d);
         System.out.println("t = "+ t);
        
//         try {
//             while(rs.next()){
//                 System.out.println(rs.getString(2));
//             }
//             
////        Admin admin = dao.findByEmailAndPassword("admin@gmail.com", "123456");
////        System.out.println(admin);
////         System.out.println(dao.findByEmail("admin@gmail.co"));
//         } catch (SQLException ex) {
//             System.out.println("12331");
//             Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
//         }
    }
}
