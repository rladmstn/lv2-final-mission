package finalmission.global;

public class MailgunException extends RuntimeException {
    private final int statusCode;
    private final String details;

    public MailgunException(String message, int statusCode, String details) {
        super(message);
        this.statusCode = statusCode;
        this.details = details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDetails() {
        return details;
    }
}
