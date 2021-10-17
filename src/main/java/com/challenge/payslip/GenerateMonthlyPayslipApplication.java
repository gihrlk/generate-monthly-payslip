package com.challenge.payslip;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.challenge.payslip.model.Employee;
import com.challenge.payslip.model.MonthlyPayslip;

@SpringBootApplication
public class GenerateMonthlyPayslipApplication implements CommandLineRunner {

	final static Logger LOGGER = LogManager.getLogger(GenerateMonthlyPayslipApplication.class);

	private static final String PAYSLIP_API_ENDPOINT = "http://localhost:{port}/payslip/";

	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(GenerateMonthlyPayslipApplication.class, args).close();
	}

	@Override
	public void run(String... args) throws Exception {
		if (args != null && args.length == 2) {
			try {
				Employee employee = new Employee(args[0], new BigDecimal(args[1]));
				getEmployeeMonthlyPayslip(employee);
			} catch (NumberFormatException nfe) {
				LOGGER.error("Incorrect annual salary.");
			}
		} else {
			LOGGER.error("Please specify the empoyee name and annual salary.");
		}
	}

	private void getEmployeeMonthlyPayslip(Employee employee) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MonthlyPayslip> response = restTemplate.exchange(
				PAYSLIP_API_ENDPOINT + "generate/" + employee.getName() + "?annualSalary={annualSalary}",
				HttpMethod.GET, new HttpEntity<Object>(generateHeaders()), MonthlyPayslip.class, serverPort,
				employee.getAnnualSalary());
		MonthlyPayslip monthlyPayslip = response.getBody();
		LOGGER.info("Monthly Payslip for: {}", () -> monthlyPayslip.getName());
		LOGGER.info("Gross Monthly Income: ${}", () -> monthlyPayslip.getGrossIncome());
		LOGGER.info("Monthly Income Tax: ${}", () -> monthlyPayslip.getIncomeTax());
		LOGGER.info("Net Monthly Income: ${}", () -> monthlyPayslip.getNetIncome());
	}

	private MultiValueMap<String, String> generateHeaders() {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Content-Type", "application/json");
		return headers;
	}

}
