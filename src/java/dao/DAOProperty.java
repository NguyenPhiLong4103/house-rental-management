/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Property;
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
public class DAOProperty extends DBConnect {

    public Property getByID(int id) {
        Property property = null;
        ResultSet rs = getData("SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[type]\n"
                + "      ,[area]\n"
                + "      ,[NumOfBedrooms]\n"
                + "      ,[address]\n"
                + "      ,[description]\n"
                + "      ,[landlord_id]\n"
                + "      ,[status]\n"
                + "      ,[promotion_id]\n"
                + "      ,[post_start_date]\n"
                + "      ,[post_end_date]\n"
                + "  FROM [dbo].[Post]\n"
                + "  WHERE [id] = " + id);
        try {
            if (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int type = rs.getInt("type");
                int area = rs.getInt("area");
                int numOfBedroom = rs.getInt("NumOfBedrooms");
                String address = rs.getString("address");
                String description = rs.getString("description");
                int landlordID = rs.getInt("landlord_id");
                int status = rs.getInt("status");
                int promotionID = rs.getInt("promotion_id");
                String startDate = rs.getString("post_start_date");
                String endDate = rs.getString("post_end_date");
                property = new Property(id, name, price, type, area, numOfBedroom, address, description, landlordID, status, promotionID, startDate, endDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return property;
    }

    public Property getByIDWithImage(int id) {
        Property property = null;
        ResultSet rs = getData("SELECT p.id, p.name, p.price, p.type, p.area, p.NumOfBedrooms, p.address, p.description, p.landlord_id, p.status, p.promotion_id, p.post_start_date, p.post_end_date, pi.img_url "
                + "FROM Post p "
                + "LEFT JOIN Post_Image pi ON p.id = pi.post_id "
                + "WHERE p.id = " + id);

        try {
            if (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int type = rs.getInt("type");
                int area = rs.getInt("area");
                int numOfBedroom = rs.getInt("NumOfBedrooms");
                String address = rs.getString("address");
                String description = rs.getString("description");
                int landlordID = rs.getInt("landlord_id");
                int status = rs.getInt("status");
                int promotionID = rs.getInt("promotion_id");
                String startDate = rs.getString("post_start_date");
                String endDate = rs.getString("post_end_date");
                String imgUrl = rs.getString("img_url");

                property = new Property(id, name, price, type, area, numOfBedroom, address, description, landlordID, status, promotionID, startDate, endDate);
                property.setPropertyImage(new PropertyImage(id, imgUrl));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return property;
    }

    public List<Property> searchProperty(String search, String type_id, String price, String sortType,
            String numOfBed, String area, int index, int pageSize) {
        if (price == null || price.isEmpty()) {
            price = " 0 and 999999999 ";
        }
        if (sortType == null || sortType.isEmpty()) {  //getAll
            sortType = " p.id asc ";
        }
        if ("price asc".equals(sortType)) {
            sortType = " CASE \n"
                    + "    WHEN pro.promotion_id IS NOT NULL THEN price * (100 - ISNULL(discount, 0)) / 100 \n"
                    + "    ELSE price\n"
                    + "  END asc ";
        }
        if ("price desc".equals(sortType)) {
            sortType = " CASE \n"
                    + "    WHEN pro.promotion_id IS NOT NULL THEN price * (100 - ISNULL(discount, 0)) / 100 \n"
                    + "    ELSE price\n"
                    + "  END desc ";
        }
        if (type_id == null || type_id.isEmpty()) {   //getAll
            type_id = "";
        }
        if (numOfBed == null || numOfBed.isEmpty()) {   //getAll
            numOfBed = "";
        }
        if (area == null || area.isEmpty()) {   //getAll
            area = " 0 and 1000";
        }
        List<Property> listProperty = new ArrayList<>();
        String sql = "  select * from Post p join Property_type pt on p.type = pt.type_id join Landlord ld on ld.id = p.landlord_id "
                + " join Post_Image poi on poi.post_id = p.id"
                + " left join Promotions pro on p.promotion_id = pro.promotion_id "
                + " where [name] like '%" + search + "%' "
                + " and status = 1 and img_type = 1 "
                + " and (price*(100-ISNULL(discount, 0))/100) between " + price
                + " and area between " + area
                + " and pt.type_id like '%" + type_id + "%'"
                + " and NumOfBedRooms like '%" + numOfBed + "%'"
                + " order by " + sortType
                + " OFFSET ? ROWS FETCH NEXT ?  ROWS ONLY ";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, (index - 1) * pageSize);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Property pro = new Property();
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setPrice(rs.getInt("price"));
                pro.setArea(rs.getInt("area"));
                pro.setNumOfBedroom(rs.getInt("NumOfBedRooms"));
                pro.setAddress(rs.getString("address"));
                pro.setLandlordID(rs.getInt("landlord_id"));
                pro.setStatus(rs.getInt("status"));
                pro.setPromotionID(rs.getInt("promotion_id"));
                pro.setPropertyImage(new PropertyImage(rs.getInt("post_id"), rs.getString("img_url")));
                listProperty.add(pro);
            }
            return listProperty;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProperty;
    }

    public int getTotalRowsCondition(String search, String type_id, String price, String numOfBed, String area) {
        if (type_id == null || type_id.equals("")) {   //getAll
            type_id = "";
        }
        if (numOfBed == null || numOfBed.equals("")) {   //getAll
            numOfBed = "";
        }
        if (price == null || price.isEmpty()) {
            price = " 0 and 999999999 ";
        }
        if (area == null || area.isEmpty()) {   //getAll
            area = " 0 and 1000";
        }
        String sql = "  select count(*) from Post p join Property_type pt on p.type = pt.type_id join Landlord ld on ld.id = p.landlord_id "
                + " join Post_Image poi on poi.post_id = p.id"
                + " left join Promotions pro on p.promotion_id = pro.promotion_id "
                + " where [name] like '%" + search + "%' "
                + " and status = 1 and img_type = 1 "
                + " and (price*(100-ISNULL(discount, 0))/100) between " + price
                + " and area between " + area
                + " and pt.type_id like '%" + type_id + "%'"
                + " and NumOfBedRooms like '%" + numOfBed + "%'";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getTotalPostedByLandlordID(int id) {
        String sql = "SELECT count(*)\n"
                + "  FROM [dbo].[Post] \n"
                + "  WHERE [landlord_id] = " + id;

        ResultSet rs = getData(sql);

        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List<Property> getPostedPropertiesByLandlordID(int index, int id) {
        List<Property> list = new ArrayList<Property>();
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Post] \n"
                + "  WHERE [landlord_id] = " + id + "\n"
                + "  ORDER BY [id]\n"
                + "  OFFSET " + (index - 1) * 6 + " ROWS FETCH NEXT 6 ROWS ONLY;";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setPrice(rs.getInt("price"));
                property.setArea(rs.getInt("area"));
                property.setNumOfBedroom(rs.getInt("NumOfBedrooms"));
                property.setAddress(rs.getString("address"));
                property.setStatus(rs.getInt("status"));
                property.setPromotionID(rs.getInt("promotion_id"));
                int promoid = rs.getInt("promotion_id");
                list.add(property);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Property> getNRecentProperties(int n) {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT TOP " + n + " [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[area]\n"
                + "      ,[NumOfBedrooms]\n"
                + "      ,[address]\n"
                + "      ,[promotion_id]\n"
                + "  FROM [dbo].[Post]\n"
                + "  WHERE [status] = 1\n"
                + "  ORDER BY [post_start_date] DESC";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setPrice(rs.getInt("price"));
                property.setArea(rs.getInt("area"));
                property.setNumOfBedroom(rs.getInt("NumOfBedrooms"));
                property.setAddress(rs.getString("address"));
                property.setPromotionID(rs.getInt("promotion_id"));
                properties.add(property);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;

    }

    public ArrayList<Property> getAllProperty() {
        ArrayList<Property> list = new ArrayList<Property>();
        ResultSet rs = getData("SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[type]\n"
                + "      ,[area]\n"
                + "      ,[NumOfBedrooms]\n"
                + "      ,[address]\n"
                + "      ,[description]\n"
                + "      ,[landlord_id]\n"
                + "      ,[status]\n"
                + "      ,[promotion_id]\n"
                + "      ,[post_start_date]\n"
                + "      ,[post_end_date]\n"
                + "  FROM [dbo].[Post]\n"
                + "  ORDER BY [id] DESC");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int type = rs.getInt("type");
                int area = rs.getInt("area");
                int numOfBedroom = rs.getInt("NumOfBedrooms");
                String address = rs.getString("address");
                String description = rs.getString("description");
                int landlordID = rs.getInt("landlord_id");
                int status = rs.getInt("status");
                int promotionID = rs.getInt("promotion_id");
                String startDate = rs.getString("post_start_date");
                String endDate = rs.getString("post_end_date");
                Property property = new Property(id, name, price, type, area, numOfBedroom, address, description, landlordID, status, promotionID, startDate, endDate);
                list.add(property);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int updateProperty(Property pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Post]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[type] = ?\n"
                + "      ,[area] = ?\n"
                + "      ,[NumOfBedrooms] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[landlord_id] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[promotion_id] = ?\n"
                + "      ,[post_start_date] = ?\n"
                + "      ,[post_end_date] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getName());
            pre.setInt(2, pro.getPrice());
            pre.setInt(3, pro.getType());
            pre.setInt(4, pro.getArea());
            pre.setInt(5, pro.getNumOfBedroom());
            pre.setString(6, pro.getAddress());
            pre.setString(7, pro.getDescription());
            pre.setInt(8, pro.getLandlordID());
            pre.setInt(9, pro.getStatus());
            if (pro.getPromotionID() != 0) {
                pre.setInt(10, pro.getPromotionID());
            } else {
                pre.setString(10, null);
            }
            pre.setString(11, pro.getStartDate());
            pre.setString(12, pro.getEndDate());
            pre.setInt(13, pro.getId());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public List<String> getNumOfBedrooms() {
        String sql = " select distinct NumOfBedrooms from Post ";
        try {
            List<String> list = new ArrayList<>();
            ResultSet rs = getData(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Property> getNRecentProperty(int n) {
        List<Property> listProperty = new ArrayList<>();
        String sql = "  SELECT * \n"
                + "FROM Post p\n"
                + "JOIN Property_type pt ON p.type = pt.type_id\n"
                + "JOIN Landlord ld ON ld.id = p.landlord_id\n"
                + "LEFT JOIN Promotions pro ON p.promotion_id = pro.promotion_id\n"
                + "WHERE status = 1\n"
                + "ORDER BY p.post_start_date DESC\n"
                + "OFFSET 0 ROWS FETCH NEXT 6 ROWS ONLY; ";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                Property pro = new Property();
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setPrice(rs.getInt("price"));
                pro.setArea(rs.getInt("area"));
                pro.setNumOfBedroom(rs.getInt("NumOfBedRooms"));
                pro.setAddress(rs.getString("address"));
                pro.setLandlordID(rs.getInt("landlord_id"));
                pro.setStatus(rs.getInt("status"));
                pro.setPromotionID(rs.getInt("promotion_id"));
                listProperty.add(pro);
            }
            return listProperty;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProperty;
    }

    public List<Property> getAllProperty(int index, int pageSize) {
        List<Property> listProperty = new ArrayList<>();
        String sql = "  select * from Post p join Property_type pt on p.type = pt.type_id join Landlord ld on ld.id = p.landlord_id "
                + " join Post_Image poi on poi.post_id = p.id"
                + " left join Promotions pro on p.promotion_id = pro.promotion_id "
                + " where img_type = 1 and status = 1"
                + " order by p.id "
                + " OFFSET ? ROWS FETCH NEXT ?  ROWS ONLY ";
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, (index - 1) * pageSize);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();
            System.out.println(sql);
            while (rs.next()) {
                Property pro = new Property();
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setPrice(rs.getInt("price"));
                pro.setArea(rs.getInt("area"));
                pro.setNumOfBedroom(rs.getInt("NumOfBedRooms"));
                pro.setAddress(rs.getString("address"));
                pro.setLandlordID(rs.getInt("landlord_id"));
                pro.setStatus(rs.getInt("status"));
                pro.setPromotionID(rs.getInt("promotion_id"));
                pro.setPropertyImage(new PropertyImage(rs.getInt("post_id"), rs.getString("img_url")));
                listProperty.add(pro);
            }
            return listProperty;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProperty;
    }

    public int addProperty(Property pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Post]\n"
                + "           ([name]\n"
                + "           ,[price]\n"
                + "           ,[type]\n"
                + "           ,[area]\n"
                + "           ,[NumOfBedrooms]\n"
                + "           ,[address]\n"
                + "           ,[description]\n"
                + "           ,[landlord_id]\n"
                + "           ,[status]\n"
                + "           ,[promotion_id]\n"
                + "           ,[post_start_date]\n"
                + "           ,[post_end_date])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getName());
            pre.setInt(2, pro.getPrice());
            pre.setInt(3, pro.getType());
            pre.setInt(4, pro.getArea());
            pre.setInt(5, pro.getNumOfBedroom());
            pre.setString(6, pro.getAddress());
            pre.setString(7, pro.getDescription());
            pre.setInt(8, pro.getLandlordID());
            pre.setInt(9, pro.getStatus());
            pre.setString(10, null);
            pre.setString(11, pro.getStartDate());
            pre.setString(12, pro.getEndDate());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int getNewId() {
        String sql = "select top(1) id from Post order by id desc";
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean updatePostStatus(int postId, int status) {
        try {
            String sql = "UPDATE [dbo].[Post]\n"
                    + " SET [status] = ? "
                    + " WHERE id = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, status);
            stm.setInt(2, postId);
            stm.executeUpdate();
            if (status == 2 || status == 0) {
                String sql2 = " update Orders set status = 2 where post_id = ? ";
                stm = conn.prepareStatement(sql2);
                stm.setInt(1, postId);
                stm.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Property> getAll() {
        ArrayList<Property> list = new ArrayList<Property>();
        ResultSet rs = getData("SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[type]\n"
                + "      ,[area]\n"
                + "      ,[NumOfBedrooms]\n"
                + "      ,[address]\n"
                + "      ,[description]\n"
                + "      ,[landlord_id]\n"
                + "      ,[status]\n"
                + "      ,[promotion_id]\n"
                + "      ,[post_start_date]\n"
                + "      ,[post_end_date]\n"
                + "  FROM [dbo].[Post]\n"
                + "  ORDER BY [id]");
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int type = rs.getInt("type");
                int area = rs.getInt("area");
                int numOfBedroom = rs.getInt("NumOfBedrooms");
                String address = rs.getString("address");
                String description = rs.getString("description");
                int landlordID = rs.getInt("landlord_id");
                int status = rs.getInt("status");
                int promotionID = rs.getInt("promotion_id");
                String startDate = rs.getString("post_start_date");
                String endDate = rs.getString("post_end_date");
                Property property = new Property(id, name, price, type, area, numOfBedroom, address, description, landlordID, status, promotionID, startDate, endDate);
                list.add(property);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Property getByPromotionId(int id) {
        Property property = null;
        ResultSet rs = getData("SELECT p.id, p.name, p.price, p.type, p.area, p.NumOfBedrooms, p.address, p.description, p.landlord_id, p.status, p.promotion_id, p.post_start_date, p.post_end_date, pi.img_url "
                + "FROM Post p "
                + "LEFT JOIN Post_Image pi ON p.id = pi.post_id "
                + "WHERE p.id = " + id);

        try {
            if (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int type = rs.getInt("type");
                int area = rs.getInt("area");
                int numOfBedroom = rs.getInt("NumOfBedrooms");
                String address = rs.getString("address");
                String description = rs.getString("description");
                int landlordID = rs.getInt("landlord_id");
                int status = rs.getInt("status");
                int promotionID = rs.getInt("promotion_id");
                String startDate = rs.getString("post_start_date");
                String endDate = rs.getString("post_end_date");
                String imgUrl = rs.getString("img_url");

                property = new Property(id, name, price, type, area, numOfBedroom, address, description, landlordID, status, promotionID, startDate, endDate);
                property.setPropertyImage(new PropertyImage(id, imgUrl));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        return property;
    }

    public static void main(String[] args) {
        DAOProperty dao = new DAOProperty();
        List<Property> list = dao.getAll();
        for(Property p: list){
            System.out.println(p);
        }
        
    }


    public void updatePostWithPromotion(int postID, int promotionID) {
        String sql = "UPDATE Post SET promotion_id = ? WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, promotionID);
            pre.setInt(2, postID);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateWhenExpired(Property p){
        String sql = "update Orders set status = 2 where post_id = ? and status =0 ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void SetPromotionIDToNull(int postID) {
        String sql = "UPDATE Post SET promotion_id = NULL WHERE id = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, postID);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProperty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
