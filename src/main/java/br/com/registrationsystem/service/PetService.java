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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;
    private final PetRepository petRepository;
    private final String NAME_REGEX = "^[a-zA-Z\\s]+$";
    BigDecimal maxWeight = new BigDecimal("60.0");
    BigDecimal minWeight = new BigDecimal("0.5");
    BigDecimal maxAge = new BigDecimal("20.0");

    @Transactional
    public Pet savePet(PetPostRequestBody petPostRequestBody) {

        if (petPostRequestBody.getName() != null) {
            if (petPostRequestBody.getName().trim().isEmpty()) {
                throw new BadRequestException("Pet name cannot be empty");
            }
        }

        if (petPostRequestBody.getName() != null) {
            if (!petPostRequestBody.getName().matches(NAME_REGEX)) {
                throw new BadRequestException("Pet name must be alphabetic");
            }
        }
        if (petPostRequestBody.getLastName() != null) {
            if (!petPostRequestBody.getLastName().matches(NAME_REGEX)) {
                throw new BadRequestException("Pet last name must be alphabetic");
            }
        }
        if (petPostRequestBody.getWeight().compareTo(maxWeight) > 0) {
            throw new BadRequestException("Pet weight must be less than or equal 60");
        }
        if (petPostRequestBody.getWeight().compareTo(minWeight) < 0) {
            throw new BadRequestException("Pet weight must be greater than or equal 0.5");
        }
        if (petPostRequestBody.getAge() != null) {
            if (petPostRequestBody.getAge().compareTo(maxAge) > 0) {
                throw new BadRequestException("Pet age must be less than or equal 20.0");
            }
        }

        if (petPostRequestBody.getBreed() != null) {
            if (petPostRequestBody.getBreed().matches(NAME_REGEX) || petPostRequestBody.getBreed().trim().isEmpty()) {
                throw new BadRequestException("Pet breed must be alphabetic");
            }
        }
        Pet pet = petMapper.toPet(petPostRequestBody);
        return petRepository.save(pet);
    }

    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new BadRequestException("Pet not found based on ID"));
    }

    public Page<Pet> findPetByName(String name, Pageable pageable) {
        return petRepository.findPetByName(name, pageable);
    }

    public Page<Pet> findAPetsByNameOrLastName(String name, String lastName, Pageable pageable) {
        return petRepository.findAll(Specification.allOf(PetSpecifications.nomeContem(name)).and(PetSpecifications.sobrenomeContem(lastName)), pageable);
    }

    public Page<Pet> findPetBySex(SexPet sexPet, Pageable pageable) {
        return petRepository.findPetBySex(sexPet, pageable);
    }

    public Page<Pet> findPetByAge(BigDecimal age, Pageable pageable) {
        return petRepository.findPetByAge(age, pageable);
    }

    public Page<Pet> findPetByWeight(BigDecimal weight, Pageable pageable) {
        return petRepository.findPetByWeight(weight, pageable);
    }

    public Page<Pet> findPetByBreed(String breed, Pageable pageable) {
        return petRepository.findPetByBreed(breed, pageable);
    }

    public Page<Pet> findPetByAddress(String street, String city, String number, Pageable pageable) {
        return petRepository.findAll(Specification.allOf(PetSpecifications.ruaContem(street))
                .and(PetSpecifications.cidadeIgual(city)
                        .and(PetSpecifications.numeroIgual(number))), pageable);

    }

    public Page<Pet> findByAnyAttribute
            (String name, String lastName, String sex, String type, String street, String city, String number,
             BigDecimal age, String breed, BigDecimal weight, Pageable pageable) {

        return petRepository.findAll(Specification.allOf(PetSpecifications.nomeContem(name))
                .and(PetSpecifications.sobrenomeContem(lastName))
                .and(PetSpecifications.sexoIgual(sex))
                .and(PetSpecifications.tipoIgual(type))
                .and(PetSpecifications.ruaContem(street))
                .and(PetSpecifications.cidadeIgual(city))
                .and(PetSpecifications.numeroIgual(number))
                .and(PetSpecifications.idadeIgual(age))
                .and(PetSpecifications.racaContem(breed))
                .and(PetSpecifications.pesoIgual(weight)),pageable);
    }


    public void replacePet(PetPutRequestBody petPutRequestBody) {
        Pet petSaved = findPetById(petPutRequestBody.getId());
        Pet pet = petMapper.toPet(petPutRequestBody);
        pet.setId(petSaved.getId());


        if (petPutRequestBody.getName() != null) {
            if (petPutRequestBody.getName().trim().isEmpty()) {
                throw new BadRequestException("Pet name cannot be empty");
            }
        }
        if (petPutRequestBody.getName() != null) {
            if (!petPutRequestBody.getName().matches(NAME_REGEX)) {
                throw new BadRequestException("Pet name must be alphabetic");
            }
        }
        if (petPutRequestBody.getLastName() != null) {
            if (!petPutRequestBody.getLastName().matches(NAME_REGEX)) {
                throw new BadRequestException("Pet last name must be alphabetic");
            }
        }
        if (petPutRequestBody.getWeight().compareTo(maxWeight) > 0) {
            throw new BadRequestException("Pet weight must be less than or equal 60");
        }
        if (petPutRequestBody.getWeight().compareTo(minWeight) < 0) {
            throw new BadRequestException("Pet weight must be greater than or equal 0.5");
        }
        if (petPutRequestBody.getAge() != null) {
            if (petPutRequestBody.getAge().compareTo(maxAge) > 0) {
                throw new BadRequestException("Pet age must be less than or equal 20.0");
            }
        }

        petRepository.save(pet);
    }

    public void deletePetById(Long id) {
        petRepository.delete(findPetById(id));
    }

    public Page<Pet> listAllPets(Pageable pageable) {
        return petRepository.findAll(pageable);
    }
}