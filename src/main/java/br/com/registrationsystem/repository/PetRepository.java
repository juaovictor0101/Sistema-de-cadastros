package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.math.BigDecimal;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {
    List<Pet> findPetByName(String name);

    List<Pet> findPetBySex(SexPet sex);

    List<Pet> findPetByAge(BigDecimal age);

    List<Pet> findPetByWeight(BigDecimal weight);

    List<Pet> findPetByBreed(String breed);

}
