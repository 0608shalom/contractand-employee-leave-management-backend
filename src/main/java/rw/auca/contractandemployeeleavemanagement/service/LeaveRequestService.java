package rw.auca.contractandemployeeleavemanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.LeaveRequest;
import rw.auca.contractandemployeeleavemanagement.model.LeaveStatus;
import rw.auca.contractandemployeeleavemanagement.repository.LeaveDeductionRepository;
import rw.auca.contractandemployeeleavemanagement.repository.LeaveRequestRepository;

@Service 
public class LeaveRequestService {
 private final LeaveRequestRepository leaveRequestRepository;
private final LeaveDeductionRepository leaveDeductionRepository;

public LeaveRequestService(
        LeaveRequestRepository leaveRequestRepository,
        LeaveDeductionRepository leaveDeductionRepository) {

    this.leaveRequestRepository = leaveRequestRepository;
    this.leaveDeductionRepository = leaveDeductionRepository;
}

    public String saveLeaveRequest(LeaveRequest leaveRequest){

        if (leaveRequest.getStartDate().isBefore(LocalDate.now())) {
            return "Leave request date cannot be in the past";
            
        }
        boolean alreadyExists = 
        leaveRequestRepository.existsByHrOfficerAndStartDateAndStatusNot(
            leaveRequest.getHrOfficer(),
            leaveRequest.getStartDate(),
            LeaveStatus.REJECTED
      );
    
if (alreadyExists) {
            return "HR officer already processes a leave request starting on that date";
        }

        leaveRequest.setStatus(LeaveStatus.SUBMITTED);

        leaveRequestRepository.save(leaveRequest);

        return "Leave request saved successfully";
    }


    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }
    public List<LeaveRequest> getLeaveRequestsByStatus(LeaveStatus status){
        return leaveRequestRepository.findByStatusOrderByStartDateAsc(status);
    }
    public List<LeaveRequest> getLeaveRequestsBetweenDates(
        LocalDate start, LocalDate end) {
            return leaveRequestRepository
            .findByStartDateBetweenOrderByStartDateAsc(start, end);
        }

    
    public Optional<LeaveRequest> getLeaveRequestById(UUID id) {
        return leaveRequestRepository.findById(id);
    }

    
    public Optional<LeaveRequest> updateLeaveRequest(
            UUID id, LeaveRequest updatedLeaveRequest) {

        return leaveRequestRepository.findById(id).map(existingRequest -> {

            existingRequest.setStartDate(updatedLeaveRequest.getStartDate());
            existingRequest.setLeaveType(updatedLeaveRequest.getLeaveType());
            existingRequest.setDays(updatedLeaveRequest.getDays());
            existingRequest.setEmployee(updatedLeaveRequest.getEmployee());
            existingRequest.setHrOfficer(updatedLeaveRequest.getHrOfficer());

            return leaveRequestRepository.save(existingRequest);
        });
    }


    public String updateLeaveRequestStatus(UUID id, LeaveStatus newStatus) {

        Optional<LeaveRequest> optionalRequest =
                leaveRequestRepository.findById(id);

        if (optionalRequest.isEmpty()) {
            return "The leave request with that id does not exist";
        }

        LeaveRequest leaveRequest = optionalRequest.get();

        LeaveStatus currentStatus = leaveRequest.getStatus();

        boolean allowed = false;

        if (currentStatus == LeaveStatus.SUBMITTED) {
            allowed = newStatus == LeaveStatus.APPROVED
                    || newStatus == LeaveStatus.REJECTED;
        }

        else if (currentStatus == LeaveStatus.APPROVED) {
            allowed = newStatus == LeaveStatus.CLOSED
                    || newStatus == LeaveStatus.REJECTED;
        }

        if (!allowed) {
            return "Cannot change status from " + currentStatus
                    + " to " + newStatus;
        }

        leaveRequest.setStatus(newStatus);

        leaveRequestRepository.save(leaveRequest);

        return "Leave request status updated successfully";
    }


    public String deleteLeaveRequest(UUID id) {

    Optional<LeaveRequest> optionalRequest =
            leaveRequestRepository.findById(id);

    if (optionalRequest.isEmpty()) {
        return "The leave request with that id does not exist";
    }

    LeaveRequest leaveRequest = optionalRequest.get();

    boolean hasDeductions =
            leaveDeductionRepository.existsByLeaveRequest(leaveRequest);

    if (hasDeductions) {
        return "That leave request has leave deductions, delete them first";
    }

    leaveRequestRepository.delete(leaveRequest);

    return "Leave request deleted successfully";
    }
}
