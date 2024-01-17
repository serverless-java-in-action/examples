package org.acme.funqy;

public class LandingDetails {
    private String planet;

    public LandingDetails() {}

    public LandingDetails(String name) {
        this.planet = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String name) {
        this.planet = name;
    }
}

