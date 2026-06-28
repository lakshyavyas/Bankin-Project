package com.bank.kyc.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bank.kyc.dto.KycRequest;
import com.bank.kyc.entity.KycRecord;
import com.bank.kyc.enums.KycStatus;
import com.bank.kyc.repository.KycRepository;

@Service
public class KycService {
	 @Autowired
	    private KycRepository repository;

	    public KycRecord submitKyc(
	            KycRequest request) {
	    	Authentication authentication =
	    	        SecurityContextHolder.getContext().getAuthentication();

	    	Long userId =
	    	        (Long) authentication.getDetails();
	    	System.out.println("UserId from JWT = " + userId);
	    	if (repository.findByUserId(userId).isPresent()) {

	    	    throw new RuntimeException("KYC already submitted");
	    	}
	        KycRecord record =
	                new KycRecord();

	        record.setUserId(userId);

	        record.setAadhaarNumber(
	                request.getAadhaarNumber());

	        record.setPanNumber(
	                request.getPanNumber());

	        record.setAddress(
	                request.getAddress());

	        record.setStatus(
	                KycStatus.PENDING);

	        record.setSubmittedAt(
	                LocalDateTime.now());

	        return repository.save(record);
	    }
	    
	    public KycRecord getMyKyc() {

	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        Long userId = (Long) authentication.getDetails();

	        return repository.findByUserId(userId)
	                .orElseThrow(() ->
	                    new RuntimeException("KYC not found"));
	    }
	    
	    
	    public KycRecord getKycByUserId(Long userId) {

	        return repository.findByUserId(userId)
	                .orElseThrow(() ->
	                        new RuntimeException("KYC Not Found"));
	    }
	    public List<KycRecord> getPendingKycs() {

	        return repository.findByStatus(
	                KycStatus.PENDING);
	    }
	    public KycRecord approveKyc(Long kycId) {

	        KycRecord record =
	                repository.findById(kycId)
	                        .orElseThrow(() ->
	                                new RuntimeException(
	                                        "KYC Not Found"));

	        if(record.getStatus() != KycStatus.PENDING) {

	            throw new RuntimeException(
	                    "KYC already processed");
	        }

	        record.setStatus(
	                KycStatus.APPROVED);

	        record.setApprovedAt(
	                LocalDateTime.now());

	        return repository.save(record);
	    }
	    
	    
	    public KycRecord rejectKyc(
	            Long kycId,
	            String remarks) {

	        KycRecord record =
	                repository.findById(kycId)
	                        .orElseThrow(() ->
	                                new RuntimeException(
	                                        "KYC Not Found"));

	        if(record.getStatus() != KycStatus.PENDING) {

	            throw new RuntimeException(
	                    "KYC already processed");
	        }

	        record.setStatus(KycStatus.REJECTED);

	        record.setRemarks(remarks);

	        record.setApprovedAt(LocalDateTime.now());

	        return repository.save(record);

	    }
	    public List<KycRecord> getAllKycs() {

	        return repository.findAll();
	    }
	    
	    

}
