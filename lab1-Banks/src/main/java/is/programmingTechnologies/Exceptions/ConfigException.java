package is.programmingTechnologies.Exceptions;

public class ConfigException extends Exception{
    private ConfigException(String message) {
        super(message);
    }
    public static ConfigException NegativePercent() {
        return new ConfigException("Percent cannot be negative!!");
    }
    public static ConfigException NegativeCommission() {
        return new ConfigException("Commission cannot be negative!!");
    }
    public static ConfigException NegativeLimit() {
        return new ConfigException("Transaction restriction limit cannot be negative!!");
    }
    public static ConfigException InvalidArgumentsInConstructor() {
        return new ConfigException("Check lists length");
    }

}
