package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    public Long create(Customer customer) {
        return 1L;
    }

    public Customer retrieve(Long customerId) {
        return Customer.builder()
                .id(1L)
                .identityNumber(1L)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();
    }
}
