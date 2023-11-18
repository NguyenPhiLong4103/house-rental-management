/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class Promotion {
    private int promotionID;
    private int discount;
    private String description;
    private String promotionStartDate;
    private String promotionEndDate;

    public Promotion() {
    }

    public Promotion(int promotionID, int discount, String description, String promotionStartDate, String promotionEndDate) {
        this.promotionID = promotionID;
        this.discount = discount;
        this.description = description;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(String promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public String getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(String promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    @Override
    public String toString() {
        return "Promotion{" + "promotionID=" + promotionID + ", discount=" + discount + ", description=" + description + ", promotionStartDate=" + promotionStartDate + ", promotionEndDate=" + promotionEndDate + '}';
    }
    
    
}
