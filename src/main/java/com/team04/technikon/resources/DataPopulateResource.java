package com.team04.technikon.resources;

import com.team04.technikon.services.IoServices;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/population")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DataPopulateResource {

    @Inject
    private IoServices ioServices;

    @GET
    public void init() {

        ioServices.readOwnersCsv("C:\\Users\\User\\Documents\\NetBeansProjects\\Technikon_Web_App\\owners.csv");
        ioServices.readPropertyCsv("C:\\Users\\User\\Documents\\NetBeansProjects\\Technikon_Web_App\\property.csv");
        ioServices.readRepairCsv("C:\\Users\\User\\Documents\\NetBeansProjects\\Technikon_Web_App\\repairs.csv");
        ioServices.relationshipsBetweenObjects();

    }

}
