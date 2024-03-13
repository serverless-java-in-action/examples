package functions;

import java.io.UncheckedIOException;

import io.quarkus.funqy.Funq;
import io.quarkus.funqy.knative.events.CloudEvent;
import io.quarkus.funqy.knative.events.CloudEventBuilder;
import io.quarkus.logging.Log;

/**
 * Your Function class
 */
public class Function {
    private enum Approval {APPROVED, DENIED}
    
    @Funq
    public CloudEvent<Output> function(CloudEvent<LandingRequestDetails> cloudEvent)  {
        LandingRequestDetails input = cloudEvent.data();
        boolean approved;
        String reason = "";
        try {
            var landingApproval = new LandingApproval();
            approved = landingApproval.approveLanding(input);
        } catch (Exception exception) {
            approved = false;
            reason = exception.getMessage();
            Log.info(exception);
        }

        String message = String.format("Landing %s on planet %s.",
                approved ? Approval.APPROVED : Approval.DENIED,
                input.planet()
        );

        Output output = new Output(message, approved, reason);
        return CloudEventBuilder.create().build(output);

    }

}
