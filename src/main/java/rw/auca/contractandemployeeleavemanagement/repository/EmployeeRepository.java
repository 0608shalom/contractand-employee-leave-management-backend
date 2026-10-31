package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.contractandemployeeleavemanagement.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

List<Employee> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastname);
}
