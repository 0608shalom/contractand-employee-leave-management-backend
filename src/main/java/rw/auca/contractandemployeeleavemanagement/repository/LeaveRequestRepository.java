package rw.auca.contractandemployeeleavemanagement.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
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
@Query("SELECT l.status, COUNT(l) FROM LeaveRequest l GROUP BY l.status")
    List<Object[]> countLeaveRequestsByStatus();


@Modifying
    @Transactional
    @Query("UPDATE LeaveRequest l SET l.status = :rejectedStatus " +
           "WHERE l.hrOfficer.id = :hrOfficerId " +
           "AND l.startDate = :date " +
           "AND l.status <> :closedStatus")
    int cancelLeaveRequestsByHrOfficerAndDate(
            @Param("hrOfficerId") UUID hrOfficerId,
            @Param("date") LocalDate date,
            @Param("rejectedStatus") LeaveStatus rejectedStatus,
            @Param("closedStatus") LeaveStatus closedStatus
    );
}
