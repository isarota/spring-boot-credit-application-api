package dev.patika.creditapplication.application.rest;

import dev.patika.creditapplication.application.request.CustomerCreateRequest;
import dev.patika.creditapplication.application.response.CustomerCreateResponse;
import dev.patika.creditapplication.application.response.CustomerResponse;
import dev.patika.creditapplication.domain.Customer;
import dev.patika.creditapplication.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerCreateResponse create(@RequestBody @Valid CustomerCreateRequest request) {
        Long createdCustomerId = service.create(request.convertToCustomer());
        return new CustomerCreateResponse(createdCustomerId);
    }

    @GetMapping
    public CustomerResponse retrieve(@RequestParam(required = false) Long id, @RequestParam(required = false) Long identityNumber) {
        Customer customer = null;
        if (id == null) {
            customer = service.retrieveByIdentityNumber(identityNumber);
        } else {
            customer = service.retrieve(id);
        }
        return CustomerResponse.from(customer);
    }
}
