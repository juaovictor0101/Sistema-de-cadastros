package br.com.registrationsystem.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String number;
}
