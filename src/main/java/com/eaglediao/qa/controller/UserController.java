package com.eaglediao.qa.controller;

import com.eaglediao.qa.domain.User;
import com.eaglediao.qa.service.UserService;



import org.springframework.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        User user;
        try {
            String employeeID = request.getParameter("employeeID");
            String password = request.getParameter("password");
            if (StringUtils.isEmpty(employeeID) || StringUtils.isEmpty(password)) {

                return new ModelAndView("login", "message", "empty employeeID or password");
            }
            user = userService.getUserByEmployeeID(employeeID);
            if (user == null || !user.getPassword().equals(password)) {
                return new ModelAndView("login", "message", "wrong employeeID or password");

            } else {

                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return new ModelAndView("redirect:/user/doWait");
            }
        } catch (Exception e) {
            log.error("login Exception", e);
            return new ModelAndView("login", "message", "shit happens");
        }

    }

    @RequestMapping("/doLogin")
    public String Login() {
        return "forward:/user/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/doLogin";
    }

    @RequestMapping("/doWait")
    public String doWait(){
        return "wait";
    }
}
