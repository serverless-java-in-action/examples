package functions;

public class Output {
    private String message;
    private boolean approved;
    private String reason;

    public Output(String message, boolean approved, String reason) {
        this.message = message;
        this.approved = approved;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
