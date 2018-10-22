package com.eaglediao.qa.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class UserListener implements HttpSessionListener ,ServletContextListener {

    private static int userCount = 0;

    public static int getUserCount() {
        return userCount;
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

        httpSessionEvent.getSession().getServletContext().setAttribute("userCount", ++userCount);

    }




    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

//        httpSessionEvent.getSession().getServletContext().setAttribute("userCount", --userCount);
//        try {
//            User user = (User) (httpSessionEvent.getSession().getAttribute("user"));
//            userService.updateUser(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        httpSessionEvent.getSession().getServletContext().setAttribute("userCount", --userCount);

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}