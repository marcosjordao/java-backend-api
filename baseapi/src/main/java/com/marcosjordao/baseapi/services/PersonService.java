package com.marcosjordao.baseapi.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcosjordao.baseapi.domain.entities.Person;
import com.marcosjordao.baseapi.domain.repositories.IPersonRepository;
import com.marcosjordao.baseapi.domain.services.IPersonService;

@Service
public class PersonService implements IPersonService {

	private IPersonRepository personRepository;
	
	@Autowired
	public PersonService(IPersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Optional<Person> getPersonById(String id) {
		return this.personRepository.findById(id);
	}

	public boolean existsById(String id) {
		return this.personRepository.existsById(id);
	}

	public List<Person> getAllPersons() {
		return this.personRepository.findAll();
	}

	public Person addPerson(Person person) {
		// PersonValidator validator = new PersonValidator();
		// validator.ValidateAndThrow(person);

		return this.personRepository.save(person);
	}

	public Person updatePerson(Person person) {
		// PersonValidator validator = new PersonValidator();
		// validator.ValidateAndThrow(person);

		return this.personRepository.saveAndFlush(person);
	}

	public void deletePerson(Person person) {
		this.personRepository.delete(person);
	}

	public void deletePersonById(String id) {
		Optional<Person> personToDelete = this.getPersonById(id);

		personToDelete.ifPresentOrElse(this::deletePerson, NoSuchElementException::new);
	}

}
