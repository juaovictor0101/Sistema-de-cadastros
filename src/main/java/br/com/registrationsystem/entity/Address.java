package br.com.registrationsystem.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

    private String number;
    private String street;
    private String city;
}
