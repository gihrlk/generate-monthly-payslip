package com.challenge.payslip.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaxCalculationUtilTest {

	@Test
	void test_annualTaxCalculation() {
		BigDecimal expected = new BigDecimal(6000);
		BigDecimal actual = TaxCalculationUtil.calculateAnnualTax(new BigDecimal(60000));
		assertTrue(expected.doubleValue() == actual.doubleValue());
	}
	
	@Test
	void test_annualTaxCalculationWithEdgeSalary() {
		BigDecimal expected = new BigDecimal(10000);
		BigDecimal actual = TaxCalculationUtil.calculateAnnualTax(new BigDecimal(80000));
		assertTrue(expected.doubleValue() == actual.doubleValue());
	}

}
