package rw.auca.contractandemployeeleavemanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "contract_type")
public class ContractType {

@Id 
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;

private String name;

@JsonIgnore 
@ManyToMany(mappedBy = "contractTypes")
private List<HrOfficer> hrOfficers = new ArrayList<>();

public ContractType(){

}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public List<HrOfficer> getHrOfficers() {
    return hrOfficers;
}

public void setHrOfficers(List<HrOfficer> hrOfficers) {
    this.hrOfficers = hrOfficers;
}

}

