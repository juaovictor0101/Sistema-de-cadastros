package br.com.registrationsystem.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetSearchRequestBody {

    private String name;
    private String lastName;
    private String sex;
    private String type;
    private String street;
    private String city;
    private String number;
    private BigDecimal age;
    private String breed;
    private BigDecimal weight;
}
