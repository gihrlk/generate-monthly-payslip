package com.challenge.payslip.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.payslip.model.Employee;
import com.challenge.payslip.model.MonthlyPayslip;
import com.challenge.payslip.service.PayslipService;

@RestController
public class PayslipController {

	private PayslipService payslipService;

	public PayslipController(PayslipService payslipService) {
		this.payslipService = payslipService;
	}

	@GetMapping("/payslip/generate/{name}")
	public ResponseEntity<MonthlyPayslip> getMonthlyPaylip(@PathVariable String name,
			@RequestParam BigDecimal annualSalary) {
		Employee employe = new Employee(name, annualSalary);
		return ResponseEntity.ok(payslipService.generateMonthlyPayslip(employe));
	}

}
