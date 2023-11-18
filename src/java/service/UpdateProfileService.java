/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author duchi
 */
public class UpdateProfileService {
    public String validateTenant(String fname,String lname, String address ,String phone){
        if(!ValidateData.validateFirstName(fname)){
            return "Invalid First Name!";
        }
        if(!ValidateData.validateLastName(lname)){
            return "Invalid Last Name!";
        }
        if(!ValidateData.validateAddress(address)){
            return "Invalid Address!";
        }
        if(!ValidateData.validatePhone(phone)){
            return "Invalid Phone!";
        }
        return "Ok";
    }
    
    public String validateLandlord(String fname,String lname, String address ,String phone,String civil_id){
        if(!ValidateData.validateFirstName(fname)){
            return "Invalid First Name!";
        }
        if(!ValidateData.validateLastName(lname)){
            return "Invalid Last Name!";
        }
        if(!ValidateData.validateAddress(address)){
            return "Invalid Address!";
        }
        if(!ValidateData.validatePhone(phone)){
            return "Invalid Phone!";
        }
        if(!ValidateData.validateCivilId(civil_id)){
            return "Invalid Civil ID"; 
        }
        return "Ok";
    }
    
    public String validateAdmin(String fname,String lname){
        if(!ValidateData.validateFirstName(fname)){
            return "Invalid First Name!";
        }
        if(!ValidateData.validateLastName(lname)){
            return "Invalid Last Name!";
        }
        return "Ok";
    }
    public static void main(String[] args) {
        System.out.println();
    }
}
