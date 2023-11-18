/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PropertyType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class DAOPropertyType extends DBConnect {

    public ArrayList<PropertyType> getListPropertyTypes() {
        String sql = " select * from Property_type ";
        try {
            ArrayList<PropertyType> list = new ArrayList<>();
            ResultSet rs = getData(sql);
            while (rs.next()) {
                PropertyType u = new PropertyType(rs.getInt(1), rs.getString(2));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String GetTypeNameByID(int id) {
        String typeName = null;
        String sql = "SELECT [type_name]\n"
                + "  FROM [dbo].[Property_type]\n"
                + "  WHERE [type_id] = " + id;
        ResultSet rs = getData(sql);
        try {
            if (rs.next()) {
                typeName = rs.getString("type_name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPropertyType.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typeName;
    }
}
