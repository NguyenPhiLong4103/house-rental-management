/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.DAOLandlord;
import dao.DAOPromotion;
import dao.DAOPropertyType;

/**
 *
 * @author Dell
 */
public class Property {

    private int id;
    private String name;
    private int price;
    private int type;
    private int area;
    private int numOfBedroom;
    private String address;
    private String description;
    private int landlordID;
    private int status;
    private int promotionID;
    private String startDate;
    private String endDate;
    private PropertyImage propertyImage;

    public Property() {
    }

    public Property(int id, String name, int price, int type, int area, int numOfBedroom, String address, String description, int landlordID, int status, int promotionID, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.area = area;
        this.numOfBedroom = numOfBedroom;
        this.address = address;
        this.description = description;
        this.landlordID = landlordID;
        this.status = status;
        this.promotionID = promotionID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getNumOfBedroom() {
        return numOfBedroom;
    }

    public void setNumOfBedroom(int numOfBedroom) {
        this.numOfBedroom = numOfBedroom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLandlordID() {
        return landlordID;
    }

    public void setLandlordID(int landlordID) {
        this.landlordID = landlordID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPromotionID() {
        return promotionID;
    }
    
    public String getPropertyTypeName() {
        DAOPropertyType daoPt = new DAOPropertyType();
        String ptname = daoPt.GetTypeNameByID(this.type);
        return  ptname;
    }

    public String getLandlordName() {
        DAOLandlord dao = new DAOLandlord();
        String name = dao.getFullnameByID(this.landlordID);
        return name;
    }
    
    public Promotion getPromotion() {
        DAOPromotion proDao = new DAOPromotion();
        Promotion promo = proDao.getPromotionById(this.promotionID);
        return promo;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public PropertyImage getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(PropertyImage propertyImage) {
        this.propertyImage = propertyImage;
    }

    @Override
    public String toString() {
        return "Property{" + "id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + ", area=" + area + ", numOfBedroom=" + numOfBedroom + ", address=" + address + ", description=" + description + ", landlordID=" + landlordID + ", status=" + status + ", promotionID=" + promotionID + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

}
