package rw.auca.contractandemployeeleavemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.Department;
import rw.auca.contractandemployeeleavemanagement.repository.DepartmentRepository;

@Service 
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    public Optional<Department>getDepartmentById(UUID id){
        return departmentRepository.findById(id);
    }

    public Optional<Department> updateDepartment(UUID id, Department updateDepartment){
        return departmentRepository.findById(id).map(existingDepartment -> {
            existingDepartment.setDeptNumber(updateDepartment.getDeptNumber());
            existingDepartment.setName(updateDepartment.getName());

            return departmentRepository.save(existingDepartment);
        });
    }
    public boolean deleteDepartment(UUID id){
        if (departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return true;
            
        }
        return false;
    }

    public void saveDepartment(Department department) {
    departmentRepository.save(department);
}
}
