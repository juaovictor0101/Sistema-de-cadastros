package br.com.registrationsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String lasName;
    private SexPet sex;
    private TypePet type;

    @Embedded
    private Address address;
    @Max(value = 20L, message = "Pet's age cannot be bigger than 20 years")
    private double age;

    @DecimalMin(value="0.5", message = "Pet's weight cannot be less than 0,5 Kg")
    @DecimalMax(value = "60.0", message = "Pet's weight cannot be bigger than 60 Kg")
    private Double weight;

    private String breed;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}