package com.expensemanager.service;

import com.expensemanager.dto.UserDTO;
import com.expensemanager.entity.User;

public interface UserService {
    public UserDTO saveUser(UserDTO userDTO);

    public User getLoggedInUser();
}
