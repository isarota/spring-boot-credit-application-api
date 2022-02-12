package dev.patika.creditapplication.application.request;

import dev.patika.creditapplication.domain.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CustomerCreateRequest {
    @NotNull
    private Long identityNumber;
    @NotBlank
    private String fullName;
    @NotNull
    private Double salary;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Integer yearOfBirth;

    public Customer convertToCustomer() {
        return Customer.builder()
                .identityNumber(identityNumber)
                .fullName(fullName)
                .salary(salary)
                .phoneNumber(phoneNumber)
                .yearOfBirth(yearOfBirth)
                .build();
    }
}
