/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Dell
 */
public class PropertyImage {

    private int id;
    private int postID;
    private String imgURL;
    private int imgType;

    public PropertyImage() {
    }
    
    public PropertyImage(int postID, String imgURL) {
        this.postID = postID;
        this.imgURL = imgURL;
    }

    public PropertyImage(int id, int postID, String imgURL, int imgType) {
        this.id = id;
        this.postID = postID;
        this.imgURL = imgURL;
        this.imgType = imgType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    @Override
    public String toString() {
        return "PropertyImage{" + "id=" + id + ", postID=" + postID + ", imgURL=" + imgURL + ", imgType=" + imgType + '}';
    }

}
