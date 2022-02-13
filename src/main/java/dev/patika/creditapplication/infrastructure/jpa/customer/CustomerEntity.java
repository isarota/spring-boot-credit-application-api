package dev.patika.creditapplication.infrastructure.jpa.customer;

import dev.patika.creditapplication.infrastructure.jpa.common.BaseEntity;
import dev.patika.creditapplication.infrastructure.jpa.creditapplication.CreditApplicationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
    @Column(nullable = false)
    private Long identityNumber;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private Double salary;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private Integer yearOfBirth;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CreditApplicationEntity creditApplication;
}
