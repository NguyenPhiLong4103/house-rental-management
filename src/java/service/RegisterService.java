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
import dao.DAOToken;
import entity.Account;
import entity.Admin;
import entity.Landlord;
import entity.Tenant;
import entity.Token;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import utils.EncryptionMD5;
import utils.SendMail;

/**
 *
 * @author duchi
 */
public class RegisterService {
   private static final DAOLandlord lDao = new DAOLandlord();
   private static final DAOTenant tDao = new DAOTenant();
   private static final DAOAdmin aDao = new DAOAdmin();
   private static final DAOToken toDao= new DAOToken();
   private static final DAOAccount acDao = new DAOAccount();
   
   
        public String registerAccount(Account account, String firstName,  String lastName){
            if(acDao.findAccountVertified(account.getEmail())!=null){
                return "Email has been activated";
            }
            int role_id = account.getRole();
            //account with role doesnt exist in database
            if(acDao.findByEmailAndRole(account.getEmail(), role_id)==null){
                // add new to database
                acDao.addAccount(account);
                Account acc = acDao.findByEmailAndRole(account.getEmail(), account.getRole());
                // add information 
                if(acc.getRole()==1){
                    //Add infor to tenant
                    Tenant tenant = new Tenant(acc.getId(), firstName, lastName, null, null);
                    tDao.addTenant(tenant);
                }
                else if(acc.getRole()==2){
                    //Add infor to landlord
                    Landlord landlord = new Landlord(acc.getId(), firstName, lastName, null, null, null, 0);
                    lDao.addLandlord(landlord);
                }else if(acc.getRole()==3){
                    Admin admin = new Admin(acc.getId(), firstName, lastName);
                    aDao.addAdmin(admin);
                }
                //add token for account
                Token token = createToken(acc.getId(), 1);
                //send token to email
                String url = "http://localhost:9999/House_Rental_Management_System/vertify?token="+token.getCode();
                SendMail.send(acc.getEmail(), "Vertify your account", "<a href =\""+url+"\">Click here to vertify</a>");
                return "Please vertify your account in your email";
            }
            //account with role exist in database => force user to use forgot password function
            else{
                Account acc = acDao.findByEmailAndRole(account.getEmail(), account.getRole());
                Token token = createToken(acc.getId(), 2);
                //send token to email
                String url = "http://localhost:9999/House_Rental_Management_System/vertify?token="+token.getCode();
                SendMail.send(acc.getEmail(), "Vertify your account", "<a href =\""+url+"\">Click here to change password</a>");
                return "Email has been you to register, vertify in email to change password";
            }
        }
        
    public static Token createToken(int user_id, int type){
        String code = generateRandomString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now().plusMinutes(30);
        String expiredDate = currentDateTime.format(formatter);
        Token token = new Token(0, user_id, code, expiredDate,type);
        toDao.insertToken(token);
        return token;
    }
    
    
    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        System.out.println("Token length: " + token.length());
        return token;
    }
    
     public String checkToken(String code){
        Token token = toDao.findByToken(code);
         Account acc = acDao.findById(token.getUser_id());
        if(token.getType()==1){   
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiredDate = LocalDateTime.parse(token.getExpriedDate(), formatter);
        if(expiredDate.isBefore(currentDateTime)){
           Token newToken= createToken(acc.getId(), 1);
            String url = "http://localhost:9999/House_Rental_Management_System/vertify?token="+newToken.getCode();
            SendMail.send(acc.getEmail(), "Vertify your account", "<a href =\""+url+"\">Click here to vertify</a>");
            return "Token is expired and new token is send";
        }
        else{
            if(acDao.findAccountVertified(acc.getEmail())!=null) return "Account has been activated";
            if(acc.getStatus()==0){
                if(acc.getRole()==1 || acc.getRole()==3){
                acc.setStatus(3);
            }
            else{
                acc.setStatus(1);
            }
            acDao.updateAccount(acc);
            }
            return "Vertify successfully";
        }
        }
        else{
            String password= createPassword();
            String hashedPassword = EncryptionMD5.encyption(password);
            acc.setHashedPassword(hashedPassword);
            if(acc.getStatus()==0){
                acc.setStatus(3);
            }
            acDao.updateAccount(acc);
            SendMail.send(acc.getEmail(), "Your new password", "Your can login with your new password: " + password);
            return "New password is send to your email";
        }   
    }
     
     public String sendForgot(String email){
        Account acc = acDao.findAccountVertified(email);
        if(acc==null){
            return "Email is not used to register.";
        }
        Token token = createToken(acc.getId(), 2);
        String url = "http://localhost:9999/House_Rental_Management_System/vertify?token="+token.getCode();
        SendMail.send(acc.getEmail(), "Vertify your account", "<a href =\""+url+"\">Click here to confirm change password</a>");
        return "Please vertify your account in your email";
    }
     
     public String createPassword(){
          int minLength = 8;

        // Characters to use for generating the random string
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        // Ensure at least 1 digit in the string
        randomString.append(characters.charAt(random.nextInt(10))); // First character is a digit

        // Generate remaining characters
        for (int i = 1; i < minLength; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            randomString.append(randomChar);
        }

        return randomString.toString();
    
     }
     
     public String validateRegister(String email, String fname, String lname, String password , String repassword){
         if(!ValidateData.validateFirstName(fname)) 
             return "First name must start with uppercase letter and contain 2-10 letters";
         if(!ValidateData.validateLastName(lname))
             return "Last name must start with uppercase letter and contain 2-10 letters";
         if(!ValidateData.validateEmail(email))
             return "Email is invalide";
         if(!ValidateData.validatePassword(password))
             return "Password must contain 6-14 characters including at least 1 letter and 1 digit";
         if(!password.equals(repassword))
             return "Password must be same as Re-password";
         return "Ok";
     }
     
    public static void main(String[] args) {
        RegisterService service = new RegisterService();
        System.out.println(service.validateRegister("duchieu20092003@gmail.com","Test","Register","duchieu03","duchieu03"));
    }
}
