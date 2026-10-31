package rw.auca.contractandemployeeleavemanagement.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import rw.auca.contractandemployeeleavemanagement.model.ContractType;

public interface ContractTypeRepository extends JpaRepository<ContractType, UUID>{


}
