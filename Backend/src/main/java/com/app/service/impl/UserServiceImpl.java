package com.app.service.impl;

import com.app.entity.User;
import com.app.repository.UserRepository;
import com.app.request.LoginRequest;
import com.app.response.Response;
import com.app.service.UserService;
import com.app.util.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

 
	@Override
	public Response registerUser(User user) {
		// TODO Auto-generated method stub
		Response response = new Response();
		if(user!=null) {
		 userRepository.save(user);
		 response.setRes(user);
		 response.setStatus(Status.SUCCESS);
		}else {
			response.setRes("Registration Fail");
			response.setStatus(Status.FAIL);
		}
		return response;
	}

	@Override
	public Response loginUser(LoginRequest loginRequest) {
		User user =userRepository.findByEmail(loginRequest.getEmail());
		Response response= new Response();
		System.out.println(user);
		
		if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
			
        	response.setRes(user);
        	response.setStatus(Status.SUCCESS);
            
        } else {
        	response.setRes("Invalid Username OR Password");
        	response.setStatus(Status.FAIL);
            
        }
		return response;
	}
}
