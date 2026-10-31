package rw.auca.contractandemployeeleavemanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.auca.contractandemployeeleavemanagement.model.Department;
import rw.auca.contractandemployeeleavemanagement.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveDepartment(
            @RequestBody Department department) {

        departmentService.saveDepartment(department);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Department saved successfully");
    }
    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    @GetMapping("/free")
public List<Department> getFreeDepartments() {
    return departmentService.getFreeDepartments();
}
     @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable UUID id) {

        Optional<Department> department =
                departmentService.getDepartmentById(id);

        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The department with that id does not exist");
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDepartment(
            @PathVariable UUID id,
            @RequestBody Department department) {

        Optional<Department> updatedDepartment =
                departmentService.updateDepartment(id, department);

        if (updatedDepartment.isPresent()) {
            return ResponseEntity.ok(updatedDepartment.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The department with that id does not exist");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable UUID id) {

        if (departmentService.deleteDepartment(id)) {
            return ResponseEntity.ok("Department deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The department with that id does not exist");
    }
}


