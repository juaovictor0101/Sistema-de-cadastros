package br.com.registrationsystem.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
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

    @Schema(description = "This is the last name of pet", example = "Silva")
    private String lastName;

    @Schema(description = "That's the type of pet, it can be just a cat or a dog.", example = "CAT or DOG")
    private String type;

    @Schema(description = "This is the pet's gender; it can only be male or female.", example = "FEMALE or MALE")
    private String sex;

    @Schema(description = "The address must include the city and street name; the number is optional. If not provided, " +
            "it will be saved as ‘NOT PROVIDED’.", example = "São Paulo, Avenida Paulista, 3004")
    @Valid
    private AddressRequestBody address;

    @Schema(description = "The pet's age must be a number greater than 0 and less than 20.", example = "8.0")
    private BigDecimal age;

    @Schema(description = "The pet's weight must be between 0.5 and 60 kg.", example = "10.0")
    private BigDecimal weight;

    @Schema(description = "the pet's breed must be a word without special characters", example = "Pug")
    private String breed;

}
