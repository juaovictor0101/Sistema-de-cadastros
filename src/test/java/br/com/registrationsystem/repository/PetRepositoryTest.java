package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.Address;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.entity.TypePet;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Pet repository")
@Log4j2
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Test
    @DisplayName("Save persists pet when successful")
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

    @Test
    @DisplayName("Updates pet when successful")
    void save_UpdatePet_WhenSuccessful() {

        Pet petToBeSaved = createPet();
        Pet savedPet = this.petRepository.save(petToBeSaved);
        savedPet.setName("Bolinha");
        Pet updatedPet = this.petRepository.save(savedPet);
        log.info(updatedPet.getName());

        Assertions.assertThat(updatedPet).isNotNull();
        Assertions.assertThat(updatedPet.getId()).isNotNull();
        Assertions.assertThat(updatedPet.getName()).isEqualTo(savedPet.getName());


    }

    @Test
    @DisplayName("Delete removes pet when successful")
    void delete_RemovesPet_WhenSuccessful() {

        Pet petToBeSaved = createPet();
        Pet savedPet = this.petRepository.save(petToBeSaved);

        this.petRepository.delete(savedPet);

        Optional<Pet> petOptional = this.petRepository.findById(savedPet.getId());

        Assertions.assertThat(petOptional).isEmpty();


    }

    @Test
    @DisplayName("Find By Name return list of pet when successful")
    void findByName_ReturnListOfPet_WhenSuccessful() {

        Pet petToBeSaved = createPet();
        Pet savedPet = this.petRepository.save(petToBeSaved);

        String name = savedPet.getName();

        Page<Pet> pets = this.petRepository.findPetByName(name, Pageable.ofSize(5));

        Assertions.assertThat(pets)
                .isNotEmpty()
                .contains(savedPet);
    }

    @Test
    @DisplayName("Find By Name return empty list of pet when no pets is found")
    void findByName_ReturnEmptyListOfPet_WhenNoPetIsNotFound() {
        Page<Pet> pets = this.petRepository.findPetByName("teste", Pageable.ofSize(5));
        Assertions.assertThat(pets).isEmpty();
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