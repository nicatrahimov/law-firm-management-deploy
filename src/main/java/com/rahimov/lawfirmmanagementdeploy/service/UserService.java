package com.rahimov.lawfirmmanagementdeploy.service;


import com.rahimov.lawfirmmanagementdeploy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDetailsService userDetailsService();

//    ResponseEntity<UserDto> getByUsername(String username) throws IOException;

    List<User> getAllUsers();

    User getById(Long id);

    void saveUser(User user);

    User getByUsername(String username);

    User getByEmail(String email);
}