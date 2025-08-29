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
        final String NAME_REGEX = "^[a-zA-Z\\s]+$";
        BigDecimal maxWeight = new BigDecimal("60.0");
        BigDecimal minWeight = new BigDecimal("0.5");
        BigDecimal maxAge = new BigDecimal("20.0");


        if(petPostRequestBody.getName() == null || petPostRequestBody.getName().trim().isEmpty()){
            throw new BadRequestException("Pet name cannot be empty");
        }
        if(!petPostRequestBody.getName().matches(NAME_REGEX)){
            throw new BadRequestException("Pet name must be alphabetic");
        }
        if(petPostRequestBody.getLastName() == null || petPostRequestBody.getLastName().trim().isEmpty()){
            throw new BadRequestException("Pet name cannot be empty");
        }
        if(!petPostRequestBody.getLastName().matches(NAME_REGEX)){
            throw new BadRequestException("Pet last name must be alphabetic");
        }
        if(petPostRequestBody.getWeight().compareTo(maxWeight)>0){
            throw new BadRequestException("Pet weight must be less than or equal 60");
        }
        if(petPostRequestBody.getWeight().compareTo(minWeight)<0){
            throw new BadRequestException("Pet weight must be greater than or equal 0.5");
        }
        if(petPostRequestBody.getAge().compareTo(maxAge)>0){
            throw  new BadRequestException("Pet age must be less than or equal 20.0");
        }
        if(petPostRequestBody.getBreed().matches(NAME_REGEX)){
            throw new BadRequestException("Pet breed must be alphabetic");
        }
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