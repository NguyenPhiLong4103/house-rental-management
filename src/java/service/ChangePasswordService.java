/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOAccount;
import entity.Account;
import utils.EncryptionMD5;

/**
 *
 * @author duchi
 */
public class ChangePasswordService {
    private DAOAccount dao = new DAOAccount();
    public boolean checkOldPassword(String oldpassword , String password){
        return oldpassword.equalsIgnoreCase(password);
    }
    
    public boolean checkRePassword(String newpassword, String repassword){
        return newpassword.equals(repassword);
    }
    
    public boolean checkNewPassword(String newpassword, String opassword){
        return newpassword.equals(opassword);
    }
    
    public String checkChange(String oldpassword , String curpassword, String newpassword, String repassword){
        if(!checkOldPassword(EncryptionMD5.encyption(oldpassword) , curpassword)) return "Old password is not correct!";
        if(!ValidateData.validatePassword(newpassword)) return "Password must contains 6 to 14 characters. Password only contains letter and digit";
        if(checkNewPassword(newpassword, oldpassword)) return "New password must be different from old password";
        if(!checkRePassword(newpassword, repassword)) return "New password must be same as re-new password!";
        return "Change Password Successfully!";
    }
    
    public void changePassword(Account acc , String password){
        String hashedPassword = EncryptionMD5.encyption(password);
        acc.setHashedPassword(hashedPassword);
        dao.updateAccount(acc);
    }
    public static void main(String[] args) {
        ChangePasswordService service = new ChangePasswordService();
        System.out.println(service.checkChange("fds9sg19", "fds9sg19", "fds9sg19", "fds9sg19"));
    }
}
