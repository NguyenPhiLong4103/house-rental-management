/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Transactions;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGOCDUC
 */
public class DAOTransactions extends DBConnect {

    public void addTransactionsPayforPost(int point, int landlordId) {   //user, cart 
        try {
            //get postId
            int postId = getNewPostId();

            //insert into Transtaction
            String sql1 = "insert into [Transactions] ([amount],[payer_id],[type],[transaction_date],[post_id],[receiver_id])\n"
                    + "values (?,?,1, getdate(), ?, null)";   //payforpost: type = 1, receiver_id = null
            PreparedStatement stm = conn.prepareStatement(sql1);
            stm.setInt(1, point);
            stm.setInt(2, landlordId);
            stm.setInt(3, postId);
            stm.executeUpdate();

            //minus point in Landlord's account
            String sql2 = "update [Landlord] set account_points = account_points - ? where [id] = ?";
            stm = conn.prepareStatement(sql2);
            stm.setInt(1, point);
            stm.setInt(2, landlordId);
            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addTransactionsPayforRePost(int point, int landlordId, int postId) {   //user, cart 
        try {

            //insert into Transtaction
            String sql1 = "insert into [Transactions] ([amount],[payer_id],[type],[transaction_date],[post_id],[receiver_id])\n"
                    + "values (?,?,1, getdate(), ?, null)";   //payforpost: type = 1, receiver_id = null
            PreparedStatement stm = conn.prepareStatement(sql1);
            stm.setInt(1, point);
            stm.setInt(2, landlordId);
            stm.setInt(3, postId);
            stm.executeUpdate();

            //minus point in Landlord's account
            String sql2 = "update [Landlord] set account_points = account_points - ? where [id] = ?";
            stm = conn.prepareStatement(sql2);
            stm.setInt(1, point);
            stm.setInt(2, landlordId);
            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getNewPostId() {
        String sql = " select top(1) [id] from Post order by id desc ";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int addTransactionsAddPoint(Transactions transactions) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Transactions]\n"
                + "           ([amount]\n"
                + "           ,[payer_id]\n"
                + "           ,[type]\n"
                + "           ,[transaction_date]\n"
                + "           ,[post_id]\n"
                + "           ,[receiver_id])\n"
                + "     VALUES\n"
                + "			(?,?,?,?,?,?)";  //addpoint: type = 2, post_id = null
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, transactions.getAmount());
            pre.setInt(2, transactions.getPayerID());
            pre.setString(3, transactions.getType());
            pre.setDate(4, getCurrentDate());
            pre.setString(5, null);
            pre.setInt(6, transactions.getReceiverID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public ArrayList<Transactions> getAddPointTransactions() {
        ArrayList<Transactions> list = new ArrayList<Transactions>();
        ResultSet rs = getData("SELECT [transaction_id]\n"
                + "      ,[amount]\n"
                + "      ,[payer_id]\n"
                + "      ,[type]\n"
                + "      ,[transaction_date]\n"
                + "      ,[post_id]\n"
                + "      ,[receiver_id]\n"
                + "  FROM [dbo].[Transactions]\n"
                + "  WHERE [type] = '2'\n"
                + "  ORDER BY [transaction_id] DESC");
        try {
            while (rs.next()) {
                Transactions addpoint = new Transactions();
                addpoint.setId(rs.getInt("transaction_id"));
                addpoint.setAmount(rs.getInt("amount"));
                addpoint.setPayerID(rs.getInt("payer_id"));
                addpoint.setType(rs.getString("type"));
                addpoint.setDate(rs.getDate("transaction_date"));
                addpoint.setPostID(rs.getInt("post_id"));
                addpoint.setReceiverID(rs.getInt("receiver_id"));
                list.add(addpoint);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Transactions> getPayforPostTransactions() {
        ArrayList<Transactions> list = new ArrayList<Transactions>();
        ResultSet rs = getData("SELECT [transaction_id]\n"
                + "      ,[amount]\n"
                + "      ,[payer_id]\n"
                + "      ,[type]\n"
                + "      ,[transaction_date]\n"
                + "      ,[post_id]\n"
                + "      ,[receiver_id]\n"
                + "  FROM [dbo].[Transactions]\n"
                + "  WHERE [type] = '1'\n"
                + "  ORDER BY [transaction_id] DESC");
        try {
            while (rs.next()) {
                Transactions addpoint = new Transactions();
                addpoint.setId(rs.getInt("transaction_id"));
                addpoint.setAmount(rs.getInt("amount"));
                addpoint.setPayerID(rs.getInt("payer_id"));
                addpoint.setType(rs.getString("type"));
                addpoint.setDate(rs.getDate("transaction_date"));
                addpoint.setPostID(rs.getInt("post_id"));
                addpoint.setReceiverID(rs.getInt("receiver_id"));
                list.add(addpoint);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Date getCurrentDate() {
        LocalDate curDate = java.time.LocalDate.now();
        return Date.valueOf(curDate.toString());
    }

    public List<Transactions> getTransationHistory(int id, String typeTransaction, String sortType) {
        if (typeTransaction == null || typeTransaction.isEmpty()) {  //getAll
            typeTransaction = "";
        }
        if (sortType == null || sortType.isEmpty()) {  //getAll
            sortType = " transaction_date desc, transaction_id desc ";
        }
        List<Transactions> list = new ArrayList<>();
        try {
            String sql = " select * from Transactions where (payer_id = ? or receiver_id = ?)"
                    + " and type like '%" + typeTransaction + "%' "
                    + " order by " + sortType;
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                Transactions pro = new Transactions();
                pro.setId(rs.getInt("transaction_id"));
                pro.setAmount(rs.getInt("amount"));
                pro.setPayerID(rs.getInt("payer_id"));
                pro.setType(rs.getString("type"));
                pro.setDate(rs.getDate("transaction_date"));
                pro.setPostID(rs.getInt("post_id"));
                pro.setReceiverID(rs.getInt("receiver_id"));
                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
