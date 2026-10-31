package rw.auca.contractandemployeeleavemanagement.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "leave_request")
public class LeaveRequest {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private String leaveType;

    private int days;

    @ManyToOne 
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne 
    @JoinColumn(name = "hr_officer_id")
    private HrOfficer hrOfficer;

    public LeaveRequest(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus submitted) {
        this.status = submitted;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public HrOfficer getHrOfficer() {
        return hrOfficer;
    }

    public void setHrOfficer(HrOfficer hrOfficer) {
        this.hrOfficer = hrOfficer;
    }

    

}
