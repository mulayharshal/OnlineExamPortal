package com.app.service;

import java.util.Optional;

import com.app.entity.User;
import com.app.request.LoginRequest;
import com.app.response.Response;

public interface UserService {

	

	Response registerUser(User user);

	Response loginUser(LoginRequest loginRequest);

}
