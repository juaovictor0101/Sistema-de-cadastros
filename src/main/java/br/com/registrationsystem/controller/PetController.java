package br.com.registrationsystem.controller;

import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;
import br.com.registrationsystem.requests.PetSearchRequestBody;
import br.com.registrationsystem.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("pets")
public class PetController {
    private final PetService petService;


    @GetMapping(path = "/users/")
    @Operation(summary = "List all animes paginated", description = "The default size is 5, use the parameter size to change th default value")
    public ResponseEntity<List<Pet>> listAll() {
        return new ResponseEntity<>(petService.listAllPets(), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByID/{id}")
    @Operation(summary = "Returns a pet based on a given ID number")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        return new ResponseEntity<>(petService.findPetById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByFirstName/")
    @Operation(summary = "Returns a pet based on a given first name")
    public ResponseEntity<List<Pet>> findByFirstName(@RequestParam String name) {
        return new ResponseEntity<>(petService.findPetByName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/findByFirstNameORLastName/")
    @Operation(summary = "Returns a pet based on a first or second name provided",
            description = "If no search parameters are provided, the method will return all users.")
    public ResponseEntity<List<Pet>> findByNameOrLastName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<>(petService.findAPetsByNameOrLastName(name, lastName), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersBySex/{sex}")
    @Operation(summary = "Returns a list of all pets based on gender.", description = "Gender can only be male or female")
    public ResponseEntity<List<Pet>> findPetBySex(@PathVariable("sex") SexPet sexPet) {
        return new ResponseEntity<>(petService.findPetBySex(sexPet), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByAge/{age}")
    @Operation(summary = "Returns a list of all pets based on age")
    public ResponseEntity<List<Pet>> findPetByAge(@PathVariable("age") BigDecimal age) {
        return new ResponseEntity<>(petService.findPetByAge(age), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByWeight/{weight}")
    @Operation(summary = "Returns a list of all pets based on weight")
    public ResponseEntity<List<Pet>> findPetByWeight(@PathVariable("weight") BigDecimal weight) {
        return new ResponseEntity<>(petService.findPetByWeight(weight), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByBreed/{breed}")
    @Operation(summary = "Returns a list of all pets based on breed")
    public ResponseEntity<List<Pet>> findPetByBreed(@PathVariable("breed") String breed) {
        return new ResponseEntity<>(petService.findPetByBreed(breed), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByAddress/")
    @Operation(summary = "Returns a list of pets based on the address.",
            description = "The search can be for a complete address (NUMBER, STREET AND CITY) or just one or the other, e.g. NUMBER AND STREET, STREET AND CITY.")
    public ResponseEntity<List<Pet>> findPetByAddress(
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String number) {
        return new ResponseEntity<>(petService.findPetByAddress(street, city, number), HttpStatus.OK);
    }

    @GetMapping(path = "/findUsersByAnyAttributes/")
    @Operation(summary = "Search pets based on any attribute",
            description = "You can search by any one or more of these parameters: first name, last name, gender, type, street, city, house number, age, race, and weight.")
    public ResponseEntity<List<Pet>> searchPets(PetSearchRequestBody petSearch) {
        List<Pet> pets = petService.findByAnyAttribute(petSearch.getName(), petSearch.getLastName(), petSearch.getSex(),
                petSearch.getType(), petSearch.getStreet(), petSearch.getCity(), petSearch.getNumber(), petSearch.getAge(),
                petSearch.getBreed(), petSearch.getWeight());
        return ResponseEntity.ok(pets);
    }

    @PostMapping(path = "/users/")
    @Operation(summary = "Save a pet in the database",
            description = "Name, surname, breed, age and house number may be left blank and will be saved in the database as NOT SPECIFIED if not provided.")
    public ResponseEntity<Pet> save(@RequestBody @Valid PetPostRequestBody petPostRequestBody) {
        return new ResponseEntity<>(petService.savePet(petPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/users/{id}")
    @Operation(summary = "Deletes a pet from the database based on a provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "When the anime does not exist in the database")}
    )
    public ResponseEntity<Pet> delete(@PathVariable Long id) {
        petService.deletePetById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/replace")
    @Operation(summary = "Updates all data for a pet saved in the database, except for the ID.")
    public ResponseEntity<Pet> update(@RequestBody @Valid PetPutRequestBody petPutRequestBody) {
        petService.replacePet(petPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}