package com.team04.technikon.resources;

import com.team04.technikon.dto.RepairDto;
import com.team04.technikon.dto.RestApiResult;
import com.team04.technikon.model.Repair;
import com.team04.technikon.services.AdminService;
import com.team04.technikon.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

    @Inject
    private AdminService adminService;

    @Inject
    private OwnerService ownerService;

    @GET
    @Path("pendingRepairs")
    @Produces("application/json")
    public List<RepairDto> getPendingRepairs() {
        List<Repair> repairs = adminService.getPendingRepairs();
        return repairs.stream().map(RepairDto::new).collect(Collectors.toList());
    }

    
    @GET
    @Path("pendingRepairsActualDates")
    @Produces("application/json")
    public List<Repair> getPendingRepairsActualDates() {
        List<Repair> repairs = adminService.displayActualDatesOfPendingRepairs();
        return repairs;

    }

    @PUT
    @Path("proposeDatesAndCosts/{repairId}")
    @Consumes("application/json")
    public RestApiResult<RepairDto> proposeDatesAndCosts(RepairDto repairDto, @PathParam("repairId") int repairId) {
        return ownerService.updateRepair(repairDto, repairId);
    }

}
