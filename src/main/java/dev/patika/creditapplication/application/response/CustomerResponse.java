package dev.patika.creditapplication.application.response;

import dev.patika.creditapplication.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private Long identityNumber;
    private String fullName;
    private Double salary;
    private String phoneNumber;
    private Integer yearOfBirth;

    public static CustomerResponse from(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .identityNumber(customer.getIdentityNumber())
                .fullName(customer.getFullName())
                .salary(customer.getSalary())
                .phoneNumber(customer.getPhoneNumber())
                .yearOfBirth(customer.getYearOfBirth())
                .build();
    }
}
