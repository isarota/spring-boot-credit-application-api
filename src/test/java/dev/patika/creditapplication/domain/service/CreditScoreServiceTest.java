package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditScoreServiceTest {

    @Autowired
    CreditScoreService underTest;

    @Test
    @Sql(scripts = "/cleanup.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void it_should_calculate_credit_score() {
        // given
        final Customer customer = Customer.builder()
                .identityNumber(1L)
                .fullName("Isa Kilikya")
                .salary(4500.0)
                .phoneNumber("00905363630000")
                .yearOfBirth(1990)
                .build();

        // when
        final Double result = underTest.query(customer);

        // then
        final Double expected = 578.0;
        assertThat(result).isEqualTo(expected);
    }
}