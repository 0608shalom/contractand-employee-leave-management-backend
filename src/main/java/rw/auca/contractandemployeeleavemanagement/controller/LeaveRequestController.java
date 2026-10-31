package rw.auca.contractandemployeeleavemanagement.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rw.auca.contractandemployeeleavemanagement.model.LeaveRequest;
import rw.auca.contractandemployeeleavemanagement.model.LeaveStatus;
import rw.auca.contractandemployeeleavemanagement.service.LeaveRequestService;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
     private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLeaveRequest(
            @RequestBody LeaveRequest leaveRequest) {

        String result = leaveRequestService.saveLeaveRequest(leaveRequest);

        if (result.equals("Leave request saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
@GetMapping("/all")
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();
    }
    @GetMapping("/by-status")
public List<LeaveRequest> getLeaveRequestsByStatus(
        @RequestParam("status") LeaveStatus status) {

    return leaveRequestService.getLeaveRequestsByStatus(status);
}
@GetMapping("/between")
public List<LeaveRequest> getLeaveRequestsBetweenDates(
        @RequestParam("start") LocalDate start,
        @RequestParam("end") LocalDate end) {

    return leaveRequestService.getLeaveRequestsBetweenDates(start, end);
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaveRequestById(@PathVariable UUID id) {

        Optional<LeaveRequest> leaveRequest =
                leaveRequestService.getLeaveRequestById(id);

        if (leaveRequest.isPresent()) {
            return ResponseEntity.ok(leaveRequest.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The leave request with that id does not exist");
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLeaveRequest(
            @PathVariable UUID id,
            @RequestBody LeaveRequest leaveRequest) {

        Optional<LeaveRequest> updatedRequest =
                leaveRequestService.updateLeaveRequest(id, leaveRequest);

        if (updatedRequest.isPresent()) {
            return ResponseEntity.ok(updatedRequest.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The leave request with that id does not exist");
    }
     @PutMapping("/update-status/{id}")
    public ResponseEntity<String> updateLeaveRequestStatus(
            @PathVariable UUID id,
            @RequestBody LeaveStatus newStatus) {

        String result =
                leaveRequestService.updateLeaveRequestStatus(id, newStatus);

        if (result.equals("Leave request status updated successfully")) {
            return ResponseEntity.ok(result);
        }

        if (result.equals("The leave request with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.badRequest().body(result);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLeaveRequest(@PathVariable UUID id) {

        if (leaveRequestService.deleteLeaveRequest(id)) {
            return ResponseEntity.ok("Leave request deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The leave request with that id does not exist");
    }
}
