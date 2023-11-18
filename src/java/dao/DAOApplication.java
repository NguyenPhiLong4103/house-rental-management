/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Application;
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
public class DAOApplication extends DBConnect {

    public Application getByID(int id) {
        Application application = null;
        String sql = "SELECT [order_id]\n"
                + "      ,[tenant_id]\n"
                + "      ,[landlord_id]\n"
                + "      ,[post_id]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[Orders]\n"
                + "  WHERE [order_id] = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                application = new Application(rs.getInt("order_id"), rs.getInt("tenant_id"), rs.getInt("landlord_id"), rs.getInt("post_id"), rs.getInt("status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return application;
    }

    public List<Application> getByTenantID(int tenantID, String txtSearch) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT [order_id]\n"
                + "      ,o.[landlord_id]\n"
                + "      ,[post_id]\n"
                + "      ,o.[status]\n"
                + "  FROM [dbo].[Orders] o\n"
                + "  JOIN [dbo].[Post] p\n"
                + "  ON o.[post_id] = p.[id]\n"
                + "  WHERE [tenant_id] = " + tenantID + " AND [name] like '%" + txtSearch + "%'\n"
                + "  ORDER BY [status], [order_id] DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                applications.add(new Application(rs.getInt("order_id"), 0, rs.getInt("landlord_id"), rs.getInt("post_id"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }
    
        public List<Application> getByTenantID2(int tenantID) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT [order_id]\n"
                + "      ,o.[landlord_id]\n"
                + "      ,[post_id]\n"
                + "      ,o.[status]\n"
                + "  FROM [dbo].[Orders] o\n"
                + "  JOIN [dbo].[Post] p\n"
                + "  ON o.[post_id] = p.[id]\n"
                + "  WHERE [tenant_id] = " + tenantID + "\n"
                + "  ORDER BY [status], [order_id] DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                applications.add(new Application(rs.getInt("order_id"), 0, rs.getInt("landlord_id"), rs.getInt("post_id"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }

    public List<Application> getByLandlordID(int landlordID, String txtSearch) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT [order_id]\n"
                + "      ,[tenant_id]\n"
                + "      ,[post_id]\n"
                + "      ,o.[status]\n"
                + "  FROM [dbo].[Orders] o\n"
                + "  JOIN [dbo].[Post] p\n"
                + "  ON o.[post_id] = p.[id]\n"
                + "  WHERE o.[landlord_id] = " + landlordID + " AND [name] like '%" + txtSearch + "%'\n"
                + "  ORDER BY [status], [order_id] DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                applications.add(new Application(rs.getInt("order_id"), rs.getInt("tenant_id"), 0, rs.getInt("post_id"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }
    
        public List<Application> getByLandlordID2(int landlordID) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT [order_id]\n"
                + "      ,[tenant_id]\n"
                + "      ,[post_id]\n"
                + "      ,o.[status]\n"
                + "  FROM [dbo].[Orders] o\n"
                + "  JOIN [dbo].[Post] p\n"
                + "  ON o.[post_id] = p.[id]\n"
                + "  WHERE o.[landlord_id] = " + landlordID + "\n"
                + "  ORDER BY [status], [order_id] DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                applications.add(new Application(rs.getInt("order_id"), rs.getInt("tenant_id"), 0, rs.getInt("post_id"), rs.getInt("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }

    public void create(Application application) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([tenant_id]\n"
                + "           ,[landlord_id]\n"
                + "           ,[post_id]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, application.getTenantID());
            ps.setInt(2, application.getLandlordID());
            ps.setInt(3, application.getPostID());
            ps.setInt(4, application.getStatus());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeStatus(int applicationID, int newStatus) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [status] = ?\n"
                + " WHERE [order_id] = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newStatus);
            ps.setInt(2, applicationID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelRemaining(int propertyID) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [status] = 2\n"
                + " WHERE [post_id] = ? and [status] = 0";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, propertyID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isApplicationSubmitted(int propertyID, int tenantID) throws SQLException {
        String sql = "SELECT COUNT(*)\n"
                + "  FROM [dbo].[Orders]\n"
                + "  WHERE [post_id] = " + propertyID + " and [tenant_id] = " + tenantID + " and [status] = 0";
        ResultSet rs = getData(sql);
        rs.next();
        return rs.getInt(1) != 0;
    }
}
