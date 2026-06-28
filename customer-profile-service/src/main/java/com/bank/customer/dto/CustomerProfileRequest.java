package com.bank.customer.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CustomerProfileRequest {
    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private LocalDate dateOfBirth;

    private String gender;

}
