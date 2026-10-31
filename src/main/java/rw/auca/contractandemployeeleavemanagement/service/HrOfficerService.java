
package rw.auca.contractandemployeeleavemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.ContractType;
import rw.auca.contractandemployeeleavemanagement.model.Department;
import rw.auca.contractandemployeeleavemanagement.model.HrOfficer;
import rw.auca.contractandemployeeleavemanagement.repository.HrOfficerRepository;

@Service
public class HrOfficerService {

    private final HrOfficerRepository hrOfficerRepository;
    private final ContractTypeService contractTypeService;
    private final DepartmentService departmentService;

    public HrOfficerService(
            HrOfficerRepository hrOfficerRepository,
            ContractTypeService contractTypeService,
            DepartmentService departmentService) {
        this.hrOfficerRepository = hrOfficerRepository;
        this.contractTypeService = contractTypeService;
        this.departmentService = departmentService;
    }

    public List<HrOfficer> getAllHrOfficers() {
        return hrOfficerRepository.findAll();
    }

    public List<HrOfficer> getHrOfficersByContractType(String name) {
        return hrOfficerRepository.findHrOfficersByContractType(name);
    }

    
    public String addContractType(UUID hrOfficerId, UUID contractTypeId) {

        Optional<HrOfficer> optionalHrOfficer =
                hrOfficerRepository.findById(hrOfficerId);

        if (optionalHrOfficer.isEmpty()) {
            return "The hr officer with that id does not exist";
        }

        Optional<ContractType> optionalContractType =
                contractTypeService.getContractTypeById(contractTypeId);

        if (optionalContractType.isEmpty()) {
            return "That contract type does not exist";
        }

        HrOfficer hrOfficer = optionalHrOfficer.get();
        ContractType contractType = optionalContractType.get();

        if (hrOfficer.getContractTypes().contains(contractType)) {
            return "The hr officer already has that contract type";
        }

        hrOfficer.getContractTypes().add(contractType);
        hrOfficerRepository.save(hrOfficer);

        return "Contract type added to the hr officer";
    }


    public String moveHrOfficerToDepartment(UUID hrOfficerId, int deptNumber) {

        Optional<HrOfficer> optionalHrOfficer =
                hrOfficerRepository.findById(hrOfficerId);

        if (optionalHrOfficer.isEmpty()) {
            return "The hr officer with that id does not exist";
        }

        Optional<Department> optionalDepartment =
                departmentService.getDepartmentDeptNumber(deptNumber);

        if (optionalDepartment.isEmpty()) {
            return "The department with that deptNumber does not exist";
        }

        HrOfficer hrOfficer = optionalHrOfficer.get();
        Department department = optionalDepartment.get();

        boolean departmentTaken =
                hrOfficerRepository.existsByDepartmentAndIdNot(
                        department, hrOfficerId);

        if (departmentTaken) {
            return "That department is taken by another hr officer";
        }

        hrOfficer.setDepartment(department);
        hrOfficerRepository.save(hrOfficer);

        return "Department changed successfully";
    }

    public void saveHrOfficer(HrOfficer hrOfficer) {
        hrOfficerRepository.save(hrOfficer);
    }

    public Optional<HrOfficer> getHrOfficerById(UUID id) {
        return hrOfficerRepository.findById(id);
    }

    public Optional<HrOfficer> updateHrOfficer(
            UUID id, HrOfficer updateHrOfficer) {
        return hrOfficerRepository.findById(id).map(existingHrOfficer -> {
            existingHrOfficer.setFirstName(updateHrOfficer.getFirstName());
            existingHrOfficer.setLastName(updateHrOfficer.getLastName());
            existingHrOfficer.setEmail(updateHrOfficer.getEmail());
            existingHrOfficer.setDepartment(updateHrOfficer.getDepartment());
            existingHrOfficer.setContractTypes(updateHrOfficer.getContractTypes());

            return hrOfficerRepository.save(existingHrOfficer);
        });
    }

    public boolean deleteHrOfficer(UUID id) {
        if (hrOfficerRepository.existsById(id)) {
            hrOfficerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
