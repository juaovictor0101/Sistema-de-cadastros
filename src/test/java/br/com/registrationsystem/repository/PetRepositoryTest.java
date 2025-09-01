package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.Address;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.entity.TypePet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
@DisplayName("Tests for Pet repository")
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    @DisplayName("Save creates pet whe successful")
    void save_PersistPet_WhenSuccessful() {

        Pet petToBeSaved = createPet();
        Pet savedPet = this.petRepository.save(petToBeSaved);

        Assertions.assertThat(savedPet).isNotNull();
        Assertions.assertThat(savedPet.getId()).isNotNull();
        Assertions.assertThat(savedPet.getName()).isEqualTo(petToBeSaved.getName());
        Assertions.assertThat(savedPet.getName()).isNotNull();

        Assertions.assertThat(savedPet.getLastName()).isNotNull();
        Assertions.assertThat(savedPet.getLastName()).isEqualTo(petToBeSaved.getLastName());

        Assertions.assertThat(savedPet.getSex()).isNotNull();
        Assertions.assertThat(savedPet.getSex()).isEqualTo(petToBeSaved.getSex());

        Assertions.assertThat(savedPet.getAge()).isNotNull();
        Assertions.assertThat(savedPet.getAge()).isEqualTo(petToBeSaved.getAge());
        Assertions.assertThat(savedPet.getAge()).isLessThanOrEqualTo(new BigDecimal(20));


        Assertions.assertThat(savedPet.getAddress()).isNotNull();
        Assertions.assertThat(savedPet.getAddress().getCity()).isNotNull();
        Assertions.assertThat(savedPet.getAddress().getStreet()).isNotNull();
        Assertions.assertThat(savedPet.getAddress().getNumber()).isNotNull();
        Assertions.assertThat(savedPet.getAddress()).isEqualTo(petToBeSaved.getAddress());
        Assertions.assertThat(savedPet.getAddress().getCity()).isEqualTo(petToBeSaved.getAddress().getCity());
        Assertions.assertThat(savedPet.getAddress().getStreet()).isEqualTo(petToBeSaved.getAddress().getStreet());
        Assertions.assertThat(savedPet.getAddress().getNumber()).isEqualTo(petToBeSaved.getAddress().getNumber());

        Assertions.assertThat(savedPet.getBreed()).isNotNull();
        Assertions.assertThat(savedPet.getBreed()).isEqualTo(petToBeSaved.getBreed());

        Assertions.assertThat(savedPet.getType()).isNotNull();
        Assertions.assertThat(savedPet.getType()).isEqualTo(petToBeSaved.getType());

        Assertions.assertThat(savedPet.getWeight()).isNotNull();
        Assertions.assertThat(savedPet.getWeight()).isBetween(new BigDecimal("0.5"), new BigDecimal(60));
        Assertions.assertThat(savedPet.getWeight()).isEqualTo(petToBeSaved.getWeight());

    }

    private Pet createPet() {
        Address address = Address.builder()
                .number("104")
                .street("Avenida da paz")
                .city("Belo Horizonte")
                .build();

        return Pet.builder()
                .name("Rex")
                .lastName("Silva")
                .sex(SexPet.MALE)
                .type(TypePet.DOG)
                .address(address)
                .age(new BigDecimal(9))
                .weight(new BigDecimal(9))
                .breed("Pitbull")
                .build();
    }

}