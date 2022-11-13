package com.concerto.lp.repository;

import org.springframework.data.repository.CrudRepository;

import com.concerto.lp.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{

}
