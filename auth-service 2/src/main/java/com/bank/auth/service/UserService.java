package com.bank.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.auth.dto.CustomerRegisterRequest;
import com.bank.auth.dto.EmployeeRequest;
import com.bank.auth.entity.Role;
import com.bank.auth.entity.User;
import com.bank.auth.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User saveUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	public boolean login(String username, String password) {

		User user = userRepository.findByUsername(username).orElse(null);

		if (user == null)
			return false;
		
		   if(!user.isEnabled())
		        return false;


		return passwordEncoder.matches(password, user.getPassword());
	}
	
    public User getUserByUsername(String username) {

        return userRepository
                .findByUsername(username)
                .orElse(null);
    }
    public User createEmployee(EmployeeRequest request) {

        User employee = new User();

        employee.setUsername(request.getUsername());

        employee.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        employee.setRole(Role.EMPLOYEE);

        employee.setEnabled(true);

        return userRepository.save(employee);
    }
    
    public List<User> getAllEmployees(){
    	return userRepository.findByRole(Role.EMPLOYEE);
    	
    }
    
    public User disableEmployee(Long id) {

        User employee =
                userRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Employee not found"));

        employee.setEnabled(false);

        return userRepository.save(employee);
    }
    
    public User enableEmployee(Long id) {

        User employee = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        employee.setEnabled(true);

        return userRepository.save(employee);
    }
    
    public User getEmployee(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
    }
    
    
    public User resetPassword(
            Long id,
            String newPassword) {

        User employee = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        employee.setPassword(
                passwordEncoder.encode(newPassword));

        return userRepository.save(employee);
    }
    
    
    public User registerCustomer(
            CustomerRegisterRequest request) {
    	

        if(userRepository.findByUsername(
                request.getUsername()).isPresent()) {

            throw new RuntimeException("Username already exists");}
    	

        User customer = new User();

        customer.setUsername(
                request.getUsername());

        customer.setPassword(
                passwordEncoder.encode(
                        request.getPassword()));

        customer.setRole(Role.CUSTOMER);

        customer.setEnabled(true);

        return userRepository.save(customer);
    }


    
    
    
}