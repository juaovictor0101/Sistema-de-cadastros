package src.main.java.requests;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import src.main.java.entity.Address;

@Data
@Builder
public class PetPutRequestBody {
    @NotEmpty(message = "Pet first name cannot bem empty")
    private String name;
    private String lasName;
    private String sex;

    @Embedded
    private Address address;

    private double age;
    private double weight;
    private String breed;
    private String type;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
}
