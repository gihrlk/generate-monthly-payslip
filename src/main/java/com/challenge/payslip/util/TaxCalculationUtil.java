package com.challenge.payslip.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class TaxCalculationUtil {

	class TaxRate {

		private BigDecimal lowerThreshold;

		private BigDecimal upperThreshold;

		private BigDecimal rate;

		TaxRate(BigDecimal lowerThreshold, BigDecimal upperThreshold, BigDecimal rate) {
			this.lowerThreshold = lowerThreshold;
			this.upperThreshold = upperThreshold;
			this.rate = rate;
		}

	}

	static List<TaxRate> taxRateList = new ArrayList<TaxRate>();

	@PostConstruct
	public void addTaxRates() {
		taxRateList.add(new TaxRate(new BigDecimal(0), new BigDecimal(20000), new BigDecimal(0)));
		taxRateList.add(new TaxRate(new BigDecimal(20000), new BigDecimal(40000), new BigDecimal(0.1)));
		taxRateList.add(new TaxRate(new BigDecimal(40000), new BigDecimal(80000), new BigDecimal(0.2)));
		taxRateList.add(new TaxRate(new BigDecimal(80000), new BigDecimal(180000), new BigDecimal(0.3)));
		taxRateList.add(new TaxRate(new BigDecimal(180000), null, new BigDecimal(0.4)));
	}

	public static BigDecimal calculateAnnualTax(BigDecimal annualSalary) {
		BigDecimal annualTax = new BigDecimal(0);

		List<TaxRate> appliableTaxRates = taxRateList.stream()
				.filter(tr -> tr.lowerThreshold.compareTo(annualSalary) < 0).collect(Collectors.toList());

		for (TaxRate taxRate : appliableTaxRates) {
			BigDecimal tax = calculateTax(annualSalary, taxRate);
			if (tax != null) {
				annualTax = annualTax.add(tax);
			}
		}

		return annualTax;
	}

	private static BigDecimal calculateTax(BigDecimal annualSalary, TaxRate taxRate) {
		if (annualSalary.compareTo(taxRate.lowerThreshold) > 0) {
			if (annualSalary.compareTo(taxRate.upperThreshold) > 0) {
				return calculateTax(taxRate.lowerThreshold, taxRate.upperThreshold, taxRate.rate);
			} else if (taxRate.upperThreshold == null || annualSalary.compareTo(taxRate.upperThreshold) <= 0) {
				return calculateTax(taxRate.lowerThreshold, annualSalary, taxRate.rate);
			}
		}

		return null;
	}

	private static BigDecimal calculateTax(BigDecimal lowerThreshold, BigDecimal upperThreshold, BigDecimal rate) {
		return (upperThreshold.subtract(lowerThreshold)).multiply(rate);
	}

}
