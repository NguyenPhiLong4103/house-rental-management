    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BannedAccount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duchi
 */
public class DAOBannedAccount extends DBConnect{
    public BannedAccount findAccount(int id){
        BannedAccount user = null;
        String sql = "select * from User_banned where id = " + id ;
        ResultSet rs= getData(sql);
        try {
            if(rs.next()){
                user = new BannedAccount(id,rs.getString("ban_start_date"), rs.getString("ban_end_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBannedAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public int deleteDetail(int id){
        int n=0;
        Statement state;
        try {
            state = conn.createStatement();
            String sql ="DELETE FROM [dbo].[User_banned]\n" +
"      WHERE id = "+ id;
         n= state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBannedAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateDetail(BannedAccount bAcc){
        int n=0;
        String sql = "UPDATE [dbo].[User_banned]\n" +
"   SET \n" +
"       [ban_start_date] = ?\n" +
"      ,[ban_end_date] = ?\n" +
" WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, bAcc.getStartDate());
            pre.setString(2, bAcc.getEndDate());
            pre.setInt(3, bAcc.getId());
            n= pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBannedAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addBanAccount(BannedAccount bAcc){
        int n=0;
        String sql="INSERT INTO [dbo].[User_banned]\n" +
"           ([id]\n" +
"           ,[ban_start_date]\n" +
"           ,[ban_end_date])\n" +
"     VALUES\n" +
"           (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, bAcc.getId());
            pre.setString(2, bAcc.getStartDate());
            pre.setString(3, bAcc.getEndDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBannedAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public static void main(String[] args) {
        DAOBannedAccount dao = new  DAOBannedAccount();
        System.out.println(dao.findAccount(4));
        
    }
}
