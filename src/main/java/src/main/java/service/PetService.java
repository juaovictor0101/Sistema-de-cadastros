package src.main.java.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import src.main.java.entity.Pet;
import src.main.java.mapper.PetMapper;
import src.main.java.repository.PetRepository;
import src.main.java.requests.PetPostRequestBody;
import src.main.java.requests.PetPutRequestBody;

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

    public Pet findPetById(Long id) {
        return petRepository.findById(id).orElseThrow( ()-> new BadRequestException("Pet not found based on ID"));
    }

    public List<Pet> findPetByName (String name) {
        return petRepository.findPetByName(name);
    }

    public void replacePet(PetPutRequestBody petPutRequestBody) {
        Pet petSaved = findPetById(petPutRequestBody.getId());
        Pet pet = PetMapper.INSTANCE.toPet(petPutRequestBody);
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
