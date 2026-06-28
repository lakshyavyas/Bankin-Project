package com.bank.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bank.auth.dto.CustomerRegisterRequest;
import com.bank.auth.dto.LoginRequest;
import com.bank.auth.dto.LoginResponse;
import com.bank.auth.entity.User;
import com.bank.auth.security.JwtUtil;
import com.bank.auth.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	   @Autowired
	    private UserService userService;
	   
	    @Autowired
	    private JwtUtil jwtUtil;
	   
	   @PostMapping("/register")
	   public User registerCustomer(
	           @RequestBody CustomerRegisterRequest request) {

	       return userService.registerCustomer(request);
	   }
	   
		@PostMapping("/login")
		public LoginResponse login(@RequestBody LoginRequest request) {

			boolean result = userService.login(request.getUsername(), request.getPassword());

			if (result) {
				

		        User user =
		                userService.getUserByUsername(
		                        request.getUsername());


				String token = jwtUtil.generateToken(user.getId(),user.getUsername(),user.getRole().name());

				  return new LoginResponse(
			                token,
			                user.getId(),
			                user.getUsername(),
			                user.getRole().name());
			}
			throw new ResponseStatusException(
			        HttpStatus.UNAUTHORIZED,
			        "Invalid Credentials");
		}

}
