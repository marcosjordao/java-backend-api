package com.marcosjordao.baseapi.domain.valueobjects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.marcosjordao.baseapi.domain.valueobjects.Email;

public class EmailTests {

	@Test
	public void should_set_address_in_the_constructor() {
		Email email = new Email("valid@address.com");
		
		assertEquals("valid@address.com", email.getAddress());
	}
	

	@Test
	public void should_return_address_in_toString() {
		Email email = new Email("valid@address.com");
		
		assertEquals("valid@address.com", email.toString());
	}
}
