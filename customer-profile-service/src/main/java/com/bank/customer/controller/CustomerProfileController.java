package com.bank.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customer.dto.CustomerProfileRequest;
import com.bank.customer.entity.CustomerProfile;
import com.bank.customer.service.CustomerProfileService;

@RestController
@RequestMapping("/profile")
public class CustomerProfileController {

    @Autowired
    private CustomerProfileService service;
    
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public List<CustomerProfile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @PostMapping
    public CustomerProfile createProfile(
            @RequestBody CustomerProfileRequest request) {
  
    	
    	

        return service.createProfile(request);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    public CustomerProfile getProfile(
            @PathVariable Long userId) {

        return service.getProfile(userId);
    }
    
//    @PutMapping("/{userId}")
//    public CustomerProfile updateProfile(
//            @PathVariable Long userId,
//            @RequestBody CustomerProfileRequest request) {
//
//        return service.updateProfile(
//                userId,
//                request);
//    }
    
    @GetMapping("/me")
    public CustomerProfile getMyProfile() {

        return service.getMyProfile();
    }
    @PutMapping("/me")
    public CustomerProfile updateMyProfile(
            @RequestBody CustomerProfileRequest request) {

        return service.updateMyProfile(request);
    }
    
    
}
