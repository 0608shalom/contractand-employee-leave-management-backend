package rw.auca.contractandemployeeleavemanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.LeaveDeduction;
import rw.auca.contractandemployeeleavemanagement.model.LeaveRequest;
import rw.auca.contractandemployeeleavemanagement.model.LeaveStatus;
import rw.auca.contractandemployeeleavemanagement.repository.LeaveDeductionRepository;
import rw.auca.contractandemployeeleavemanagement.repository.LeaveRequestRepository;

@Service
public class LeaveDeductionService {

    private final LeaveDeductionRepository leaveDeductionRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveDeductionService(
            LeaveDeductionRepository leaveDeductionRepository,
            LeaveRequestRepository leaveRequestRepository) {
        this.leaveDeductionRepository = leaveDeductionRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }
    public String saveLeaveDeduction(UUID leaveRequestId, LeaveDeduction leaveDeduction) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElse(null);

        if (leaveRequest == null) {
            return "The leave request with that id does not exist";
        }

        if (leaveRequest.getStatus() != LeaveStatus.CLOSED) {
            return "Leave deductions are only for CLOSED leave requests";
        }

        leaveDeduction.setRecordedDate(LocalDate.now());
        leaveDeduction.setLeaveRequest(leaveRequest);

        leaveDeductionRepository.save(leaveDeduction);

        return "Leave deduction saved successfully";
    }
    public List<LeaveDeduction> getDeductionsByEmployeeNumber(String employeeNumber) {

        return leaveDeductionRepository
                .findByEmployeeNumberOrderByRecordedDateDesc(employeeNumber);
    }
    public boolean deleteLeaveDeduction(UUID id) {

        if (!leaveDeductionRepository.existsById(id)) {
            return false;
        }

        leaveDeductionRepository.deleteById(id);

        return true;
    }
}