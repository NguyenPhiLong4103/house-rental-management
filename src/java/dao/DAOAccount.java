/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
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
public class DAOAccount extends DBConnect{
    public Account findAccountVertified(String email){
        Account acc = null;
        String sql = "select * from Users where email = '"+email+"' and status != 0";
        ResultSet rs = getData(sql);
        try {
            if(rs.next()){
                int id = rs.getInt("id");
                String hashed_password = rs.getString("hashed_password");
                int role = rs.getInt("role_id");
                int status = rs.getInt("status");
                acc = new Account(id, email, hashed_password, role, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return acc;
    }
    
    public Account loginAccount(String email, String password){
        String hashedPassword = EncryptionMD5.encyption(password);
        Account acc = null;
        String sql = "Select * from Users where email = ? and hashed_password = ? and (status = 2 Or status = 3)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2,hashedPassword);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String hashed_password = rs.getString("hashed_password");
                int role = rs.getInt("role_id");
                int status = rs.getInt("status");
                acc = new Account(id, email, hashed_password, role, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
    
     public Account checkAccount(String email, String password){
        String hashedPassword = EncryptionMD5.encyption(password);
        Account acc = null;
        String sql = "Select * from Users where email = ? and hashed_password = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            pre.setString(2,hashedPassword);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String hashed_password = rs.getString("hashed_password");
                int role = rs.getInt("role_id");
                int status = rs.getInt("status");
                acc = new Account(id, email, hashed_password, role, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
    
    public Account findByEmailAndRole(String email, int role){
        Account acc = null;
        String sql = "select * from Users where email = '"+email +"' and role_id = "+ role;
        ResultSet rs = getData(sql);
        try {
            if(rs.next()){
                int id = rs.getInt("id");
                String hashed_password = rs.getString("hashed_password");
                int status = rs.getInt("status");
                acc = new Account(id, email, hashed_password, role, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return acc;
    }
    
    public int updateAccount(Account acc){
        int n=0;
        String sql="UPDATE [dbo].[Users]\n" +
"   SET [email] = ?\n" +
"      ,[hashed_password] = ?\n" +
"      ,[role_id] = ?\n" +
"      ,[status] = ?\n" +
" WHERE id =?";
        try {
            PreparedStatement pre= conn.prepareStatement(sql);
            pre.setString(1, acc.getEmail());
            pre.setString(2,acc.getHashedPassword());
            pre.setInt(3, acc.getRole());
            pre.setInt(4, acc.getStatus());
            pre.setInt(5, acc.getId());
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
        public int updateStatus(int id, int status){
        int n=0;
        String sql="UPDATE [dbo].[Users]\n" +
"   SET [status] = "+ status +"\n" +
" WHERE id = " + id;
        try {
            PreparedStatement pre= conn.prepareStatement(sql);
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int addAccount(Account acc){
        int n=0;
        String sql= "INSERT INTO [dbo].[Users]\n" +
"               ([email]\n" +
"           ,[hashed_password]\n" +
"           ,[role_id]\n" +
"           ,[status])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,acc.getEmail());
            pre.setString(2, acc.getHashedPassword());
            pre.setInt(3,acc.getRole());
            pre.setInt(4, acc.getStatus());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Account findById(int id){
        Account acc= null;
        String sql ="select * from Users where id = "+ id;
        ResultSet rs= getData(sql);
        try {
            if(rs.next()){
                String email = rs.getString("email");
                String hashed_password = rs.getString("hashed_password");
                int status = rs.getInt("status");
                int role = rs.getInt("role_id");
                acc = new Account(id, email, hashed_password, role, status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return acc;
    }
        public ResultSet findById2(int id) {
        String sql = "select * from Users where id = "+ id;
        ResultSet rs= getData(sql);
        return rs;
    }
        
    public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
        //System.out.println(dao.loginAccount("landlord2@gmail.com","12345678"));
//        Account acc= new Account(0, "test@gmail.com", EncryptionMD5.encyption("12345678"), 1, 0);
        System.out.println(dao.findById2(2));
//        System.out.println(dao.updateStatus(2, 3));
    }
}
