package rw.auca.contractandemployeeleavemanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import rw.auca.contractandemployeeleavemanagement.model.Employee;
import rw.auca.contractandemployeeleavemanagement.repository.EmployeeRepository;

@Service 
public class EmployeeService {

    private  final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    
    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeById(UUID id){
        return employeeRepository.findById(id);
    }
    public Optional<Employee> updateEmployee(UUID id, Employee updateEmployee){
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setFirstName(updateEmployee.getFirstName());
            existingEmployee.setLastName(updateEmployee.getLastName());
            existingEmployee.setEmployeeNumber(updateEmployee.getEmployeeNumber());
            existingEmployee.setEmail(updateEmployee.getEmail());
            existingEmployee.setJobTitle(updateEmployee.getJobTitle());

            return employeeRepository.save(existingEmployee);
        });
        
    }

    public boolean deleteEmployee(UUID id){
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
            
        }
        return false;
    }
    public List<Employee> getEmployeesByLastName(String lastname){
        return employeeRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastname);
    }

}
