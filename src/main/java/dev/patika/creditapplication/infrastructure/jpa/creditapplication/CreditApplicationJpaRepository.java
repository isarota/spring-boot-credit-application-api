package dev.patika.creditapplication.infrastructure.jpa.creditapplication;

import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditApplicationJpaRepository extends JpaRepository<CreditApplicationEntity, Long> {
    Optional<CreditApplicationEntity> findByCustomer(CustomerEntity customer);
}
