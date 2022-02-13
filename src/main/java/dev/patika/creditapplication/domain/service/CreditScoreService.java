package dev.patika.creditapplication.domain.service;

import dev.patika.creditapplication.domain.Constants;
import dev.patika.creditapplication.domain.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreditScoreService {

    public Double query(Customer customer) {
        final int age = LocalDateTime.now().getYear() - customer.getYearOfBirth();
        final Double salary = customer.getSalary();

        if (age < 18 || salary < 0) throw new RuntimeException();

        return (salary * Constants.CREDIT_SCORE_SALARY_FACTOR) + (age * Constants.CREDIT_SCORE_AGE_FACTOR);
    }
}
