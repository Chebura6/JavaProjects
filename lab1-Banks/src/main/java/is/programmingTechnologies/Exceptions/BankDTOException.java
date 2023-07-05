package is.programmingTechnologies.Exceptions;

public class BankDTOException extends Exception{
    private BankDTOException(String message) {
        super(message);
    }
    public static BankDTOException NegativePercent() {
        return new BankDTOException("Percent cannot be negative!");
    }
}
