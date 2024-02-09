package functions;

import io.quarkus.funqy.knative.events.CloudEvent;
import io.quarkus.funqy.knative.events.CloudEventBuilder;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class FunctionTest {

    @Test
    void testFunction() {
        CloudEvent<Output> output = (new Function()).function(
            CloudEventBuilder.create().build(
                new LandingRequestDetails("Risa", 100)));
        Assertions.assertTrue(output.data().isApproved());
        Assertions.assertEquals(
            "Landing APPROVED on planet Risa.", 
            output.data().getMessage());
    }

    @Test
    void testFunctionTooHeavy() {        
        CloudEvent<LandingRequestDetails> input = CloudEventBuilder.create().build(
            new LandingRequestDetails("Risa", 10001));
        CloudEvent<Output> output = (new Function()).function(input);
        Assertions.assertFalse(output.data().isApproved());
        Assertions.assertEquals(
            "Landing DENIED on planet Risa.", 
            output.data().getMessage());
    }

    @Test
    public void testFunctionIntegration() {
        RestAssured.given().contentType("application/json")
                .body("{\"planet\": \"Risa\", \"weight\": \"100\"}")
                .header("ce-id", "42")
                .header("ce-specversion", "1.0")
                .post("/")
                .then().statusCode(200)
                .body("message", equalTo("Landing APPROVED on planet Risa."))
                .body("approved", is(true));
    }

    @Test
    public void testFunctionIntegrationWithWeightTooHigh() {
        RestAssured.given().contentType("application/json")
                .body("{\"planet\": \"Risa\", \"weight\": \"1001\"}")
                .header("ce-id", "42")
                .header("ce-specversion", "1.0")
                .post("/")
                .then()
                .body("message", equalTo("Landing DENIED on planet Risa."))
                .body("approved", is(false))
                .body("reason", equalTo("Ship is too heavy"));
    }

    @Test
    public void testFunctionIntegrationWithMissingDetails() {
        RestAssured.given().contentType("application/json")
                .body("{\"planet\": \"Risa\"}")
                .header("ce-id", "42")
                .header("ce-specversion", "1.0")
                .post("/")
                .then()
                .body("message", equalTo("Landing DENIED on planet Risa."))
                .body("approved", is(false))
                .body("reason", equalTo("Weight is invalid"));
    }
}
