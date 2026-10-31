package rw.auca.contractandemployeeleavemanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.contractandemployeeleavemanagement.model.LeaveDeduction;
import rw.auca.contractandemployeeleavemanagement.service.LeaveDeductionService;

@RestController
@RequestMapping("/api/leave-deductions")
public class LeaveDeductionController {

    private final LeaveDeductionService leaveDeductionService;

    public LeaveDeductionController(LeaveDeductionService leaveDeductionService) {
        this.leaveDeductionService = leaveDeductionService;
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveLeaveDeduction(
            @RequestParam UUID leaveRequestId,
            @RequestBody LeaveDeduction leaveDeduction) {

        String result = leaveDeductionService.saveLeaveDeduction(
                leaveRequestId, leaveDeduction);

        if (result.equals("The leave request with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.equals("Leave deductions are only for CLOSED leave requests")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
     @GetMapping("/employee/{employeeNumber}")
    public ResponseEntity<List<LeaveDeduction>> getDeductionsByEmployeeNumber(
            @PathVariable String employeeNumber) {

        List<LeaveDeduction> deductions =
                leaveDeductionService.getDeductionsByEmployeeNumber(employeeNumber);

        return ResponseEntity.ok(deductions);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLeaveDeduction(@PathVariable UUID id) {

        boolean deleted = leaveDeductionService.deleteLeaveDeduction(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The leave deduction with that id does not exist");
        }

        return ResponseEntity.ok("Leave deduction deleted successfully");
    }
}