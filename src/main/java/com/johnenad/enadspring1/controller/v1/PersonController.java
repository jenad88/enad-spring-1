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

    @GetMapping("")
    public ResponseEntity<PersonResponse> getAll() {
        try {
            List<PersonDTO> persons = new ArrayList<>();

            personService.fetchAll().forEach(persons::add);

            if (persons.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(PersonResponse.builder().data(persons).build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable("id") long id) {
        Optional<PersonDTO> personOpt = personService.getById(id);

        if (personOpt.isPresent()) {
            return new ResponseEntity<>(PersonResponse.builder().data(personOpt.get()).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonDTO personDTO) {
        try {
            personDTO.setActive(true);
            PersonDTO newPerson = personService.save(personDTO);
            return new ResponseEntity<>(PersonResponse.builder().data(newPerson).build(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO) {
        personDTO.setId(id);
        PersonDTO updatedPersonDTO = personService.update(personDTO);
        if (updatedPersonDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PersonResponse personResponse = PersonResponse.builder().data(updatedPersonDTO).build();

        return new ResponseEntity<>(personResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id) {
        try {
            personService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/active")
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