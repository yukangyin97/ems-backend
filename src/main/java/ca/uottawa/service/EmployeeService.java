package ca.uottawa.service;

import ca.uottawa.entity.Employee;
import ca.uottawa.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface EmployeeService {
    Result addEmployee(Employee employee);

    Result editEmployee(Employee employee);

    Result deleteEmployee(String empId);

    Result getEmployee(String empId);

    Page<Employee> filterEmployeeBy(String empId, String name, String surname, String phoneNumber, String address, String title, Integer pageNum, Integer pageSize);
}
