/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NGOCDUC
 */
public class Transactions {
    private int id;
    private int amount;
    private int payerID;
    private String type;
    private Date date;
    private int postID;
    private int receiverID;

    public Transactions() {
    }

    public Transactions(int id, int amount, int payerID, String type, Date date, int postID, int receiverID) {
        this.id = id;
        this.amount = amount;
        this.payerID = payerID;
        this.type = type;
        this.date = date;
        this.postID = postID;
        this.receiverID = receiverID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayerID() {
        return payerID;
    }

    public void setPayerID(int payerID) {
        this.payerID = payerID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
    
    
    
    public String getDateTransactions() {
        java.util.Date utilDate = new java.util.Date(date.getTime());
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(utilDate);
    }

    @Override
    public String toString() {
        return "Transactions{" + "id=" + id + ", amount=" + amount + ", payerID=" + payerID + ", type=" + type + ", date=" + date + ", postID=" + postID + ", receiverID=" + receiverID + '}';
    }
    
    
}
