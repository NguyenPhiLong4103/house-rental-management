/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PropertyImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DAOPropertyImage extends DBConnect {

    public List<PropertyImage> findByPostID(int postID) {
        List<PropertyImage> propertyImages = new ArrayList<>();
        ResultSet rs = getData("SELECT *\n"
                + "  FROM [dbo].[Post_Image]\n"
                + "  WHERE post_id = " + postID);
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String imageURL = rs.getString("img_url");
                int imageType = rs.getInt("img_type");
                propertyImages.add(new PropertyImage(id, postID, imageURL, imageType));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propertyImages;
    }
 public PropertyImage findThumbnailByID(int postID) {
        PropertyImage propertyThumbnail = null;
        ResultSet rs = getData("SELECT [id],[img_url]\n"
                + "  FROM [dbo].[Post_Image]\n"
                + "  WHERE [post_id] = " + postID + " and [img_type] = 1");
        try {
            if (rs.next()) {
                propertyThumbnail = new PropertyImage();
                propertyThumbnail.setId(rs.getInt("id"));
                propertyThumbnail.setPostID(postID);
                propertyThumbnail.setImgURL(rs.getString("img_url"));
                propertyThumbnail.setImgType(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropertyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propertyThumbnail;
    }
 
    public PropertyImage getThumbnailByID(int postID) {
        PropertyImage propertyThumbnail = null;
        ResultSet rs = getData("SELECT [img_url]\n"
                + "  FROM [dbo].[Post_Image]\n"
                + "  WHERE [post_id] = " + postID + " and [img_type] = 1");
        try {
            if (rs.next()) {
                propertyThumbnail = new PropertyImage();
                propertyThumbnail.setPostID(postID);
                propertyThumbnail.setImgURL(rs.getString("img_url"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropertyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return propertyThumbnail;
    }

    public int addNewImage(PropertyImage img) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Post_Image]\n"
                + "           ([post_id]\n"
                + "           ,[img_url]\n"
                + "           ,[img_type])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, img.getPostID());
            pre.setString(2, img.getImgURL());
            pre.setInt(3, img.getImgType());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropertyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateImage(PropertyImage img) {
        int n = 0;
        String sql = "UPDATE [dbo].[Post_Image]\n"
                + "   SET [img_url] = ?\n"
                + "      ,[img_type] = ?\n"
                + " WHERE [id] = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, img.getImgURL());
            pre.setInt(2, img.getImgType());
            pre.setInt(3, img.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropertyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int deleteImageByID(int postID){
        int n=0;
        String sql="DELETE FROM [dbo].[Post_Image]\n" +
"      WHERE [post_id] = ? and [img_type] = 0";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, postID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropertyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
