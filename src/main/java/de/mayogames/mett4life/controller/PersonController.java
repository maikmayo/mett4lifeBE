package de.mayogames.mett4life.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.mayogames.mett4life.dto.PersonDto;
import de.mayogames.mett4life.entity.Person;
import de.mayogames.mett4life.exception.PersonNotFoundException;
import de.mayogames.mett4life.repository.PersonRepository;

@RestController
public class PersonController implements ControllerDefinition<Person, PersonDto> {

	@Autowired
	private PersonRepository personRepo;

	@Override
	@GetMapping("/person/{personID}")
	public PersonDto getSingleEntryWithID(@PathVariable int personID) {
		return convertEntityToDto(
				personRepo.findById(personID).orElseThrow(() -> new PersonNotFoundException(personID)));
	}

	@Override
	@GetMapping("/person")
	public List<PersonDto> getAll() {
		List<PersonDto> dtos = new ArrayList<PersonDto>();
		for (Person entity : personRepo.findAll()) {
			dtos.add(convertEntityToDto(entity));
		}
		return dtos;
	}

	@Override
	@PostMapping("/person")
	public PersonDto add(@RequestBody Person newPerson) {
		return convertEntityToDto(personRepo.save(newPerson));
	}

	@Override
	@PutMapping("/person/{personID}")
	public PersonDto update(@RequestBody Person updatedPerson, @PathVariable int personID) {
		return convertEntityToDto(personRepo.findById(personID).map(person -> {
			person.setKuerzel(updatedPerson.getKuerzel());
			person.setPc(updatedPerson.getPc());
			person.setPw_hash(updatedPerson.getPw_hash());
			person.setRegistration(updatedPerson.getRegistration());
			return personRepo.save(person);
		}).orElseGet(() -> {
			updatedPerson.setId(personID);
			return personRepo.save(updatedPerson);
		}));
	}

	@Override
	@DeleteMapping("/person/{id}")
	public void delete(@PathVariable int personID) {
		personRepo.deleteById(personID);
	}

	/*
	 * DTO Conversion
	 */
	@Override
	public PersonDto convertEntityToDto(Person entity) {
		PersonDto dto = new PersonDto(entity.getId(), entity.getKuerzel(), entity.getPw_hash(), entity.getPc(),
				entity.getRegistration());
		return dto;
	}

	@Override
	public Person convertDtoToEntity(PersonDto dto) {
		Person person = new Person(0, null, null, null, null, null);

		return person;
	}
}
