package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.contractandemployeeleavemanagement.model.Department;
import rw.auca.contractandemployeeleavemanagement.model.HrOfficer;

public interface HrOfficerRepository extends JpaRepository<HrOfficer, UUID>{

    @Query("SELECT h FROM HrOfficer h JOIN h.contractTypes c " +
       "WHERE LOWER(c.name) = LOWER(:name)")
List<HrOfficer> findHrOfficersByContractType(@Param("name") String name);

boolean existsByDepartmentAndIdNot(
    Department department,
    UUID id
);
}
