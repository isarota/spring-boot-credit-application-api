package dev.patika.creditapplication.application.response;

import dev.patika.creditapplication.domain.CreditApplication;
import dev.patika.creditapplication.domain.CreditApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditApplicationResponse {
    private Long id;
    private Long customerId;
    private Double creditScore;
    private CreditApplicationStatus status;
    private Double creditLimit;

    public static CreditApplicationResponse from(CreditApplication creditApplication) {
        return CreditApplicationResponse.builder()
                .id(creditApplication.getId())
                .customerId(creditApplication.getCustomerId())
                .creditScore(creditApplication.getCreditScore())
                .status(creditApplication.getStatus())
                .creditLimit(creditApplication.getCreditLimit())
                .build();
    }
}
