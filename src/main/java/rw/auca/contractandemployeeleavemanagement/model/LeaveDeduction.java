package rw.auca.contractandemployeeleavemanagement.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name =  "leave_deduction")
public class LeaveDeduction {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String balanceType;
    
    private String remarks;

    private int daysDeducted;

    private LocalDate recordedDate;

    @ManyToOne 
    @JoinColumn(name = "leave_request_id")
    private LeaveRequest leaveRequest;

    public LeaveDeduction(){
        
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDaysDeducted() {
        return daysDeducted;
    }

    public void setDaysDeducted(int daysDeducted) {
        this.daysDeducted = daysDeducted;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    public LeaveRequest getLeaveRequest() {
        return leaveRequest;
    }

    public void setLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

}
