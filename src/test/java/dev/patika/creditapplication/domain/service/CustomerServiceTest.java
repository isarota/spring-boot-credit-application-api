package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService underTest;

    @Test
    void it_should_create_new_customer() {
        // given
        final Customer customer = Customer.builder()
                .id(1L)
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
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void it_should_retrieve_existing_customer() {
        // given
        final Customer expected = Customer.builder()
                .id(1L)
                .identityNumber(1L)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();
        final Long customerId = underTest.create(expected);

        // when
        Customer result = underTest.retrieve(customerId);

        // then
        assertThat(result.getIdentityNumber()).isEqualTo(expected.getIdentityNumber());
    }
}