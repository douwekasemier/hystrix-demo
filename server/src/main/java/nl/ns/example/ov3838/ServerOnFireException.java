package nl.ns.example.ov3838;

public class ServerOnFireException extends RuntimeException {

    public ServerOnFireException(String message) {
        super(message);
    }

    public ServerOnFireException(String message, Throwable cause) {
        super(message, cause);
    }
}
