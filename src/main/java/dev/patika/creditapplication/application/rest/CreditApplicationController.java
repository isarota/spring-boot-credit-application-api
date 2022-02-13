package dev.patika.creditapplication.application.rest;

import dev.patika.creditapplication.application.request.CreditApplicationCreateRequest;
import dev.patika.creditapplication.application.response.CreditApplicationCreateResponse;
import dev.patika.creditapplication.application.response.CreditApplicationResponse;
import dev.patika.creditapplication.domain.CreditApplication;
import dev.patika.creditapplication.domain.CreditApplicationStatus;
import dev.patika.creditapplication.domain.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/credit-application")
public class CreditApplicationController {

    private final CreditApplicationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreditApplicationCreateResponse create(@RequestBody @Valid CreditApplicationCreateRequest request) {
        Long createdCreditApplicationId = service.create(request.convertToCreditApplication());
        return new CreditApplicationCreateResponse(createdCreditApplicationId, CreditApplicationStatus.WAITING);
    }

    @GetMapping
    public CreditApplicationResponse retrieve(@RequestParam Long customerIdentityNumber) {
        final CreditApplication creditApplication = service.retrieveByCustomerIdentityNumber(customerIdentityNumber);
        return CreditApplicationResponse.from(creditApplication);
    }
}
