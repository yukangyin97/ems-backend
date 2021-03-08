package ca.uottawa.controller;

import ca.uottawa.entity.Employee;
import ca.uottawa.service.EmployeeService;
import ca.uottawa.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
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

    @DeleteMapping("/api/employees/{empId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("empId") String empId) {
        Result result = employeeService.deleteEmployee(empId);
        int code = result.getCode();
        if (code == 200) {
            return ResponseEntity.status(200).body(null);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("error", result.getMsg());
            return ResponseEntity.status(code).body(map);
        }
    }

    @GetMapping("/api/employees/{empId}")
    public ResponseEntity<Object> getEmployee(@PathVariable("empId") String empId) {
        Result result = employeeService.getEmployee(empId);
        int code = result.getCode();
        if (code == 200) {
            Employee employee = (Employee) result.getData();
            return ResponseEntity.status(200).body(employee);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("error", result.getMsg());
            return ResponseEntity.status(code).body(map);
        }
    }

    @GetMapping("/api/employees")
    public ResponseEntity<Object> filterEmployee(@RequestParam(required = false, name = "p") Integer p,
                                                 @RequestParam(required = false, name = "empId") String empId,
                                                 @RequestParam(required = false, name = "name") String name,
                                                 @RequestParam(required = false, name = "surname") String surname,
                                                 @RequestParam(required = false, name = "phoneNumber") String phoneNumber,
                                                 @RequestParam(required = false, name = "address") String address,
                                                 @RequestParam(required = false, name = "title") String title,
                                                 ServletRequest request) {
        Map<String, String[]> queryMap = request.getParameterMap();
        Map<String, String> map = new HashMap<>();

        for (String query : queryMap.keySet()) {
            if (!query.equals("p") && !query.equals("empId") && !query.equals("name") && !query.equals("surname") && !query.equals("phoneNumber")
                    && !query.equals("address") && !query.equals("title")) {
                map.put("error", "Query parameter " + query + " is not supported");
                return ResponseEntity.status(400).body(map);
            }
        }

        if (p == null) p = 0;
        Integer pageSize = 5;  // set pageSize to 5 as default
        Page<Employee> employees = employeeService.filterEmployeeBy(empId, name, surname, phoneNumber, address, title, p, pageSize);
        return ResponseEntity.status(200).body(employees);
//        if (employees.getContent().isEmpty()) {
//            map.put("error", "No employee found");
//            return ResponseEntity.status(404).body(map);
//        }
//        else {
//            return ResponseEntity.status(200).body(employees);
//        }
    }
}
