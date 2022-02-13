package dev.patika.creditapplication.infrastructure.jpa.creditapplication;

import dev.patika.creditapplication.domain.CreditApplicationStatus;
import dev.patika.creditapplication.infrastructure.jpa.common.BaseEntity;
import dev.patika.creditapplication.infrastructure.jpa.customer.CustomerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "credit_application")
public class CreditApplicationEntity extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column
    private Double creditScore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditApplicationStatus status;

    @Column(columnDefinition = "Decimal(16,2) default '0.0'")
    private Double creditLimit;
}
