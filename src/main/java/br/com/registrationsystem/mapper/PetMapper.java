package br.com.registrationsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class PetMapper {
    public static final PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    public abstract Pet toPet (PetPostRequestBody petPostRequestBody);

    public abstract Pet toPet(PetPutRequestBody petPutRequestBody);
}
