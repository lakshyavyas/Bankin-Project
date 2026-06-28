package com.bank.kyc.entity;

import java.time.LocalDateTime;

import com.bank.kyc.enums.KycStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kyc_records")
public class KycRecord {
    @Id
    @GeneratedValue(	strategy = GenerationType.IDENTITY)
	private Long kycId;

    private Long userId;

    private String aadhaarNumber;

    private String panNumber;

    private String address;

    @Enumerated(EnumType.STRING)
    private KycStatus status;

    private String remarks;

    private LocalDateTime submittedAt;

    private LocalDateTime approvedAt;

}
