 package chapter3.google-cloud-function;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class MyHttpRequest implements HttpFunction {

  private static HttpClient httpClient =
      HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();

  @Override
  public void service(HttpRequest request, HttpResponse response)
      throws IOException, InterruptedException {

    String url = "http://bit.ly/danielohtv";
    var getRequest = java.net.http.HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
    var getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
    var writer = new PrintWriter(response.getWriter());
    writer.printf("Received code '%s' from url '%s'.", getResponse.statusCode(), url);
  }
  
}