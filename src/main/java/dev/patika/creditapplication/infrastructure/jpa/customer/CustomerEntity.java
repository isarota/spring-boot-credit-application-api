package dev.patika.creditapplication.infrastructure.jpa.customer;

import dev.patika.creditapplication.infrastructure.jpa.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "customer")
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
}
