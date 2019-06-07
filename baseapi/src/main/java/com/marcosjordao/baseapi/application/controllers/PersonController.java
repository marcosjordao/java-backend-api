package com.marcosjordao.baseapi.application.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.marcosjordao.baseapi.domain.entities.Person;
import com.marcosjordao.baseapi.domain.services.IPersonService;

@RestController()
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private IPersonService personService;
	

	@GetMapping
	public List<Person> getAll() {
		return personService.getAllPersons();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Person> get(@PathVariable Long id) {
		Optional<Person> person = personService.getPersonById(id);
		
		return person.map(ResponseEntity::ok)
					 .orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Person> add(@Valid @RequestBody Person person) {
		Person personAdded = this.personService.addPerson(person);
		
		return ResponseEntity.created(URI.create("/" + personAdded.getId())).build();
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody Person person) {
		if (!personService.existsById(person.getId()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		this.personService.updatePerson(person);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@RequestBody Person person) {
		if (!personService.existsById(person.getId()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		this.personService.deletePerson(person);
	}
	
}
