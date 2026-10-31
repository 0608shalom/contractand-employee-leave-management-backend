package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.auca.contractandemployeeleavemanagement.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID>{

@Query("SELECT d FROM Department d " +
       "WHERE NOT EXISTS " +
       "(SELECT h FROM HrOfficer h WHERE h.department = d) " +
       "ORDER BY d.deptNumber ASC")
List<Department> findFreeDepartments();
}
