package Model;

public class InvalidFlightException extends RuntimeException {

    public InvalidFlightException(String message) {
        super(message);
    }
}
