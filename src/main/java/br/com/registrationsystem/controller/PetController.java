package br.com.registrationsystem.controller;

import br.com.registrationsystem.entity.Address;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;
import br.com.registrationsystem.service.PetService;
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

    @GetMapping (path = "/all")
    public ResponseEntity<List<Pet>> listAll() {
        return new ResponseEntity<>(petService.listAllPets(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pet> findById (@PathVariable Long id) {
        return new ResponseEntity<>(petService.findPetById(id), HttpStatus.OK);
    }

    @GetMapping (path = "/findByName")
    public ResponseEntity<List<Pet>> findByFirstName (@RequestParam String name) {
        return new ResponseEntity<>(petService.findPetByName(name), HttpStatus.OK);
    }
    @GetMapping(path="/findByNameORLastName")
    public ResponseEntity<List<Pet>> findByNameOrLastName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<>(petService.findAPetsByNameOrLastName(name, lastName), HttpStatus.OK);
    }

    @GetMapping(path = "/findBySex/{sex}")
    public ResponseEntity <List <Pet>> findPetBySex(@PathVariable ("sex") SexPet sexPet){
        return new ResponseEntity<>(petService.findPetBySex(sexPet), HttpStatus.OK);
    }

    @GetMapping(path = "/findByAge/{age}")
    public ResponseEntity<List<Pet>> findPetByAge(@PathVariable ("age") BigDecimal age){
        return new ResponseEntity<>(petService.findPetByAge(age),HttpStatus.OK);
    }

    @GetMapping(path = "/findByWeight/{weight}")
    public ResponseEntity <List <Pet>> findPetByWeight(@PathVariable ("weight") BigDecimal weight){
        return new ResponseEntity<>(petService.findPetByWeight(weight),HttpStatus.OK);
    }

    @GetMapping(path = "/findByBreed/{breed}")
    public ResponseEntity<List<Pet>> findPetByBreed(@PathVariable ("breed") String breed){
        return new ResponseEntity <>(petService.findPetByBreed(breed),HttpStatus.OK);
    }

    @GetMapping(path = "/findByAddress")
    public ResponseEntity<List<Pet>> findPetByAddress(
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String number) {
        return new ResponseEntity<>(petService.findPetByAddress(street, city, number), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> save(@RequestBody @Valid PetPostRequestBody petPostRequestBody) {
        return new ResponseEntity<>(petService.savePet(petPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/delete/{id}")
    public ResponseEntity<Pet> delete (@PathVariable Long id) {
        petService.deletePetById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Pet> update (@RequestBody @Valid PetPutRequestBody petPutRequestBody) {
        petService.replacePet(petPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}