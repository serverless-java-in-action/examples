package org.acme.funqy;
import io.quarkus.funqy.Funq;

public class LandingRequestFunction {

    @Funq("landingrequest")
    public String landingRequest(LandingDetails details) {
        return "Approved for landing on planet " + details.getPlanet();
    }
}
