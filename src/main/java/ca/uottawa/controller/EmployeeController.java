package ca.uottawa.controller;

import ca.uottawa.entity.Employee;
import ca.uottawa.service.EmployeeService;
import ca.uottawa.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/api/employees")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        Map<String, String> map = new HashMap<>();
        if (employee.getEmpId() == null) {
            map.put("error", "Employee Id is required");
            return ResponseEntity.status(400).body(map);
        }

        Result result = employeeService.addEmployee(employee);
        int code = result.getCode();
        if (code == 200) {
            Employee save = (Employee) result.getData();
            return ResponseEntity.status(200).body(save);
        } else {
            map.put("error", result.getMsg());
            return ResponseEntity.status(code).body(map);
        }
    }

    @PutMapping("/api/employees")
    public ResponseEntity<Object> editEmployee(@RequestBody Employee employee) {
        Map<String, String> map = new HashMap<>();
        if (employee.getEmpId() == null) {
            map.put("error", "Employee Id is required");
            return ResponseEntity.status(400).body(map);
        }

        Result result = employeeService.editEmployee(employee);
        int code = result.getCode();
        if (code == 200) {
            Employee edited = (Employee) result.getData();
            return ResponseEntity.status(200).body(edited);
        } else {
            map.put("error", result.getMsg());
            return ResponseEntity.status(code).body(map);
        }
    }

}
