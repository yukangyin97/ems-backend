package ca.uottawa.service;

import ca.uottawa.entity.Employee;
import ca.uottawa.utils.Result;

public interface EmployeeService {
    Result addEmployee(Employee employee);
}