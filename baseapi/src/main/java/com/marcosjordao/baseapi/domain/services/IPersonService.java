package com.marcosjordao.baseapi.domain.services;

import java.util.List;
import java.util.Optional;

import com.marcosjordao.baseapi.domain.entities.Person;

public interface IPersonService {

	Optional<Person> getPersonById(Long id);

	boolean existsById(Long id);

	List<Person> getAllPersons();

	Person addPerson(Person person);

	Person updatePerson(Person person);

	void deletePerson(Person person);

	void deletePersonById(Long id);
}
