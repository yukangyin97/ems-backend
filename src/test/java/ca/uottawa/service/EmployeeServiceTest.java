package ca.uottawa.service;

import ca.uottawa.entity.Employee;
import ca.uottawa.repository.EmployeeRepository;
import ca.uottawa.utils.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Precondition: Execute init.sql to insert one employee record to database
 */
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Add
     * ['2021001002', 'John Smith', 'Smith', '+1(613)438-7843', '171 Lees Ave', 'Senior Full Stack Developer']
     * to employee table
     */
    @Test
    @Transactional
    void addEmployeeTest() {
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

    /**
     * Edit John Doe's surname to "Peterson"
     */
    @Test
    void editEmployeeTest() {
        // scenario 1: employee's empId is not specified
        Employee employee = new Employee();
        employee.setEmpId("");
        employee.setName("John Peterson");
        employee.setSurname("Peterson");
        Result result = employeeService.editEmployee(employee);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 2: employee doesn't exists
        employee.setEmpId("2021001002");
        result = employeeService.editEmployee(employee);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 3: employee edit success
        employee.setEmpId("2021001001");
        // reserve old employee's info
        Optional<Employee> reserved = employeeRepository.findByEmpId(employee.getEmpId());
        Assertions.assertTrue(reserved.isPresent());
        Employee reservedEmployee = reserved.get();

        // edit
        result = employeeService.editEmployee(employee);
        System.out.println(result);
        Assertions.assertEquals(200, result.getCode());
        // recover employee's info after test
        employeeRepository.save(reservedEmployee);
    }

}
