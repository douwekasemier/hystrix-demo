package nl.ns.example.client.domain;

public class AdviceException extends RuntimeException {

    public String errorType;
    public String errorMessage;

    public String getErrorType() {
        return errorType;
    }

    public AdviceException(String type, String message, Throwable cause) {
        super(message, cause);

        errorType = type;
        errorMessage = message;
    }

    public AdviceError toError() {
        return new AdviceError(errorType, errorMessage);
    }
}
