package ca.uottawa.service;

import ca.uottawa.entity.Employee;
import ca.uottawa.repository.EmployeeRepository;
import ca.uottawa.utils.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.beans.Transient;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional
    void addEmployeeTest() {
        /**
         * Precondition: executing db.init script to insert one employee record to employee table
         */

        // scenario 1: new employee's empId is not specified
        Employee employee = new Employee();
        employee.setEmpId("");
        employee.setName("John Smith");
        employee.setSurname("Smith");
        employee.setPhoneNumber("+1(613)438-7843");
        employee.setAddress("171 Lees Ave");
        employee.setTitle("Senior Full Stack Developer");

        Result result = employeeService.addEmployee(employee);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 2: employee already exists
        employee.setEmpId("2021001001");
        result = employeeService.addEmployee(employee);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 3: employee with new empId can be added to database
        employee.setEmpId("2021001002");
        result = employeeService.addEmployee(employee);
        System.out.println(result);
        Assertions.assertEquals(200, result.getCode());
        // delete newly added employee data after test
        employeeRepository.deleteByEmpId("2021001002");
    }

}
