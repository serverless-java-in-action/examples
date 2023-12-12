 package chapter3.azure-http-trigger;

 import com.microsoft.azure.functions.ExecutionContext;
 import com.microsoft.azure.functions.HttpMethod;
 import com.microsoft.azure.functions.HttpRequestMessage;
 import com.microsoft.azure.functions.HttpResponseMessage;
 import com.microsoft.azure.functions.HttpStatus;
 import com.microsoft.azure.functions.annotation.AuthorizationLevel;
 import com.microsoft.azure.functions.annotation.FunctionName;
 import com.microsoft.azure.functions.annotation.HttpTrigger;
 
 import java.util.Optional;
 
public class MyHttpTriggerFunction {

    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        final String query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(query);

        if (name == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please input a name on the query string").build();
        } else {
            return request.createResponseBuilder(HttpStatus.OK).body("Welcome to Azure Functions, " + name).build();
        }
    }
 
 }