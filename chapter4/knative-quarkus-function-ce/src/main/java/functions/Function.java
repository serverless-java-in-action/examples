package functions;

import io.quarkus.funqy.Funq;
import io.quarkus.funqy.knative.events.CloudEvent;
import io.quarkus.funqy.knative.events.CloudEventBuilder;

/**
 * Your Function class
 */
public class Function {
    private enum Approval {APPROVED, DENIED}
    
    @Funq
    public CloudEvent<Output> function(CloudEvent<LandingRequestDetails> input) {
        boolean approved;
        String reason = "";
        try {
            var landingApproval = new LandingApproval();
            approved = landingApproval.approveLanding(input.data());
        } catch (Exception exception) {
            approved = false;
            reason = exception.getMessage();
        }

        String message = String.format("Landing %s on planet %s.",
                approved ? Approval.APPROVED : Approval.DENIED,
                input.data().planet()
        );

        Output output = new Output(message, approved, reason);
        return CloudEventBuilder.create().build(output);

    }

}
