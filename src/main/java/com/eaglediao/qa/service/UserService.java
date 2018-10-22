package com.eaglediao.qa.service;

import com.eaglediao.qa.domain.User;

public interface UserService {

    User getUserByEmployeeID(String employeeID) throws Exception;
    void updateUser(User user) throws Exception;
}