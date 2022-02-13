package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.CreditApplication;
import dev.patika.creditapplication.domain.CreditApplicationStatus;
import dev.patika.creditapplication.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CreditApplicationServiceTest {

    @Autowired
    CreditApplicationService underTest;

    @Autowired
    CustomerService customerService;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_create_new_credit_application_and_retrieve_it_by_customer_id() {
        // TODO: this test should be separated by two
        // given
        final Long customerIdentityNumber = 1L;
        final Customer customer = Customer.builder()
                .identityNumber(customerIdentityNumber)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();
        final Long customerId = customerService.create(customer);

        final CreditApplication creditApplication = CreditApplication.builder()
                .customerId(customerId)
                .build();

        // when
        underTest.create(creditApplication);
        final CreditApplication result = underTest.retrieveByCustomerIdentityNumber(customerIdentityNumber);

        // then
        final CreditApplicationStatus expected = CreditApplicationStatus.APPROVED;
        assertThat(result.getStatus()).isEqualTo(expected);
    }
}