package com.team04.technikon.resources;

import com.team04.technikon.dto.PropertyDto;
import com.team04.technikon.dto.RestApiResult;
import com.team04.technikon.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("propertyResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class PropertyResource {

    @Inject
    private OwnerService ownerService;

    @GET
    @Path("/property/{propertyId}")
    @Produces("application/json")
    public RestApiResult<PropertyDto> getProperty(@PathParam("propertyId") int propertyId) {
        return ownerService.getProperty(propertyId);
    }

    @PUT
    @Path("/property/{propertyId}")
    @Consumes("application/json")
    public RestApiResult<PropertyDto> updateProperty(PropertyDto propertyDto, @PathParam("propertyId") int propertyId) {
        return ownerService.updateProperty(propertyDto, propertyId);
    }

    @POST
    @Path("property")
    @Produces("application/json")
    @Consumes("application/json")
    public void createProperty(PropertyDto property) {
        ownerService.registerNewPropertyDto(property);
    }

    @DELETE
    @Path("property/{id}")
    @Consumes("application/json")
    public boolean deleteProperty(@PathParam("id") int id) {
        return ownerService.deleteProperty(id);
    }

}
