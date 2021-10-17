package com.challenge.payslip.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.payslip.model.Employee;
import com.challenge.payslip.model.MonthlyPayslip;

@SpringBootTest
public class PayslipServiceTest {

	@Autowired
	PayslipService payslipService;

	@Test
	void test_generateMonthPayslip() {
		MonthlyPayslip expected = new MonthlyPayslip("Mary Song", new BigDecimal(5000), new BigDecimal(500),
				new BigDecimal(4500));
		Employee employee = new Employee("Mary Song", new BigDecimal(60000));
		MonthlyPayslip actual = payslipService.generateMonthlyPayslip(employee);
		assertTrue(expected.equals(actual));
	}

}
