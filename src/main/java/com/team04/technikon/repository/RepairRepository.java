package com.team04.technikon.repository;

import com.team04.technikon.enums.RepairStatus;
import com.team04.technikon.enums.RepairType;
import com.team04.technikon.model.Repair;

import java.util.List;

public interface RepairRepository extends Repository<Repair> {

    boolean delete(int id);

    Repair search(int id);

    List<Repair> search(String submissionDate);

    void updatePropertyId(int repairId, int propertyId);

    void updateRepairType(int id, RepairType repairType);

    void updateRepairDescription(int id, String repairDescription);

    void updateSubmissionDate(int id, String submissionDate);

    void updateWorkDescription(int id, String workDescription);

    void updateStartDate(int id, String startDate);

    void updateEndDate(int id, String endDate);

    void updateCost(int id, double cost);

    void updateAcceptance(int id, boolean acceptance);

    void updateRepairStatus(int id, RepairStatus repairStatus);

    void updateActualStartDate(int id, String actualStartDate);

    void updateActualEndDate(int id, String actualEndDate);

    List<Repair> readAll();

    Repair findById(int id);

    boolean deleteRepair(int id);

    List<Repair> findAll();

    List<Repair> findbyExactDate(String date);

    List<Repair> findRepairsOfOwner(int id);
}
