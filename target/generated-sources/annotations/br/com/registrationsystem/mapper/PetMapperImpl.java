package br.com.registrationsystem.mapper;

import br.com.registrationsystem.entity.Address;
import br.com.registrationsystem.entity.Pet;
import br.com.registrationsystem.requests.AddressDto;
import br.com.registrationsystem.requests.PetPostRequestBody;
import br.com.registrationsystem.requests.PetPutRequestBody;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-15T13:23:28-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (Microsoft)"
)
@Component
public class PetMapperImpl implements PetMapper {

    @Override
    public Pet toPet(PetPostRequestBody petPostRequestBody) {
        if ( petPostRequestBody == null ) {
            return null;
        }

        Pet.PetBuilder pet = Pet.builder();

        pet.name( petPostRequestBody.getName() );
        pet.lastName( petPostRequestBody.getLastName() );
        pet.sex( toSexPet( petPostRequestBody.getSex() ) );
        pet.type( toTypePet( petPostRequestBody.getType() ) );
        pet.address( toAddress( petPostRequestBody.getAddress() ) );
        pet.age( petPostRequestBody.getAge() );
        pet.weight( petPostRequestBody.getWeight() );
        pet.breed( petPostRequestBody.getBreed() );

        return pet.build();
    }

    @Override
    public Pet toPet(PetPutRequestBody petPutRequestBody) {
        if ( petPutRequestBody == null ) {
            return null;
        }

        Pet.PetBuilder pet = Pet.builder();

        pet.name( petPutRequestBody.getName() );
        pet.lastName( petPutRequestBody.getLastName() );
        pet.sex( toSexPet( petPutRequestBody.getSex() ) );
        pet.type( toTypePet( petPutRequestBody.getType() ) );
        pet.address( toAddress( petPutRequestBody.getAddress() ) );
        pet.age( petPutRequestBody.getAge() );
        pet.weight( petPutRequestBody.getWeight() );
        pet.breed( petPutRequestBody.getBreed() );
        pet.id( petPutRequestBody.getId() );

        return pet.build();
    }

    @Override
    public Address toAddress(AddressDto addressDto) {
        if ( addressDto == null ) {
            return null;
        }

        Address address = new Address();

        address.setNumber( addressDto.getNumber() );
        address.setStreet( addressDto.getStreet() );
        address.setCity( addressDto.getCity() );

        return address;
    }
}
