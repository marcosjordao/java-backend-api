package com.marcosjordao.baseapi.domain.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.marcosjordao.baseapi.domain.entities.base.BaseEntity;
import com.marcosjordao.baseapi.domain.valueobjects.Email;

@Entity
public class Person extends BaseEntity {

	@NotEmpty
	@Size(max = 80)
	private String name;

	@Embedded
	@Valid
	private Email email;

	@Size(max = 20)
	private String phone;

	
	@SuppressWarnings("unused") // constructor for JPA
	private Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return name + (email != null ? " <" + email.getAddress() + ">" : "");
	}
}
