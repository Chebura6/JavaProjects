package is.programmingTechnologies.Exceptions;

public class CentralBankException extends Exception {
    private CentralBankException(String message) {
        super(message);
    }
    public static CentralBankException InvalidBankName() {
        return new CentralBankException("Invalid bank name!!");
    }
}
