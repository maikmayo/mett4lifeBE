package de.mayogames.mett4life.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.mayogames.mett4life.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
