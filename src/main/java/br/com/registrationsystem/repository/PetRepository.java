package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.SexPet;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.registrationsystem.entity.Pet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetByName(String name);

    @Query(value = "SELECT * FROM pets WHERE name = :name OR last_name = :lastName", nativeQuery = true)
    List<Pet> findByNameOrLastName(@Param("name") String name, @Param("lastName") String lastName);

    List<Pet> findPetBySex(SexPet sex);
    List<Pet> findPetByAge(BigDecimal age);
}
