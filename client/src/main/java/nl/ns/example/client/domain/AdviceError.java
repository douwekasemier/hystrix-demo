package nl.ns.example.client.domain;

public class AdviceError {
    private final String errorType;
    private final String errorMessage;

    public AdviceError(String errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
