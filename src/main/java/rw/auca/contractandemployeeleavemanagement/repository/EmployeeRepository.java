package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.contractandemployeeleavemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

List<Employee> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastname);
@Query("SELECT DISTINCT l.employee FROM LeaveRequest l " +
       "WHERE l.hrOfficer.id = :hrOfficerId")
List<Employee> findEmployeesByHrOfficer(
        @Param("hrOfficerId") UUID hrOfficerId);
}
