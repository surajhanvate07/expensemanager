package com.expensemanager.service.impl;

import com.expensemanager.dto.UserDTO;
import com.expensemanager.entity.User;
import com.expensemanager.repository.UserRepository;
import com.expensemanager.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User newUser = mapToEntity(userDTO);
        newUser.setUserId(UUID.randomUUID().toString());
        userRepository.save(newUser);
        return mapToDTO(newUser);
    }

    @Override
    public User getLoggedInUser() {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String loggedInUserEmail = auth.getName();
       return userRepository.getUserByUserName(loggedInUserEmail);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    private User mapToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
