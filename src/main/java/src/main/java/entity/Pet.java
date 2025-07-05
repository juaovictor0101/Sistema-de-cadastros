package src.main.java.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
