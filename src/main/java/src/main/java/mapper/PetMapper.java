package src.main.java.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import src.main.java.entity.Pet;
import src.main.java.requests.PetPostRequestBody;
import src.main.java.requests.PetPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class PetMapper {
    public static final PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    public abstract Pet toPet (PetPostRequestBody petPostRequestBody);

    public abstract Pet toPet(PetPutRequestBody petPutRequestBody);
}
