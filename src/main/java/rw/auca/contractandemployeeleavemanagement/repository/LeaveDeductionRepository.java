package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.contractandemployeeleavemanagement.model.LeaveDeduction;
import rw.auca.contractandemployeeleavemanagement.model.LeaveRequest;

public interface LeaveDeductionRepository extends JpaRepository<LeaveDeduction, UUID>{

     @Query("SELECT d FROM LeaveDeduction d " +
           "WHERE d.leaveRequest.employee.employeeNumber = :employeeNumber " +
           "ORDER BY d.recordedDate DESC")
    List<LeaveDeduction> findByEmployeeNumberOrderByRecordedDateDesc(
            @Param("employeeNumber") String employeeNumber
    );

    boolean existsByLeaveRequest(LeaveRequest leaveRequest);
}
