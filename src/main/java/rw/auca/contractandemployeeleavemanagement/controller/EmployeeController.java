package rw.auca.contractandemployeeleavemanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.auca.contractandemployeeleavemanagement.model.Employee;
import rw.auca.contractandemployeeleavemanagement.service.EmployeeService;
import rw.auca.contractandemployeeleavemanagement.service.HrOfficerService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
private final HrOfficerService hrOfficerService;

public EmployeeController(
        EmployeeService employeeService,
        HrOfficerService hrOfficerService) {
    this.employeeService = employeeService;
    this.hrOfficerService = hrOfficerService;
}

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(
            @RequestBody Employee employee) {

        employeeService.saveEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Employee saved successfully");
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id) {

        Optional<Employee> employee =
                employeeService.getEmployeeById(id);

        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The employee with that id does not exist");
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(
            @PathVariable UUID id,
            @RequestBody Employee employee) {

        Optional<Employee> updatedEmployee =
                employeeService.updateEmployee(id, employee);

        if (updatedEmployee.isPresent()) {
            return ResponseEntity.ok(updatedEmployee.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The employee with that id does not exist");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID id) {

        if (employeeService.deleteEmployee(id)) {
            return ResponseEntity.ok("Employee deleted successfully");
        }
return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The employee with that id does not exist");
    }
    @GetMapping("/by-last-name")
    public List<Employee> getEmployeesByLastName(
        @RequestParam String lastName){
            return employeeService.getEmployeesByLastName(lastName);
        }
        @GetMapping("/of-hr-officer/{hrOfficerId}")
public ResponseEntity<?> getEmployeesByHrOfficer(
        @PathVariable UUID hrOfficerId) {

    if (hrOfficerService.getHrOfficerById(hrOfficerId).isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The hr officer with that id does not exist");
    }

    return ResponseEntity.ok(
            employeeService.getEmployeesByHrOfficer(hrOfficerId));
}
}