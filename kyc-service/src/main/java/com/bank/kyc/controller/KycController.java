//package com.bank.kyc.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bank.kyc.dto.KycRequest;
//import com.bank.kyc.dto.RejectKycRequest;
//import com.bank.kyc.entity.KycRecord;
//import com.bank.kyc.service.KycService;
//
//
//@RestController
//@RequestMapping("/kyc")
//public class KycController {
//	
//    @Autowired
//    private KycService service;
//
//    @PreAuthorize("hasRole('CUSTOMER')")
//    @PostMapping("/submit")
//    public KycRecord submitKyc(
//            @RequestBody KycRequest request) {
//
//        return service.submitKyc(request);
//    }
//    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
//    @GetMapping("/user/{userId}")
//    public KycRecord getCustomerKyc(
//            @PathVariable Long userId) {
//
//        return service.getKycByUserId(userId);
//    }
//    
//    
//    @PreAuthorize("hasRole('CUSTOMER')")
//    @GetMapping("/me")
//    public KycRecord getKyc() {
//
//        return service.getMyKyc();
//    }
//    
//    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
//    @GetMapping("/pending")
//    public List<KycRecord> getPendingKycs() {
//
//        return service.getPendingKycs();
//    }
//    
//    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
//    @PutMapping("/{kycId}/approve")
//    public KycRecord approveKyc(
//            @PathVariable Long kycId) {
//
//        return service.approveKyc(kycId);
//    }
//    
//    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
//    @PutMapping("/{kycId}/reject")
//    public KycRecord rejectKyc(
//            @PathVariable Long kycId,
//            @RequestBody RejectKycRequest request) {
//
//        return service.rejectKyc(
//                kycId,
//                request.getRemarks());
//    }
//}
package com.bank.kyc.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.kyc.dto.KycRequest;
import com.bank.kyc.dto.RejectKycRequest;
import com.bank.kyc.entity.KycRecord;
import com.bank.kyc.service.KycService;

@RestController
@RequestMapping("/kyc")
public class KycController {

    private final KycService service;

    public KycController(KycService service) {
        this.service = service;
    }

    // =====================================================
    // CUSTOMER APIs
    // =====================================================

    // Submit KYC
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/submit")
    public KycRecord submitKyc(
            @RequestBody KycRequest request) {

        return service.submitKyc(request);
    }

    // View own KYC
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/me")
    public KycRecord getMyKyc() {

        return service.getMyKyc();
    }

    // =====================================================
    // EMPLOYEE / ADMIN APIs
    // =====================================================

    // View all pending KYC requests
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/pending")
    public List<KycRecord> getPendingKycs() {

        return service.getPendingKycs();
    }

    // View a particular customer's KYC
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @GetMapping("/user/{userId}")
    public KycRecord getCustomerKyc(
            @PathVariable Long userId) {

        return service.getKycByUserId(userId);
    }

    // Approve KYC
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PutMapping("/{kycId}/approve")
    public KycRecord approveKyc(
            @PathVariable Long kycId) {

        return service.approveKyc(kycId);
    }

    // Reject KYC
    @PreAuthorize("hasAnyRole('EMPLOYEE','ADMIN')")
    @PutMapping("/{kycId}/reject")
    public KycRecord rejectKyc(
            @PathVariable Long kycId,
            @RequestBody RejectKycRequest request) {

        return service.rejectKyc(
                kycId,
                request.getRemarks());
    }

    // =====================================================
    // ADMIN ONLY (Optional)
    // =====================================================

    // View all KYC records
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<KycRecord> getAllKycs() {

        return service.getAllKycs();
    }
}
