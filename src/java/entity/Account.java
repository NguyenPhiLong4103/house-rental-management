/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author duchi
 */
public class Account {
    private int id;
    private String email;
    private String hashedPassword;
    private int role;
    private int status;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", hashedPassword=" + hashedPassword + ", role=" + role + ", status=" + status + '}';
    }

    public Account(int id, String email, String hashedPassword, int role, int status) {
        this.id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
