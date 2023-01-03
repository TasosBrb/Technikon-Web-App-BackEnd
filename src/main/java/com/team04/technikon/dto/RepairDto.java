package com.team04.technikon.dto;

import com.team04.technikon.enums.RepairStatus;
import com.team04.technikon.model.Repair;
import com.team04.technikon.enums.RepairType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RepairDto {

    private RepairType repairType;
    private String repairDescription;
    private LocalDate submissionDate;
    private String workDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private boolean acceptance;
    private RepairStatus repairStatus;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private PropertyDto property;

    public RepairDto(Repair repair) {

        this.repairType = repair.getRepairType();
        this.repairDescription = repair.getRepairDescription();
        this.submissionDate = repair.getSubmissionDate();
        this.workDescription = repair.getWorkDescription();
        this.startDate = repair.getStartDate();
        this.endDate = repair.getEndDate();
        this.cost = repair.getCost();
        this.repairStatus = repair.getRepairStatus();
        this.actualStartDate = repair.getActualStartDate();
        this.actualEndDate = repair.getActualEndDate();
    }

    public Repair asRepair() {
        Repair repair = new Repair();
        repair.setRepairType(repairType);
        repair.setRepairDescription(repairDescription);
        repair.setSubmissionDate(submissionDate);
        repair.setWorkDescription(workDescription);
        repair.setStartDate(startDate);
        repair.setEndDate(endDate);
        repair.setCost(cost);
        repair.setRepairStatus(repairStatus);
        repair.setActualStartDate(actualStartDate);
        repair.setEndDate(endDate);
        return repair;
    }

}
