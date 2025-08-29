package br.com.registrationsystem.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetPostRequestBody {

    @Schema(description = "This is the anime first name", example = "Zeus")
    private String name;

    private String lastName;

    private String type;

    private String sex;

    @Valid
    private AddressRequestBody address;

    private BigDecimal age;

    private BigDecimal weight;

    private String breed;

}
