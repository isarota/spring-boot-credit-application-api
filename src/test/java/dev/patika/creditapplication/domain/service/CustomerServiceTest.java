package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService underTest;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_create_new_customer() {
        // given
        final Customer customer = Customer.builder()
                .identityNumber(1L)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();

        // when
        final Long result = underTest.create(customer);

        // then
        final Long expected = 1L;
        assertThat(result).isGreaterThanOrEqualTo(expected);
    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_existing_customer_by_id() {
        // given
        final Customer newCustomer = Customer.builder()
                .identityNumber(1L)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();
        final Long customerId = underTest.create(newCustomer);

        // when
        Customer result = underTest.retrieve(customerId);

        // then
        final Long expected = 1L;
        assertThat(result.getIdentityNumber()).isEqualTo(expected);
    }

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_retrieve_existing_customer_by_identity_number() {
        // given
        Long identityNumber = 1L;
        final Customer newCustomer = Customer.builder()
                .identityNumber(identityNumber)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();
        underTest.create(newCustomer);

        // when
        Customer result = underTest.retrieveByIdentityNumber(identityNumber);

        // then
        final Long expected = 1L;
        assertThat(result.getIdentityNumber()).isEqualTo(expected);
    }
}