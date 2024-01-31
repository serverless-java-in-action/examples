package functions;

public class LandingApproval {
    public boolean approveLanding(LandingRequestDetails landingRequestDetails) {
        if (landingRequestDetails.weight() == 0) {
            throw new IllegalArgumentException("Weight is invalid");
        } else if(landingRequestDetails.weight() > 1000) {
            throw new InvalidLandingRequestException("Ship is too heavy");
        }
        return true;
    }
}

class InvalidLandingRequestException extends RuntimeException {
    public InvalidLandingRequestException(String errorMessage){
        super(errorMessage);
    }
}

