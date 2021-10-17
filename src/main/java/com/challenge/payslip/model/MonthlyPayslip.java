package com.challenge.payslip.model;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonthlyPayslip {

	@JsonProperty("name")
	private String name;

	@JsonProperty("grossIncome")
	private BigDecimal grossIncome;

	@JsonProperty("incomeTax")
	private BigDecimal incomeTax;

	@JsonProperty("netIncome")
	private BigDecimal netIncome;

	public MonthlyPayslip() {

	}

	public MonthlyPayslip(String name, BigDecimal grossIncome, BigDecimal incomeTax, BigDecimal netIncome) {
		this.name = name;
		this.grossIncome = grossIncome;
		this.incomeTax = incomeTax;
		this.netIncome = netIncome;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("grossIncome")
	public BigDecimal getGrossIncome() {
		return grossIncome;
	}

	@JsonProperty("grossIncome")
	public void setGrossIncome(BigDecimal grossIncome) {
		this.grossIncome = grossIncome;
	}

	@JsonProperty("incomeTax")
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	@JsonProperty("incomeTax")
	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}

	@JsonProperty("netIncome")
	public BigDecimal getNetIncome() {
		return netIncome;
	}

	@JsonProperty("netIncome")
	public void setNetIncome(BigDecimal netIncome) {
		this.netIncome = netIncome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MonthlyPayslip payslip = (MonthlyPayslip) o;
		return Objects.equals(name, payslip.name)
				&& Objects.equals(grossIncome.doubleValue(), payslip.grossIncome.doubleValue())
				&& Objects.equals(incomeTax.doubleValue(), payslip.incomeTax.doubleValue())
				&& Objects.equals(netIncome.doubleValue(), payslip.netIncome.doubleValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, grossIncome, incomeTax, netIncome);
	}

}
