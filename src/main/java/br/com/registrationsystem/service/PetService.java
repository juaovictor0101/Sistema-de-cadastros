package br.com.registrationsystem.service;

import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.exception.BadRequestException;
import br.com.registrationsystem.mapper.PetMapper;
import br.com.registrationsystem.repository.PetRepository;
import br.com.registrationsystem.repository.specifications.PetSpecifications;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;
    private final PetRepository petRepository;

    @Transactional
    public Pet savePet(PetPostRequestBody petPostRequestBody) {
        Pet pet = petMapper.toPet(petPostRequestBody);
        return petRepository.save(pet);
    }

    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new BadRequestException("Pet not found based on ID"));
    }

    public List<Pet> findPetByName(String name) {
        return petRepository.findPetByName(name);
    }

    public List<Pet> findAPetsByNameOrLastName(String name, String lastName) {
        return petRepository.findAll(Specification.allOf(PetSpecifications.nomeContem(name)).and(PetSpecifications.sobrenomeContem(lastName)));
    }

    public List<Pet> findPetBySex(SexPet sexPet) {
        return petRepository.findPetBySex(sexPet);
    }

    public List<Pet> findPetByAge(BigDecimal age) {
        return petRepository.findPetByAge(age);
    }

    public List<Pet> findPetByWeight(BigDecimal weight) {
        return petRepository.findPetByWeight(weight);
    }

    public List<Pet> findPetByBreed(String breed) {
        return petRepository.findPetByBreed(breed);
    }

    public List<Pet> findPetByAddress(String street, String city, String number) {
        return petRepository.findAll(Specification.allOf(PetSpecifications.ruaContem(street))
                .and(PetSpecifications.cidadeIgual(city)
                .and(PetSpecifications.numeroIgual(number))));

    }

    public List<Pet> findByAnyAttribute
            (String name, String lastName, String sex, String type, String street, String city, String number,
             BigDecimal age, String breed, BigDecimal weight) {

        return petRepository.findAll(Specification.allOf(PetSpecifications.nomeContem(name))
                .and(PetSpecifications.sobrenomeContem(lastName))
                .and(PetSpecifications.sexoIgual(sex))
                .and(PetSpecifications.tipoIgual(type))
                .and(PetSpecifications.ruaContem(street))
                .and(PetSpecifications.cidadeIgual(city))
                .and(PetSpecifications.numeroIgual(number))
                .and(PetSpecifications.idadeIgual(age))
                .and(PetSpecifications.racaContem(breed))
                .and(PetSpecifications.pesoIgual(weight)));
    }




    public void replacePet(PetPutRequestBody petPutRequestBody) {
        Pet petSaved = findPetById(petPutRequestBody.getId());
        Pet pet = petMapper.toPet(petPutRequestBody);
        pet.setId(petSaved.getId());
        petRepository.save(pet);
    }

    public void deletePetById(Long id) {
        petRepository.delete(findPetById(id));
    }

    public List<Pet> listAllPets() {
        return petRepository.findAll();
    }
}