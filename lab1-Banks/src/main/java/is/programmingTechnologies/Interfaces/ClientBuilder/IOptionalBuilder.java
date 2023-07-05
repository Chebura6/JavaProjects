package is.programmingTechnologies.Interfaces.ClientBuilder;
import is.programmingTechnologies.Models.Client;

/**
 * This interface is a part of client builder logic.
 */

public interface IOptionalBuilder {
    IOptionalBuilder withAddress(String address);
    IOptionalBuilder withPassport(String passport);
    Client build();

}
