package br.com.registrationsystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.mapper.PetMapper;
import br.com.registrationsystem.repository.PetRepository;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    @Transactional
    public Pet savePet(PetPostRequestBody petPostRequestBody) {
        Pet pet = PetMapper.INSTANCE.toPet(petPostRequestBody);
        return petRepository.save(pet);
    }

    public Pet findPetById(Long id) throws BadRequestException {
        return petRepository.findById(id).orElseThrow( ()-> new BadRequestException("Pet not found based on ID"));
    }

    public List<Pet> findPetByName (String name) {
        return petRepository.findPetByName(name);
    }

    public void replacePet(PetPutRequestBody petPutRequestBody) throws BadRequestException {
        Pet petSaved = findPetById(petPutRequestBody.getId());
        Pet pet = PetMapper.INSTANCE.toPet(petPutRequestBody);
        pet.setId(petSaved.getId());
        petRepository.save(pet);
    }

    public void deletePetById(Long id) throws BadRequestException {
        petRepository.delete(findPetById(id));
    }

    public List<Pet> listAllPets(){
        return petRepository.findAll();
    }




}
