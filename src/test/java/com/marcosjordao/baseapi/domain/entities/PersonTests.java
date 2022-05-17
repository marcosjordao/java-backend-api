package com.marcosjordao.baseapi.domain.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.marcosjordao.baseapi.domain.entities.Person;
import com.marcosjordao.baseapi.domain.valueobjects.Email;

public class PersonTests {

	@Test
	public void should_set_name_in_the_constructor() {
		Person person = new Person("Person Name");
		
		assertEquals("Person Name", person.getName());
	}

	@Test
	public void should_return_only_the_name_in_toString() {
		Person person = new Person("Person Name");
		
		assertEquals("Person Name", person.toString());
	}

	@Test
	public void should_return_the_name_and_email_in_toString() {
		Person person = new Person("Person Name");
		person.setEmail(new Email("person@mail.com"));
		
		assertEquals("Person Name <person@mail.com>", person.toString());
	}
}
