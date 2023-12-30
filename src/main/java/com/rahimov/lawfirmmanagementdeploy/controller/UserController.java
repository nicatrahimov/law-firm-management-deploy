package com.rahimov.lawfirmmanagementdeploy.controller;



import com.rahimov.lawfirmmanagementdeploy.model.User;
import com.rahimov.lawfirmmanagementdeploy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class    UserController {

   private final UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws IOException {
       return new ResponseEntity<>(userService.getById(id), HttpStatusCode.valueOf(200));
    }
}