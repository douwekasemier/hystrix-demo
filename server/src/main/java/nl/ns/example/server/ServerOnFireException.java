package nl.ns.example.server;

public class ServerOnFireException extends RuntimeException {

    public ServerOnFireException(String message) {
        super(message);
    }

    public ServerOnFireException(String message, Throwable cause) {
        super(message, cause);
    }
}
