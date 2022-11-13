package com.concerto.lp.service;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concerto.lp.model.Customer;
import com.concerto.lp.repository.CustomerRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findCustmerByEmail(String emailId) {
		Optional<Customer> optCust = this.customerRepository.findById(emailId);
		return optCust.orElseThrow(()->new EntityNotFoundException("Customer with "+emailId+" not found"));
	}
	
	public boolean addCustomer(Customer customer) {

		if(this.customerRepository.existsById(customer.getEmailId()))
		{
				throw new EntityExistsException("Customer with "+customer.getEmailId()+" already exists");
		}
		this.customerRepository.save(customer);
		return true;
	}
}
