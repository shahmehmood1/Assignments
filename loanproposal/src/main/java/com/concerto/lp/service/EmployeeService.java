package com.concerto.lp.service;


import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concerto.lp.model.Customer;
import com.concerto.lp.model.Employee;
import com.concerto.lp.model.Loan;
import com.concerto.lp.repository.CustomerRepository;
import com.concerto.lp.repository.EmployeeRepository;
import com.concerto.lp.repository.LoanRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class EmployeeService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LoanRepository loanRepository;

	
	public boolean searchEmployeeById(String id) {
		
		if (this.employeeRepository.existsById(id))
			return true;
		return false;
	}
	
	public boolean addEmployee(Employee employee) {

		if(this.employeeRepository.existsById(employee.getId()))
		{
				throw new EntityExistsException("Employee with "+employee.getId()+" already exists");
		}
		this.employeeRepository.save(employee);
		return true;
	}
	
	public void approveLoan(String employeeId) throws Exception {
		if (this.searchEmployeeById(employeeId)) {
			List<Loan> loans = this.loanRepository.findEmployeeByEmployeeId(employeeId);
			System.out.println(loans);
			if (loans.size() > 0) {
				for (Loan loan : loans) {
					Customer customer = loan.getCustomer();
					if (loan.getLoanAmount() > (10 * customer.getAnnualIncome())) {
						loan.setRemarks("Loan amount cannot be 10 times of annual income");
						loan.setApproved(false);
						this.loanRepository.save(loan);
					} else if (loan.getCollaterals() == null || loan.getCollaterals().size() == 0) {
						loan.setRemarks("No collateral submitted");
						loan.setApproved(false);
						this.loanRepository.save(loan);
					} else if (!customer.isItrAttached()) {
						loan.setRemarks("Income proof not attached");
						loan.setApproved(false);
						this.loanRepository.save(loan);
					} else if (customer.getIdentity() == null) {
						loan.setRemarks("Identity document not submitted");
						loan.setApproved(false);
						this.loanRepository.save(loan);
					} else {
						loan.setRemarks("Approved");
						loan.setApproved(true);
						this.loanRepository.save(loan);
					}
					System.out.println(loans);
				}
			} else {
				throw new Exception("No loan proposals from this employee id");
			}
		} else
			throw new EntityNotFoundException("Employee does not exist");
	}

}
