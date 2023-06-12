package com.johnenad.enadspring1.service;

import com.johnenad.enadspring1.dto.PersonDTO;
import com.johnenad.enadspring1.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<PersonDTO> fetchAll();

    Optional<PersonDTO> getById(long id);

    PersonDTO update(PersonDTO person);

    PersonDTO add(String firstName, String lastName, boolean active);

    void delete(long id);

    PersonDTO save(PersonDTO person);

    List<PersonDTO> fetchByActive(boolean active);
}
