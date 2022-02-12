package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.Customer;
import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerEntity;
import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerJpaRepository jpaRepository;

    public Long create(Customer customer) {
        final CustomerEntity entity = jpaRepository.save(customer.toEntity());
        return entity.getId();
    }

    public Customer retrieve(Long customerId) {
        final Optional<CustomerEntity> entityOptional = jpaRepository.findById(customerId);
        return Customer.from(entityOptional.orElse(null));
    }

    public Customer retrieveByIdentityNumber(Long customerIdentityNumber) {
        final Optional<CustomerEntity> entityOptional = jpaRepository.findByIdentityNumber(customerIdentityNumber);
        return Customer.from(entityOptional.orElse(null));
    }
}
