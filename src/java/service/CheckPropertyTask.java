/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOPromotion;
import dao.DAOProperty;
import entity.Promotion;
import entity.Property;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author duchi
 */
public class CheckPropertyTask extends TimerTask{
    public DAOProperty pDao = new DAOProperty();
    public DAOPromotion prDao = new DAOPromotion();
    @Override
    public void run() {
        List<Property> list = pDao.getAllProperty();
        List<Promotion> listP = prDao.getListPromotion();
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        for(Property p : list){
            System.out.println(p);
            String time = p.getEndDate();
            LocalDate endDate = LocalDate.parse(time, dateFormatter);
            if(endDate.isBefore(date)){
                p.setStatus(0);
                pDao.updateProperty(p);
                pDao.updateWhenExpired(p);
            }
        }
        
        for(Promotion p : listP){
            System.out.println(p);
            String time = p.getPromotionEndDate();
            LocalDate endDate = LocalDate.parse(time, dateFormatter);
            if(endDate.isBefore(date)){
               prDao.removePromotion(p);
            }
        }
    }
    
}
