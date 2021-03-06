package ca.uottawa.service;

import ca.uottawa.entity.Employee;
import ca.uottawa.utils.Result;

import java.util.List;

public interface EmployeeService {
    Result addEmployee(Employee employee);

    Result editEmployee(Employee employee);

    Result deleteEmployee(String empId);

    Result getEmployee(String empId);

    List<Employee> filterEmployeeBy(String empId, String name, String surname, String phoneNumber, String address, String title);
}
