package functions;

import io.quarkus.funqy.Funq;

/**
 * Your Function class
 */
public class Function {
    private enum Approval {APPROVED, DENIED};


    /**
     *
     * @param input a Java record containing details about the vessel
     * @return a Java bean
     */
    @Funq
    public Output function(LandingRequestDetails input) {

        boolean approved;
        String reason = "";
        try {
            var landingApproval = new LandingApproval();
            approved = landingApproval.approveLanding(input);
        } catch (Exception exception){
            approved = false;
            reason = exception.getMessage();
        }

        String message = String.format("Landing %s on planet %s.",
                approved ? Approval.APPROVED : Approval.DENIED,
                input.planet()
                );

        return new Output(message, approved, reason);
    }

}
