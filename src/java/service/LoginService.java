/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOAccount;
import dao.DAOAdmin;
import dao.DAOBannedAccount;
import dao.DAOLandlord;
import dao.DAOTenant;
import entity.Account;
import entity.Admin;
import entity.BannedAccount;
import entity.Landlord;
import entity.Tenant;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duchi
 */
public class LoginService {

    public static final DAOBannedAccount bDao = new DAOBannedAccount();
    public static final DAOAccount aDao = new DAOAccount();

    public Account login(String email, String password) {
        Account acc = aDao.loginAccount(email, password);
        if(acc!=null){
            //acc is not banned
            if(acc.getStatus()==3){
                return acc;
            }
            //status of account is banned
            if(acc.getStatus()==2){
                BannedAccount ban = bDao.findAccount(acc.getId());
                if(checkTimeUnban(ban)){
                    //unban account
                    acc.setStatus(3); 
                    aDao.updateAccount(acc);
                    //delete in user_banned
                    bDao.deleteDetail(ban.getId());
                   
                    return acc;
                }
            }
        }
        return null;
    }
    
    public static boolean checkTimeUnban(BannedAccount ban){
        boolean check = true;
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
         LocalDateTime currentDateTime = LocalDateTime.now();
         LocalDateTime expiredDate = LocalDateTime.parse(ban.getEndDate(), formatter);
         System.out.println(currentDateTime + "\n" + expiredDate);
         if(expiredDate.isAfter(currentDateTime)) check = false;
        return check;
    }

    public static void main(String[] args) {
        LoginService service = new LoginService();
        //System.out.println(LoginService.login("landlord3@gmail.com", "12345678"));
    }
}
