package com.eaglediao.qa.dao;

import com.eaglediao.qa.domain.User;

public interface UserDao {
    public User getUserByID(String employeeID) throws Exception;
    public void updateUser(User user) throws Exception;
}