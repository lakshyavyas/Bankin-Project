package com.bank.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bank.customer.dto.CustomerProfileRequest;
import com.bank.customer.entity.CustomerProfile;
import com.bank.customer.repository.CustomerProfileRepository;

@Service
public class CustomerProfileService {

	
	
	 @Autowired
	    private CustomerProfileRepository repository;

	    public CustomerProfile createProfile(
	            CustomerProfileRequest request) {
	    	
	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        Long userId = (Long) authentication.getDetails();
	    	
	    	
//	    	if(repository.findByUserId(
//	    	        request.getUserId()).isPresent()) {
//
//	    	    throw new RuntimeException(
//	    	            "Profile already exists");
//	    	}

	        CustomerProfile profile =
	                new CustomerProfile();

	        profile.setUserId(userId);
	        profile.setFirstName(request.getFirstName());
	        profile.setLastName(request.getLastName());
	        profile.setEmail(request.getEmail());
	        profile.setMobile(request.getMobile());
	        profile.setDateOfBirth(request.getDateOfBirth());
	        profile.setGender(request.getGender());

	        profile.setProfileCompleted(true);

	        return repository.save(profile);
	    }

	    public CustomerProfile getProfile(
	            Long userId) {

	        return repository.findByUserId(userId)
	                .orElseThrow(() ->
	                        new RuntimeException(
	                                "Profile not found"));
	    }
	    
	    public CustomerProfile updateProfile(
	            Long userId,
	            CustomerProfileRequest request) {

	        CustomerProfile profile =
	                repository.findByUserId(userId)
	                        .orElseThrow(() ->
	                                new RuntimeException(
	                                        "Profile not found"));

	        profile.setFirstName(request.getFirstName());
	        profile.setLastName(request.getLastName());
	        profile.setEmail(request.getEmail());
	        profile.setMobile(request.getMobile());
	        profile.setDateOfBirth(request.getDateOfBirth());
	        profile.setGender(request.getGender());

	        return repository.save(profile);
	    }
	    
	    public CustomerProfile getMyProfile() {

	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        Long userId = (Long) authentication.getDetails();

	        return repository.findByUserId(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("Profile not found"));
	    }
	    
	    public CustomerProfile updateMyProfile(CustomerProfileRequest request) {

	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        Long userId = (Long) authentication.getDetails();

	        CustomerProfile profile = repository.findByUserId(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("Profile not found"));

	        profile.setFirstName(request.getFirstName());
	        profile.setLastName(request.getLastName());
	        profile.setEmail(request.getEmail());
	        profile.setMobile(request.getMobile());
	        profile.setDateOfBirth(request.getDateOfBirth());
	        profile.setGender(request.getGender());

	        return repository.save(profile);
	    }

		public List<CustomerProfile> getAllProfiles() {
			// TODO Auto-generated method stub
			 return repository.findAll();
		}
	    
}
