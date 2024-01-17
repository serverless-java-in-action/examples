package org.acme.funqy;

import org.junit.jupiter.api.Test;

import io.quarkus.google.cloud.functions.test.FunctionType;
import io.quarkus.google.cloud.functions.test.WithFunction;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
@WithFunction(FunctionType.FUNQY_CLOUD_EVENTS)
class LandingRequestFunctionTest {
    @Test
    void testLandingRequest() throws Exception {
        given()
        .body("{\"planet\": \"Risa\"}")
        .header("ce-id", "123451234512345") 
        .header("ce-specversion", "1.0")
        .header("ce-time", "2023-01-17T12:34:56.789Z")
        .header("ce-type", "org.acme.quarkus-funqy-gcp")
        .header("ce-subject", "LandingDetails")
        .header("ce-source", "space")
        .when()
        .post()
        .then()
        .statusCode(200);
    }

}

