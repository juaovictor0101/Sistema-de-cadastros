package br.com.registrationsystem.mapper;

import br.com.registrationsystem.entity.Address;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.entity.SexPet;
import br.com.registrationsystem.entity.TypePet;
import br.com.registrationsystem.requests.AddressRequestBody;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "id", ignore = true)
    Pet toPet(PetPostRequestBody petPostRequestBody);

    Pet toPet(PetPutRequestBody petPutRequestBody);

    public abstract Address toAddress(AddressRequestBody adressRequestBody);

    default SexPet toSexPet(String sex) {
        if (sex == null || sex.trim().isEmpty()) {
            return null;
        }
        try {
            return SexPet.valueOf(sex.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor inválido para o campo 'sex': " + sex);
        }
    }

    default TypePet toTypePet(String type) {
        if (type == null || type.trim().isEmpty()) {
            return null;
        }
        try {
            return TypePet.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor inválido para o campo 'type': " + type);
        }
    }
}