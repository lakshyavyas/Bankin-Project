package com.bank.kyc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.kyc.entity.KycRecord;
import com.bank.kyc.enums.KycStatus;

@Repository
public interface KycRepository extends JpaRepository<KycRecord, Long> {

    Optional<KycRecord> findByUserId(Long userId);
    List<KycRecord> findByStatus(KycStatus status);
}
