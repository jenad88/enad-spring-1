package com.johnenad.enadspring1.controller.v1;

import com.johnenad.enadspring1.dto.PersonDTO;
import com.johnenad.enadspring1.dto.PersonResponse;
import com.johnenad.enadspring1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.johnenad.enadspring1.util.DemoUtil.PATH_V1;

@RestController
@RequestMapping(PATH_V1 + "/persons")
public class PersonController implements PersonApi {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public ResponseEntity<PersonResponse> getAll() {
        try {

            List<PersonDTO> persons = new ArrayList<>(personService.fetchAll());

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(PersonResponse.builder().data(persons).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PersonResponse> getPersonById(long id) {
        Optional<PersonDTO> personOpt = personService.getById(id);

        return personOpt.map(personDTO -> new ResponseEntity<>(PersonResponse.builder().data(personDTO).build(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<PersonResponse> createPerson(PersonDTO personDTO) {
        try {
            personDTO.setActive(true);
            PersonDTO newPerson = personService.save(personDTO);
            return new ResponseEntity<>(PersonResponse.builder().data(newPerson).build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PersonResponse> updatePerson(long id, PersonDTO personDTO) {
        personDTO.setId(id);
        PersonDTO updatedPersonDTO = personService.update(personDTO);
        if (updatedPersonDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PersonResponse personResponse = PersonResponse.builder().data(updatedPersonDTO).build();

        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deletePerson(long id) {
        try {
            personService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<PersonResponse> findByActive() {
        try {
            List<PersonDTO> persons = personService.fetchByActive(true);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(PersonResponse.builder().data(persons).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}