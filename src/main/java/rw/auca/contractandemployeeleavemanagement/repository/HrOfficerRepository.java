package rw.auca.contractandemployeeleavemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.contractandemployeeleavemanagement.model.HrOfficer;

public interface HrOfficerRepository extends JpaRepository<HrOfficer, UUID>{

}
