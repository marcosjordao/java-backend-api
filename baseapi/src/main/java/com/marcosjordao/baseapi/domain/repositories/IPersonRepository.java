package com.marcosjordao.baseapi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosjordao.baseapi.domain.entities.Person;

public interface IPersonRepository extends JpaRepository<Person, Long> {

}
