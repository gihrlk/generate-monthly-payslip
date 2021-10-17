package com.challenge.payslip.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("annualSalary")
	private BigDecimal annualSalary;
	
	public Employee() {
		
	}
	
	public Employee(String name, BigDecimal annualSalary) {
		this.name = name;
		this.annualSalary = annualSalary;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("annualSalary")
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}

	@JsonProperty("annualSalary")
	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

}
