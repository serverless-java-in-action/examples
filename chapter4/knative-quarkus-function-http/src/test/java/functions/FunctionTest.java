package functions;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class FunctionTest {

    @Test
    void testFunction() {
        Output output = (new Function()).function(new LandingRequestDetails("Risa", 100));
        Assertions.assertTrue(output.isApproved());
        Assertions.assertEquals("Landing APPROVED on planet Risa.", output.getMessage());

    }

    @Test
    void testFunctionTooHeavy() {
        Output output = (new Function()).function(new LandingRequestDetails("Risa", 10001));
        Assertions.assertFalse(output.isApproved());
        Assertions.assertEquals("Landing DENIED on planet Risa.", output.getMessage());

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
