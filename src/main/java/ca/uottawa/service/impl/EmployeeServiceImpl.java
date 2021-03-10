package ca.uottawa.service.impl;

import ca.uottawa.entity.Employee;
import ca.uottawa.repository.EmployeeRepository;
import ca.uottawa.service.EmployeeService;
import ca.uottawa.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    @Override
    @Transactional
    public Result deleteEmployee(String empId) {
        empId = empId.trim();
        if (StringUtils.isEmpty(empId)) {
            return new Result(400, "Employee Id is required", null);
        }

        Optional<Employee> existedEmployee = employeeRepository.findByEmpId(empId);
        if (!existedEmployee.isPresent()) {
            return new Result(400, "Employee not exist", null);
        }

        try {
            employeeRepository.deleteByEmpId(empId);
            return new Result(200, null, null);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new Result(500, exception.getMessage(), null);
        }
    }

    @Override
    public Result getEmployee(String empId) {
        empId = empId.trim();
        if (StringUtils.isEmpty(empId)) {
            return new Result(400, "Employee Id is required", null);
        }

        Optional<Employee> existedEmployee = employeeRepository.findByEmpId(empId);
        if (!existedEmployee.isPresent()) {
            return new Result(404, "Employee not exist", null);
        } else {
            Employee employee = existedEmployee.get();
            return new Result(200, null, employee);
        }
    }

    @Override
    public Page<Employee> filterEmployeeBy(String empId, String name, String surname, String phoneNumber, String address, String title, Integer pageNum, Integer pageSize) {
        return employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (Objects.nonNull(empId) && !StringUtils.isEmpty(empId.trim())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("empId"), "%" + empId.trim() + "%"));
                }
                if (Objects.nonNull(name) && !StringUtils.isEmpty(name.trim())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name.trim() + "%"));
                }
                if (Objects.nonNull(surname) && !StringUtils.isEmpty(surname.trim())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("surname"), "%" + surname.trim() + "%"));
                }
                if (Objects.nonNull(phoneNumber) && !StringUtils.isEmpty(phoneNumber.trim())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber.trim() + "%"));
                }
                if (Objects.nonNull(address) && !StringUtils.isEmpty(address.trim())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("address"), "%" + address.trim() + "%"));
                }
                if (Objects.nonNull(title) && !StringUtils.isEmpty(title.trim())) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + title.trim() + "%"));
                }
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("empId")));
                return predicate;
            }
        }, PageRequest.of(pageNum, pageSize));
    }
}
