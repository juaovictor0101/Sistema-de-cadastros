package src.main.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.java.entity.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetByName(String name);
}
