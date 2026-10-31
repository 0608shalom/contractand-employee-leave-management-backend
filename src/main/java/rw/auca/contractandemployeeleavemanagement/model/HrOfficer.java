package rw.auca.contractandemployeeleavemanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "hr_officer") 
public class HrOfficer {


    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne 
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany 
    @JoinTable(

        name = "hr_officer_contract_type",
        joinColumns = @JoinColumn(name = "hr_officer_id"),
        inverseJoinColumns = @JoinColumn(name = "contract_type_id")
    )
    private List<ContractType>contractTypes = new ArrayList<>();

    public HrOfficer(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ContractType> getContractTypes() {
        return contractTypes;
    }

    public void setContractTypes(List<ContractType> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    
}
