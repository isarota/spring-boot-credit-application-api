package dev.patika.creditapplication.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer {
    private Long id;
    private Long identityNumber;
    private String fullName;
    private Double salary;
    private String phoneNumber;
    private Integer yearOfBirth;
}
