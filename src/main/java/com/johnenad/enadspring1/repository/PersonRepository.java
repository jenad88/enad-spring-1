package com.johnenad.enadspring1.repository;

import com.johnenad.enadspring1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByActive(boolean active);
    List<Person> findByLastNameContaining(String lastName);
}