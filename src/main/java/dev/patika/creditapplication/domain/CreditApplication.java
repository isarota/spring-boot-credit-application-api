package dev.patika.creditapplication.domain;

import dev.patika.creditapplication.infrastructure.jpa.creditapplication.CreditApplicationEntity;
import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreditApplication {
    private Long id;
    private Long customerId;
    private Double creditScore;
    private CreditApplicationStatus status;
    private Double creditLimit;

    public CreditApplicationEntity toEntity(CustomerEntity customerEntity) {
        final CreditApplicationEntity entity = new CreditApplicationEntity();
        entity.setId(id);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCustomer(customerEntity);
        entity.setCreditScore(creditScore);
        entity.setStatus(status);
        entity.setCreditLimit(creditLimit);
        return entity;
    }

    public static CreditApplication from(CreditApplicationEntity entity) {
        return CreditApplication.builder()
                .id(entity.getId())
                .customerId(entity.getCustomer().getId())
                .creditScore(entity.getCreditScore())
                .status(entity.getStatus())
                .creditLimit(entity.getCreditLimit())
                .build();
    }
}
