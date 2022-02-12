package dev.patika.creditapplication.domain;

import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Customer {
    private Long id;
    private Long identityNumber;
    private String fullName;
    private Double salary;
    private String phoneNumber;
    private Integer yearOfBirth;

    public static Customer from(CustomerEntity customerEntity) {
        return Customer.builder()
                .id(customerEntity.getId())
                .identityNumber(customerEntity.getIdentityNumber())
                .fullName(customerEntity.getFullName())
                .salary(customerEntity.getSalary())
                .phoneNumber(customerEntity.getPhoneNumber())
                .yearOfBirth(customerEntity.getYearOfBirth())
                .build();
    }

    public CustomerEntity toEntity() {
        final CustomerEntity entity = new CustomerEntity();
        entity.setId(id);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setIdentityNumber(identityNumber);
        entity.setFullName(fullName);
        entity.setSalary(salary);
        entity.setPhoneNumber(phoneNumber);
        entity.setYearOfBirth(yearOfBirth);
        return entity;
    }
}
