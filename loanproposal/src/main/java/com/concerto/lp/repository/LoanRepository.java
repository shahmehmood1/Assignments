package com.concerto.lp.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.concerto.lp.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, String>{

	@Query(value = "select * from loan where employee_Id=:employeeId",nativeQuery = true)
	public ArrayList<Loan> findEmployeeByEmployeeId(String employeeId);
	
	@Query()
	Optional<Loan> findByLoanId(String Id);
}
