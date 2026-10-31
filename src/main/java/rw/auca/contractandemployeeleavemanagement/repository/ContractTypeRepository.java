package rw.auca.contractandemployeeleavemanagement.repository;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rw.auca.contractandemployeeleavemanagement.model.ContractType;

public interface ContractTypeRepository extends JpaRepository<ContractType, UUID>{

@Query("SELECT c FROM ContractType c WHERE c.hrOfficers IS EMPTY")
List<ContractType> findUnusedContractTypes();

}
