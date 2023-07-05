package is.programmingTechnologies.Exceptions;

public class StateException extends Exception {
    private StateException(String message) {
        super(message);
    }
    public static StateException InvalidState() {
        return new StateException("Invalid command state!");
    }
}
