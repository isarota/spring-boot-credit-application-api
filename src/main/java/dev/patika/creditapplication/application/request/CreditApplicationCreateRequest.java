package dev.patika.creditapplication.application.request;

import dev.patika.creditapplication.domain.CreditApplication;
import dev.patika.creditapplication.domain.CreditApplicationStatus;
import dev.patika.creditapplication.domain.Customer;
import lombok.Getter;

@Getter
public class CreditApplicationCreateRequest {
    private Long customerId;

    public CreditApplication convertToCreditApplication() {
        return CreditApplication.builder()
                .customerId(customerId)
                .build();
    }
}
