package ca.uottawa.service.impl;

import ca.uottawa.entity.Employee;
import ca.uottawa.repository.EmployeeRepository;
import ca.uottawa.service.EmployeeService;
import ca.uottawa.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Result addEmployee(Employee employee) {
        String empId = employee.getEmpId();
        empId = empId.trim();
        if (StringUtils.isEmpty(empId)) {
            return new Result(400, "Employee Id is required", null);
        }

        Optional<Employee> existedEmployee = employeeRepository.findByEmpId(empId);
        if (existedEmployee.isPresent()) {
            return new Result(400, "Employee existed", null);
        }

        try {
            Employee save = employeeRepository.save(employee);
            return new Result(200, "", save);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(500, exception.getMessage(), null);
        }
    }

    @Override
    public Result editEmployee(Employee employee) {
        String empId = employee.getEmpId();
        empId = empId.trim();
        if (StringUtils.isEmpty(empId)) {
            return new Result(400, "Employee Id is required", null);
        }

        Optional<Employee> existedEmployee = employeeRepository.findByEmpId(empId);
        if (!existedEmployee.isPresent()) {
            return new Result(400, "Employee not exist", null);
        }

        // update old employee's information (keep empId unchanged)
        Employee oldEmployee = existedEmployee.get();
        oldEmployee.setName(employee.getName());
        oldEmployee.setSurname(employee.getSurname());
        oldEmployee.setPhoneNumber(employee.getPhoneNumber());
        oldEmployee.setAddress(employee.getAddress());
        oldEmployee.setTitle(employee.getTitle());

        try {
            Employee save = employeeRepository.save(oldEmployee);
            return new Result(200, "", save);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(500, exception.getMessage(), null);
        }
    }
}
