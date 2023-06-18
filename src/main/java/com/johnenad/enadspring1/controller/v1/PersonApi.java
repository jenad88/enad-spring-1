package com.johnenad.enadspring1.controller.v1;

import com.johnenad.enadspring1.dto.PersonDTO;
import com.johnenad.enadspring1.dto.PersonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PersonApi {

    @GetMapping("")
    ResponseEntity<PersonResponse> getAll();

    @GetMapping("/{id}")
    ResponseEntity<PersonResponse> getPersonById(@PathVariable("id") long id);

    @PostMapping("")
    ResponseEntity<PersonResponse> createPerson(@RequestBody PersonDTO personDTO);

    @PutMapping("/{id}")
    ResponseEntity<PersonResponse> updatePerson(@PathVariable("id") long id, @RequestBody PersonDTO personDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") long id);

    @GetMapping("/active")
    ResponseEntity<PersonResponse> findByActive();
}
