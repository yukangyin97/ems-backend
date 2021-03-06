package ca.uottawa.repository;

import ca.uottawa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    Optional<Employee> findByEmpId(String empId);

    void deleteByEmpId(String empId);
}
