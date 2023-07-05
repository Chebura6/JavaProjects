package is.programmingTechnologies.Exceptions;

public class BanksServletException extends Exception{
    private BanksServletException(String message) {
        super(message);
    }
    public static BanksServletException InvalidBankName() {
        return new BanksServletException("Invalid bank name!!");
    }
}
