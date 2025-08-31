package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;

public interface PetRepository extends JpaRepository<Pet, Long>, JpaSpecificationExecutor<Pet> {
    Page<Pet> findPetByName(String name, Pageable pageable);

    Page<Pet> findPetBySex(SexPet sex, Pageable pageable);

    Page<Pet> findPetByAge(BigDecimal age, Pageable pageable);

    Page<Pet> findPetByWeight(BigDecimal weight, Pageable pageable);

    Page<Pet> findPetByBreed(String breed, Pageable pageable);

}
