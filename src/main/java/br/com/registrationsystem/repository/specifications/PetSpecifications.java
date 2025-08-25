package br.com.registrationsystem.repository.specifications;

import br.com.registrationsystem.entity.Pet;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PetSpecifications {

    public static Specification<Pet> nomeContem(String name) {
        return (root, query, cb) ->
                (name == null || name.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Pet> sobrenomeContem(String lastName) {
        return (root, query, cb) ->
                (lastName == null || lastName.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<Pet> sexoIgual(String sex) {
        return (root, query, cb) ->
                (sex == null || sex.isEmpty())
                        ? null
                        : cb.equal(cb.upper(root.get("sex")), sex.toUpperCase());
    }

    public static Specification<Pet> tipoIgual(String type) {
        return (root, query, cb) ->
                (type == null || type.isEmpty())
                        ? null
                        : cb.equal(cb.upper(root.get("type")), type.toUpperCase());
    }

    public static Specification<Pet> ruaContem(String street) {
        return (root, query, cb) ->
                (street == null || street.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("address").get("street")), "%" + street.toLowerCase() + "%");
    }

    public static Specification<Pet> cidadeIgual(String city) {
        return (root, query, cb) ->
                (city == null || city.isEmpty())
                        ? null
                        : cb.equal(cb.lower(root.get("address").get("city")), city.toLowerCase());
    }

    public static Specification<Pet> numeroIgual(String number) {
        return (root, query, cb) ->
                (number == null || number.isEmpty())
                        ? null
                        : cb.equal(root.get("address").get("number"), number);
    }

    public static Specification<Pet> idadeIgual(BigDecimal age) {
        return (root, query, cb) ->
                (age == null)
                        ? null
                        : cb.equal(root.get("age"), age);
    }

    public static Specification<Pet> racaContem(String breed) {
        return (root, query, cb) ->
                (breed == null || breed.isEmpty())
                        ? null
                        : cb.like(cb.lower(root.get("breed")), "%" + breed.toLowerCase() + "%");
    }

    public static Specification<Pet> pesoIgual(BigDecimal weight) {
        return (root, query, cb) ->
                (weight == null)
                        ? null
                        : cb.equal(root.get("weight"), weight);
    }
}
