package br.com.registrationsystem.controller;

import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;
import br.com.registrationsystem.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("pets")
public class PetController {
    private final PetService petService;

    @GetMapping (path = "/all")
    public ResponseEntity<List<Pet>> listAll() {
        return new ResponseEntity<List<Pet>>(petService.listAllPets(), HttpStatus.OK);
    }

    //to do: tratar execção do service quanto a busca por ID
    @GetMapping(path = "/{id}")
    public ResponseEntity<Pet> findById (@PathVariable long id) {
        try {
            return new ResponseEntity<>(petService.findPetById(id), HttpStatus.OK);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping (path = "/findByName")
    public ResponseEntity<List<Pet>> findByFirstName (@RequestParam String name) {
        return new ResponseEntity<>(petService.findPetByName(name), HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Pet>> searchByNameOrLastName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName) {
        return new ResponseEntity<>(petService.findAPetsByNameOrLastName(name, lastName), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> save(@RequestBody @Valid PetPostRequestBody petPostRequestBody) {
        return new ResponseEntity<>(petService.savePet(petPostRequestBody), HttpStatus.CREATED);
    }


    //to do: Tratar execeção do service quanto ao deleteByID
    @DeleteMapping (path = "{id}")
    public ResponseEntity<Pet> delete (@PathVariable long id) {
        try {
            petService.deletePetById(id);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //to do: Tratar execeção do service quanto ao update
    @PutMapping
    public ResponseEntity<Pet> update (@RequestBody @Valid PetPutRequestBody petPutRequestBody) {
        try {
            petService.replacePet(petPutRequestBody);
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}