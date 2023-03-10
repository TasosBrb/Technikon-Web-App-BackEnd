package com.team04.technikon.services;


import com.team04.technikon.model.Repair;
import java.util.List;

public interface AdminService {

    /**
     * This method feches all repairs wich are not completed
     *
     * @return
     */
   List<Repair> getPendingRepairs();

    /**
     * This method updates the cost value to the proposed by the admin
     *
     * @param repair
     */
    void proposeCosts(int id, double cost);

    /**
     * This method sets the proposed by the admin Dates of the repair.
     *
     * @param repair
     * @param startDate
     * @param endDate
     */
    void proposeDates(Repair repair, String startDate, String endDate);

    /**
     * This method displays a list of the final start and end dates of the
     * repairs
     *
     * @return
     */
     List<Repair> displayActualDatesOfPendingRepairs();

}