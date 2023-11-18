/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.Timer;
import java.util.TimerTask;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.Calendar;

/**
 * Application Lifecycle Listener implementation class MyServletContextListener
 *
 */
@WebListener
public class TimerInitialized implements ServletContextListener {

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
        System.out.println("*********ServletContextListener started*********");
        long delay = calculateInitialDelay();
        long intervalMillis = 24 * 60 * 60 * 1000; // 24 hours in milliseconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new CheckPropertyTask(), delay, intervalMillis);
        servletContext.setAttribute("timer", timer);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        ServletContext servletContext = arg0.getServletContext();
// get our timer from the Context
        Timer timer = (Timer) servletContext.getAttribute("timer");
        if (timer != null) {
            timer.cancel();
        }
        servletContext.removeAttribute("timer");
        System.out.println("ServletContextListener destroyed");

    }
    
    private long calculateInitialDelay() {
    // Calculate the initial delay until 12:30 AM of the next day
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);  // Set the hour to 12 (midnight)
    calendar.set(Calendar.MINUTE,12 );      // Set the minute to 30
    calendar.set(Calendar.SECOND, 0);       // Set the second to 0
    calendar.set(Calendar.MILLISECOND, 0);  // Set the millisecond to 0

    long currentTimeMillis = System.currentTimeMillis();
    long scheduledTimeMillis = calendar.getTimeInMillis();

    // If the current time is already past 12:30 AM, schedule it for the next day
    if (currentTimeMillis > scheduledTimeMillis) {
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day (to schedule it for the next day)
        scheduledTimeMillis = calendar.getTimeInMillis();
    }

    return scheduledTimeMillis - currentTimeMillis;
}

}
