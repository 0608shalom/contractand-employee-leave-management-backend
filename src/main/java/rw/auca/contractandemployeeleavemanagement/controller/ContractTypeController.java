package rw.auca.contractandemployeeleavemanagement.controller;

import java.util.List;
import java.util.UUID;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.contractandemployeeleavemanagement.model.ContractType;
import rw.auca.contractandemployeeleavemanagement.service.ContractTypeService;

@RestController 
@RequestMapping ("/api/contract-types")
public class ContractTypeController {

    public final ContractTypeService contractTypeService;

    public ContractTypeController(ContractTypeService contractTypeService) {
        this.contractTypeService = contractTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String>saveContractType(
        @RequestBody ContractType contractType) {
            
            contractTypeService.saveContractType(contractType);

            return ResponseEntity.status(HttpStatus.CREATED)
            .body("Contract type saved successfully");

        }
        @GetMapping("/all")
        public List<ContractType> getAllContractTypes(){
            return contractTypeService.getAllContractTypes();
        }
        @GetMapping("/unused")
public List<ContractType> getUnusedContractTypes() {
    return contractTypeService.getUnusedContractTypes();
}
        @GetMapping("/{id}")
public ResponseEntity<?> getContractTypeById(@PathVariable UUID id) {

    Optional<ContractType> contractType =
            contractTypeService.getContractTypeById(id);

    if (contractType.isPresent()) {
        return ResponseEntity.ok(contractType.get());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The contract type with that id does not exist");
}
        @PutMapping("/update/{id}")
public ResponseEntity<?> updateContractType(
        @PathVariable UUID id,
        @RequestBody ContractType contractType) {

    Optional<ContractType> updatedContractType =
            contractTypeService.updateContractType(id, contractType);

    if (updatedContractType.isPresent()) {
        return ResponseEntity.ok(updatedContractType.get());
    }

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The contract type with that id does not exist");
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContractType(@PathVariable UUID id) {

        if (contractTypeService.deleteContractType(id)) {
            return ResponseEntity.ok("Contract type deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The contract type with that id does not exist");
    }

}
