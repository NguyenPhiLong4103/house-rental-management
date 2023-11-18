/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LE HAI THINH
 */
public class DAOReport extends DBConnect {

    public int sendReportSystem(Report rp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Report]\n"
                + "           ([Reporter_id]"
                + "           ,[Categories]"
                + "           ,[Description]"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, rp.getReporter_id());
            pre.setString(2, rp.getCategories());
            pre.setString(3, rp.getDescription());
            pre.setString(4, rp.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
        public int sendReportTenant(Report rp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Report]\n"
                + "           ([Reporter_id]"
                + "           ,[Reported_id]"
                + "           ,[Categories]"
                + "           ,[Description]"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, rp.getReporter_id());
            pre.setInt(2, rp.getReported_id());
            pre.setString(3, rp.getCategories());
            pre.setString(4, rp.getDescription());
            pre.setString(5, rp.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

                public int sendReportProperty(Report rp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Report]\n"
                + "           ([Reporter_id]"
                + "           ,[Reported_id]"
                + "           ,[Property_id]"
                + "           ,[Categories]"
                + "           ,[Description]"
                + "           ,[Status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, rp.getReporter_id());
            pre.setInt(2, rp.getReported_id());
            pre.setInt(3, rp.getProperty_id());
            pre.setString(4, rp.getCategories());
            pre.setString(5, rp.getDescription());
            pre.setString(6, rp.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public ResultSet getAll() {
        String sql = "SELECT *from Report order BY \n" +
"  CASE \n" +
"    WHEN Status = 'process' THEN 1\n" +
"    WHEN Status = 'unread' THEN 2\n" +
"    WHEN Status = 'done' THEN 3\n" +
"  END;";
        ResultSet rs = getData(sql);
        return rs;
    }

    public int updateStatus(int id, String status) {
        int n = 0;
        String sql = "UPDATE [dbo].[Report]\n"
                + "   SET [status] = '" + status + "'\n"
                + " WHERE report_id = " + id;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
//        public Report findById(int id){
//        Report rp= null;
//        String sql ="select * from Report where report_id = "+ id;
//        ResultSet rs= getData(sql);
//        try {
//            if(rs.next()){
//                int report = rs.getInt("report_id");
//                int reporter = rs.getInt("reporter_id");
//                int property = rs.getInt("property_id");
//                int reported = rs.getInt("reported_id");
//                String cat = rs.getString("categories");
//                String description = rs.getString("description");
//                String status = rs.getString("status");
//                rp = new Report(report, reporter, property, reported, cat, description, status);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return rp;
//    }

    public ResultSet findById(int id) {
        String sql = "select * from Report where report_id = " + id;
        ResultSet rs = getData(sql);
        return rs;
    }

    public static void main(String[] args) {
        DAOReport dao = new DAOReport();
        //System.out.println(dao.loginAccount("landlord2@gmail.com","12345678"));
//        Account acc= new Account(0, "test@gmail.com", EncryptionMD5.encyption("12345678"), 1, 0);
        Report rp = new Report(2, null, null, 2, "System", "viết dấu mà không được huhu", "Unread");
        System.out.println(dao.sendReportSystem(rp));
//        System.out.println(dao.updateStatus(2, 3));
    }
}
