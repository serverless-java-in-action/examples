package org.acme.funqy;

import io.quarkus.funqy.Funq;

public class LandingRequestFunction {

    @Funq
    public String LandingRequest(LandingDetails details) {
        return "Approved for landing on planet " + details.getPlanet();
    }
}
