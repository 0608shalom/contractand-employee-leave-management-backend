package rw.auca.contractandemployeeleavemanagement.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.contractandemployeeleavemanagement.model.HrOfficer;
import rw.auca.contractandemployeeleavemanagement.model.LeaveRequest;
import rw.auca.contractandemployeeleavemanagement.model.LeaveStatus;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, UUID>{

    boolean existsByHrOfficerAndStartDateAndStatusNot(
        HrOfficer hrOfficer,
        LocalDate startDate,
        LeaveStatus status
);
List<LeaveRequest> findByStatusOrderByStartDateAsc(LeaveStatus status);

List<LeaveRequest> findByStartDateBetweenOrderByStartDateAsc(
    LocalDate start,
    LocalDate end
);
}
