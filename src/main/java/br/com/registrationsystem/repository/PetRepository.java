package br.com.registrationsystem.repository;

import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
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



    @Query("SELECT p FROM Pet p WHERE " +
            "(:name IS NULL OR :name = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:lastName IS NULL OR :lastName = '' OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:sex IS NULL OR :sex = '' OR UPPER(p.sex) = UPPER(:sex)) AND " +
            "(:type IS NULL OR :type = '' OR UPPER(p.type) = UPPER(:type)) AND " +
            "(:street IS NULL OR :street = '' OR LOWER(p.address.street) LIKE LOWER(CONCAT('%', :street, '%'))) AND " +
            "(:city IS NULL OR :city = '' OR LOWER(p.address.city) = LOWER(:city)) AND " +
            "(:number IS NULL OR :number = '' OR p.address.number = :number) AND " +
            "(:age IS NULL OR p.age = :age) AND " +
            "(:breed IS NULL OR :breed = '' OR LOWER(p.breed) LIKE LOWER(CONCAT('%', :breed, '%'))) AND " +
            "(:weight IS NULL OR p.weight = :weight)")
    List<Pet> findByAnyAttribute(@Param("name") String name,
                                 @Param("lastName") String lastName,
                                 @Param("sex") String sex,
                                 @Param("type") String type,
                                 @Param("street") String street,
                                 @Param("city") String city,
                                 @Param("number") String number,
                                 @Param("age") BigDecimal age,
                                 @Param("breed") String breed,
                                 @Param("weight") BigDecimal weight);
    //name, lastName, sex, type, street, city, number, age, breed, weight


    @Query("SELECT p FROM Pet p WHERE " +
            "(:street IS NULL OR :street = '' OR LOWER(p.address.street) LIKE LOWER(CONCAT('%', :street, '%'))) AND " +
            "(:city IS NULL OR :city = '' OR LOWER(p.address.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
            "(:number IS NULL OR :number = '' OR p.address.number LIKE %:number%)")
    List<Pet> findPetByAddressFields(@Param("street") String street,
                                     @Param("city") String city,
                                     @Param("number") String number);
}
