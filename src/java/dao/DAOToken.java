/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Token;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duchi
 */
public class DAOToken extends DBConnect{
    public int insertToken(Token token){
        int n=0;
        String sql = "INSERT INTO [Token]\n" +
"           ([user_id]\n" +
"           ,[token]\n" +
"           ,[expired_date],"
                + "[type])\n" +
"     VALUES\n" +
"           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, token.getUser_id());
            pre.setString(2, token.getCode());
            pre.setString(3,token.getExpriedDate());
            pre.setInt(4,token.getType());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOToken.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public Token findByToken(String token){
        Token tmp =null;
        String sql= "select * from Token where token = '"+token+"'";
        ResultSet rs = getData(sql);
        try {
            if(rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String code = rs.getString("token");
                String expiredDate = rs.getString("expired_date");
                int type = rs.getInt("type");
                tmp = new Token(id, userId, code, expiredDate, type);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOToken.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
}
