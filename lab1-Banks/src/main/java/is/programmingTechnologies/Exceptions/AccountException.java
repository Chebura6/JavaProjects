package is.programmingTechnologies.Exceptions;

public class AccountException extends Exception {
    private AccountException(String message) {
        super(message);
    }
    public static AccountException LimitExceeded() {
        return new AccountException("Percent cannot be negative!");
    }
    public static AccountException NotEnoughMoney() {
        return new AccountException("Not enough money on account!");
    }
    public static AccountException OperationNotPermitted() {
        return new AccountException("Not enough time has passed!");
    }
}
