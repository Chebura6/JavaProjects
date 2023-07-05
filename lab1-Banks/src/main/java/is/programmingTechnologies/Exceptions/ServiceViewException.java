package is.programmingTechnologies.Exceptions;

public class ServiceViewException extends Exception {
    private ServiceViewException(String message) {
        super(message);
    }
    public static ServiceViewException InvalidInput() {
        return new ServiceViewException("Invalid input!!");
    }

    public static ServiceViewException TransactionError() {
        return new ServiceViewException("Transaction not done!!");
    }
}
