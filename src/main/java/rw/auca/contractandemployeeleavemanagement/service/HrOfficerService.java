package rw.auca.contractandemployeeleavemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.HrOfficer;
import rw.auca.contractandemployeeleavemanagement.repository.HrOfficerRepository;

@Service 
public class HrOfficerService {

    private final HrOfficerRepository hrOfficerRepository;

    public HrOfficerService(HrOfficerRepository hrOfficerRepository){
        this.hrOfficerRepository = hrOfficerRepository;
    }
    public List<HrOfficer> getAllHrOfficers(){
        return hrOfficerRepository.findAll();
    }
        
    public List<HrOfficer> getHrOfficersByContractType(String name) {
        return hrOfficerRepository.findHrOfficersByContractType(name);
    
    }
    public void saveHrOfficer(HrOfficer hrOfficer) {
    hrOfficerRepository.save(hrOfficer);
}
    public Optional<HrOfficer> getHrOfficerById(UUID id){
        return hrOfficerRepository.findById(id);
    }

    public Optional<HrOfficer> updateHrOfficer(UUID id, HrOfficer updateHrOfficer){
        return hrOfficerRepository.findById(id).map(existingHrOfficer -> {
            existingHrOfficer.setFirstName(updateHrOfficer.getFirstName());
            existingHrOfficer.setLastName(updateHrOfficer.getLastName());
            existingHrOfficer.setEmail(updateHrOfficer.getEmail());
            existingHrOfficer.setDepartment(updateHrOfficer.getDepartment());
            existingHrOfficer.setContractTypes(updateHrOfficer.getContractTypes());
        
        return hrOfficerRepository.save(existingHrOfficer);
        });
    }

    public boolean deleteHrOfficer(UUID id){
        if (hrOfficerRepository.existsById(id)) {
            hrOfficerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
