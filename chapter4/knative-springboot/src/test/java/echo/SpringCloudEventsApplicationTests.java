package echo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.cloud.function.cloudevent.CloudEventMessageUtils.ID;
import static org.springframework.cloud.function.cloudevent.CloudEventMessageUtils.SOURCE;
import static org.springframework.cloud.function.cloudevent.CloudEventMessageUtils.SPECVERSION;
import static org.springframework.cloud.function.cloudevent.CloudEventMessageUtils.SUBJECT;
import static org.springframework.cloud.function.cloudevent.CloudEventMessageUtils.TYPE;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = SpringCloudEventsApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringCloudEventsApplicationTests {
  
  @LocalServerPort
  private int port;
  
  private WebTestClient getWebTestClient() {
    return WebTestClient.bindToServer()
        .baseUrl("http://localhost:" + port)
        .build();
  }

  @Test
  public void testEchoInput() throws Exception {

    String input ="hello";

    getWebTestClient().post()
        .uri("/echo")
        .contentType(MediaType.APPLICATION_JSON)
        .header(SPECVERSION, "1.0")
        .header(ID, UUID.randomUUID().toString())
        .header(TYPE, "echo")
        .header(SOURCE, "http://localhost:8080/echo")
        .header(SUBJECT, "Echo content")
        .bodyValue(input)
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .value(body -> {
          assertThat(body, notNullValue());
          assertThat(body, equalTo(input));
        });
  }

  @Test
  public void testEchoRoutingBasedOnType() throws Exception {

    String input ="hello";

    getWebTestClient().post()
        .uri("/")
        .contentType(MediaType.APPLICATION_JSON)
        .header(SPECVERSION, "1.0")
        .header(ID, UUID.randomUUID().toString())
        .header(TYPE, "echo")
        .header(SOURCE, "http://localhost:8080/echo")
        .header(SUBJECT, "Echo content")
        .bodyValue(input)
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .value(body -> {
          assertThat(body, notNullValue());
          assertThat(body, equalTo(input));
        });
  }
}
