package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.contractandemployeeleavemanagement.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID>{


}
