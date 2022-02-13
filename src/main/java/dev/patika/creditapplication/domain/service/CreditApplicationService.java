package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.CreditApplication;
import dev.patika.creditapplication.domain.CreditApplicationStatus;
import dev.patika.creditapplication.domain.Customer;
import dev.patika.creditapplication.infrastructure.jpa.creditapplication.CreditApplicationEntity;
import dev.patika.creditapplication.infrastructure.jpa.creditapplication.CreditApplicationJpaRepository;
import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {

    private final CreditApplicationJpaRepository jpaRepository;
    private final CustomerService customerService;

    public Long create(CreditApplication creditApplication) {
        final CustomerEntity customerEntity = customerService.retrieveEntityById(creditApplication.getCustomerId());
        final CreditApplicationStatus status = CreditApplicationStatus.WAITING;
        final CreditApplication creditApplicationForEntity = CreditApplication.builder()
                .customerId(creditApplication.getCustomerId())
                .status(status)
                .build();
        final CreditApplicationEntity entity = jpaRepository.save(creditApplicationForEntity.toEntity(customerEntity));
        return entity.getId();
    }

    public CreditApplication retrieveByCustomerIdentityNumber(Long customerIdentityNumber) {
        final Customer customer = customerService.retrieveByIdentityNumber(customerIdentityNumber);
        final Optional<CreditApplicationEntity> entityOptional = jpaRepository.findByCustomer(customer.toEntity());
        final CreditApplicationEntity entity = entityOptional.get();

        entity.setCreditScore(600.0);  // TODO: Hardcoded

        if (entity.getCreditScore() < 500) {  // TODO: Hardcoded
            entity.setStatus(CreditApplicationStatus.REJECTED);
            jpaRepository.save(entity);
            return CreditApplication.from(entity);
        } else {
            entity.setStatus(CreditApplicationStatus.APPROVED);
        }

//        TODO: Hardcoded part
        if (entity.getCreditScore() < 1000) {
            if (customer.getSalary() < 5000) {
                entity.setCreditLimit(10000.0);
            } else {
                entity.setCreditLimit(20000.0);
            }
        } else {
            // TODO: aylik_gelir * kredi_limit_carpani
            entity.setCreditLimit(customer.getSalary() * 4.0);
        }

        jpaRepository.save(entity);
        return CreditApplication.from(entity);
    }
}
