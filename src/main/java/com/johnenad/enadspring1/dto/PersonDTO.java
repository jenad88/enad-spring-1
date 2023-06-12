package com.johnenad.enadspring1.dto;

import com.johnenad.enadspring1.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PersonDTO {
    private long id;
    private String firstName;
    private String lastName;
    private boolean active;

    public static PersonDTO convert(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setActive(person.isActive());
        return personDTO;
    }

    public static List<PersonDTO> convert(List<Person> persons) {
        return persons.stream().map(PersonDTO::convert).collect(Collectors.toList());
    }
}
