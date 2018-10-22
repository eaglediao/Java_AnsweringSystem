package com.eaglediao.qa.service.impl;

import com.eaglediao.qa.dao.UserDao;
import com.eaglediao.qa.domain.User;
import com.eaglediao.qa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Transactional
    public User getUserByEmployeeID(String employeeID) throws Exception {

        return userDao.getUserByID(employeeID);
    }

    @Transactional
    public void updateUser(User user) throws Exception {
        this.userDao.updateUser(user);
    }

}