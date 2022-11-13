package com.concerto.lp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "mehmood_cust")
public class Customer {

	private String name;
	private String address;
	@Id
	private String emailId;
	private String identity;
	private int annualIncome;
	private boolean itrAttached;
}
