package org.acme;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/api")
public class LightSpeedBoosterResource {

    @GET
    @Path("/getLSBData")
    public List<LightSpeedBooster> listAll() {
        return LightSpeedBooster.listAll();
    }

    @GET
    @Path("/getWarpDrive")
    public List<LightSpeedBooster> findByWarpDrive() {
        return LightSpeedBooster.findWarpDrive();
    }

    /* 
    curl -X POST http://localhost:8080/api -v \
        -H 'Content-Type: application/json' \
        -d '{"name":"Hyperspace Jumper","quantity":1,"credit":1000000,"isWarpDrive":true}' 
   */
    @POST
    @Transactional
    public Response addNewPart(LightSpeedBooster lightSpeedBooster) {
        lightSpeedBooster.persist();
        return Response.status(Status.CREATED).entity(lightSpeedBooster).build();
    }

}