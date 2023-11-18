/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author duchi
 */
public class ValidateData {
   public static boolean validatePassword(String password) {
    String pattern = "^(?=.*[a-zA-Z])(?=.*\\d).{6,14}$";
    if (password.matches(pattern)) {
        return true;
    }
    return false;
}
    
    public static boolean validatePhone(String phone){
    String regex = "^(03|05|07|08|09)\\d{8}$"; 
    if(phone.matches(regex)) {
        return true;
    }
    return false;
}
       // validate first name
   public static boolean validateFirstName( String firstName ) {
      return firstName.matches( "[A-Z][a-z]{1,10}" );
   }
   // validate last name
   public static boolean validateLastName( String lastName ) {
      return lastName.matches( "[A-Z][a-z]{1,10}" );
   }
   
   public static boolean validateAddress( String address )
   {
       return address.matches( "([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)" );
   } 
    
    public static boolean validateCivilId(String cccd) {
        String regex = "^[0-9]{12}$";
        return cccd.matches(regex);
    }
    
    public static boolean validateEmail(String email){
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    
   
    public static void main(String[] args) {
        System.out.println(ValidateData.validateLastName("Register"));
    }
    
}
