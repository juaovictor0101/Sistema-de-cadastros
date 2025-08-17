package br.com.registrationsystem.service;

import br.com.registrationsystem.exception.BadRequestException;
import br.com.registrationsystem.mapper.PetMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.repository.PetRepository;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;

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
        return petRepository.findById(id)
                .orElseThrow(()->new BadRequestException("Pet not found based on ID"));
    }

    public List<Pet> findPetByName (String name) {
        return petRepository.findPetByName(name);
    }

    public List<Pet> findAPetsByNameOrLastName(String name , String lastName) {
        return petRepository.findByNameOrLastName(name, lastName);
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

    public List<Pet> listAllPets(){
        return petRepository.findAll();
    }
}