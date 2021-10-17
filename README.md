# Monthly Payslip Generator
Coding Challenge for MYOB

### Assumptions
1. Annual Salary input shouldn't include the $ sign.
2. Gross Monthly Income, Monthly Income Tax and Net Monthly Salary are displayed with two decimal places.
3. GenerateMonthlyPayslip functionality is exposed as a REST API endpoint. It runs on SpringBoot's internal Tomcat server. By default, the application will use port 8080. If the port 8080 is not available, you can change the server port in application.properties file. 
4. The console output will have some log messages related to SpringBoot startup. The application output will appear last.
5. Internationalization is not supported.

### How to run the application
Application code is available in attached zip file. Please extract it to your local computer and navigate to the project's root directory.

You need Java 1.8+ to run the application. 

A compiled jar file is available in target directory. Run the application jar file with two arguments.<br>
Argument 1: Employee Name<br>
Argument 2: Annual Salary
##### java -jar target\GenerateMonthlyPayslip.jar "Mary Song" 60000
