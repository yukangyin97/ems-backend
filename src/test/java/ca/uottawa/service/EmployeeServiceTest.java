package ca.uottawa.service;

import ca.uottawa.entity.Employee;
import ca.uottawa.repository.EmployeeRepository;
import ca.uottawa.utils.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
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

    /**
     * delete John Doe's data (empId: 2021001001)
     */
    @Test
    void deleteEmployeeTest() {
        // scenario 1: employee's empId is not specified
        String empId = "";
        Result result = employeeService.deleteEmployee(empId);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 2: employee doesn't exist
        empId = "2021050050";
        result = employeeService.deleteEmployee(empId);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 3: delete employee success
        empId = "2021001001";
        // reserve employee's info before deletion
        Optional<Employee> reserved = employeeRepository.findByEmpId(empId);
        Assertions.assertTrue(reserved.isPresent());
        Employee reservedEmployee = reserved.get();

        //delete
        result = employeeService.deleteEmployee(empId);
        System.out.println(result);
        Assertions.assertEquals(200, result.getCode());
        // recover employee's info after test
        employeeRepository.save(reservedEmployee);
    }

    @Test
    void getEmployeeTest() {
        // scenario 1: employee's empId is not specified
        String empId = "";
        Result result = employeeService.getEmployee(empId);
        System.out.println(result);
        Assertions.assertEquals(400, result.getCode());

        // scenario 2: employee doesn't exist
        empId = "2021050050";
        result = employeeService.getEmployee(empId);
        System.out.println(result);
        Assertions.assertEquals(404, result.getCode());

        // scenario 3: get employee data success
        empId = "2021001001";
        result = employeeService.getEmployee(empId);
        System.out.println(result);
        Assertions.assertEquals(200, result.getCode());
    }

    @Test
    void filterEmployeeTest() {
        String empId = null;
        String name = "";
        String surname = "";
        String phoneNumber = "";
        String address = "";
        String title = "";
        Integer pageNum = 0;
        Integer pageSize = 5;
        Page<Employee> employees = employeeService.filterEmployeeBy(empId, name, surname, phoneNumber, address, title, pageNum, pageSize);
        List<Employee> content = employees.getContent();
        Assertions.assertFalse(content.isEmpty());
    }

}
