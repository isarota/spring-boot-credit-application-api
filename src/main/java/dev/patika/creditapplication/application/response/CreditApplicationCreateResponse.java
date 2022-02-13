package dev.patika.creditapplication.application.response;

import dev.patika.creditapplication.domain.CreditApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreditApplicationCreateResponse {
    private final Long id;
    private final CreditApplicationStatus status;
}
