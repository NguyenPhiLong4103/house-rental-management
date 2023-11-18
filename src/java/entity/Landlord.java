/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author duchi
 */
public class Landlord {
     private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String civil_id;
    private int accountPoint;

    public Landlord() {
    }

    public Landlord(int id, String firstName, String lastName, String address, String phone, String civil_id, int accountPoint) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.civil_id = civil_id;
        this.accountPoint = accountPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCivil_id() {
        return civil_id;
    }

    public void setCivil_id(String civil_id) {
        this.civil_id = civil_id;
    }

    public int getAccountPoint() {
        return accountPoint;
    }

    public void setAccountPoint(int accountPoint) {
        this.accountPoint = accountPoint;
    }

    @Override
    public String toString() {
        return "Landlord{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phone=" + phone + ", civil_id=" + civil_id + ", accountPoint=" + accountPoint + '}';
    }

    
 
}
