package chapter3.aws-requesthandler;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class MyRequestHandler implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        String name = input.get("name");
        String message = input.get("message");
        if (name == null || message == null) {
            return "Please input your name and a message.";
        } else {
            return String.format("Welcome to AWS Lambda, %s! %s", name, message);
        }
    }
}