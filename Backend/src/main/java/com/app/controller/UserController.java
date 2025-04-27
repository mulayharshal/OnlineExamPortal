package com.app.controller;

import com.app.entity.User;
import com.app.request.LoginRequest;
import com.app.response.Response;
import com.app.service.UserService;
import com.app.util.Status;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    

    
   
    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody User user) {
    	Response response = userService.registerUser(user);
       return new ResponseEntity<Response>(response,HttpStatus.OK);

    }

    
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest) {
    		Response response= userService.loginUser(loginRequest); 
        return new ResponseEntity<Response>(response,HttpStatus.OK);
    }

    
}
