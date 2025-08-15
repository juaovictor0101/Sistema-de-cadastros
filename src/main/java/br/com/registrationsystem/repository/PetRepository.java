package br.com.registrationsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.registrationsystem.entity.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetByName(String name);
    List<Pet> findByNameOrLastName(String name, String lastName);
}
