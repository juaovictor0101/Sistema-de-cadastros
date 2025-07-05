package src.main.java.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import src.main.java.entity.Address;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetPostRequestBody {

    @NotEmpty(message = "Pets first name cannot be empty")
    @Schema(description = "This is the anime first name", example = "Zeus")
    private String name;
    private String lasName;
    private String sex;

    @Embedded
    private Address address;

    private double age;
    private double weight;
    private String breed;
    private String type;
}
