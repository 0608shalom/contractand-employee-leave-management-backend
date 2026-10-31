package rw.auca.contractandemployeeleavemanagement.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.auca.contractandemployeeleavemanagement.model.HrOfficer;
import rw.auca.contractandemployeeleavemanagement.service.HrOfficerService;

@RestController
@RequestMapping("/api/hr-officers")
public class HrOfficerController {

    private final HrOfficerService hrOfficerService;

    public HrOfficerController(HrOfficerService hrOfficerService) {
        this.hrOfficerService = hrOfficerService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveHrOfficer(
            @RequestBody HrOfficer hrOfficer) {

        hrOfficerService.saveHrOfficer(hrOfficer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("HR officer saved successfully");
    }
    @GetMapping("/all")
public List<HrOfficer> getAllHrOfficers() {
    return hrOfficerService.getAllHrOfficers();
}
@GetMapping("/by-contract-type")
public List<HrOfficer> getHrOfficersByContractType(
        @RequestParam("name") String name) {

    return hrOfficerService.getHrOfficersByContractType(name);
}
     @GetMapping("/{id}")
    public ResponseEntity<?> getHrOfficerById(@PathVariable UUID id) {

        Optional<HrOfficer> hrOfficer =
                hrOfficerService.getHrOfficerById(id);

        if (hrOfficer.isPresent()) {
            return ResponseEntity.ok(hrOfficer.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The HR officer with that id does not exist");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHrOfficer(
            @PathVariable UUID id,
            @RequestBody HrOfficer hrOfficer) {

        Optional<HrOfficer> updatedHrOfficer =
                hrOfficerService.updateHrOfficer(id, hrOfficer);

        if (updatedHrOfficer.isPresent()) {
            return ResponseEntity.ok(updatedHrOfficer.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The HR officer with that id does not exist");
    }
    @PutMapping("/{hrOfficerId}/contract-types/{contractTypeId}")
public ResponseEntity<String> addContractType(
        @PathVariable UUID hrOfficerId,
        @PathVariable UUID contractTypeId) {

    String result = hrOfficerService.addContractType(
            hrOfficerId, contractTypeId);

    if (result.equals("Contract type added to the hr officer")) {
        return ResponseEntity.ok(result);
    }

    if (result.equals("The hr officer already has that contract type")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
}
@PatchMapping("/{hrOfficerId}/department")
public ResponseEntity<String> moveHrOfficerToDepartment(
        @PathVariable UUID hrOfficerId,
        @RequestParam("deptNumber") int deptNumber) {

    String result = hrOfficerService.moveHrOfficerToDepartment(
            hrOfficerId, deptNumber);

    if (result.equals("Department changed successfully")) {
        return ResponseEntity.ok(result);
    }

    if (result.equals("That department is taken by another hr officer")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHrOfficer(@PathVariable UUID id) {

        if (hrOfficerService.deleteHrOfficer(id)) {
            return ResponseEntity.ok("HR officer deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The HR officer with that id does not exist");
    }
}

