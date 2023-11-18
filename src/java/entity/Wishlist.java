/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class Wishlist {
    private int id;
    private int userID;
    private int propertyID;

    public Wishlist() {
    }

    public Wishlist(int id, int userID, int propertyID) {
        this.id = id;
        this.userID = userID;
        this.propertyID = propertyID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "id=" + id + ", userID=" + userID + ", propertyID=" + propertyID + '}';
    }
    
    
}
