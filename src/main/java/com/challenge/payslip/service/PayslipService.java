package com.challenge.payslip.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.challenge.payslip.model.Employee;
import com.challenge.payslip.model.MonthlyPayslip;
import com.challenge.payslip.util.TaxCalculationUtil;

@Service
public class PayslipService {
	
	private static final BigDecimal MONTHS_IN_YEAR = new BigDecimal(12);

	public MonthlyPayslip generateMonthlyPayslip(Employee employee) {
		MonthlyPayslip payslip = new MonthlyPayslip();
		payslip.setName(employee.getName());
		payslip.setGrossIncome(calculateGrossMonthlyIncome(employee.getAnnualSalary()));
		payslip.setIncomeTax(calculateMonthlyIncomeTax(employee.getAnnualSalary()));
		payslip.setNetIncome(calculateNetMonthlyIncome(payslip.getGrossIncome(), payslip.getIncomeTax()));
		return payslip;
	}

	private BigDecimal calculateGrossMonthlyIncome(BigDecimal annaulSalary) {
		BigDecimal grossMonthlyIncome = annaulSalary.divide(MONTHS_IN_YEAR, 2, RoundingMode.HALF_UP);
		grossMonthlyIncome = grossMonthlyIncome.setScale(2, RoundingMode.HALF_UP);
		return grossMonthlyIncome;
	}

	private BigDecimal calculateMonthlyIncomeTax(BigDecimal annaulSalary) {
		BigDecimal annualIncomeTax = TaxCalculationUtil.calculateAnnualTax(annaulSalary);
		BigDecimal monthlyIncomeTax = annualIncomeTax.divide(MONTHS_IN_YEAR, 2, RoundingMode.HALF_UP);
		return monthlyIncomeTax;
	}

	private BigDecimal calculateNetMonthlyIncome(BigDecimal grossMonthlyIncome, BigDecimal monthlyIncomeTax) {
		BigDecimal netMonthlyIncome = grossMonthlyIncome.subtract(monthlyIncomeTax);
		netMonthlyIncome = netMonthlyIncome.setScale(2, RoundingMode.HALF_UP);
		return netMonthlyIncome;
	}

}
