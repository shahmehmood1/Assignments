package com.concerto.lp.repository;

import org.springframework.data.repository.CrudRepository;

import com.concerto.lp.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{

}
