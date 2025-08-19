package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.Address;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.entity.TypePet;
import org.springframework.data.jpa.repository.JpaRepository;
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

    List<Pet> findPetByWeight(BigDecimal weight);

    List<Pet> findPetByBreed(String breed);

    List<Pet> findPetByNameOrLastNameOrSexOrTypeOrAddressOrAgeOrBreedOrWeight
            (String name, String lastName, SexPet sex, TypePet type, Address address, BigDecimal age, String breed, BigDecimal weight);

    @Query("SELECT p FROM Pet p WHERE " +
            "(:street IS NULL OR :street = '' OR LOWER(p.address.street) LIKE LOWER(CONCAT('%', :street, '%'))) AND " +
            "(:city IS NULL OR :city = '' OR LOWER(p.address.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
            "(:number IS NULL OR :number = '' OR p.address.number LIKE %:number%)")
    List<Pet> findPetByAddressFields(@Param("street") String street,
                                     @Param("city") String city,
                                     @Param("number") String number);
}
