package br.com.registrationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "pets")
public class Pet {


    @NotEmpty(message = "Pet first name cannot be empty")
    private String name;

    @NotEmpty(message = "Pet lastname cannot be empty")
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private SexPet sex;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypePet type;

    @Embedded
    private Address address;

    @Max(value = 20L, message = "Pet's age cannot be bigger than 20 years")
    private BigDecimal age;

    @DecimalMin(value = "0.5", message = "Pet's weight cannot be less than 0,5 Kg")
    @DecimalMax(value = "60.0", message = "Pet's weight cannot be bigger than 60 Kg")
    private BigDecimal weight;

    private String breed;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PreUpdate
    @PrePersist
    public void beforeSaveOrUpdate() {
        if (name == null) {
            this.name = "NÃO INFORMADO";
        }

        if (lastName == null) {
            this.lastName = "NÃO INFORMADO";
        }
        if (breed == null) {
            this.breed = "NÃO INFORMADO";
        }
        if (age == null || age.intValue() <= 0) {
            this.age = BigDecimal.ZERO;
        }
        if (this.address != null && this.address.getNumber() == null) {
            this.address.setNumber("NÃO INFORMADO");
        }
    }

}