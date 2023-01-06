package com.team04.technikon.resources;

import com.team04.technikon.dto.RepairDto;
import com.team04.technikon.dto.RestApiResult;
import com.team04.technikon.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("repairResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class RepairResource {

    @Inject
    private OwnerService ownerService;

    @GET
    @Path("repair/{repairId}")
    @Produces("application/json")
    public RestApiResult<RepairDto> getRepair(@PathParam("repairId") int repairId) {
        return ownerService.getRepair(repairId);
    }

    @PUT
    @Path("repair/{repairId}")
    @Consumes("application/json")
    public RestApiResult<RepairDto> updateRepair(RepairDto repairDto, @PathParam("repairId") int repairId) {
        return ownerService.updateRepair(repairDto, repairId);
    }

    @POST
    @Path("/repair")
    @Produces("application/json")
    @Consumes("application/json")
    public void createRepair(RepairDto repair) {
        ownerService.createRepair(repair);
    }

    @DELETE
    @Path("repair/{repairId}")
    @Consumes("application/json")
    public boolean deleteRepair(@PathParam("repairId") int repairId) {
        return ownerService.deleteRepair(repairId);
    }

}
