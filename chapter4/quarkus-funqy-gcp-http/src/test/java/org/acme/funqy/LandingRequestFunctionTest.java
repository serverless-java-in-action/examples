package org.acme.funqy;

import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest

class LandingRequestFunctionTest {
    @Test
    void testLandingRequest() throws Exception {
        LandingDetails in = new LandingDetails();
        in.setPlanet("Mars");
        given()
                .contentType("application/json")
                .accept("application/json")
                .body(in)
                .when()
                .post("/landingRequest")
                .then()
                .statusCode(200)
                .body(containsString("planet Mars"));
    }

}

