/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.DAOAccount;
import dao.DAOBannedAccount;
import entity.Account;
import entity.BannedAccount;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author duchi
 */
public class BanAccountService {

    private DAOAccount aDao = new DAOAccount();
    private DAOBannedAccount bDao = new DAOBannedAccount();

    public boolean checkBanUser(Account acc) {
        if (acc.getStatus() == 2) {
            return true;
        }
        return false;
    }

    public String banUser(int id, String time) {
        Account acc = aDao.findById(id);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // Parse the input string to a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.parse(time, inputFormatter);
        // Create a custom DateTimeFormatter for the desired format
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        // Format the LocalDateTime object to the desired format
        String endDate = dateTime.format(customFormatter);
        String startDate = LocalDateTime.now().format(customFormatter);
        if(dateTime.isBefore(LocalDateTime.now())){
            return "End time ban must be larger  than start time ban!";
        }
        if (checkBanUser(acc)) {
            BannedAccount bAcc = bDao.findAccount(id);
            bAcc.setStartDate(startDate);
            bAcc.setEndDate(endDate);
            bDao.updateDetail(bAcc);
            //update ban information
        }
        else{
            BannedAccount bAcc = new BannedAccount(id, startDate, endDate);
            //new ban information
            acc.setStatus(2);
            aDao.updateAccount(acc);
            bDao.addBanAccount(bAcc);
        }
        return "User is banned!";
    }
    
    public String unbanUser(int id){
        Account acc = aDao.findById(id);
        aDao.updateStatus(id, 3);
        bDao.deleteDetail(id);
        return "Unban successfully!";
    }
    public static void main(String[] args) {
        BanAccountService service = new BanAccountService();
        System.out.println(service.banUser(2, "2023-10-05T19:00"));
    }
}
