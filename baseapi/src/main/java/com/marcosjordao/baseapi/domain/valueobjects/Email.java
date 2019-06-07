package com.marcosjordao.baseapi.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Email {

	@javax.validation.constraints.Email
	@Column(length = 80, name = "emailAddress")
	private String address;

	
	@SuppressWarnings("unused") // constructor for JPA
	private Email() {

	}

	public Email(String address) {
		this.address = address;
	}


	public String getAddress() {
		return address;
	}
	
	@Override
	public String toString() {
		return address;
	}
}
