package br.com.registrationsystem.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PetPutRequestBody {
    @Id
    private Long id;

    @Schema(description = "This is the anime first name", example = "Zeus")
    private String name;

    private String lastName;

    private String type;

    @NotEmpty
    private String sex;

    @Valid
    private AddressRequestBody address;

    @DecimalMax("20.0")
    private BigDecimal age;

    @DecimalMin("0.5") @DecimalMax("60.0")
    private BigDecimal weight;

    private String breed;
}
