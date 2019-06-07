package com.marcosjordao.baseapi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcosjordao.baseapi.domain.entities.Person;
import com.marcosjordao.baseapi.domain.repositories.IPersonRepository;
import com.marcosjordao.baseapi.domain.services.IPersonService;
import com.marcosjordao.baseapi.domain.valueobjects.Email;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonServiceTests {

	private IPersonService personService;

	@Autowired
	private IPersonRepository personRepository;

	@Before
	public void setUp() {
		personService = new PersonService(personRepository);

		Person initialPerson = new Person("Initial Person");
		initialPerson.setEmail(new Email("initial@person.com"));
		initialPerson.setPhone("1234567890");

		personRepository.save(initialPerson);
	}

	@Test
	public void should_add_new_Person() {
		int countBeforeAdd = personService.getAllPersons().size();

		Person personToAdd = new Person("Complete Name");
		personService.addPerson(personToAdd);

		Long id = personToAdd.getId();

		Optional<Person> personAdded = personService.getPersonById(id);

		int countAfterAdd = personService.getAllPersons().size();

		assertEquals(countAfterAdd, countBeforeAdd + 1);
		assertTrue(personAdded.isPresent());
		assertEquals(id, personAdded.get().getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void should_throw_exception_on_add_invalid_Person() {
		Person personToAdd = new Person("teste");
		personToAdd.setEmail(new Email("invalid"));

		personService.addPerson(personToAdd);
	}

	@Test
	public void should_update_existing_Person() {
		List<Person> persons = personService.getAllPersons();
		Long id = persons.get(0).getId();

		Person personToUpdate = personService.getPersonById(id).get();
		personToUpdate.setEmail(new Email("mail@updated.com"));

		personService.updatePerson(personToUpdate);

		Person personUpdated = personService.getPersonById(id).get();

		assertEquals("mail@updated.com", personUpdated.getEmail().getAddress());
	}

	@Test(expected = ConstraintViolationException.class)
	public void should_throw_exception_on_update_invalid_Person() {
		List<Person> persons = personService.getAllPersons();
		Long id = persons.get(0).getId();

		Person personToUpdate = personService.getPersonById(id).get();
		personToUpdate.setEmail(new Email("invalid"));

		personService.updatePerson(personToUpdate);
	}

	@Test
	public void should_delete_Person() {
		List<Person> persons = personService.getAllPersons();
		int countBeforeDelete = persons.size();

		Long idToDelete = persons.get(0).getId();

		Person personToDelete = personService.getPersonById(idToDelete).get();
		personService.deletePerson(personToDelete);

		Optional<Person> personDeleted = personService.getPersonById(idToDelete);

		int countAfterDelete = personService.getAllPersons().size();

		assertFalse(personDeleted.isPresent());
		assertEquals(countAfterDelete, countBeforeDelete - 1);
	}
}
