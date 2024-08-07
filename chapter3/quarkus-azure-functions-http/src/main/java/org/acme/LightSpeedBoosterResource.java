package org.acme;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class LightSpeedBoosterResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/getLSBData")
    public List<String> listAll() {

        List<String> lightSpeedBooster = new ArrayList<String>();
        lightSpeedBooster.add("Tachyon Burst");
        lightSpeedBooster.add("Thrusters");
        lightSpeedBooster.add("Transwarp");
        lightSpeedBooster.add("Turbolift");
        return lightSpeedBooster;

    }

}