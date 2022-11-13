package com.concerto.lp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concerto.lp.constants.LoanConstants;
import com.concerto.lp.model.Collateral;
import com.concerto.lp.model.Employee;
import com.concerto.lp.model.Loan;
import com.concerto.lp.repository.CollateralRepository;
import com.concerto.lp.repository.CustomerRepository;
import com.concerto.lp.repository.EmployeeRepository;
import com.concerto.lp.repository.LoanRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CollateralRepository collateralRepository;

	public Loan findLoanById(String loanId) {
		Optional<Loan> optLoan = this.loanRepository.findById(loanId);
		return optLoan.orElseThrow(() -> new EntityNotFoundException("Loan with " + loanId + " not found"));
	}

	public ArrayList<Loan> findByEmployeeId(String empId) {
		ArrayList<Loan> list = new ArrayList<>();
		list = this.loanRepository.findEmployeeByEmployeeId(empId);
		return list;
	}
	
	public Loan applyForLoan(String loanType, double loanAmount, double period, String email) { 
		Loan loan = new Loan();
		int i = 0;
		loan.setLoanId("test"+this.loanRepository.count()+1);
		System.out.println(++i);
		loan.setLoanType(loanType);
		System.out.println(++i);
		loan.setLoanAmount(loanAmount); 
		System.out.println(++i);
		System.out.println("Period "+period);
		loan.setInterestRate(LoanConstants.calculateRate(period));
		System.out.println(++i);
		loan.setPeriod(period);
		System.out.println(++i);
		loan.setRemarks("Approved");
		System.out.println(++i);
		loan.setCustomer(this.customerRepository.findById(email).get());
		System.out.println(++i);
		List<Employee> employees = new ArrayList<>();
		int i1 = (int)(Math.random()* (employeeRepository.count()));
		this.employeeRepository.findAll().forEach(employee -> employees.add(employee));
		loan.setEmployee(employees.get(i1)); 
		
		this.loanRepository.save(loan);
		return loan;
			
	}
	
	
	public boolean uploadCollateral(String loanId, List<String> collateralIds) 
	{
		Optional<Loan> loan = this.loanRepository.findById(loanId);
		
		if(loan.isPresent()) {
			loan.ifPresent(c->{
				List<Collateral> collaterals = new ArrayList<Collateral>();
				collateralIds.forEach(id->{
					collaterals.add(this.collateralRepository.findById(id).get());
				});
				System.out.println(collaterals);
				c.setCollaterals(collaterals);
				this.loanRepository.save(c);
			});
			return true;	
		}else
		{
			return false;
		}
	}


}
