package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.*;
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
    private final CreditScoreService creditScoreService;

    private static final Integer MODERATE_SALARY = 5000;

    private static final Double CREDIT_LIMIT_LOW = 10000.0;
    private static final Double CREDIT_LIMIT_MODERATE = 20000.0;

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

        final Double creditScore = creditScoreService.query(customer);

        entity.setCreditScore(creditScore);

        if (entity.getCreditScore() < CreditScoreLimit.LOWER_LIMIT.getLimit()) {
            entity.setStatus(CreditApplicationStatus.REJECTED);
            jpaRepository.save(entity);
            return CreditApplication.from(entity);
        } else {
            entity.setStatus(CreditApplicationStatus.APPROVED);
        }

        setCreditLimit(customer, entity);
        jpaRepository.save(entity);
        return CreditApplication.from(entity);
    }

    private void setCreditLimit(Customer customer, CreditApplicationEntity entity) {
        if (entity.getCreditScore() < CreditScoreLimit.UPPER_LIMIT.getLimit()) {
            if (customer.getSalary() < MODERATE_SALARY) {
                entity.setCreditLimit(CREDIT_LIMIT_LOW);
            } else {
                entity.setCreditLimit(CREDIT_LIMIT_MODERATE);
            }
        } else {
            entity.setCreditLimit(customer.getSalary() * Constants.CREDIT_LIMIT_FACTOR);
        }
    }
}
