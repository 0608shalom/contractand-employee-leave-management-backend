package rw.auca.contractandemployeeleavemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.ContractType;
import rw.auca.contractandemployeeleavemanagement.repository.ContractTypeRepository;

@Service 
public class ContractTypeService {

    private final ContractTypeRepository contractTypeRepository;

    public ContractTypeService(ContractTypeRepository contractTypeRepository) {
        this.contractTypeRepository = contractTypeRepository;
    }

    public ContractType saveContractType(ContractType contractType){
        return contractTypeRepository.save(contractType);
    }

    public List<ContractType>getAllContractTypes(){
        return contractTypeRepository.findAll();
    }
    public List<ContractType> getUnusedContractTypes() {
    return contractTypeRepository.findUnusedContractTypes();
}

    public Optional<ContractType>getContractTypeById(UUID id){
        return contractTypeRepository.findById(id);
    }

    public Optional<ContractType>updateContractType(UUID id, ContractType updateContractType){
        return contractTypeRepository.findById(id).map(existingContractType -> {
            existingContractType.setName(updateContractType.getName());    
        return contractTypeRepository.save(existingContractType);
        });
    }
    public boolean deleteContractType(UUID id){
        if (contractTypeRepository.existsById(id)) {
         contractTypeRepository.deleteById(id);   
         return true;
        }
        return false;
    }
}
