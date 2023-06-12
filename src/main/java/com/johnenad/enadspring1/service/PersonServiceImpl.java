package com.johnenad.enadspring1.service;

import com.johnenad.enadspring1.dto.PersonDTO;
import com.johnenad.enadspring1.model.Person;
import com.johnenad.enadspring1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDTO> fetchAll() {
        List<Person> persons = personRepository.findAll();
        return PersonDTO.convert(persons);
    }

    @Override
    public List<PersonDTO> fetchByActive(boolean active) {
        List<Person> persons = personRepository.findByActive(active);
        return PersonDTO.convert(persons);
    }

    @Override
    public Optional<PersonDTO> getById(long id) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isPresent()) {
            return Optional.of(PersonDTO.convert(personOpt.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        Optional<Person> p = personRepository.findById(personDTO.getId());
        if (!p.isPresent()) {
            return null;
        }

        Person person = p.get();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setActive(personDTO.isActive());

        Person updatedPerson = personRepository.save(person);
        return PersonDTO.convert(updatedPerson);
    }

    @Override
    public PersonDTO add(String firstName, String lastName, boolean active) {
        Person person = personRepository.save(new Person(firstName, lastName, active));
        return PersonDTO.convert(person);
    }

    @Override
    public void delete(long id) {
        Optional<Person> todo = personRepository.findById(id);
        if (todo.isPresent()) {
            personRepository.delete(todo.get());
        }
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        Person person = new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getLastName());
        person.setActive(personDTO.isActive());

        Person savedPerson = personRepository.save(person);
        return PersonDTO.convert(savedPerson);
    }
}